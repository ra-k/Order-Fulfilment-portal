package com.OrderApplicarion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OrderApplicarion.entities.Products;



public interface ProductsRepository extends JpaRepository<Products, Integer>{

}
