package com.zx.utils;

import com.zx.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils{

    /**
    * @Description：[用一句话描述方法] 一次性把所有i请求的参数注入到JavaBean对象中
    * @Param
    * @return bean对象
     * public static void copyParamToBean(HttpServletRequest req,Object bean)这样写不好
     * 因为DAO和Servlet无法访问req。  将此处改为Map   小细节
     *
    */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return bean;
    }


    /**
    * @Description：[用一句话描述方法] 将数字字符串转换成Integer类 数字
    * @Param
    * @return
    */
    public static int parseInt(String string,int defaultInt){

        try {
            return Integer.parseInt(string);
        }catch (Exception e){
            e.printStackTrace();
            return defaultInt;
        }

    }

}
