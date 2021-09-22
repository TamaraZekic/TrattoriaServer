package trattoria.spring.demo.dto;

import trattoria.spring.demo.model.Meal;

public class MealDTO {

	private Long Id;
	private String name;
	private Integer price;
	private CategoryDTO category;
	
	public MealDTO() {
		
	}
	
	public MealDTO(Long id, String name, Integer price, CategoryDTO category) {
		super();
		Id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	
	public MealDTO(Meal meal) {
		this.Id = meal.getId();
		this.name = meal.getName();
		this.price = meal.getPrice();
		this.category =new CategoryDTO(meal.getCategory());
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


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public CategoryDTO getCategory() {
		return category;
	}


	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	
	
	
	
}
