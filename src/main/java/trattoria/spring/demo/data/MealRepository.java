package trattoria.spring.demo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import trattoria.spring.demo.model.Meal;

@Component
public interface MealRepository extends JpaRepository<trattoria.spring.demo.model.Meal, Long> {
	
	List<Meal> findMealByNameContains(String name);
	
	@Query("select meal from Meal meal where category_id=?1")
	List<Meal> findMealByCat(Long id);
}