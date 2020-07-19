package com.swm.sprint1.domain;

import com.swm.sprint1.domain.base.DateEntity;
import com.swm.sprint1.domain.base.UserDateEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Restaurant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menuList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantPhoto> photos = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    private double googleRating;

    private double naverRating;

    private int naverReviewCount ;

    private int googleReviewCount ;

    private String openingHours;

    private String businessStatus;

    private int priceLevel;

    private String address;

    private String roadAddress;

    private float longitude;

    private float latitude;

    private String phone;
}
