<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
       <jsp:include page="commonHeader.jsp" flush="false"/>
       
       <%	// 한글 캐릭터셋 변환
	request.setCharacterEncoding("UTF-8");%>


<nav class="navbar navbar-default navbar-fixed-top">

	<!-- Brand and toggle get grouped for better mobile display -->

	<!-- Collect the nav links, forms, and other content for toggling -->

	<div class="navbar-tttop">


		<div class="col-md-12" id="bs-example-navbar-collapse-1">
			<div class="col-md-6">
				<ul class="nav navbar-nav navbar-left">
					<li><a href="#" onclick="Auth.logout();">Food Chaser </a></li>
				</ul>
			</div>
			<div class="col-md-6">
				<c:if test="${empty sessionScope.memberStatus}">
					<ul class="nav navbar-nav navbar-right">
						<li class="hidden"><a href="#page-top"></a></li>
						<li class="page-scroll"><a href="#about">About</a></li>
						<li class="page-scroll"><a id="loginTxt" href="#"
							onclick="Auth.open()">Login</a></li>
						<li class="page-scroll"><a
							id="joinTxt" href="#"
							onclick="Auth.joinopen()">Join</a></li>
					</ul>
				</c:if>

				<c:if test="${sessionScope.memberStatus == 1}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="main.jsp">Map</a></li>
						<li><a><span>'${sessionScope.memberId}' </span><span>님
									환영합니다.</span></a></li>
						<li><a href="#" onclick="Auth.logout();">LogOut</a></li>
					</ul>
				</c:if>
				
				<c:if test="${sessionScope.memberStatus == 0}">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="main.jsp">Map</a></li>
						<li><a href="/truck?action=getTruck">Admin</a></li>
						<li><a><span>'${sessionScope.memberId}' </span><span>님
									환영합니다.</span></a></li>
						<li><a href="#" onclick="Auth.logout();">LogOut</a></li>
					</ul>
				</c:if>
			</div>
		</div>
	</div>

	<!-- /.container-fluid -->
</nav>

<c:if test="${empty sessionScope.memberStatus}">
	<div id="loginNav">
		<div class="top">
			<div class="left">
				<span class="loginNav-title">FoodChaser Login</span>
			</div>
			<div class="right">
				<img class="exitBtn" src="/img/close.png" onclick="Auth.close()" />
			</div>
		</div>

		<div class="middle">
			<div class="form-group">
				<input type="email" class="form-control" id="username" required />
			</div>

			<div class="form-group">
				<input type="password" class="form-control" id="userpassword"
					required />
			</div>

			<div class="form-group">
				<button type="button" class="btn btn-honbabin-login btn-block"
					onclick="Auth.login()">Login</button>
			</div>
			<div class="form-grop">
				<button type="button" class="btn btn-honbabin-login btn-block"
					onclick="Auth.joinopen()">join</button>
			</div>

		</div>
	</div>


	<div id="loginBackground" class="background-fixed"
		style="background: rgba(0, 0, 0, 0.6); z-index: 9999; display: none;"></div>
</c:if>

<c:if test="${empty sessionScope.memberStatus}">
	<div id="joinNav">
		<div class="top">
			<div class="left">
				<span class="loginNav-title">FoodChaser Join</span>
			</div>
			<div class="right">
				<img class="exitBtn" src="/img/close.png" onclick="Auth.joinclose()" />
			</div>
		</div>

		<div class="middle">
			<div class="form-group joinName">
				<span>ID :</span><input type="text" class="form-control" id="userid" onkeyup="Auth.joinCheck(1);" required />
			</div>
			<div class="msgRow1">Id must be more than 4 letter</div>
			
			<div class="form-group joinPassword">
				<span>Password :</span><input type="password" class="form-control" id="userpasswordPre" onkeyup="Auth.joinCheck(2);"
					required />
			</div>
			<div class="msgRow2">Password must be more than 4 letter</div>
			
			<div class="form-group">
				<span>Password Check:</span><input type="password" class="form-control" id="userpasswordCon" onkeyup="Auth.joinCheck(3);"
					required />
			</div>
			<div class="msgRow3">Incorrect Password</div>
			
			<div class="form-grop"> 
					<label class=""><input type="radio" name="member_status" value="1" checked="checked"> Guest &nbsp; &nbsp;</label>
					<label><input type="radio" name="member_status" value="2">Owner</label>
				
			</div>

			<div class="form-group">
				<button type="button" class="btn btnJoin btn-block"
					onclick="Auth.join()">Join</button>
			</div>
			
			<div class="form-group">
				<button type="button" class="btn btn-block"
					onclick="Auth.open()">Login</button>
			</div>

		</div>
	</div>


	<div id="loginBackground" class="background-fixed"
		style="background: rgba(0, 0, 0, 0.6); z-index: 9999; display: none;"></div>
</c:if>