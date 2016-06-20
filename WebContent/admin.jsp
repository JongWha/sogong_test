<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Food Chaser Admin Page</title>
<jsp:include page="/commonHeader.jsp" flush="false"/>
</head>
<body class="admin">

<jsp:include page="/commonNav.jsp" flush="false"/>

	<section class="section" id="admin">
	<div class="container">
		<div class="space"></div>
		<div class="space"></div>
		<div class="title">Welcome to Admin page</div>
		<div class="space"></div>
		<div class="space"></div>
	
		<form method="post" enctype="multipart/form-data" action ="/truck?action=createTruck">
		<div class="subject">
			<div class="text">Food Truck Title</div>
			<div class="input">
				<input type="text" name="inputTruckTitle" id="inputTruckTitle" maxlength="15"
					style="font-size: 1.5em; border-bottom: 1px solid #eeeeee; padding-left: 10px" />
			</div>
			<div class="check">max 15 letters</div>
		</div>
		<div class="form">
			<textarea name="inputTruckInfo" id="inputTruckInfo"
				style="width: 100%; min-height: 400px;">
				Simple description about food truck
			</textarea>
		</div>
		<div class="truckPic">
			<div class="input">
				<input type="file" name="inputTruckPic" id="inputTruckPic" />
			</div>
		</div>
		
		<div class="space"></div>
		
		<div class="fin">
			<input type="submit" value="Enrollment">
		</div>
		</form>
   	

	</div>
	

	</section>


	<jsp:include page="/commonfooter.jsp" flush="false"/>

</body>
</html>