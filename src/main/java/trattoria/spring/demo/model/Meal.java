package trattoria.spring.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import trattoria.spring.demo.dto.MealDTO;


@Entity
public class Meal {
	
	@Id
	@GeneratedValue
	private Long Id;
	
	private String name;
	
	private Integer price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	public Meal() {
		
	}

	public Meal(Long id, String name, Integer price, Category category) {
		super();
		Id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public Meal(MealDTO mealDTO) {
		this.Id = mealDTO.getId();
		this.name = mealDTO.getName();
		this.price = mealDTO.getPrice();
		this.category = new Category(mealDTO.getCategory());
		
		
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
	
}
