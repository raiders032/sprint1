package com.swm.sprint1.repository;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.swm.sprint1.domain.QCategory;
import com.swm.sprint1.domain.QUserCategory;
import com.swm.sprint1.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.swm.sprint1.domain.QCategory.*;
import static com.swm.sprint1.domain.QUser.*;
import static com.swm.sprint1.domain.QUserCategory.userCategory;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final  JPAQueryFactory queryFactory;

    @Override
    public List<User> findAllCustom() {
        return queryFactory.select(user)
                .from(user)
                .fetch();
    }

}
