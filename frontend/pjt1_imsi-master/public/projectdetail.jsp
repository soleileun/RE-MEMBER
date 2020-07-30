<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>projectdetail</title>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/dragula/3.7.2/dragula.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d0d50b55f6a9816774963048e01b9004&libraries=services"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
	<div class="hello">
		<div style="background: #E6E6E6; height: 1000px; padding: 20px;">
			<div style="width: 70%; margin: 0 auto;">
				<div id="projectList"></div>
				<div class="text-right">
					<button class="btn btn-primary" id="create">등록</button>
				</div>
			</div>


			<section>
				<div
					style="background: white; margin-top: 40px; margin-bottom: 40px; height: 300px; width: 47%; float: left; border-radius: 10px; padding-top: 10px; padding-left: 10px; padding-right: 10px;">
					전체 이슈 처리 현황 그래프를 넣어주자.</div>

				<div
					style="background: white; margin-top: 40px; margin-bottom: 40px; height: 300px; width: 47%; float: right; border-radius: 10px; padding-top: 10px; padding-left: 10px; padding-right: 10px; overflow-y: scroll">
					진행중인 이슈 나한테 할당된 이슈나, 이런거 보여줌<br> 아니면 총 이슈에 대한 정보를 스크롤 바 형식으로
					보여주면 좋을것같음.

					<section
						style="padding: 5px; margin: 5px; border: 1px solid #D8D8D8; background: white; border-radius: 25px;">
						이런식으로 들어감</section>
					<section
						style="padding: 5px; margin: 5px; border: 1px solid #D8D8D8; background: white; border-radius: 25px;">
						요렇게</section>
				</div>


			</section>

			<div
				style="height: 300px; background: white; border-radius: 10px; width: 100%; float: left;">
				간단한 이슈 대시보드 <br>

				<div class="left" id="before" ref="before"
					style="background: lightgrey; width: 29%; height: 80%; border-radius: 20px; margin: 10px; padding: 10px; float: left;">
					생성된 이슈</div>

				<div class="left" id="during" ref="during"
					style="background: lightgrey; width: 29%; height: 80%; border-radius: 20px; margin: 10px; padding: 10px; float: left;">
					진행중인 이슈 <br>
					<div class="test" id="test1"
						style="padding: 5px; margin: 5px; background: white; border-radius: 25px;">
						이런식으로 들어감</div>
					<div class="test" id="test2"
						style="padding: 5px; margin: 5px; background: white; border-radius: 25px;">
						요렇게</div>
				</div>
				<div ref="end" id="end" class="left"
					style="background: lightgrey; width: 29%; height: 80%; border-radius: 20px; margin: 10px; padding: 10px; float: right;">
					완료된 이슈</div>

			</div>

		</div>


		<div>
			<input type="text" name="address" size="12" id="address">
			<button onclick="javascript:testing()" type="submit">위치 입력</button>
			<button onclick="javascript:address()" type="submit">주소 검색
				api</button>
			<br>
		</div>

		<!-- <div id="map" style="width: 700px; height: 500px;"></div> -->
		<div class="map_wrap">
			<div id="map"
				style="width: 700px; height: 500px; position: relative; overflow: hidden;"></div>
			<div class="hAddr">
				<span class="title">지도중심기준 행정동 주소정보</span> <span id="centerAddr"></span>
			</div>
		</div>


	</div>



	<script>
		var dragAndDrop = {

			limit : 2,
			count : 0,

			init : function() {
				this.dragula();
				this.drake();
			},

			drake : function() {
				this.dragula.on('drop', this.dropped.bind(this));
			},

			dragula : function() {
				this.dragula = dragula([ document.querySelector('#test1'),
						document.querySelector('#test2') ], {
					moves : this.canMove.bind(this),
					copy : true,
				});
			},

			canMove : function() {
				return this.count < this.limit;
			},

			dropped : function(el) {
				this.count++;
			}

		};

		dragAndDrop.init();

		function loadProjectList() {
			$
					.ajax({
						type : "get",
						url : "http://localhost:8080/api/project",
						success : function(result) {
							var htmlTxt = '<table class="table table-bordered table-condensed"><tr><th>제목</th><th>이름</th><th>내용</th></tr>';
							$("#projectList").empty();
							$.each(result, function(idx, item) {
								htmlTxt += '<tr>' + '<td>' + item.pjtName
										+ '</td>' + '<td>' + item.pid + '</td>'
										+ '<td>' + item.pjtContent
										+ '</td></tr>';
							});
							htmlTxt += '</table>'
							$('#projectList').html(htmlTxt);
						},
						error : function() {
							alert("서버 이상으로 목록 조회에 실패하였습니다. 잠시후 다시 시도해주세요.");

						}
					});
		}

		loadProjectList();

		function address() {
			new daum.Postcode({
				oncomplete : function(data) {
					alert(data.address);
					// data.address로 회원가입시 주소를 입력하면 됨.
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
								// 정상적으로 검색이 완료됐으면 
								if (status === kakao.maps.services.Status.OK) {
									markerCenter.setMap(null);
									var coords = new kakao.maps.LatLng(
											result[0].y, result[0].x);

									addMarker(coords);

									var totalX = 0;
									var totalY = 0;
									var len = markers.length;
									console.log("len is " + len);
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

									// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
									var category = new kakao.maps.InfoWindow({
										zIndex : 1,
										removable : true
									});

									// 장소 검색 객체를 생성합니다
									var ps = new kakao.maps.services.Places(map);

									ps.categorySearch('CE7', placesSearchCB, {
										useMapBounds : true
									});

									// 키워드 검색 완료 시 호출되는 콜백함수 입니다
									function placesSearchCB(data, status,
											pagination) {
										if (status === kakao.maps.services.Status.OK) {
											tmpData = [];
											tmpData.push(data[i]);
											for (var i = 0; i < data.length; i++) {
												displayMarker(data[i]);
											}
										} else {
											alert("주위 검색 실패!")
										}
									}

									// 지도에 마커를 표시하는 함수입니다
									function displayMarker(place) {
										// 마커를 생성하고 지도에 표시합니다

										var imageSrc = 'https://cdn3.iconfinder.com/data/icons/pin-4/100/pin_10-512.png', // 마커이미지의 주소입니다    
										imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
										imageOption = {
											offset : new kakao.maps.Point(27,
													69)
										}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

										// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
										var markerImage = new kakao.maps.MarkerImage(
												imageSrc, imageSize,
												imageOption);

										var marker2 = new kakao.maps.Marker({
											map : map,
											position : new kakao.maps.LatLng(
													place.y, place.x),
											image : markerImage
										});

										// 마커에 클릭이벤트를 등록합니다
										kakao.maps.event
												.addListener(
														marker2,
														'click',
														function() {
															// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다

															var content = '<div class="placeinfo">'
																	+ '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">'
																	+ place.place_name
																	+ '</a>';

															if (place.road_address_name) {
																content += '    <span title="' + place.road_address_name + '">'
																		+ place.road_address_name
																		+ '</span>'
																		+ '  <span class="jibun" title="' + place.address_name + '">(지번 : '
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

									// 인포윈도우를 생성합니다
									var infowindow = new kakao.maps.InfoWindow(
											{
												content : iwContent
											});

									// 마커에 마우스오버 이벤트를 등록합니다
									kakao.maps.event.addListener(markerCenter,
											'mouseover', function() {
												// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
												infowindow.open(map,
														markerCenter);
											});

									// 마커에 마우스아웃 이벤트를 등록합니다
									kakao.maps.event.addListener(markerCenter,
											'mouseout', function() {
												// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
												infowindow.close();
											});

									// markerCenter 에 대한 주소 상세
									searchDetailAddrFromCoords(
											coords,
											function(result, status) {
												if (status === kakao.maps.services.Status.OK) {
													var detailAddr = !!result[0].road_address ? '<div>도로명주소 : '
															+ result[0].road_address.address_name
															+ '</div>'
															: '';
													detailAddr += '<div>지번 주소 : '
															+ result[0].address.address_name
															+ '</div>';

													var content = '<div class="bAddr">'
															+ '<span class="title">법정동 주소정보</span>'
															+ detailAddr
															+ '</div>';

													// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
													infowindow
															.setContent(content);
													/* infowindow
															.open(map, marker); */
												}
											});

								} else {
									alert("검색 실패!");
								}
							});

		}

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var markerCenter = new kakao.maps.LatLng(33.450701, 126.570667);

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption);

		var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
		imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
		imageOption = {
			offset : new kakao.maps.Point(27, 69)
		}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
				imageOption);

		// 마커를 생성합니다
		markerCenter = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(33.450701, 126.570667),
			image : markerImage
		// 마커이미지 설정 
		});

		var markers = [];

		var tmpData = [];

		// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		function addMarker(position) {

			// 마커를 생성합니다

			var marker = new kakao.maps.Marker({
				position : position
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);

			// 생성된 마커를 배열에 추가합니다
			markers.push(marker);
		}

		// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
		function setMarkers(map) {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}

		// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
		kakao.maps.event.addListener(map, 'idle', function() {
			searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
			// 좌표로 행정동 주소 정보를 요청합니다
			geocoder.coord2RegionCode(coords.getLng(), coords.getLat(),
					callback);
		}

		function searchDetailAddrFromCoords(coords, callback) {
			// 좌표로 법정동 상세 주소 정보를 요청합니다
			geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
		function displayCenterInfo(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var infoDiv = document.getElementById('centerAddr');

				for (var i = 0; i < result.length; i++) {
					// 행정동의 region_type 값은 'H' 이므로
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