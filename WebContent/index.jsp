<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%	// 한글 캐릭터셋 변환
	request.setCharacterEncoding("UTF-8");%> 
<!DOCTYPE html>
<html lang="utf-8">
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Food Chaser</title>
    
    <jsp:include page="/commonHeader.jsp" flush="false"/>

</head>

<body id="page-top" class="index">

<jsp:include page="/commonNav.jsp" flush="false"/>
   

    <!-- Header -->
    

    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <img class="img-responsive" src="img/profile.png" alt="">
                    <div class="intro-text">
                        <span class="name">Food Chaser</span>
                        <hr class="star-light">
                        <span class="skills"> what about food truck? Enjoy the cheap and tasty food! </span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- About Section -->
    <section class="success" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2>About</h2>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-2">
                    <p>Food truck is restaurant on the road. Food chaser will be one of the solutions when you want to eat something tasty but cheap. 
                    You can easily get information about food truck like location and menu. Also we provide review.</p>
                </div>
                <div class="col-lg-4">
                    <p>We hope you to find food truck whenever you want. We support management system to owner. Have a great meal with our service. Thanks. </p>
                </div>
            </div>
        </div>
    </section>

<jsp:include page="/commonfooter.jsp" flush="false"/>
 


</body>

</html>