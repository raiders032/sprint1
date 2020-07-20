package com.swm.sprint1.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swm.sprint1.domain.QMenu;
import com.swm.sprint1.domain.QRestaurantPhoto;
import com.swm.sprint1.domain.Restaurant;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import static com.swm.sprint1.domain.QMenu.*;
import static com.swm.sprint1.domain.QRestaurant.*;
import static com.swm.sprint1.domain.QRestaurantCategory.*;
import static com.swm.sprint1.domain.QRestaurantPhoto.*;
import static com.swm.sprint1.domain.QUserCategory.userCategory;

@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Restaurant> findRestaurantByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude) {
        return queryFactory
                .select(restaurant)
                .from(restaurant)
                .where(latitudeBetween(latitude),longitudeBetween(longitude))
                .fetch();
    }

    @Override
    public List<Restaurant> findRestaurantByLatitudeAndLongitudeAndUserCategory(BigDecimal latitude, BigDecimal longitude, Long id) {
        return null;
    }

    private BooleanExpression latitudeBetween(BigDecimal latitude){
        return latitude != null ? restaurant.latitude.between(latitude.subtract(BigDecimal.valueOf(0.01)), latitude.add(BigDecimal.valueOf(0.01))) : null;
    }

    private BooleanExpression longitudeBetween(BigDecimal longitude){
        return longitude != null ? restaurant.longitude.between(longitude.subtract(BigDecimal.valueOf(0.01)), longitude.add(BigDecimal.valueOf(0.01))) : null;
    }

    private BooleanExpression restaurantInUserCategory(Long id){
        return id != null ? restaurantCategory.category.id.in(JPAExpressions.select(userCategory.category.id).from(userCategory).where(userCategory.user.id.eq(id))) :null;
    }
}
