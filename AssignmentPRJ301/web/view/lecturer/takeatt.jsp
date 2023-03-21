<%-- 
    Document   : att
    Created on : Mar 14, 2023, 1:54:29 PM
    Author     : thaim
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html> 
<html>
    <head class="header">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
    </head>

    <style>
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
        .button-container{
            text-align: right;
            padding-right: 160px;
            padding-top: 10px;
        }
        input[type="submit"] {
            width: 50px;
            height: 25px;

        }
        .header-text{
            padding-left: 80px;
            font-size: 30px;
            font-family: Arial;
        }
        .home-container{
            font-size: 15px;
            margin:0;
            font-family: Arial;
            text-align: left;
            padding:0;
        }
        .username-container{
            text-align: right;
            color: white;
        }
        .info-container{
            background: #4dfb9b;
        }

        body{
            margin: 0;
            padding: 0;
            background: repeating-radial-gradient(#898989);
            height: 100vh;
            overflow: hidden;
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
        <form action="takeattend" method="POST">

            <div class="table-container">
                <center>
                    <table>
                        <thead>
                        <td>No</td>
                        <td>Id</td>
                        <td>Name</td>
                        <td>Image</td>
                        <td>Absent/Present</td>
                        <td>Description</td>
                        <td>Record Time</td>
                        </thead>
                        <c:forEach items="${requestScope.atts}" var="a" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${a.student.id}
                                    <input type="hidden" name="sid" value="${a.student.id}" />
                                    <input type="hidden" name="aid${a.student.id}" value="${a.id}" />
                                </td>
                                <td>${a.student.name}</td>

                                <!-- Student image -->
                                <td class="img-container">

                                    <img id="${a.student.id}" class="student-image" src="data:image/jpeg;base64,${a.student.image}" style="border-width:0px;" alt="Student Image">

                                </td>


                                <td><input type="radio"
                                           <c:if test="${!a.status}">
                                               checked="checked"
                                           </c:if>
                                           name="status${a.student.id}" value="absent"/>Absent
                                    <input type="radio"
                                           <c:if test="${a.status}">
                                               checked="checked"
                                           </c:if>
                                           name="status${a.student.id}" value="present"/>Present
                                </td>
                                <td><input type="text" value="${a.description}" name="description${a.student.id}"/></td>
                                <td><fmt:formatDate value="${a.record}" pattern="dd/MM/yyy HH:mm:ss" /></td>
                                </tr>
                        </c:forEach>
                    </table>
                </center>
                <input type="hidden" name="sessionid" value="${param.id}"/>
                <div class="button-container">
                    <input type="submit" value="Save"/> 
                </div>
            </div>
        </form>
    </body>

</html>
