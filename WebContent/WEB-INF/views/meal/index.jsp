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
        <h2>食事内容</h2>
        <table>
            <tbody>
                <tr>
                    <th>品名</th>
                    <th>数量</th>
                    <th>カロリー</th>
                </tr>
                <c:forEach var="meal" items="${meals}" varStatus="status">
                    <tr>
                        <td><c:out value="${meal.addition.itemname}" /></td>
                        <td><c:out value="${meal.quantity}" /></td>
                        <td><c:out value="${meal.addition.caloriesper}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="POST" action="<c:url value='/meal/save'/>">
            <h3>★総カロリー</h3>
            <input type="number" name="totalCalorie" value="<c:out value='${totalCalorie}' />">
            kcal
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">保存</button>
        </form>
        <form method="GET" action="<c:url value='/meal/edit'/>">
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <button type="submit">編集画面</button>
        </form>
        <br />
        <form method="GET" action="<c:url value='/index.html'/>">
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <button type="submit">カロリーTOP</button>
        </form>
    </c:param>
</c:import>