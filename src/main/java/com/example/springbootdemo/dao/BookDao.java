package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.Book;
import com.example.springbootdemo.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int addBook(Book book){

        String sql = "insert into book(ISBN,bookName,username,addTime) values(?,?,?,?)";
        int result = jdbcTemplate.update(sql,
                book.getISBN(),
                book.getBookName(),
                book.getUsername(),
                book.getAddTime());
        return result;

    }
}
