package com.zx.test;

import com.zx.dao.BookDao;
import com.zx.dao.impl.BookDaoimpl;
import com.zx.pojo.Book;
import com.zx.service.BookService;
import com.zx.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
     private BookDao bookDao = new BookDaoimpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"牧原的下猪圈体验",new BigDecimal(6000),"学长",0,100,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(11);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(6,"牧原的下猪圈体验",new BigDecimal(6000),"学长",0,100,"static/img/zhu.jpg"));
    }

    @Test
    public void queryBookForSimple() {
        System.out.println(bookDao.queryBookById(2));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook:bookDao.queryBooks()
             ) {
            System.out.println(queryBook);
        }
    }
}