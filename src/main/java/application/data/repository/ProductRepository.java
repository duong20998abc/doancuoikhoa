package application.data.repository;

import application.data.model.PaginableItemList;
import application.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select count(p.id) from tbl_product p")
    long getTotalProducts();

    @Query("select p from tbl_product p")
    ArrayList<Product> getAllProducts();

    @Query("select p from tbl_product p where p.category = :id")
    ArrayList<Product> getProductsByCategory(@Param("id")int id);

//    @Query("select p from tbl_product p where p.category = :id")
//    Page<Product> findProductsByCategoryId(@Param("id")Set<Integer>id, Pageable pageable);
    @Query("select p from tbl_product p where p.name = :name")
    Product findByName(@Param("name") String name);

    ArrayList<Product> findByNameContaining(@Param("name") String name);

}
