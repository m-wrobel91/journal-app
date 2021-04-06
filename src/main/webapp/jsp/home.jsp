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
        <br>
        <div id="layout" class="pure-g">
            
            <div class="sidebar pure-u-1 pure-u-md-1-5">

            </div>
            <div class="content pure-u-1 pure-u-md-3-5">
                
                <div class="entries">
                    <h1 class="content-subhead">Pinned Post</h1>
                    <!-- A single blog post -->
                    <section class="entry">
                        <header class="entry-header">
                            <img width="48" height="48" alt="Wróbel's avatar" class="entry-avatar" src="images/avatar.jpg">

                            <h2 class="entry-title">Introducing Journal Entry CRUD MVC Application</h2>
                            <p class="entry-meta">
                                By <a href="#" class="entry-author">Michał Wróbel</a> under <a class="entry-category entry-category-design" href="#">CSS</a> <a class="entry-category entry-category-pure" href="#">Pure</a>
                            </p>
                        </header>

                        <div class="entry-description">
                            <p>
                                Welcome on <b>Journal MVC CRUD App</b> page. You can create new JournalEntry, update previous entries, 
                    delete entries and search the database. See below last 5 entries introduced to page. 
                            </p>
                        </div>
                    </section>
                </div>
                    
                <div class="entries">
                    <h1 class="content-subhead">Recent Posts</h1>
                        
                    <c:forEach var="tempEntry" items="${entries}">

                        <section class="entry">
                            <header class="entry-header">
                                <img width="48" height="48" alt="Author's avatar" class="entry-avatar" src="images/user-solid.svg">

                                <h2 class="entry-title">${tempEntry.title}</h2>
                                <p class="entry-meta">
                                    At ${tempEntry.timestamp}
                                    by <a class="entry-author" href="#">Anonymous</a> under <a class="entry-category entry-category-${(tempEntry.category).toLowerCase()}" href="#">${tempEntry.category}</a>
                                </p>
                            </header>

                            <div class="entry-description">
                                <p class="justified">
                                    ${tempEntry.content}
                                </p>
                            </div>
                        </section>

                    </c:forEach>
                </div> 
                <div>
                    <
                    <c:forEach var="pageNo" begin= "0" end = "${noOfPages}">
                        <a href="1">${pageNo}</a>
                    </c:forEach>
                    >
                </div>
        </center>
        <div class="sidebar pure-u-1 pure-u-md-1-5">

        </div>
    </div>


    
</body>
<%@include  file="const/footer.html" %>
</html>