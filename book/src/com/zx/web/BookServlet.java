package com.zx.web;

import com.zx.pojo.Book;
import com.zx.pojo.Page;
import com.zx.service.BookService;
import com.zx.service.impl.BookServiceImpl;
import com.zx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends BaseServlet{
    BookService bookService = new BookServiceImpl();


/**
* @Description：[用一句话描述方法] 处理分页功能
* @Param
* @return 
*/
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int page_size = WebUtils.parseInt(req.getParameter("page_Size"), Page.getPageSize());
        //调用Service.page()方法，俩参数 pageNo,pageSize
        Page<Book> page = bookService.page(pageNo,page_size);
        req.setAttribute("page",page);
        page.setUrl("manager/bookServlet?action=page");
        //请求转发***********forward
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    

/**
* @Description：[用一句话描述方法]  获取图书列表
* @Param 
* @return 
*/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取图书资料
        List<Book> books = bookService.queryBooks();
        //把图书全部保存再请求域
        req.setAttribute("books",books);
        //请求转发到manager的jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }
/**
* @Description：[用一句话描述方法] 添加书
* @Param 
* @return 
*/
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求对象，封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用BookService.addBook()保存图书
        bookService.addBook(book);

        //跳转到图书列表界面 /manager/bookServlet?action=list
        //获取工程路径          req.getContextPath()
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
/**
* @Description：[用一句话描述方法] 修改书本信息
* @Param 
* @return 
*/
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //将请求信息封装成一个javabean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //调用bookservice.updateBook方法进行修改
        bookService.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));

    }
/**
* @Description：[用一句话描述方法] 删除书本
* @Param 
* @return 
*/
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的id

        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService。deleteByID()删除屠户
        bookService.deleteBookById(id);
        //跳转到图书列表界面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * @Description：[用一句话描述方法]修改信息，从manager.jsp 跳转到 edit.jsp 通过servlet进行，这是一种思想。。
     * @Param
     * @return
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求ID
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //从Id获取图书信息
        Book book = bookService.queryBookForSimple(id);

        //保存到requset域中
        req.setAttribute("book",book);

        //然后请求转发edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
//        resp.sendRedirect(req.getContextPath()+);
    }

}
