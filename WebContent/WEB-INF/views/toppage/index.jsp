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
        <form method="GET" action="<c:url value='/meal/index' />">
            <table id="calorieintake_list">
                <tr>
                    <th><label for="date">摂取カロリー</label></th>
                </tr>
                <tr align="center">
                    <td><c:out value="${calorieintake}" /> kcal</td>
                </tr>
             </table>
            <input type="hidden" id="calorieintakeDay" name="day" value="<c:out value='${calorieintake}' />">
            <br />
            <button onclick="editCalorieintake()" >編集</button>
        </form>

        <h3>★総消費カロリー</h3>
        <form method="POST" action="<c:url value='/CalculationServlet'/>">
            <table id="CaloriesBurned_list">
                <tr>
                    <th><label for="text">歩数</label></th>
                    <th>＝</th>
                    <th><label for="text">消費カロリー</label></th>
                </tr>
                <tr align="center">
                    <td><input type="text" name="StepCount" value="<c:out value='${StepCount}' />">歩</td>
                    <td>=</td>
                    <td><c:out value="${CaloriesBurned}" /> kcal</td>
                </tr>
             </table>
            <input type="hidden" id="caloriesBurnedDay" name="day" value="">
            <input type="hidden" name="CaloriesBurned" value="<c:out value='${CaloriesBurned}' />">
            <br />
        <button onclick="editCaloriesBurned()">編集</button>
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