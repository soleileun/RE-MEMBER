<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="icon" type="image/png" href="assets/img/favicon.ico">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>Light Bootstrap Dashboard by Creative Tim</title>

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />




<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="assets/css/animate.min.css" rel="stylesheet" />

<!--  Light Bootstrap Table core CSS    -->
<link href="assets/css/light-bootstrap-dashboard.css?v=1.4.0"
	rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="assets/css/demo.css" rel="stylesheet" />


<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300'
	rel='stylesheet' type='text/css'>
<link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet" />

</head>
<body>

	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">이슈 추가하기</h3>
					<button type="button" class="close" data-dismiss="modal">x</button>

				</div>
				<div class="modal-body">
					<div class="col-md-12">
						<div class="card">
							<div class="header">
								<h4 class="title">이슈 정보 등록</h4>
							</div>
							<div class="content">
								<form>
									<div class="row">
										<div class="col-md-7">
											<div class="form-group">
												<label>프로젝트 명</label> <input type="text"
													class="form-control" disabled placeholder="Company"
													value="Creative Code Inc.">
											</div>
										</div>
										<div class="col-md-5">
											<div class="form-group">
												<label>등록자</label> <input type="text" class="form-control"
													disabled placeholder="Username" value="michael23">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>이슈 제목</label> <input type="text" class="form-control"
													placeholder="이슈 제목을 적으세요" value="">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>선제 작업</label> <input type="text" class="form-control"
													placeholder="등록하려는 이슈 전 선제되야 하는 작업을 고르시오" value="">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label>이슈 상태</label> <input type="text" class="form-control"
													placeholder="생성/진행/완료">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>이슈 종류</label> <input type="text" class="form-control"
													placeholder="에픽/스토리/태스크">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>이슈 중요도</label> <input type="text"
													class="form-control" placeholder="상/중/하">
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label>이슈 내용</label>
												<textarea rows="5" class="form-control"
													placeholder="이슈 내용을 적으세요" value="Mike"></textarea>
											</div>
										</div>
									</div>

									<button type="submit" class="btn btn-info btn-fill pull-right">Update
										Issue</button>
									<div class="clearfix"></div>
								</form>
							</div>
						</div>
					</div>



				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<div class="wrapper">
		<div class="sidebar" data-color="purple"
			data-image="assets/img/sidebar-5.jpg">


			<div class="sidebar-wrapper">
				<div class="logo">
					<a href="" class="simple-text"> Project Info </a>
				</div>

				<ul class="nav">
					<li class="active"><a href="/main"> <i class="pe-7s-graph"></i>
							<p>Issue Board</p>
					</a></li>

					<li><a href="teamInfo.html"> <i class="pe-7s-note2"></i>
							<p>Team Info</p>
					</a></li>
					<li><a href="maps.html"> <i class="pe-7s-map-marker"></i>
							<p>Maps</p>
					</a></li>

				</ul>
			</div>
		</div>

		<div class="main-panel">
			<nav class="navbar navbar-default navbar-fixed">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Issue Board</a>
				</div>
			</nav>


			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-5">
							<div class="card">

								<div class="header">
									<h4 class="title">이슈 처리 현황 그래프</h4>

								</div>
								<div class="content">

									<div class="ct-chart">

										<canvas id="myChart" style="height: 100%; width: 100%;"></canvas>



									</div>
									<div class="footer">
										<hr>
										<div class="stats">
											<i class="fa fa-history"></i> Updated 3 minutes ago
										</div>
									</div>
								</div>


							</div>
						</div>



						<div class="col-md-7">
							<div class="card">
								<div class="header">
									<h4 class="title">최근 프로젝트 이슈</h4>
									<p class="category">날짜순</p>
								</div>
								<div class="content">
									<div class="table-full-width">
										<table class="table">
											<thead>
												<th>날짜</th>
												<th>제목</th>
												<th class="td-actions text-right">상태</th>
											</thead>
											<tbody>
												<tr>
													<td>2020-08-03 21:09</td>
													<td>이슈 테스팅1</td>
													<td class="td-actions text-right">
														<div style="color: red">진행중</div>
													</td>
												</tr>
												<tr>
													<td>2020-08-01 20:02</td>
													<td>이슈 테스팅2</td>
													<td class="td-actions text-right">
														<div style="color: yellowgreen">완료</div>
													</td>
												</tr>

											</tbody>
										</table>
									</div>

									<div class="footer">
										<hr>
										<div class="stats">
											<i class="fa fa-history"></i> Updated 3 minutes ago
										</div>
									</div>
								</div>
							</div>
						</div>



						<div class="col-md-4">
							<div class="card">
								<div class="header">
									<h4 class="title" style="color: skyblue">생성된 이슈</h4>
									<p class="category">날짜순</p>
									<p align="right">
										<button type="button" rel="tooltip" title="Add"
											class="btn btn-info btn-simple btn-xs" data-toggle="modal"
											data-target="#myModal">
											<i class="fa fa-edit"></i>
										</button>
									</p>
								</div>
								<div class="content">
									<div class="table-full-width">
										<table class="table">
											<tbody>
												<tr>
													<td>2020-08-01 10:01</td>
													<td>생성된 이슈 테스팅1</td>
													<td class="td-actions text-right">
														<button type="button" rel="tooltip" title="Remove"
															class="btn btn-danger btn-simple btn-xs">
															<i class="fa fa-times"></i>
														</button>
													</td>
												</tr>
												<tr>
													<td>2020-08-01 20:09</td>
													<td>생성된 이슈 테스팅2</td>
													<td class="td-actions text-right">
														<button type="button" rel="tooltip" title="Remove"
															class="btn btn-danger btn-simple btn-xs">
															<i class="fa fa-times"></i>
														</button>
													</td>
												</tr>

											</tbody>
										</table>
									</div>

									<div class="footer">
										<hr>
										<div class="stats">
											<i class="fa fa-history"></i> Updated 3 minutes ago
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="card ">
								<div class="header">
									<h4 class="title" style="color: red">진행중인 이슈</h4>
									<p class="category">날짜순</p>
									<p align="right">
										<button type="button" rel="tooltip" title="Add"
											data-toggle="modal" data-target="#myModal"
											class="btn btn-info btn-simple btn-xs">
											<i class="fa fa-edit"></i>
										</button>
									</p>
								</div>
								<div class="content">
									<div class="table-full-width">
										<table class="table" id="ongoingTable">
											<tbody class="t_sortable">
												<tr>
													<td style="width: 40%;">2020-08-03 22:10</td>
													<td
														style="width: 100%; font-size: 120%; overflow: hidden; text-overflow: ellipsis;">진행중
														이슈1</td>
													<td class="td-actions text-right">
														<button type="button" rel="tooltip" title="Remove"
															class="btn btn-danger btn-simple btn-xs">
															<i class="fa fa-times"></i>
														</button>
													</td>
												</tr>

											</tbody>
										</table>
									</div>

									<div class="footer">
										<hr>
										<div class="stats">
											<i class="fa fa-history"></i> Updated 3 minutes ago
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="card ">
								<div class="header">
									<h4 class="title" style="color: yellowgreen">완료된 이슈</h4>
									<p class="category">날짜순</p>
									<p align="right">
										<button type="button" rel="tooltip" title="Add"
											data-toggle="modal" data-target="#myModal"
											class="btn btn-info btn-simple btn-xs">
											<i class="fa fa-edit"></i>
										</button>
									</p>
								</div>
								<div class="content">
									<div class="table-full-width">
										<table class="table" id="doneTable">
											<tbody class="t_sortable">
												<tr>
													<td>2020-08-03 23:02</td>
													<td>완료 이슈 테스팅1</td>
													<td class="td-actions text-right">
														<button type="button" rel="tooltip" title="Remove"
															class="btn btn-danger btn-simple btn-xs">
															<i class="fa fa-times"></i>
														</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>

									<div class="footer">
										<hr>
										<div class="stats">
											<i class="fa fa-history"></i> Updated 3 minutes ago
										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div>


			<footer class="footer">
				<div class="container-fluid">
					<nav class="pull-left">
						<ul>
							<li><a href="#"> Home </a></li>
							<li><a href="#"> Company </a></li>
							<li><a href="#"> Portfolio </a></li>
							<li><a href="#"> Blog </a></li>
						</ul>
					</nav>
					<p class="copyright pull-right">
						&copy;
						<script>
							document.write(new Date().getFullYear())
						</script>
						<a href="http://www.creative-tim.com">Creative Tim</a>, made with
						love for a better web
					</p>
				</div>
			</footer>

		</div>
	</div>


</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>


<script type="text/javascript">
	$(document).ready(
			function() {

				demo.initChartist();

				// charts 관련 메소드

				var data = [];

				var colors = [ 'skyblue', 'red', 'yellowgreen', '#c3e6cb',
						'#dc3545', '#6c757d' ];

				var donutOptions = {
					cutoutPercentage : 30,
					legend : {
						position : 'bottom',
						padding : 5,
						labels : {
							pointStyle : 'circle',
							usePointStyle : true
						}
					}
				};
				var chDonutData1 = {
					labels : [ '생성된 이슈', '진행중인 이슈', '완료된 이슈' ],
					datasets : [ {
						backgroundColor : colors.slice(0, 3),
						borderWidth : 0,
						data : [ 30, 71, 11 ]
					} ]
				};
				var chDonut1 = document.getElementById("myChart");
				if (chDonut1) {
					new Chart(chDonut1, {
						type : 'pie',
						data : chDonutData1,
						options : donutOptions
					});
				}

			});

	// ajax 로 불러오기 메소드

	function loadIssueList() {
		$
				.ajax({
					type : "get",
					url : "http://localhost:8080/api/project",
					success : function(result) {
						var htmlTxt = '<table class="table table-bordered table-condensed"><tr><th>제목</th><th>이름</th><th>내용</th></tr>';
						$("#projectList").empty();
						$.each(result, function(idx, item) {
							htmlTxt += '<tr>' + '<td>' + item.pjtName + '</td>'
									+ '<td>' + item.pid + '</td>' + '<td>'
									+ item.pjtContent + '</td></tr>';
						});
						htmlTxt += '</table>'
						$('#projectList').html(htmlTxt);
					},
					error : function() {
						alert("서버 이상으로 목록 조회에 실패하였습니다. 잠시후 다시 시도해주세요.");

					}
				});

	}

	// url에서 값 읽어오기

	var getUrlParameter = function getUrlParameter(sParam) {
		var sPageURL = window.location.search.substring(1), sURLVariables = sPageURL
				.split('&'), sParameterName, i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true
						: decodeURIComponent(sParameterName[1]);
			}
		}
	};

	//loadProjectList();
	var pid = getUrlParameter('pid');
	var userId = getUrlParameter('userId');
	/* alert(pid); */
</script>

</html>
