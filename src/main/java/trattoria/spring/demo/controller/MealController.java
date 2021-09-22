package trattoria.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import trattoria.spring.demo.dto.MealDTO;
import trattoria.spring.demo.model.Category;
import trattoria.spring.demo.model.Meal;
import trattoria.spring.demo.service.CategoryService;
import trattoria.spring.demo.service.MealService;

@CrossOrigin("*")
@RestController
public class MealController {
	
	@Autowired
	MealService mealService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = "api/meals",  produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<MealDTO>> getMeals(@RequestParam(required=false, defaultValue="") String filterValue){
		List<Meal> meals;
		if(filterValue.equals("")) {
			meals = mealService.findAll();
		}else {
			meals = mealService.findByFilter(filterValue);
		}
		
		List<MealDTO> mealsDTO = new ArrayList<MealDTO>();
		
		for(Meal meal: meals) {
			mealsDTO.add(new MealDTO(meal));
		}
		
		return new ResponseEntity<>(mealsDTO, HttpStatus.OK); 
	}
	
	
	@GetMapping(value="api/meals", params = {"page", "size"})
	public ResponseEntity<List<MealDTO>> getPaginatedMeals(Pageable pageable){
		Page<Meal> meals = mealService.findAll(pageable);
		
		List<MealDTO> mealsDTO = new ArrayList<MealDTO>();
		
		for(Meal meal: meals.getContent()) {
			mealsDTO.add(new MealDTO(meal));
		}
		return new ResponseEntity<List<MealDTO>>(mealsDTO, HttpStatus.OK); 
	}
	
	
	@PostMapping(value = "api/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MealDTO> create(@RequestBody MealDTO mealDTO){
		Meal meal = new Meal(mealDTO);
		
		Category category = new Category(mealDTO.getCategory());
		//category = categoryService.save(category);
		meal.setCategory(category);
		
		Meal meal1 = mealService.save(meal);
		
		return new ResponseEntity<MealDTO>(new MealDTO(meal1), HttpStatus.OK);
		
	}
	
	
	
	@PutMapping(value = "api/meals", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MealDTO> update(@RequestBody MealDTO mealDTO){
		Optional<Meal> mealOptional = mealService.findOne(mealDTO.getId());
		
		if(mealOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Meal mealUpdated = mealOptional.get();
		
		mealUpdated = new Meal(mealDTO);
		
		Category category = new Category(mealDTO.getCategory());
		category = categoryService.save(category);
		mealUpdated.setCategory(category);
		
		mealUpdated = mealService.save(mealUpdated);
		
		return new ResponseEntity<MealDTO>(new MealDTO(mealUpdated), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "api/meals/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Optional<Meal> mealOptional = mealService.findOne(id);
		
		if(mealOptional.isPresent()) {
			mealService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
