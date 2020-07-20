package com.swm.sprint1.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swm.sprint1.domain.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import java.math.BigDecimal;
import java.util.List;

import static com.swm.sprint1.domain.QRestaurant.restaurant;
import static com.swm.sprint1.domain.QRestaurantCategory.restaurantCategory;
import static com.swm.sprint1.domain.QUserCategory.userCategory;


@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantRepositoryTest {

    @Autowired
    private EntityManager em;

    private JPAQueryFactory queryFactory;

    @Before
    public void before(){
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void test1(){
        BigDecimal latitude =BigDecimal.valueOf(37.54747780);
        BigDecimal longitude=BigDecimal.valueOf(126.9173149);
        Long id = 1L;

        List<Restaurant> fetch = queryFactory
                .select(restaurant)
                .from(restaurant)
                .where(latitudeBetween(latitude), longitudeBetween(longitude))
                //.where(restaurant.latitude.between(subtract, add))
                .fetch();

        fetch.stream().forEach(x->System.out.println(x));
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