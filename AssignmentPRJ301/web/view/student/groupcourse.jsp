<%-- 
    Document   : groupcourse
    Created on : Mar 15, 2023, 10:06:07 PM
    Author     : thaim
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>FPT University Academic Portal</title>
    </head>
    <style>
        a{
            text-decoration: none;
        }
        .student-image{
            margin: 5px;
            max-width: 120px;
            height:146px;
            width:111px;

        }
        table {
            width: 90%;
            background: #f1f1f1;
            border-radius: 10px;
            box-shadow: 5px 5px 10px rgba(0,0,0,0.05);
            text-align: center
        }
        .image-container{
            text-align: center;
        }
        a{
            text-decoration: none;
        }
        .student-image{
            max-width: 120px;
            height:146px;
            width:111px;

        }
        table {
            width: 90%;
            background: #f1f1f1;
            border-radius: 10px;
            box-shadow: 5px 5px 10px rgba(0,0,0,0.05);
        }
        thead{
            background-color: #00c8fb   ;
            color: white;
            font-size: larger;
        }
        th,td{
            border-bottom: 1px solid #cbcbcb;
            padding: 10px;
            text-align: left;
        }
        .header-text{
            padding-left: 80px;
            font-size: 30px;
            font-family: Arial;
        }
        .img-container{
            text-align: center;
        }
        .header {
            padding: 10px;
            text-align: center;
            background: #f7f6f6;
            color: black;
            font-size: 30px;
            margin: 15px 78px 15px 78px;
        }
        .home-container{
            font-size: 15px;
            margin:0;
            font-family: Arial;
            text-align: left;
            padding:0;
        }
        .middle-text-container{
            padding-left: 100px;
            padding-top:20px;
            padding-bottom:20px;    
            font-size: 20px;
        }
    </style>
    <body>
        <div class="header-text">
            FPT University Academic Portal
        </div>
        <div class="header">
            <div>
                <a class="home-container" href="home">Home</a>
                <span class="home-container"> <a href="../logout">Logout</a> </span>
            </div>

        </div>
        <div class="table1-container">
            <center>
                <table>
                    <thead>
                        <tr>
                        <tr>
                            <th scope="col">Campus</th>
                            <th scope="col">Term</th>
                            <th scope="col">Department</th>
                            <th scope="col">Course</th>
                            <th scope="col">Group</th>
                        </tr>
                        </tr>
                    </thead>
                    <tbody>

                    <td valign="top">
                         <div id="">
                            <table>
                                <tr>
                                    <td>
                                        FU-HL
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>

                    <td valign="top">
                        <div id="">
                            <table>
                                    <c:forEach items="${requestScope.terms}" var="t">
                                        <tr>
                                            <td>
                                                <c:if test="${requestScope.tid_chosen ne t.id}">
                                                    <a href="?tid=${t.id}">${t.name}</a> <br>
                                                </c:if>
                                                <c:if test="${requestScope.tid_chosen eq t.id}">
                                                    <span>${t.name}</span> <br>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>

                            </table>
                        </div>
                    </td>
                    <td valign="top">
                        <div id="">
                            <table>
                                <tbody>

                                    <c:forEach items="${requestScope.depts}" var="d">
                                        <tr>
                                            <td>
                                                <c:if test="${d.id ne requestScope.did_chosen}">
                                                    <a href="?tid=${d.term.id}&did=${d.id}">${d.name}</a> <br>
                                                </c:if>
                                                <c:if test="${d.id eq requestScope.did_chosen}">
                                                    <span>${d.name}</span> <br>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>

                            </table>
                        </div>
                    </td>
                    <td valign="top">
                        <div id="">
                            <table>
                                <tbody>

                                    <c:forEach items="${requestScope.courses}" var="c">
                                        <tr>
                                            <td>
                                                <c:if test="${requestScope.cid_chosen ne c.id }">
                                                    <a href="?tid=${c.dept.term.id}&did=${c.dept.id}&cid=${c.id}">${c.name} (${c.code}  )</a> <br>
                                                </c:if>
                                                <c:if test="${requestScope.cid_chosen eq c.id }">
                                                    <span>${c.name} (${c.code})</span> <br>
                                                </c:if>
                                            </td>

                                        </tr>
                                    </c:forEach>

                            </table>
                        </div>
                    </td>
                    <td valign="top">
                        <div id="">
                            <table>
                                <tbody>

                                    <c:forEach items="${requestScope.groups}" var="g">
                                        <tr>
                                            <td>
                                                <c:if test="${requestScope.gid_chosen ne g.id}">
                                                    <a href="?tid=${g.course.dept.term.id}&did=${g.course.dept.id}&cid=${g.course.id}&gid=${g.id}">${g.name}</a> <br>
                                                </c:if>
                                                <c:if test="${requestScope.gid_chosen eq g.id}">
                                                    <span>${g.name}</span> <br>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:forEach>

                            </table>
                        </div>
                    </td>
                    </tbody>
                </table>
            </center>
        </div>
        <div class="middle-text-container"><span style="color: #757575">... then see student list</span> (<a href="">Export data</a>)</div>
        <div class="table2-container">
            <center>
                <c:if test="${requestScope.students ne null}">
                    <table>
                        <thead>
                            <tr>
                            <tr>
                                <th scope="col">No</th>
                                <th scope="col">Image</th>
                                <th scope="col">Code</th>
                                <th scope="col">Name</th>
                            </tr>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.students}" var="s" varStatus="loop">
                                <tr>
                                    <td>${loop.index}</td>
                                    <td class="image-container">

                                        <img id="${s.id}" class="student-image" src="data:image/jpeg;base64,${s.image}" style="border-width:0px;" alt="Student Image">

                                    </td>
                                    <td>${s.id}</td>
                                    <td>${s.name}</td>



                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </c:if>
            </center>
        </div>

    </body>
</html>
