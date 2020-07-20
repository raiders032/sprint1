package com.swm.sprint1.controller;

import com.swm.sprint1.domain.Restaurant;
import com.swm.sprint1.domain.User;
import com.swm.sprint1.payload.request.GetRestaurantRequest;
import com.swm.sprint1.repository.RestaurantRepository;
import com.swm.sprint1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/api/v1/restaurant")
    public ResponseEntity<?> getRestaurant(@RequestBody GetRestaurantRequest request){
        List<Restaurant> restaurantList;
        restaurantList = restaurantRepository.findRestaurantByLatitudeAndLongitude(
                    request.getLatitude(), request.getLongitude());
        return ResponseEntity.ok(restaurantList);
    }
}
