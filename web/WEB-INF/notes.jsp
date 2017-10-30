<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Notes</title>
        <link rel="stylesheet" href="<c:url value='styles/notes.css' />" />
    </head>
    <body>
        <h1>Manage Notes</h1>
        <h2>Notes</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>Note ID</th>
                <th>Date</th>
                <th>Contents</th>
            </tr>
            <c:forEach var="notes" items="${notes}">
                <tr>
                    <td>${notes.noteID}</td>
                    <td>${notes.dateCreated}</td>
                    <td>${notes.contents}</td>
                    <td>
                        <form action="notes" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedUsername" value="${notes.noteID}">
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedUsername" value="${notes.noteID}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedUser == null}">
            <h3>Add User</h3>
            <form action="users" method="POST">
                username: <input type="text" name="username"><br>
                first name: <input type="text" name="firstname"><br>
                last name: <input type="text" name="lastname"><br>
                password: <input type="password" name="password"><br>
                email: <input type="email" name="email"><br>
                active: <input type="checkbox" name="active"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedUser != null}">
            <h3>Edit User</h3>
            <form action="users" method="POST">
                username: <input type="text" name="username" value="${selectedUser.username}" readonly><br>
                first name: <input type="text" name="firstname" value="${selectedUser.firstname}"><br>
                last name: <input type="text" name="lastname" value="${selectedUser.lastname}"><br>
                password: <input type="password" name="password" value="${selectedUser.password}"><br>
                email: <input type="email" name="email" value="${selectedUser.email}"><br>
                active: <input type="checkbox" name="active" ${selectedUser.active == 1 ? "checked" : ""}><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
