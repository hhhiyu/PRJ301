<%-- 
    Document   : home.jsp
    Created on : Mar 16, 2023, 6:40:49 PM
    Author     : thaim
--%>    
<%@ page language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="formattedDate" />


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
        <%
          // Get the current date
          Calendar calendar = Calendar.getInstance();
          Date currentDate = calendar.getTime();

          // Get the date 6 days from now
          calendar.add(Calendar.DATE, 6);
          Date dateAfterSixDays = calendar.getTime();

          // Format the date using SimpleDateFormat
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          String resultDate = sdf.format(dateAfterSixDays);
          request.setAttribute("resultDate",resultDate);
        %>
        <div class="header-text">
            FPT University Academic Portal
        </div>
        <div class="header">
            <div>
                <a class="home-container" href="home">Home</a>
                <span class="home-container"> <a href="../logout">Logout</a> </span>
            </div>
        </div>
        <div class="">
            <center>
                <table>
                    <tr>
                    </tr>
                    <td valign="top">
                        <div id="">
                            <table>
                                <tr>
                                    <td>
                                        <a href="list">View List Student</a><br>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>

                    <td valign="top">
                        <div id="">
                            <table>
                                <tr>
                                    <td>

                                        <a href="timetable?lid=1&from=${formattedDate}&to=${requestScope.resultDate}">View weekly timetable</a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </table>
            </center>
        </div>
    </body>
</html>
