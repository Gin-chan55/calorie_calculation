<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>

        <h1>★総摂取カロリー</h1>
        <input type="text">

        <form method="post" action="<c:url value='/%総カロリー編集する処理のURL%' />">
            <button type="submit">編集</button>
        </form>

        <h1>★総消費カロリー</h1>

        <h2>TOPページ</h2>
    </c:param>
</c:import>