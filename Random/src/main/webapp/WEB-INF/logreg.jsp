<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Random</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
</head>
<body>
<div class="mx-auto min-vh-100 text-center bg-secondary pt-5">
    <h1>Welcome!</h1>
    <h3>Please register or log in below.</h3>
    <div class="row g-0 justify-content-center pt-5">
        <div class="col-md-7 mt-3">
            <h2>Register</h2>
            <form:form action="/register" modelAttribute="newUser" method="POST" class="form">
                <table class="table table-bordered">
                    <tr>
                        <th class="col-5">
                            <div>
                                User Name:
                            </div>
                            <div class="">
                                <form:errors class="text-danger fw-bold" path="name"/>
                            </div>
                        </th>
                        <td><form:input type="text" path="name" class="form-control"/></td>
                    </tr>
                    <tr>
                        <th class="col-5">
                            <div>
                                Email:
                            </div>
                            <div class="">
                                <form:errors class="text-danger fw-bold" path="email"/>
                            </div>
                        </th>
                        <td><form:input path="email" class="form-control" type="email"/></td>
                    </tr>
                    <tr>
                        <th class="col-5">
                            <div>
                                Password:
                            </div>
                            <div class="">
                                <form:errors class="text-danger fw-bold" path="password"/>
                            </div>
                        </th>
                        <td><form:input type="password" path="password" class="form-control"/></td>
                    </tr>
                    <tr>
                        <th class="col-5">
                            <div>
                                Confirm Password:
                            </div>
                            <div class="">
                                <form:errors class="text-danger fw-bold" path="confirm"/>
                            </div>
                        </th>
                        <td><form:input type="password" path="confirm" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="text-center">
                            <button type="submit" class="btn btn-info col-6">Register</button>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
        <div class="col-md-7 mt-5">
            <h2>Log In</h2>
            <form:form action="/login" modelAttribute="newLogin" method="POST" class="form">
                <table class="table table-bordered">
                    <tr>
                        <th class="col-5">
                            <div>
                                Email:
                            </div>
                            <div class="">
                                <form:errors class="text-danger fw-bold" path="email"/>
                            </div>
                        </th>
                        <td><form:input path="email" class="form-control" type="email"/></td>
                    </tr>
                    <tr>
                        <th class="col-5">
                            <div>
                                Password:
                            </div>
                            <div class="">
                                <form:errors class="text-danger fw-bold" path="password"/>
                            </div>
                        </th>
                        <td><form:input type="password" path="password" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="text-center">
                            <button type="submit" class="btn btn-info col-6">Login</button>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
