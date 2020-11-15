package com.zx.dao;

import com.zx.pojo.Book;

import java.util.List;

public interface BookDao {
    //添加书本
    public int addBook(Book book);

    //删除书本
    public int deleteBookById(int id);

    //修改书本
    public int updateBook(Book book);

    //查询分为两种
    public Book queryBookById(int id);

    //查询图书
    public List<Book> queryBooks();

    //查询总数量
    Integer queryForPageTotalCount();

    //分页查询
    List<Book> queryForItems(int begin, int page_size);

    //通过价格筛选出来,获得总数据数
    Integer queryForPageByPrice(int min, int max);

    //然后排序，每页显示量
    List<Book> queryForItemsByPrice(int begin, int page_size, int min, int max);




}
