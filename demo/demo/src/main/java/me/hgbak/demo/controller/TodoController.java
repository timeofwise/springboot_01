package me.hgbak.demo.controller;

import me.hgbak.demo.service.TodoService;
import me.hgbak.demo.dto.ResponseDTO;
import me.hgbak.demo.dto.TodoDTO;
import me.hgbak.demo.model.TodoEntity;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/todo")
public class TodoController {

	
	@Autowired
	private TodoService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	};
	
	@GetMapping()
	public ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temporary-user";
		
		// 서비스 메서드의 retrieve 메서드를 사용해서 Todo 리스트를 가죠온
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		
		// 자바 스트림을 이용해서 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//변환된 TodoList를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		//ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	};
	
	@PostMapping
	@CrossOrigin(origins = "http://10.42.214.238:8002")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		System.out.println("post~!~!~!~!");
		try {
			String temporaryUserId = "temporary-user";
			
			// 1. Todo Entity로 변환
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// 2. id를 null로 초기화함. 생성당시엔 ID가 업성야 하므로
			entity.setId(null);
			
			// 3. 임시 유저id 설정.
			entity.setUserId(temporaryUserId);
			
			// 4. 서비스릉 이용해 Todo 엔티티 생성
			List<TodoEntity> entities = service.create(entity);
			
			// 5. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO로 변환
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// 6. 변환된 todoDTO리스트를 이용해 responseDTO를 초기화
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			// 7. ResponseDTO를 리턴
			return ResponseEntity.ok().body(response);
		} catch(Exception e) {
			// 8 예외처리
			String error = e.getMessage();
			
			ResponseDTO<TodoDTO> resposne = ResponseDTO.<TodoDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(resposne);
		}
	};
	
	@PutMapping
	@CrossOrigin(origins = "http://10.42.214.238:8002")
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
		System.out.println("PUT~!~!~~!!");
		System.out.println(dto);
		String temporaryUserId = "temporary-user";
		
		//DTO를 entity로 변환한다.
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		//id를 temporaryUserId로 초기화한다. 여기에 인증부분에서 수정될예정
		entity.setUserId(temporaryUserId);
		
		
		//서비스를 이용해 entity를 업데이트한다.
		List<TodoEntity> entities = service.update(entity);
		
		//자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO로 변환.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		//ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
		
	};
};
