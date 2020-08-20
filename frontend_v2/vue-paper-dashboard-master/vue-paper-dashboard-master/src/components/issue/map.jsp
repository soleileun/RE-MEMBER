<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>�ּҷ� ��� ǥ���ϱ�</title>
<style>
.map_wrap {
	position: relative;
	width: 100%;
	height: 350px;
}

.title {
	font-weight: bold;
	display: block;
}

.hAddr {
	position: absolute;
	left: 10px;
	top: 10px;
	border-radius: 2px;
	background: #fff;
	background: rgba(255, 255, 255, 0.8);
	z-index: 1;
	padding: 5px;
}

#centerAddr {
	display: block;
	margin-top: 2px;
	font-weight: normal;
}

.bAddr {
	padding: 5px;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

.placeinfo_wrap {
	position: absolute;
	bottom: 28px;
	left: -150px;
	width: 300px;
}

.placeinfo {
	position: relative;
	width: 100%;
	border-radius: 6px;
	border: 1px solid #ccc;
	border-bottom: 2px solid #ddd;
	padding-bottom: 10px;
	background: #fff;
}

.placeinfo:nth-of-type(n) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.placeinfo_wrap .after {
	content: '';
	position: relative;
	margin-left: -12px;
	left: 50%;
	width: 22px;
	height: 12px;
	background:
		url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')
}

.placeinfo a, .placeinfo a:hover, .placeinfo a:active {
	color: #fff;
	text-decoration: none;
}

.placeinfo a, .placeinfo span {
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

.placeinfo span {
	margin: 5px 5px 0 5px;
	cursor: default;
	font-size: 13px;
}

.placeinfo .title {
	font-weight: bold;
	font-size: 14px;
	border-radius: 6px 6px 0 0;
	margin: -1px -1px 0 -1px;
	padding: 10px;
	color: #fff;
	background: #d95050;
	background: #d95050 no-repeat right 14px center;
}

.placeinfo .tel {
	color: #0f7833;
}

.placeinfo .jibun {
	color: #999;
	font-size: 11px;
	margin-top: 0;
}
</style>

</head>
<body>
	<p style="margin-top: -12px">
		<em class="link"> <a href="javascript:void(0);"
			onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
				Ȥ�� �ּ� ����� �߸� ������ ��쿡�� ���⿡ �������ּ���. </a>
		</em>
	</p>

	<div>
		<input type="text" name="address" size="12" id="address">
		<button onclick="javascript:testing()" type="submit">��ġ �Է�</button>
		<button onclick="javascript:address()" type="submit">�ּ� �˻�
			api</button>
		<br>
	</div>

	<!-- <div id="map" style="width: 700px; height: 500px;"></div> -->
	<div class="map_wrap">
		<div id="map"
			style="width: 700px; height: 500px; position: relative; overflow: hidden;"></div>
		<div class="hAddr">
			<span class="title">�����߽ɱ��� ������ �ּ�����</span> <span id="centerAddr"></span>
		</div>
	</div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d0d50b55f6a9816774963048e01b9004&libraries=services"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script>
		function address() {
			new daum.Postcode({
				oncomplete : function(data) {
					alert(data.address);
					// data.address�� ȸ�����Խ� �ּҸ� �Է��ϸ� ��.
				}
			}).open();
		}

		var geocoder = new kakao.maps.services.Geocoder();
		function testing() {
			var input = document.getElementById("address").value;

			geocoder
					.addressSearch(
							input,
							function(result, status) {
								// ���������� �˻��� �Ϸ������ 
								if (status === kakao.maps.services.Status.OK) {
									markerCenter.setMap(null);
									var coords = new kakao.maps.LatLng(
											result[0].y, result[0].x);

									addMarker(coords);

									var totalX = 0;
									var totalY = 0;
									var len = markers.length;
									for (var i = 0; i < len; i++) {
										totalX += markers[i].getPosition().Ha;
										totalY += markers[i].getPosition().Ga;
									}
									totalX /= len;
									totalY /= len;

									var coords = new kakao.maps.LatLng(totalX,
											totalY);

									markerCenter = new kakao.maps.Marker({
										map : map,
										position : coords,
										image : markerImage
									});
									markerCenter.setMap(map);
									map.setCenter(coords);

									// ��Ŀ�� Ŭ���ϸ� ��Ҹ��� ǥ���� ���������� �Դϴ�
									var category = new kakao.maps.InfoWindow({
										zIndex : 1,
										removable : true
									});

									// ��� �˻� ��ü�� �����մϴ�
									var ps = new kakao.maps.services.Places(map);

									ps.categorySearch('CE7', placesSearchCB, {
										useMapBounds : true
									});

									// Ű���� �˻� �Ϸ� �� ȣ��Ǵ� �ݹ��Լ� �Դϴ�
									function placesSearchCB(data, status,
											pagination) {
										if (status === kakao.maps.services.Status.OK) {
											tmpData = [];
											tmpData.push(data[i]);
											for (var i = 0; i < data.length; i++) {
												displayMarker(data[i]);
											}
										} else {
											alert("���� �˻� ����!")
										}
									}

									// ������ ��Ŀ�� ǥ���ϴ� �Լ��Դϴ�
									function displayMarker(place) {
										// ��Ŀ�� �����ϰ� ������ ǥ���մϴ�

										var imageSrc = 'https://cdn3.iconfinder.com/data/icons/pin-4/100/pin_10-512.png', // ��Ŀ�̹����� �ּ��Դϴ�    
										imageSize = new kakao.maps.Size(64, 69), // ��Ŀ�̹����� ũ���Դϴ�
										imageOption = {
											offset : new kakao.maps.Point(27,
													69)
										}; // ��Ŀ�̹����� �ɼ��Դϴ�. ��Ŀ�� ��ǥ�� ��ġ��ų �̹��� �ȿ����� ��ǥ�� �����մϴ�.

										// ��Ŀ�� �̹��������� ������ �ִ� ��Ŀ�̹����� �����մϴ�
										var markerImage = new kakao.maps.MarkerImage(
												imageSrc, imageSize,
												imageOption);

										

										var marker2 = new kakao.maps.Marker({
											map : map,
											position : new kakao.maps.LatLng(
													place.y, place.x),
											image : markerImage
										});

										// ��Ŀ�� Ŭ���̺�Ʈ�� ����մϴ�
										kakao.maps.event
												.addListener(
														marker2,
														'click',
														function() {
															// ��Ŀ�� Ŭ���ϸ� ��Ҹ��� ���������쿡 ǥ��˴ϴ�

															var content = '<div class="placeinfo">'
																	+ '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">'
																	+ place.place_name
																	+ '</a>';

															if (place.road_address_name) {
																content += '    <span title="' + place.road_address_name + '">'
																		+ place.road_address_name
																		+ '</span>'
																		+ '  <span class="jibun" title="' + place.address_name + '">(���� : '
																		+ place.address_name
																		+ ')</span>';
															} else {
																content += '    <span title="' + place.address_name + '">'
																		+ place.address_name
																		+ '</span>';
															}

															content += '    <span class="tel">'
																	+ place.phone
																	+ '</span>'
																	+ '</div>'
																	+ '<div class="after"></div>';
															/* category
																	.setContent('<div style="padding:5px;font-size:12px;">'
																			+ place.place_name
																			+ '</div>'); */
															category
																	.setContent(content);
															category.open(map,
																	marker2);
														});
									}

									var iwContent = '<div style="padding:5px;">Center</div>';

									// ���������츦 �����մϴ�
									var infowindow = new kakao.maps.InfoWindow(
											{
												content : iwContent
											});

									// ��Ŀ�� ���콺���� �̺�Ʈ�� ����մϴ�
									kakao.maps.event.addListener(markerCenter,
											'mouseover', function() {
												// ��Ŀ�� ���콺���� �̺�Ʈ�� �߻��ϸ� ���������츦 ��Ŀ���� ǥ���մϴ�
												infowindow.open(map,
														markerCenter);
											});

									// ��Ŀ�� ���콺�ƿ� �̺�Ʈ�� ����մϴ�
									kakao.maps.event.addListener(markerCenter,
											'mouseout', function() {
												// ��Ŀ�� ���콺�ƿ� �̺�Ʈ�� �߻��ϸ� ���������츦 �����մϴ�
												infowindow.close();
											});

									// markerCenter �� ���� �ּ� ��
									searchDetailAddrFromCoords(
											coords,
											function(result, status) {
												if (status === kakao.maps.services.Status.OK) {
													var detailAddr = !!result[0].road_address ? '<div>���θ��ּ� : '
															+ result[0].road_address.address_name
															+ '</div>'
															: '';
													detailAddr += '<div>���� �ּ� : '
															+ result[0].address.address_name
															+ '</div>';

													var content = '<div class="bAddr">'
															+ '<span class="title">������ �ּ�����</span>'
															+ detailAddr
															+ '</div>';

													// ���������쿡 Ŭ���� ��ġ�� ���� ������ �� �ּ������� ǥ���մϴ�
													infowindow
															.setContent(content);
													/* infowindow
															.open(map, marker); */
												}
											});

								} else {
									alert("�˻� ����!");
								}
							});

		}

		var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // ������ �߽���ǥ
			level : 3
		// ������ Ȯ�� ����
		};

		var markerCenter = new kakao.maps.LatLng(33.450701, 126.570667);

		// ������ �����մϴ�    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // ��Ŀ�̹����� �ּ��Դϴ�    
		imageSize = new kakao.maps.Size(64, 69), // ��Ŀ�̹����� ũ���Դϴ�
		imageOption = {
			offset : new kakao.maps.Point(27, 69)
		}; // ��Ŀ�̹����� �ɼ��Դϴ�. ��Ŀ�� ��ǥ�� ��ġ��ų �̹��� �ȿ����� ��ǥ�� �����մϴ�.

		// ��Ŀ�� �̹��������� ������ �ִ� ��Ŀ�̹����� �����մϴ�
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
				imageOption);

		// ��Ŀ�� �����մϴ�
		markerCenter = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(33.450701, 126.570667),
			image : markerImage
		// ��Ŀ�̹��� ���� 
		});

		var markers = [];

		var tmpData = [];

		// ���� ���� �߽���ǥ�� �ּҸ� �˻��ؼ� ���� ���� ��ܿ� ǥ���մϴ�
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		function addMarker(position) {

			// ��Ŀ�� �����մϴ�

			var marker = new kakao.maps.Marker({
				position : position
			});

			// ��Ŀ�� ���� ���� ǥ�õǵ��� �����մϴ�
			marker.setMap(map);

			// ������ ��Ŀ�� �迭�� �߰��մϴ�
			markers.push(marker);
		}

		// �迭�� �߰��� ��Ŀ���� ������ ǥ���ϰų� �����ϴ� �Լ��Դϴ�
		function setMarkers(map) {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}

		// �߽� ��ǥ�� Ȯ�� ������ ������� �� ���� �߽� ��ǥ�� ���� �ּ� ������ ǥ���ϵ��� �̺�Ʈ�� ����մϴ�
		kakao.maps.event.addListener(map, 'idle', function() {
			searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
			// ��ǥ�� ������ �ּ� ������ ��û�մϴ�
			geocoder.coord2RegionCode(coords.getLng(), coords.getLat(),
					callback);
		}

		function searchDetailAddrFromCoords(coords, callback) {
			// ��ǥ�� ������ �� �ּ� ������ ��û�մϴ�
			geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// ���� ������ܿ� ���� �߽���ǥ�� ���� �ּ������� ǥ���ϴ� �Լ��Դϴ�
		function displayCenterInfo(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var infoDiv = document.getElementById('centerAddr');

				for (var i = 0; i < result.length; i++) {
					// �������� region_type ���� 'H' �̹Ƿ�
					if (result[i].region_type === 'H') {
						infoDiv.innerHTML = result[i].address_name;
						break;
					}
				}
			}
		}
	</script>
</body>
</html>