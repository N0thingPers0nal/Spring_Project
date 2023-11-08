<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<html>
<body>
    <c:set var="i" value="0"/>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tbody>
                <tr>
                    <td>
                        <c:set var="i" value="${i + 1}"/>
                        <c:out value="${i}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                </tr>
            </tbody>
        </c:forEach>
    </table>
</body>
</html>