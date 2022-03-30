package com.java.web_ecommerce_spring.repositorys;

import com.java.web_ecommerce_spring.domain.Category;
import com.java.web_ecommerce_spring.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
    List<Product> findAll();
    void deleteById(int id);
    Product findProductByName(String name);
    Product findProductById(int id);
    List<Product> findAll(Sort sort);
    List<Product> findProductByDiscount(int discount, Sort sort);
    List<Product> findProductByCategory(Category category);
    Page<Product> findProductByCategory(Category category, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findProductByNameLike(String name,Pageable pageable);
    List<Product> findProductByNameLike(String name);
}
