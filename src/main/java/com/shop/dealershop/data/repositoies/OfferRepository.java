package com.shop.dealershop.data.repositoies;

import com.shop.dealershop.data.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Offer getOfferById(long id);

    @Modifying
    @Query("DELETE FROM Offer o WHERE o.id=:id")
    void deleteOfferById(@Param("id") long id);
}
