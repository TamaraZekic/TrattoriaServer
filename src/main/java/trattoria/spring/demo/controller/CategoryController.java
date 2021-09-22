package trattoria.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import trattoria.spring.demo.dto.CategoryDTO;
import trattoria.spring.demo.model.Category;
import trattoria.spring.demo.model.Meal;
import trattoria.spring.demo.service.CategoryService;
import trattoria.spring.demo.service.MealService;

@CrossOrigin("*")
@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	MealService mealService;
	
	@GetMapping(value = "api/categories", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<Category> categories = categoryService.findAll();
		
		List<CategoryDTO> categoriesDTO = new ArrayList<CategoryDTO>();
		
		for(Category category : categories) {
			categoriesDTO.add(new CategoryDTO(category));
		}
		
		return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
	}
	
	
	
	@PostMapping(value = "api/categories", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO){
		
		Category newCategory = new Category(categoryDTO);
		
		newCategory = categoryService.save(newCategory);
		
		return new ResponseEntity<CategoryDTO>(new CategoryDTO(newCategory), HttpStatus.OK);
	}
	
	
	
	@PutMapping(value = "api/categories",consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO){
		Optional<Category> categoryOptional = categoryService.findOne(categoryDTO.getId());
		
		if(categoryOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Category categoryUpdated = categoryOptional.get();
		
		categoryUpdated = new Category(categoryDTO);
		
		categoryUpdated = categoryService.save(categoryUpdated);
		
		return new ResponseEntity<CategoryDTO>(new CategoryDTO(categoryUpdated), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value = "api/categories/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Optional<Category> categoryOptional = categoryService.findOne(id);
		
		List<Meal> catMeals = mealService.findMealByCatById(id);
		
		if(catMeals.size() > 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(categoryOptional.isPresent()) {
			categoryService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
