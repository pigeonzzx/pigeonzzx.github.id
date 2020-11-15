package com.zx.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.zx.pojo.Book;
import com.zx.pojo.Page;
import com.zx.service.BookService;
import com.zx.service.impl.BookServiceImpl;
import com.zx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
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

        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page",page);
        //请求转发***********forward
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
    /**
    * @Description：[用一句话描述方法] 通过价格搜索区间显示数据
    * @Param
    * @return
    */

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //需要获取四个参数 pageNo、pageSize、min、max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int page_size = WebUtils.parseInt(req.getParameter("page_Size"), Page.getPageSize());
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        //调用Service.page()方法，俩参数 pageNo,pageSize
        Page<Book> page = bookService.pageByPrice(pageNo,page_size,min,max);
        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min")!=null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(stringBuilder.toString());
        req.setAttribute("page",page);
        //请求转发***********forward
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
}
