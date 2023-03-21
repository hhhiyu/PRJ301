<%-- 
    Document   : timetable
    Created on : Mar 13, 2023, 11:15:29 PM
    Author     : thaim
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="formattedDate" />
<fmt:setLocale value="en_US"/>
<fmt
    <!DOCTYPE html>
    <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>JSP Page</title>    
            <style>
                .input-date{
                    padding-left: 100px;
                    padding-bottom: 30px;
                }
                .input-date-text-container{
                    padding-left: 100px;
                }
                a {
                    text-decoration: none;
                }
                thead{
                    background-color: #00c8fb;
                    color: white;
                }
                th,td{
                    border-bottom: 1px solid #e8eaea;
                    padding: 10px;
                    text-align: left;
                }
                table{
                    background-color: #f5f5f5;
                    font-family: unset;
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate(-50%, -50%);
                    width: 400px;
                    background: #ececec;
                    border-radius: 10px;
                    box-shadow: 10px 10px 15px rgba(0,0,0,0.05);

                }
                .table-container{
                    border-collapse: collapse;
                    border-radius: 20px;
                    overflow: hidden;
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
        </head>

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
            <!--            <div class="input-date">
                            <span class="input-date-text-container">Select Start Date:</span>
                            <form action="timetable" method="get">
                                <input type="date">
                                <input type="submit" value="Send">
                            </form>
                        </div>-->

            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th rowspan="2">
                            </th>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <th align="center">
                                    <fmt:formatDate value="${d}" pattern="EEE" /><br/>
                                </th>
                            </c:forEach>
                        </tr>
                        <tr>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <th>
                                    <div style="width: 100px;">
                                        <fmt:formatDate value="${d}" pattern="dd/MM"/><br/>
                                    </div>
                                </th>
                            </c:forEach>
                        </tr>
                    </thead>    

                    <c:forEach items="${requestScope.slots}" var="s">
                        <tr>
                            <td>${s.description}</td>
                            <c:forEach items="${requestScope.dates}"  var="d">
                                <td>
                                    <c:forEach items="${requestScope.sessions}" var="ses">
                                        <c:if test="${ses.slot.id eq s.id and ses.date eq d}">

                                            <a href="list?tid=${ses.group.course.dept.term.id}&did=${ses.group.course.dept.id}&cid=${ses.group.course.id}&gid=${ses.group.id}">${ses.group.name}</a>-${ses.group.course.code} <br/>
                                            at ${ses.room.name} <br/>

                                            <c:if test="${ses.status}">
                                                <span style="color: #008300;">(Attended)</span>-<a href="takeattend?id=${ses.id}">(edit)</a>

                                            </c:if>
                                            <c:if test="${!ses.status and ses.date eq formattedDate}">
                                                -<span style="color:red;">(not yet) </span><a href="takeattend?id=${ses.id}">take attend</a>
                                            </c:if>
                                            <c:if test="${!ses.status and ses.date lt formattedDate}">
                                                -<span style="color:red;">(Absent)</span>
                                            </c:if>
                                            <c:if test="${!ses.status and ses.date gt formattedDate}">
                                                -<span style="color:red;">(not yet)</span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ses.slot.id ne s.id and ses.date eq d}">
                                            -

                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        <tr/> 
                    </c:forEach>

                </table>
            </div>
        </body>
    </html>
