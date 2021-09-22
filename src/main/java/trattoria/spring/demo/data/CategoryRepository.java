package trattoria.spring.demo.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CategoryRepository extends JpaRepository<trattoria.spring.demo.model.Category, Long> {
}
