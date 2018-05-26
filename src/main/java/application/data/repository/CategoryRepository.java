package application.data.repository;

import application.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Integer> {

}
