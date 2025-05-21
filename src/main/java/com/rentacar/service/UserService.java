package com.rentacar.service;

import com.rentacar.dao.UserDAO;
import com.rentacar.exception.UserNotFoundException;
import com.rentacar.model.User;
import com.rentacar.util.PasswordUtil;

public class UserService {
    private final UserDAO userDAO = new UserDAO(); // DAO katmanı

    // Giriş işlemini yapar

    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("E-posta '" + email + "' için kullanıcı bulunamadı.");
        }

        if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            throw new UserNotFoundException("Şifre yanlış.");
        }

        return user;
    }

}
