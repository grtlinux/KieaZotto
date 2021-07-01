package org.tain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tain.files.StorageService;

@Controller
public class UploadController {

	@Autowired
	private StorageService service;
	
	// uploadForm.html
	@RequestMapping(value = {"/files"}, method = {RequestMethod.GET})
	public String list(Model model) throws Exception {
		List<String> lst = this.service.list().map(
				path -> MvcUriComponentsBuilder
					.fromMethodName(UploadController.class, "serveFile", path.getFileName().toString())
					.build()
					.toUri()
					.toString()
			).collect(Collectors.toList());
		model.addAttribute("files", lst);
		return "files/uploadForm";
	}
	
	// upload
	@RequestMapping(value = {"/files"}, method = {RequestMethod.POST})
	public String upload(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
		this.service.store(file);
		redirectAttributes.addFlashAttribute("message", "You successfully upload [" + file.getOriginalFilename() + "]");
		return "redirect:/files";
	}
	
	// download
	@RequestMapping(value = {"/files/{filename:.+}"}, method = {RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception {
		Resource file = this.service.loadAsResource(filename);
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
