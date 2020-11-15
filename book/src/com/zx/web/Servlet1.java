package com.zx.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
/*****************根本项目无关****************************/

public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //先判断当前上传的数据是否是多段的
        if (ServletFileUpload.isMultipartContent(request)){
            //创建一个FileItemFactory的实现类
            FileItemFactory ff = new DiskFileItemFactory();
            //创建一个解析上传数据的工具类
            ServletFileUpload ss = new ServletFileUpload(ff);

            try {
                //解析上传数据，得到每一个表单项FileItem
                List<FileItem> lists = ss.parseRequest(request);
                for (FileItem list : lists) {
                    if (list.isFormField()){
                        //true 普通表单项
                        System.out.println("表单项的name属性值"+list.getFieldName());
                        //utf-8 可能解决乱码问题
                        System.out.println("表单项的value值"+list.getString("utf-8"));
                    }else {
                        //上传表单项

                        System.out.println("表单项的name属性值"+list.getFieldName());
                        System.out.println("上传文件名"+list.getName());
                        list.write(new File("e:\\"+list.getName()));
                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
