package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    void deleteById(int id);
    Category findCategoryByName(String name);
    Category findCategoryById(int id);
}
