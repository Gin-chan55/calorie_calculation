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
        <h2>食事内容追加</h2>
        <h3>食品を検索する</h3>
        <form method="GET" action="<c:url value='/meal/search'/>">
            <input type="text" name="keyword" required/>
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <button type="submit">食品を探す</button>
            <p>食品を入れて検索してください。例：りんご</p>
        </form>
        <form method="POST" action="<c:url value='/meal/add'/>">
            <table>
                <tbody>
                    <tr>
                        <th>和洋中</th>
                        <th>分類</th>
                        <th>料理</th>
                        <th>数量</th>
                    </tr>
                    <c:forEach var="result" items="${searchResult}" varStatus="status">
                        <tr>
                            <td>
                                <c:out value="${result.jpncnw}" />
                            </td>
                            <td><c:out value="${result.category.name}" /></td>
                            <td><c:out value="${result.itemname}" /></td>
                            <td>
                                <input type="hidden" name="addition-id" value="<c:out value='${result.id}' />">
                                <input type="hidden" name="addition-calorie" value="<c:out value='${result.caloriesper}' />">
                                <input type="number" name="addition-quantity" value="">
                                <input type="hidden" name="_token" value="${_token}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <button type="submit">追加</button>
        </form>
        <h2>食品内容を削除</h2>
        <form method="POST" action="<c:url value='/meal/destroy'/>">
            <table>
                <tbody>
                    <tr>
                        <th></th>
                        <th>品名</th>
                        <th>数量</th>
                        <th>カロリー</th>
                    </tr>
                    <c:forEach var="meal" items="${meals}" varStatus="status">
                        <tr>
                            <td>
                                <input type="checkbox" name="delete-meal" value="<c:out value='${meal.id}' />"/>
                            </td>
                            <td><c:out value="${meal.addition.itemname}" /></td>
                            <td><c:out value="${meal.quantity}" /></td>
                            <td><c:out value="${meal.addition.caloriesper}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="hidden" name="_token" value="${_token}" />
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <button type="submit">削除</button>
        </form>
        <br />
        <form method="GET" action="<c:url value='/index.html'/>">
            <input type="hidden" name="day" value="<c:out value='${day}' />">
            <button type="submit">カロリーTOP</button>
        </form>
    </c:param>
</c:import>