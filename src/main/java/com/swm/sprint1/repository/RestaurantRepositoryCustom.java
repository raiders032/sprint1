package com.swm.sprint1.repository;

import com.swm.sprint1.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryCustom {
    List<Restaurant> findRestaurantByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
