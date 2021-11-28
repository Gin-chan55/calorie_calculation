<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null }">
            <div id="flush_success">
                <c:out value="${flush }"></c:out>
            </div>
        </c:if>

        <fmt:parseDate value="${loginDay}" pattern="yyyy-MM-dd" var="loginDay" type="date" />
        <label for="date"></label>
        <input type="date" name="loginDay" value="<fmt:formatDate value='${loginDay}' pattern='yyyy-MM-dd' />" />

        <form method="post" action="<c:url value='/calorie_calculation/src/controllers/toppage/TopPageIndexServlet.java' />">
        <h3>★総摂取カロリー</h3>
        <label for="text">カロリー</label><br />
        <c:out value="${calorieintake}" /> kcal
        <button type="submit">編集</button>
        </form>

        <form method="post" action="<c:url value='/%総カロリー編集する処理のURL%' />">
        </form>


        <h3>★総消費カロリー</h3>

        <form method="POST" action="/calorie_calculation/src/controllers/toppage/TopPageIndexServlet.java">
            <label for="StepCount">歩数</label><br />
            <input type="text" name="StepCount" />歩 &nbsp; = <input type="text" name="CaloriesBurned" <c:out value="${CaloriesBurned}" />/> kcal
            <br /><br />
        <button type="submit">編集</button>
        <br /><br />
        </form>

        <h2>TOPページ</h2>
    </c:param>
</c:import>