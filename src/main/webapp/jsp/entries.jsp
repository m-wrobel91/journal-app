<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.5/build/pure-min.css" integrity="sha384-LTIDeidl25h2dPxrB2Ekgc9c7sEC3CWGM6HeFmuDNUjX76Ert4Z4IY714dhZHPLd" crossorigin="anonymous">
<link rel="stylesheet" href="/css/styles.css" type="text/css"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include  file="const/head-menu.html" %>
</head>
<body>
    <center>
    <div class="content pure-u-1">
        <div class="entries">
            <h1>Entries from query.</h1>
            <div id = "wrapper">
                <div id = "header">
                    <h2>Journal Entries</h2>
                </div>
            </div>
            <div id = "container" >
                <div id = "content" >
                    <!-- table -->
                    <table class="pure-table">
                        <tr> 
                            <th> Date </th>
                            <th> Title </th>
                            <th> Category </th>
                            <th> Content </th>
                        </tr>
                        <c:forEach var="tempEntry" items="${entries}">
                            <tr>
                                <td> ${tempEntry.timestamp} </td>
                                <td> ${tempEntry.title} </td>
                                <td> ${tempEntry.category} </td>
                                <td max-width="50" class ="wrapped"> ${tempEntry.content} </td>

                            </tr>         
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </center>

</body>
<%@include  file="const/footer.html" %>
</html>