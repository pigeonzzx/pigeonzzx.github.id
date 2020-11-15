package com.zx.service.impl;

import com.zx.dao.BookDao;
import com.zx.dao.impl.BookDaoimpl;
import com.zx.pojo.Book;
import com.zx.pojo.Page;
import com.zx.service.BookService;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceImpl implements BookService {

    //service同过访问DAo层来访问数据库
    private BookDao bookDao = new BookDaoimpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
        //new Book(null,"牧原的下猪圈体验",new BigDecimal(6000),"学长",0,100,null)
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);

    }

    @Override
    public Book queryBookForSimple(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int page_size) {
        Page<Book> page = new Page<>();

        //设置当前页码
        page.setPageNo(pageNo);

        //设置当前页数量
        page.setPageSize(page_size);

        //总记录数
        Integer pageTatalCount = bookDao.queryForPageTotalCount();
        page.setPagePotalCount(pageTatalCount);
        //设置总页数
        Integer pageTotal =pageTatalCount/page_size;
        if (pageTatalCount%page_size >= 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //求当前页数据
        int begin=(page.getPageNo()-1)*page_size;
        List<Book> items = bookDao.queryForItems(begin,page_size);
        page.setItems(items);

        //分页
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int page_size, int min, int max) {
        Page<Book> page = new Page<>();

        //设置当前页码
        page.setPageNo(pageNo);

        //设置当前页数量
        page.setPageSize(page_size);

        //总记录数
        Integer pageTatalCount = bookDao.queryForPageByPrice(min,max);
        page.setPagePotalCount(pageTatalCount);
        //设置总页数
        Integer pageTotal =pageTatalCount/page_size;
        if (pageTatalCount%page_size >= 0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        //求当前页数据
        int begin=(page.getPageNo()-1)*page_size;
        List<Book> items = bookDao.queryForItemsByPrice(begin,page_size,min,max);
        page.setItems(items);

        //分页
        return page;
    }
}
