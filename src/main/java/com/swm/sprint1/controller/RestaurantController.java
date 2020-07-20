package com.swm.sprint1.controller;

import com.swm.sprint1.domain.Restaurant;
import com.swm.sprint1.domain.User;
import com.swm.sprint1.payload.request.GetRestaurantRequest;
import com.swm.sprint1.repository.RestaurantRepository;
import com.swm.sprint1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/api/v1/restaurant")
    public ResponseEntity<?> getRestaurant(@RequestParam BigDecimal longitude, @RequestParam BigDecimal latitude){
        List<Restaurant> restaurantList;
        restaurantList = restaurantRepository.findRestaurantByLatitudeAndLongitude(latitude, longitude);
        return ResponseEntity.ok(restaurantList);
    }

    @GetMapping("/api/v1/restaurant/user/category")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getRestaurantWithUserCategory(@RequestBody GetRestaurantRequest request){
        List<Restaurant> restaurantList;
        restaurantList = restaurantRepository.findRestaurantByLatitudeAndLongitudeAndUserCategory(
                request.getLatitude(), request.getLongitude(),request.getId());
        return ResponseEntity.ok(restaurantList);
    }
}
