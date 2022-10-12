package com.example.user.repository;

import com.example.user.domain.QUser;
import com.example.user.domain.User;
import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements CustomUserRepository{
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    @Transactional
    @Override
    public void updateUser(Long id, User user) {
        QUser qUser = QUser.user;
        UpdateClause<JPAUpdateClause> updateBuilder = update(qUser);

        if(user.getPassword() != null) {
            updateBuilder.set(qUser.password, user.getPassword());
        }
        if(user.getName() != null) {
            updateBuilder.set(qUser.name, user.getName());
        }
        if(user.getBirth() != null) {
            updateBuilder.set(qUser.birth, user.getBirth());
        }

        updateBuilder
                .where(qUser.id.eq(id))
                .execute();
    }


}
