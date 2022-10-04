package com.example.user.repository;

import com.example.user.domain.User;

public interface CustomUserRepository {
    void updateUser(Long id, User user);
}
