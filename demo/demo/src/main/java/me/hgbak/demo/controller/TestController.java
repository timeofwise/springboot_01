package me.hgbak.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	

	public String testController() {
		return "Hello World!";
	};
	
	@GetMapping("/testGetMapping")
	public String testControllerWithPath() {
		return "Hello World2!";
	};
	
	
}
