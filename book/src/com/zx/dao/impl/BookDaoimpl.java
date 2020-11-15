package com.zx.dao.impl;

import com.zx.dao.BookDao;
import com.zx.pojo.Book;

import java.util.List;

public class BookDaoimpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id =?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book where id=?";
        return queryForSimple(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,price,author,sales,stock,img_path img_Path from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(id) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int page_size) {
        String sql = "select id,name,price,author,sales,stock,img_path img_Path from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,page_size);

    }

    @Override
    public Integer queryForPageByPrice(int min, int max) {
        String sql ="select count(id) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int page_size, int min, int max) {
        String sql = "select id,name,price,author,sales,stock,img_path img_Path from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,page_size);

    }





}
