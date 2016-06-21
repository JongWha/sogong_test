<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Food Chaser Admin Page</title>
<jsp:include page="/commonHeader.jsp" flush="false"/>
  
  <!--  
<script type="text/javascript">

Truck.getMap("inputTruckMap",314219, 544425, 770, 380, true);

</script>
 -->

</head>
<body class="admin">

<jsp:include page="/commonNav.jsp" flush="false"/>

	<section class="section" id="admin">
	<div class="container" style="height:100% !important">
		<div class="space"></div>
		<div class="space"></div>
		<div class="title">Welcome to Admin page</div>
		<div class="space"></div>
		<div class="space"></div>

		<c:if test="${empty requestScope.truckNo}">

			<form method="post" enctype="multipart/form-data"
				action="/truck?action=createTruck">
				<div class="subject">
					<div class="text">Food Truck Title</div>

					<input type="text" name="inputTruckTitle" id="inputTruckTitle"
						maxlength="15"
						style="font-size: 1.5em; border-bottom: 1px solid #eeeeee; padding-left: 10px" />

					<div class="check">max 15 letters</div>
				</div>
				<div class="form">
					<textarea name="inputTruckInfo" id="inputTruckInfo"
						style="width: 100%; min-height: 400px;">
				Simple description about food truck
			</textarea>
				</div>
				<div class="truckPic">

					<input type="file" name="inputTruckPic" id="inputTruckPic" />

				</div>

				<div class="space"></div>

				<div id="truckCreateLocation">
				<div class="address">
					<div class="text">Truck Location</div>

					<div class="input" style="width: 500px;" id="outputTruckLocation">
						<input type="text" id="inputTruckLocation"
							style="font-size: 1.2em; border-bottom: 1px solid #eeeeee; padding-left: 10px;" />
					</div>
					<input type="hidden" name="inputTruckLocation" /> 
					
					<input
						type="hidden" name="inputTruckLatitude" id="inputTruckLatitude" />
					
					<input type="hidden" name="inputTruckLongitude"
						id="inputTruckLongitude" />
					<div class="button">
						<button type="button" class="btn btn-foodTruck-search"
							onclick="Truck.getLocation()">Search</button>
					</div>
					
					<!-- <div class="map" id="inputTruckMap"></div>  -->

				</div>
				</div>

				<div class="space"></div>

				<div class="fin">
					<input type="submit" value="Enrollment">
				</div>
			</form>
		</c:if>
		
		<c:if test="${not empty requestScope.truckNo}">
			
			<div class="title"><span>Truck Title : </span>${requestScope.truckTitle}</div>
			<div class="space"></div>
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="col-md-6">
				<div class="pic">
					<img src="${requestScope.truckPic}"
						style="width: 350px; height: 350px;" />
				</div>
				</div>
				<div class="col-md-6">
				<div class="intro">intro :</div>
				<div class="info">${requestScope.truckInfo}</div>
				</div>
			</div>
			<div class="col-md-1"></div>

		</c:if>

	</div>
	

	</section>


	<jsp:include page="/commonfooter.jsp" flush="false"/>

</body>
</html>