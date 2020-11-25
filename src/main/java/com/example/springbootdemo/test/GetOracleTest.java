package com.example.springbootdemo.test;


import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetOracleTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public  User findByName(String username) {
        String querySQL = "select email,password from user where email=?";
        User user = this.jdbcTemplate.queryForObject(querySQL, new Object[]{username}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user1 = new User();
                user1.setEmail(resultSet.getString("email"));
                user1.setPassword(resultSet.getString("password"));
                return user1;
            }
        });
        return user;
    }

    @Test
    public void getUser(){
        System.out.println(jdbcTemplate);
        String querySQL = "select email,password from person where email= ?";
        User user = this.jdbcTemplate.queryForObject(querySQL, new Object[]{"wang"}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user1 = new User();
                user1.setEmail(resultSet.getString("email"));
                user1.setPassword(resultSet.getString("password"));
                return user1;
            }
        });

        System.out.println(user.getEmail());
    }

}
