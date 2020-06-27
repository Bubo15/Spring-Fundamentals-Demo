package com.shop.dealershop.data.repositoies;

import com.shop.dealershop.data.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    Model findByName(String name);
}
