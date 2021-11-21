<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h1>食品を検索する</h1>




        <form method="post" action="<c:url value='/%総カロリー編集する処理のURL%' />">
            <button type="submit">編集</button>
        </form>


    </c:param>
</c:import>