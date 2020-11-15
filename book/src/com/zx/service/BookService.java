package com.zx.service;

import com.zx.pojo.Book;
import com.zx.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookForSimple(Integer id);

    public List<Book> queryBooks();


    Page<Book> page(int pageNo, int page_size);

    Page<Book> pageByPrice(int pageNo, int page_size, int min, int max);
}
