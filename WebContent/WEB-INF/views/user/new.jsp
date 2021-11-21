<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h1>新規登録</h1>
        <p>
            アカウントをお持ちですか？
            <button type="submit">こちらからログイン</button>
        </p>

        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
                    ・<c:out value="${error}" />
                    <br />
                </c:forEach>

            </div>
        </c:if>
        <form method="POST" action="${pageContext.request.contextPath}/create">

            <label for="address">メールアドレス</label>
            <br />
            <input type="email" id="address" name="address" />
            <br />
            <br />
            <label for="password">パスワード</label>
            <br />
            <input type="password" name="password" />
            <br />
            <br />
            <label for="confirm-password">パスワード再入力</label>
            <br />
            <input type="password" name="confirm-password" />
            <br />
            <br />
            <label for="tentacles">身長</label>
            <br />
            <input type="number" name="height">
            <br />
            <br />
            <label for="tentacles">体重</label>
            <br />
            <input type="number" name="weight">
            <br />
            <br />
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">登録</button>
        </form>
    </c:param>
</c:import>