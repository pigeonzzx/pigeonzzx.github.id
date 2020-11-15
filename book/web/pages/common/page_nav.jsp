<%--
  Created by IntelliJ IDEA.
  User: 小朱
  Date: 2020/11/7
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--		分页前--%>
<div id="page_nav">
    <%--当前页数大于1，显示上一页--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <c:choose>
        <%--情况1：总页码数小于等于5--%>
        <c:when test="${requestScope.page.pageTotal<=5}" >
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${requestScope.page.pageNo==i}">
                    【${i}】
                </c:if>
                <c:if test="${requestScope.page.pageNo!=i}">
                    <a href="${requestScope.page.url}&pageNo=${i}"> ${i}</a>
                </c:if>

            </c:forEach>
        </c:when>

        <%--情况2；总页码数大于5--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--2-1：页数为前三个,页面显示1 2 3  4 5--%>
                <c:when test="${requestScope.page.pageNo<4}">
                    <c:forEach begin="1" end="${5}" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}"> ${i}</a>
                        </c:if>

                    </c:forEach>
                </c:when>
                <%--2-2 页数为后三个，假设总共10页，页面显示6 7 8 9 10--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}"> ${i}</a>
                        </c:if>

                    </c:forEach>
                </c:when>
                <%--2-3 页面为中间 34567等--%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${requestScope.page.pageNo==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNo!=i}">
                            <a href="${requestScope.page.url}&pageNo=${i}"> ${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>



    </c:choose>

    <%--<a href="#">3</a>--%>
    <%--			【${requestScope.page.pageNo}】--%>
    <%--<a href="#">5</a>--%>

    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，共${requestScope.page.pagePotalCount}条记录
    到第<input value="${param.pageNo }" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            //跳转指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                //js中 提供了一个location地址栏对象
                //他有一个属性叫href，它可以获取浏览器地址栏中的地址
                location.href="${pageScope.basePath}${requestScope.page.url}&pageNo="+pageNo;

            })

        })
    </script>
</div>
<%--		分页后--%>

