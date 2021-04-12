<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.5/build/pure-min.css" integrity="sha384-LTIDeidl25h2dPxrB2Ekgc9c7sEC3CWGM6HeFmuDNUjX76Ert4Z4IY714dhZHPLd" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@include  file="const/head-menu.html" %>
</head>
<body>
    <center>
        <div class="pure-u-3-5">
            <form action="searchEntries">
            <br>
                <div> Type text and click search to look for the word withing the journal entries db. </div><br>

                <div class="pure-control-group">
                    <td><label>Phrase: </label></td>
                    <td><input type="text" class="pure-input-1" name="phrase"}></td>
                    <input type="submit" value="Search">
                </div>
                    
            </form>
                    
        </div>    
    </center>

</body>
<%@include  file="const/footer.html" %>
</html>