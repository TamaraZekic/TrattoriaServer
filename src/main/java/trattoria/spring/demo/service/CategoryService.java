package trattoria.spring.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import trattoria.spring.demo.data.CategoryRepository;
import trattoria.spring.demo.model.Category;

@Component
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public Optional<Category> findOne(Long Id){
		return categoryRepository.findById(Id);
	}
	
	public void remove(Long id) {
		categoryRepository.deleteById(id);
	}
}
