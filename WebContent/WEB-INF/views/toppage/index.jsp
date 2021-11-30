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

        <h3>★総摂取カロリー</h3>
        <form method="post" action="<c:url value='/calorie_calculation/src/controllers/toppage/TopPageIndexServlet.java' />">
            <label for="text">カロリー</label><br />
            <c:out value="${calorieintake}" /> kcal
            <input type="hidden" id="calorieintakeDay" name="day" value="">
            <button onclick="editCalorieintake()" >編集</button>
        </form>

        <h3>★総消費カロリー</h3>
        <form method="POST" action="<c:url value='/CalculationServlet'/>">
            <label for="text">歩数</label>
            <br />
            <input type="text" name="StepCount" />歩 &nbsp; = <input type="text" name="CaloriesBurned" <c:out value="${CaloriesBurned}" />/> kcal
            <input type="hidden" id="caloriesBurnedDay" name="day" value="">
            <br /><br />
        <button onclick="editCaloriesBurned()">編集</button>
        <br /><br />
        </form>

        <script>
            function editCalorieintake() {
                var day = document.getElementsByName("loginDay")[0].value;
                document.getElementById("calorieintakeDay").value = day;
                document.forms[0].submit();
            }
            function editCaloriesBurned() {
                var day = document.getElementsByName("loginDay")[0].value;
                document.getElementById("caloriesBurnedDay").value = day;
                document.forms[0].submit();
            }
        </script>



        <h2>TOPページ</h2>
    </c:param>
</c:import>