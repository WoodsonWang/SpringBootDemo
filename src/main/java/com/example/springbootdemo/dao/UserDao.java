package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByName(String username,String password) {
        String querySQL = "select * from person where email=? and password=?";
        User user = null;
        try{
            user = this.jdbcTemplate.queryForObject(querySQL, new Object[]{username,password}, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user1 = new User();
                    user1.setUsername(resultSet.getString("NAME"));
                    user1.setEmail(resultSet.getString("EMAIL"));
                    user1.setPassword(resultSet.getString("PASSWORD"));
                    return user1;
                }
            });
        }catch (EmptyResultDataAccessException e){
            return user;
        }
        return user;
    }

    /**
     * 获取数据库中的所有用户信息
     * @return
     */
    public List<User> getUsers(){
        String sql = "select * from person";
        List<User> users = this.jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUsername(resultSet.getString("NAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setPassword(resultSet.getString("PASSWORD"));
                return user;
            }
        });

        return users;
    }

    /**
     * 添加一个新用户
     * @param user
     * @return
     */
    public int addUser(User user){
        String sql = "insert into person(email,password,name) values(?,?,?)";
        int result = this.jdbcTemplate.update(sql,user.getEmail(),
                user.getPassword(),
                user.getUsername());
        // result is 0 or 1
        return result;
    }

    @Test
    public void getUser(){

    }
}