package application.data.repository;

import application.data.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Transactional(readOnly = true)
    @Query("select u from tbl_user u where u.email = :email")
    Iterable<User> findByEmail(@Param("email")String email);

    @Transactional(readOnly = true)
    @Query("select u from tbl_user u where u.username = :username")
    List<User> findByUsername(@Param("username")String username);

    @Query("select u.image from tbl_user u where u.username =:username")
    String getImgByUserName(@Param("username") String username);

    @Query("select u from tbl_user u where u.id = :id")
    User findUserById(@Param("id") int id);

    @Query("select u from tbl_user u")
    ArrayList<User> getListUsers();
}
