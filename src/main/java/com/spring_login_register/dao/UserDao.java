package com.spring_login_register.dao;

import com.spring_login_register.db.DatabaseConnects;
import com.spring_login_register.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDao {
    private final Connection connection;
    public final String selectUser = "SELECT id,name,email,password FROM user WHERE email=? and password=?;";
    public static String getAllUsers = "SELECT id,name,email,password FROM user;";
    public static String addUser = "INSERT INTO user(name,email,password) VALUES(?,?,?);";


    public UserDao() {
        connection = DatabaseConnects.getConnection();
    }
    public User isLoggedIn(String name, String password) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public boolean register(String name, String email, String password, String cPassword) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(getAllUsers);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("email"), email)) {
                    return true;
                }
            }
            PreparedStatement preparedStatement = connection.prepareStatement(addUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<User> getAllUsers() {
        List<User> users=new ArrayList<>();
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(getAllUsers);
            ResultSet resultSet=preparedStatement1.executeQuery();
            while (resultSet.next()){
                User user=new User();
                user.setId(Integer.parseInt(resultSet.getString("id")));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
