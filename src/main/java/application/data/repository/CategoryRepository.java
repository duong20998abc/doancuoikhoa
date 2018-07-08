package application.data.repository;

import application.data.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository  extends JpaRepository<Category,Integer> {
    @Query("select c from tbl_category c where c.id = :id")
    Category findCategoryById(@Param("id") int id);
}
