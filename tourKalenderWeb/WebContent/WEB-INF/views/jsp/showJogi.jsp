<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="org.pakkagames.tourkalender.domain.Position"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Jogis ist unterwegs</title>
<script src="https://code.jquery.com/jquery.min.js"></script>
<style type="text/css">
html, body, #map-canvas {
	height: 100%;
	margin: 0;
	padding: 0;
}

#panel {
	position: absolute;
	top: 5px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}
</style>

  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBrniTa20vxNsQRx3Aoq4aeza3EKd5yuuk" type="text/javascript"></script>
<!-- <script async defer src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script> -->

<script type="text/javascript">
	var markers = [];
	var map;
	var start = new google.maps.LatLng(50.733321, 7.089178);
	var newTrack = [];

	function showDataOfHour(hours) {
		$
				.ajax({
					dataType : "json",
					url : "/tourkalender/getDataOfHour",
					cache : false,
					data : 'numberHours='+hours,
					success : function(response) {

						newTrack = response;
						var factor = 1 / hours;
						drop(factor);

					},
					error : function() {
						alert('Error while request..');
					}
				});
	}
	
	function showAll() {
		$.ajax({
				dataType : "json",
				url : "/tourkalender/getAll",
				cache : false,
				success : function(response) {
					alert('success tree.');

					newTrack = response;
					drop();
				},
				error : function() {
					alert('Error while request..');
				}
			});
	}
	function initialize() {
		var bonn = new google.maps.LatLng(50.735371, 7.108363);

		var mapOptions = {
			zoom : 14,
			center : bonn,
			mapTypeId : 'terrain'
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);

		var contentString = '<div id="content">'
				+ '<div id="siteNotice">'
				+ '</div>'
				+ '<h1 id="firstHeading" class="firstHeading">Bonn, Startpunkt</h1>'
				+ '<div id="bodyContent">'
				+ '<p>In <b>Bonn</b> starte ich.</p>' + '</div>' + '</div>';

		var infowindow = new google.maps.InfoWindow({
			content : contentString
		});

		var markerStart = new google.maps.Marker({
			position : start,
			map : map,
			title : 'Bonn'
		});
		google.maps.event.addListener(markerStart, 'click', function() {
			infowindow.open(map, markerStart);
		});

		// Draw the path, using the Visualization API and the Elevation service.
		//drawPath();

	}

	function drop(factor) {
		clearMarkers();
		$.each(newTrack.positions, function(i, trackPoint) {
			var latitude = trackPoint.latitude;
			var longitude = trackPoint.longitude;
			var temp = new Date(trackPoint.dateTime);
			var dateTime = temp.getDate().toString() + "."
					+ temp.getMonth().toString() + ".  "
					+ temp.getHours().toString() + ":"
					+ temp.getMinutes().toString() + ":"
					+ temp.getSeconds().toString();

			addMarkerWithTimeout(trackPoint.latitude, trackPoint.longitude,
					dateTime, trackPoint.comment, i * 100 * factor);
		});
	}

	function addMarkerWithTimeout(lat, lng, dateTime, comment, timeout) {
		var infowindow = new google.maps.InfoWindow({
			content : ''
		});
		window.setTimeout(function() {
			var marker = new google.maps.Marker({
				position : new google.maps.LatLng(lat, lng),
				map : map,
				animation : google.maps.Animation.DROP,
				title : dateTime + "  " + comment
			});
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.setContent(dateTime + "  " + comment);
				infowindow.setPosition(this.getPosition());
				infowindow.setMap(map);
			});
			markers.push(marker);
		}, timeout);
	}

	function clearMarkers() {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div id="panel" style="margin-left: -52px">
		<button id="drop" onclick="showDataOfHour(2)">Positionen der letzten 2 Stunden</button>
		<button id="drop" onclick="showDataOfHour(1)">Positionen der letzten Stunde</button>
		<button id="drop" onclick="showAll()">Zeige alle Positionen</button>
	</div>

	<div id="map-canvas" style="height: 750px;"></div>


</body>
</html>

