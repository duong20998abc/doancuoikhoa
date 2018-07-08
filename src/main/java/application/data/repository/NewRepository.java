package application.data.repository;

import application.data.model.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface NewRepository extends JpaRepository<New,Integer> {
    @Query("select count(n.newId) from tbl_new n")
    long getTotalNews();

    @Query("select n from tbl_new n")
    ArrayList<New> getAll();
}
