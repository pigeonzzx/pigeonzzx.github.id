package com.zx.test;

import com.zx.dao.BookDao;
import com.zx.dao.impl.BookDaoimpl;
import com.zx.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    @Test
    public void addBook() {
        BookDao bb = new BookDaoimpl();
        bb.addBook(new Book(null,"我们的成长",new BigDecimal(20),"+502",200,1,null));
    }

    @Test
    public void deleteBookById() {
        BookDao bb = new BookDaoimpl();
        bb.deleteBookById(16);
    }

    @Test
    public void updateBook() {
        BookDao bb = new BookDaoimpl();
        bb.updateBook(new Book(10,"我们的成长",new BigDecimal(20),"+502",200,1,null));
    }

    @Test
    public void queryBookById() {
        BookDao bb = new BookDaoimpl();
        System.out.println(bb.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        BookDao bb = new BookDaoimpl();
        System.out.println(bb.queryBooks());
    }


    @Test
    public void queryForPageTotalCount() {
        BookDao bb = new BookDaoimpl();
        System.out.println(bb.queryForPageTotalCount());
    }

    @Test
    public void queryForItems() {
        BookDao bb = new BookDaoimpl();
        List<Book> books = bb.queryForItems(0, 3);
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }

    //通过价格筛选出来,获得总数据数
    @Test
    public void queryForPageByPrice() {
        BookDao bb = new BookDaoimpl();
        System.out.println(bb.queryForPageByPrice(10,150));
    }

    @Test
    //然后排序，每页显示量
    public void queryForItemsByPrice(){
        BookDao bb = new BookDaoimpl();
        List<Book> books = bb.queryForItemsByPrice(1, 3, 10, 150);
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }
}