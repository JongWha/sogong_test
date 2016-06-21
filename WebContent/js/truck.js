/**
 * 
 */

var Truck = {};

Truck.checkTruck = function(inputMemberId){
	
	var check = false;
	
	alert(inputMemberId);
	
	var form_data = {inputMemberID : inputMemberId};
	
	var action="/truck?action=checkTruck";
	
	$.ajax({
		type : "POST",
		url : action,
		data : form_data,
		dataType : "text",
		async : false,
		success: function(response) {
			if(response == "Exist Truck") {
				check = true;
			} else if(response == "No Exist Truck") {
				alert("Why dont you Enroll your truck?");
				check = false;
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});

	if(check){
		
		action = "/truck?action=getTruck";
		
		$.ajax({
			type : "POST",
			url : action,
			dataType : "text",
			async : false,
			success: function(response) {
				if(response == 1) {
					check = true;
				} else if(response == -1) {
					alert("fail to access db");
					check = false;
				}
			}, error: function(xhr,status,error) {
				alert(error);
			}
		});
	}
	
	return check;

		
}

Truck.getLocation = function(){
	
	if($("#inputTruckLocation").val() == "") {
		alert("Please enter the Location");
		return false;
	}
	
	var action = "/truck?action=getTruckLocation";
	var form_data = {
		inputKeyword : $("#inputTruckLocation").val()
	}

	$.ajax({
		type : "POST",
		url : action,
		data : form_data,
		dataType : "json",
		success: function(response) {
			var locations = "<select id='inputTruckLocation' title='select addr' data-width='100%' data-size='8' onchange='Truck.setMap()'>";
			
			for(i=0; i<response.outputTruckLocation.length; i++) {
				locations += "<option name='" + i + "' data-location-x='" + response.outputTruckLocation[i].outputX + "' data-location-y='" + response.outputTruckLocation[i].outputY + "'>" + response.outputTruckLocation[i].outputAddress + " " + response.outputTruckLocation[i].outputTitle + "</option>";
			}
			
			locations += "</select>";
			
			$("#outputTruckLocation").html(locations);
			$("div.address div.button").html("<button class='btn btn-foodTruck-search' onclick='Truck.resetLocation()'>Research</button>");
			$("#inputTruckLocation").selectpicker();
			
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});
	
}

Truck.getMap = function(id, latitude, longitude, width, height, isZoom) {

	$("#" + id).empty();

	var oPoint = new nhn.api.map.TM128(latitude, longitude);
	nhn.api.map.setDefaultPoint("TM128");
	var oMap = new nhn.api.map.Map(id, {
		point : oPoint,
		zoom : 11,
		enableWheelZoom : isZoom,
		enableDragPan : true,
		enableDblClickZoom : true,
		mapMode : 0,
		activateTrafficMap : false,
		activateBicycleMap : false,
		minMaxLevel : [ 1, 14 ],
		size : new nhn.api.map.Size(width, height)
	});

	var markerCount = 0;
	var oSize = new nhn.api.map.Size(60, 89);
	var oOffset = new nhn.api.map.Size(30, 88);
	var oIcon = new nhn.api.map.Icon("/img/marker.png", oSize, oOffset);
	var mapInfoTestWindow = new nhn.api.map.InfoWindow(); // - info window 생성
	mapInfoTestWindow.setVisible(false); // - infowindow 표시 여부 지정.
	oMap.addOverlay(mapInfoTestWindow);     // - 지도에 추가.    
	var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
	oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.
	mapInfoTestWindow.attach('changeVisible', function(oCustomEvent) {
		if (oCustomEvent.visible) {
			oLabel.setVisible(false);
		}
	});

	var oMarker = new nhn.api.map.Marker(oIcon);
	oMarker.setPoint(oPoint);
	oMap.addOverlay(oMarker);
	var oLabel = new nhn.api.map.MarkerLabel();
	oMap.addOverlay(oLabel);
	//   oLabel.setVisible(true,oMarker);
}

Truck.resetLocation = function() {
	$("#outputTruckLocation").html("<input type='text' id='inputTruckLocation' name='inputTruckLocation' style='font-size:1.5em; border-bottom:1px solid #eeeeee; padding-left:10px;' />");
	$("div.address div.button").html("<button class='btn btn-foodTruck-search' onclick='Truck.getLocation()'>Search</button>");
}

Truck.setMap = function() {
	Truck.getMap("inputTruckMap", $("#inputTruckLocation option:selected").attr("data-location-x"), $("#inputTruckLocation option:selected").attr("data-location-y"), 770, 380, true);
	$("#truckCreateLocation input[name='inputTruckLocation']").val($("#inputTruckLocation option:selected").val());
	$("#inputTruckLatitude").val($("#inputTruckLocation option:selected").attr("data-location-x"));
	$("#inputTruckLongitude").val($("#inputTruckLocation option:selected").attr("data-location-y"));   
}