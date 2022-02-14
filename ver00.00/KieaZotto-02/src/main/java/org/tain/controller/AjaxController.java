package org.tain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AjaxController {

	// /ajax/01
	@RequestMapping(value = {"/ajax/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String ajax1(@PathVariable(value = "id") String id) {
		log.info(">>>>> id: " + id);
		return "ajax/test" + id;
	}

	// ajax?id=01
	@RequestMapping(value = {"/ajax"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String ajax2(@RequestParam(value = "id", defaultValue = "00") String id) {
		log.info(">>>>> id: " + id);
		return "ajax/test" + id;
	}
}
