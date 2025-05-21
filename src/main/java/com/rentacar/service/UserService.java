package com.rentacar.service;

import com.rentacar.dao.UserDAO;
import com.rentacar.model.User;
import com.rentacar.util.PasswordUtil;

public class UserService {
    private final UserDAO userDAO = new UserDAO(); // DAO katmanı

    // Giriş işlemini yapar
    public User login(String email, String password) {
        String hashed = PasswordUtil.hashPassword(password); // Şifreyi SHA-256 ile hashle
        return userDAO.findByEmailAndPassword(email, hashed); // Veritabanında ara
    }
}
