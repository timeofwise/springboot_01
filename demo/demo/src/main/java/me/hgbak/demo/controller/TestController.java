package me.hgbak.demo.controller;

import me.hgbak.demo.dto.ResponseDTO;
import me.hgbak.demo.dto.TestRequestBodyDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://10.42.214.238:8002")
@RequestMapping("/test")
public class TestController {
	
	@GetMapping
	public String testController() {
		return "Hello World!";
	};
	
	@CrossOrigin(origins = "http://10.42.214.238:8002")
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(
		@RequestParam(required = false) int id,
		@RequestParam(required = false) String message
	) {
		
		return "Hello World! ID: " + id + " Message: " + message;
	};
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<>();
		list.add("hello world! I am responseDTO");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("hello world! I am responseEntity");
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	//@GetMapping("/{id}")
	//public String testControllerWithPathVariables(@PathVariable(required=false) int id) {
	//	return "Hello world! ID " + id;
	//};
	
	
	
	
}
