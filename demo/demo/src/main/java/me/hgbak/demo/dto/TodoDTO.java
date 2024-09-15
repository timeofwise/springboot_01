package me.hgbak.demo.dto;

import me.hgbak.demo.model.TodoEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	};
	
	public static TodoEntity toEntity(final TodoDTO dto) {
		return TodoEntity.builder()
			.id(dto.getId())
			.title(dto.getTitle())
			.done(dto.isDone())
			.build();
	};
}
