<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.5/build/pure-min.css" integrity="sha384-LTIDeidl25h2dPxrB2Ekgc9c7sEC3CWGM6HeFmuDNUjX76Ert4Z4IY714dhZHPLd" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
   <%@include  file="const/head-menu.html" %>
</head>
<body>
   <center>
   <br>
   <div class="pure-u-3-5">
       <h1>${message}</h1>
       <table class="pure-table pure-table-horizontal">

                    <tr>
                        <div class="pure-control-group">
                            <td><label> Title: ${entryDto.title}</td>
                        </div>
                    </tr>
                    <tr>
                        <div class="pure-control-group">
                            <td>Time stamp: ${entryDto.timestamp}</td>
                        </div>
                    </tr>
                    <tr>                
                        <div class="pure-control-group">
                            <td>Category: ${entryDto.category}</td>  
                        </div>
                    </tr>
                    <tr>
                        <div class="wrapped pure-control-group">
                            <td>Content: ${entryDto.content}</td> 
                        </div>
                    </tr>
 
        </table>
    </div>
   </center>
</body>
<%@include  file="const/footer.html" %>
</html>