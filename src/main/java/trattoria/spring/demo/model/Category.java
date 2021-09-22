package trattoria.spring.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import trattoria.spring.demo.dto.CategoryDTO;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private Long Id;
	
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Meal> meal;

	public Category() {
		
	}

	public Category(Long id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	
	public Category(CategoryDTO categoryDTO) {
		this.Id = categoryDTO.getId();
		this.name = categoryDTO.getName();
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
