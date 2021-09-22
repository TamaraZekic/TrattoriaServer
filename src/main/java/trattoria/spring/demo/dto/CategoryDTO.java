package trattoria.spring.demo.dto;

import trattoria.spring.demo.model.Category;

public class CategoryDTO {
	
	private Long Id;
	private String name;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Long id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category category) {
		if(category!=null) {
			this.Id = category.getId();
			this.name = category.getName();
		}
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
