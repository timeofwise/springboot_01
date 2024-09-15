package me.hgbak.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import me.hgbak.demo.model.TodoEntity;
import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	
	@Query(value="select * from Todo t where t.user_id = ?1", nativeQuery=true)
	List<TodoEntity> findByUserId(String userId);
}