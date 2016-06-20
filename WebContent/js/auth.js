/**
 * 
 */

var Auth = {};

Auth.joinCheck = function(mode){

	
	var param = $("#userid").val();
	var password1 = $("#userpasswordPre").val();
	var password2 = $("#userpasswordCon").val();
	
	//check id

	if(mode == 1){
		if(param.length < 4){
			$("#joinNav div.msgRow1").show();
			$("#joinNav button.btnJoin").prop("disabled",true);
		}else{
			$("#joinNav div.msgRow1").hide();
			$("#joinNav button.btnJoin").prop("disabled",false);
		}
		return false;
	}
	else if(mode == 2){
		if(password1.length < 4){
			$("#joinNav div.msgRow2").show();
			$("#joinNav button.btnJoin").prop("disabled",true);
		}else{
			$("#joinNav div.msgRow2").hide();
			$("#joinNav button.btnJoin").prop("disabled",false);
		}
		return false;
	}else if(mode == 3){
		if(password1 != password2){
			$("#joinNav div.msgRow3").show();
			$("#joinNav button.btnJoin").prop("disabled",true);
		}else{
			$("#joinNav div.msgRow3").hide();
			$("#joinNav button.btnJoin").prop("disabled",false);
		}
		
		return false;

		
	}
}

Auth.join = function(){

	
	
	var userId = $("#userid").val();
	var userPassword = $("#userpasswordPre").val();
	var obj = document.getElementsByName('member_status');
	var userStatus = '';
	
	for(i=0; i< obj.length; i++){
		if(obj[i].checked){
			userStatus = obj[i].value;
		}
	}
	
	var action ="/member?action=joinMember";
	var form_data = {
			inputMemberId : userId,
			inputMemberPassword : userPassword,
			inputMemberStatus : userStatus
	};
	
	$.ajax({
		type : "POST",
		url : action,
		data : form_data,
		dataType : "text",
		success: function(response) {
			if(response == "JoinOK") {
				location.href="index.jsp";
				alert("Welcome Join is complete. Try login");
				$("#joinNav, #loginBackground").fadeOut(250);
			}else if(response == "Exist Member"){
				alert("Exist member id. please change Id");
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});

	return false;
	
}


Auth.login = function() {

	//check the input form
	
	var inputMemberId = $("#username").val();
	var inputMemberPassword = $("#userpassword").val();

	if(inputMemberId == null || inputMemberId == "") {
		alert('Something wrong Check the input.');
		return false;
	}

	if(inputMemberPassword == "") {
		 alert('Something wrong Check the input.');
		return false;
	}

	
	// 3. 서버로부터 받은 공개키로 이메일과 비밀번호 암호화
	var memberEmail = $("#username").val();
	var memberPassword = $("#userpassword").val();

	// 4. 암호화된 이메일과 비밀번호를 서버로 전송   
	var action = "/member?action=loginMember";
	var form_data = {
			inputMemberId : memberEmail,
			inputMemberPassword : memberPassword
	};

	$.ajax({
		type : "POST",
		url : action,
		data : form_data,
		dataType : "text",
		success: function(response) {
			if(response == "LoginOK") {
				location.href="main.jsp";
			}else if(response == "Please check the Id or Password"){
				alert("Please check the Id or Password");
			}
			else if(response == "No member Exist"){
				alert("No member exist. Please move to Join page");
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});

	return false;
}

Auth.loginCheck = function() {

	var check = false;

	$.ajax({
		type : "POST",
		url : "/member?action=loginCheck",
		dataType : "text",
		async : false,
		success: function(response) {
			if(response == 1) {
				check = true;
			} else if(response == -1) {
				alert("로그인이 필요합니다.");
				check = false;
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});

	return check;
}

Auth.logout = function() {

	var action = "/member?action=logoutMember";

	$.ajax({
		type : "POST",
		url : action,
		dataType : "text",
		async: false,
		success: function(response) {
			if(response == "LogoutOk") {
				location.href="index.jsp";
				
			} else {
				alert("Something wrong");
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});
}
	
Auth.open = function() {
	$("#loginNav, #loginBackground").fadeIn(250);
	$("#loginTxt").text("cancel");
	$("#loginTxt").attr("onclick", "Auth.close()");
}

Auth.joinopen = function() {
	$("#joinNav, #loginBackground").fadeIn(250);
	$("#joinTxt").text("cancel");
	$("#joinTxt").attr("onclick", "Auth.joinclose()");
}

Auth.close = function() {
	$("#loginNav, #loginBackground").fadeOut(250);
	$("#loginTxt").text("login");
	$("#loginTxt").attr("onclick", "Auth.open()");
}


Auth.joinclose = function() {
	$("#joinNav, #loginBackground").fadeOut(250);
	$("#joinTxt").text("join");
	$("#joinTxt").attr("onclick", "Auth.joinopen()");
}