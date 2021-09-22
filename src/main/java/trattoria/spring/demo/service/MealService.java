package trattoria.spring.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import trattoria.spring.demo.data.MealRepository;
import trattoria.spring.demo.model.Meal;

@Component
public class MealService {

	@Autowired
	MealRepository mealRepository;
	
	public List<Meal> findAll(){
		return mealRepository.findAll();
	}
	
	public Page<Meal> findAll(Pageable page){
		return mealRepository.findAll(page);
	}
	
	public Meal save(Meal meal) {
		return mealRepository.save(meal);
		
	}
	
	public Optional<Meal> findOne(Long id) {
		return mealRepository.findById(id);
	}
	
	public void remove(Long id) {
		mealRepository.deleteById(id);
	}

	public List<Meal> findByFilter(String filterValue) {
		return mealRepository.findMealByNameContains(filterValue);
		
	}
	
	public List<Meal> findMealByCatById(Long id){
		return mealRepository.findMealByCat(id);
	}
}
