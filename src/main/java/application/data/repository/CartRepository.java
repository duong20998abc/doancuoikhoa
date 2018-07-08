package application.data.repository;

import application.data.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query("select c from tbl_cart c where c.username = :username")
    List<Cart> findByUserName(@Param("username") String username);

    @Query("select c from tbl_cart c where c.guid = :guid")
    List<Cart> findByGuid(@Param("guid") String guid);

}
