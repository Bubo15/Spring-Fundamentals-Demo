package com.shop.dealershop.data.repositoies;

import com.shop.dealershop.data.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand getByName(String name);
}
