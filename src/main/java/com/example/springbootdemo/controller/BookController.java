package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.Book;
import com.example.springbootdemo.dao.BookDao;
import com.example.springbootdemo.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    @Autowired
    private BookDao bookDao;

    @ResponseBody
    @RequestMapping("/addBook")
    public String addBook(String ISBN,String bookName,String username){
        String addTime = Tools.getTimeStr();
        Book book  =new Book();
        book.setISBN(ISBN);
        book.setBookName(bookName);
        book.setUsername(username);
        book.setAddTime(addTime);
        int result = bookDao.addBook(book);
        if (result == 1){
            return "success";
        }else{
            return "error";
        }

    }
}
