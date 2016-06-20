<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <%
	// 한글 캐릭터셋 변환
	request.setCharacterEncoding("UTF-8");

%>
<!DOCTYPE html>
<html lang="utf-8">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png">

    <title>Food Chaser - 상세정보</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">


    <!-- Custom styles for this template -->
    <link href="assets/css/main.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="assets/js/hover.zoom.js"></script>
    <script src="assets/js/hover.zoom.conf.js"></script>
  </head>

  <body>

    <jsp:include page="/commonNav.jsp" flush="false"/>
	<!-- +++++ Posts Lists +++++ -->
	<div id="white">
	    <div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">           <!-- tag를 이용해 서블릿과 연동하여 DAO를 통해 정보를 가져온다. -->
					<div class="col-lg-6"><img src="${requestScope.truck_picture}" width="320px" height="320px"></div>
					<h1>
					${requestScope.truck_name}
					</h1>
					<h2>
					소개 : ${requestScope.truck_intro}
					</h2><br/>
					<h4>
					인스타  : ${requestScope.truck_mail}
					</h4>
					<br/>
				</div>
			</div>
	    </div> 
	</div>
	<div class="container" align="center">
		<div class="row"align="center" >	
			<h2> 메 뉴</h2>
			<c:forEach items="${requestScope.list}" var ="tru2">		
			<%
				for(int  i = 0 ; i < 1; i++)
				{	
					// 메뉴의 정보를 가져와서 그 갯수만큼 뿌려준다.
					out.print("<div class='col-lg-3' align='center'><img class='img-responsive' src='");
					%>${tru2.piture}<%
					out.print("'width='100px' height='100px'/>");
					out.print("<p>");	
					%>${tru2.menu_name}<%
					out.print("<br/>");	
					%>${tru2.price}<%
					out.print("<br/></p> </div>");
					
				}
			%>
			</c:forEach>
		</div>
	</div>
	
   <!-- +++++ Footer Section +++++ -->
   <div id="footer">
     <div class="container">
        <div class="row"  align="center">
		</div>
	 </div>
  </div>
   
  </body>
</html>
