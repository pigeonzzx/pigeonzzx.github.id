package com.zx.web;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Base64;

/*                                  根本项目无关****************/
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //选择下载文件名称
        String downloadFileName = "鞠.jpg";
        //获取一个可以读取下载内容的对象
        ServletContext servletContext = getServletContext();
        String mimeType = servletContext.getMimeType("/photo/" + downloadFileName);
        //回传数据前，告诉客户端返回的数据类型
        resp.setContentType(mimeType);
//        if (req.getHeader("User-Agent").contains("Chrome")) {
            //有这个UTF-8后，图片必须是中文才可以访问，就很奇怪。****************************************
            resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
//        }

        InputStream resourceAsStream = servletContext.getResourceAsStream("/photo/" + downloadFileName);

        ServletOutputStream outputStream = resp.getOutputStream();

        IOUtils.copy(resourceAsStream,outputStream);


    }
}
