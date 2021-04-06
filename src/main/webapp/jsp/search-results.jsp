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
        <div>
        <style scoped="">
            .button-delete,
            .button-edit {
                color: white;
                border-radius: 4px;
                text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
            }

            .button-delete {
                background: rgb(202, 60, 60);
                /* this is a maroon */
            }

            .button-edit {
                background: rgb(66, 184, 221);
                /* this is a light blue */
            }</style>
            <div class="pure-u-3-5">
                <form action="searchEntries">
                <br>
                    <div> Type text and click search to look for the word withing the journal entries db. </div><br>

                    <div class="pure-control-group">
                        <td><label>Text: </label></td>
                        <td><input type="text" class="pure-input-1" name="text"}></td>
                        <input type="submit" value="Search">
                    </div>
                        
                </form>
                <h1> Results:</h1>
                <div id = "container" >
                    <div id = "content" >
                        <!-- table -->
                        <table class="pure-table">
                            <tr> 
                                <th> Date </th>
                                <th> Title </th>
                                <th> Category </th>
                                <th> Content </th>
                                <th> Edit </th>
                                <th> Delete </th>
                            </tr>
                            <c:forEach var="tempEntry" items="${entries}">
                                <tr>
                                    <td> ${tempEntry.timestamp} </td>
                                    <td> ${tempEntry.title} </td>
                                    <td> ${tempEntry.category} </td>
                                    <td max-width="50" class="wrapped"> ${tempEntry.content} </td>
                                    <td> <a class="button-edit pure-button" href="getEntry/?id=${tempEntry.id}"><i class="far fa-edit"></i></a></td>
                                    <td> <a class="button-delete pure-button" href="deleteEntry/?id=${tempEntry.id}" onclick="if (!(confirm('Are you sure you want to delete this entry?'))) return false"><i class="far fa-trash-alt"></i></button> </td>

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