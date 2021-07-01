package org.tain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tain.files.StorageService;
import org.tain.utils.CurrentInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileUploadController {

	@Autowired
	private StorageService service;
	
	// uploadForm.html
	@GetMapping(value = {"/files"})
	public String list(Model model) throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		List<String> lst = this.service.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(
						FileUploadController.class
						, "serveFile"
						, path.getFileName().toString()
				).build().toUri().toString()
		).collect(Collectors.toList());
		model.addAttribute("files", lst);
		return "files/uploadForm";
	}
	
	// upload
	@PostMapping(value = {"/files"})
	public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		this.service.store(file);
		redirectAttributes.addFlashAttribute("message", "You successfully upload [" + file.getOriginalFilename() + "]");
		return "redirect:/files";
	}
	
	// download
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception {
		log.info("KANG-20210405 >>>>> {} {}", CurrentInfo.get());
		
		Resource file = this.service.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=\"" + file.getFilename() + "\"").body(file);
	}
}
