package me.hgbak.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.hgbak.demo.model.TodoEntity;
import me.hgbak.demo.persistence.TodoRepository;
import java.util.List;
import java.util.Optional;

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
	
	public List<TodoEntity> update(final TodoEntity entity){
		
		//Validation
		validate(entity);
		
		// 넘겨받은 entity id를 이용해 todo entity를가져온다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(todo -> {
			// 반환된 todoEntity가 존재하면 새 entity의 값으로 덮어씌운다/
			
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			repository.save(todo);
			
			log.info("Enity Id: {} is updated.", todo.getId());
		});
		
		
		
		return retrieve(entity.getUserId());
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
	
	
	public List<TodoEntity> retrieve(final String userId){
		log.info("retrieved entity by user (id: {}).", userId);
		return repository.findByUserId(userId);
	};
	
};
