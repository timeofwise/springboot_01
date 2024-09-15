package me.hgbak.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.hgbak.demo.model.TodoEntity;
import me.hgbak.demo.persistence.TodoRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	
	public String testService() {
		//return "Test Service";
		
		//TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		
		//TodoEntity 저장
		repository.save(entity);
		
		//TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		
		return savedEntity.getTitle();
	};
	
	public List<TodoEntity> create(final TodoEntity entity){
		
		//Validation
		validate(entity);
		
		
		repository.save(entity);
		
		log.info("Enity Id: {} is saved.", entity.getId());
		
		return repository.findByUserId(entity.getUserId());
	};
	
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		};
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		};
	};
	
};
