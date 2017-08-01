<%@page language="java" pageEncoding="utf-8" %>
<!doctype html>
<html lang="en">

	<head>
		<title>后台管理测试</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		<!-- VENDOR CSS -->
		<link rel="stylesheet" href="../static/assets/vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="../static/assets/vendor/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../static/assets/vendor/linearicons/style.css">
		<link rel="stylesheet" href="../static/assets/vendor/chartist/css/chartist-custom.css">
		<!-- MAIN CSS -->
		<link rel="stylesheet" href="../static/assets/css/main.css">
		<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
		<link rel="stylesheet" href="../static/assets/css/demo.css">
		
		<!--easyui-->
		<link rel="stylesheet" href="../static/css/easyui/themes/material/easyui.css" />
		<link rel="stylesheet" href="../static/css/easyui/themes/icon.css" />

		<!-- GOOGLE FONTS -->
		<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
		<!-- ICONS -->
		<link rel="apple-touch-icon" sizes="76x76" href="../static/assets/img/apple-icon.png">
		<!--logo图标-->
		<link rel="icon" type="image/png" sizes="96x96" href="../static/assets/img/favicon.png">
		
		
		<script type="text/javascript" src="../static/js/jquery.min.js"></script>
		<script type="text/javascript" src="../static/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="../static/common/page.js" ></script>
		<!--model-->
		<script type="text/javascript" src="../static/model/role.js" ></script>
		<script type="text/javascript" src="../static/model/menu.js" ></script>
		<script type="text/javascript" src="../static/model/user.js" ></script>
	</head>

	<body>
		<!-- WRAPPER -->
		<div id="wrapper">
			<!-- NAVBAR -->
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="brand">
					<a href="index.html"><img src="../static/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
				</div>
				<div class="container-fluid">
					<div class="navbar-btn">
						<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
					</div>

					<!--			<div class="navbar-btn navbar-btn-right"></div>-->
					<div id="navbar-menu">
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
									<i class="lnr lnr-alarm"></i>
									<span class="badge bg-danger">5</span>
								</a>
								<ul class="dropdown-menu notifications">
									<li>
										<a href="#" class="notification-item"><span class="dot bg-warning"></span>System space is almost full</a>
									</li>
									<li>
										<a href="#" class="notification-item"><span class="dot bg-danger"></span>You have 9 unfinished tasks</a>
									</li>
									<li>
										<a href="#" class="notification-item"><span class="dot bg-success"></span>Monthly report is available</a>
									</li>
									<li>
										<a href="#" class="notification-item"><span class="dot bg-warning"></span>Weekly meeting in 1 hour</a>
									</li>
									<li>
										<a href="#" class="notification-item"><span class="dot bg-success"></span>Your request has been approved</a>
									</li>
									<li>
										<a href="#" class="more">See all notifications</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="lnr lnr-question-circle"></i> <span>Help</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
								<ul class="dropdown-menu">
									<li>
										<a href="#">Basic Use</a>
									</li>
									<li>
										<a href="#">Working With Data</a>
									</li>
									<li>
										<a href="#">Security</a>
									</li>
									<li>
										<a href="#">Troubleshooting</a>
									</li>
								</ul>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="../static/assets/img/user.png" class="img-circle" alt="Avatar"> <span id="username">Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
								<ul class="dropdown-menu">
									<li>
										<a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a>
									</li>
									<li>
										<a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a>
									</li>
									<li>
										<a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a>
									</li>
									<li>
										<a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a>
									</li>
								</ul>
							</li>
							<!-- <li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
						</ul>
					</div>
				</div>
			</nav>
			<!-- END NAVBAR -->
			<!-- LEFT SIDEBAR -->
			<div id="sidebar-nav" class="sidebar">
				<div class="sidebar-scroll">
					<nav>
						<ul id="menutree" class="nav">
						
						</ul>
					</nav>
				</div>
			</div>
			<!-- END LEFT SIDEBAR -->
			<!-- MAIN -->
			<div class="main">
				<!-- MAIN CONTENT -->
				<div class="main-content .container-fluid">
				<div id="tt" class="easyui-tabs " style="width:98%;height:100%;">
					<div title="首页" style="padding:20px;display:none;">
						tab1
					</div>
				</div>
				</div>
				<!-- END MAIN CONTENT -->
			</div>
			<!-- END MAIN -->
			<div class="clearfix"></div>
			<footer>
				<div class="container-fluid">
					<p class="copyright">&copy; 2017
						<a href="#" target="_blank">Theme I Need</a>. All Rights Reserved. More Templates
						<a href="#" target="_blank" title="测试界面">测试界面</a> - Collect from
						<a href="#" title="测试界面" target="_blank">测试界面</a>
					</p>
				</div>
			</footer>
		</div>
		<!-- END WRAPPER -->
		<!-- Javascript -->
		<script src="../static/assets/vendor/jquery/jquery.min.js"></script>
		<script src="../static/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
		<script src="../static/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
		<script src="../static/assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
		<script src="../static/assets/vendor/chartist/js/chartist.min.js"></script>
		<script src="../static/assets/scripts/klorofil-common.js"></script>
		<script type="text/javascript" src="../static/js/main-menu.js" ></script>
		<!--easyui-->
		<script type="text/javascript" src="../static/js/jquery.easyui.min.js" ></script>		

		
		<script>
			var username = '${user.name}'
			$('#username').html(username);
			//遍历生成菜单树
			$(function() {
				$.get('../menu/getmenu', {}, function(data) {
					var _html = '';
					for(var i = 0; i < data.length; i++) {
						//生成一级菜单
						var m = data[i];
						_html += getMenuHtml(m);
					}
					$('#menutree').html(_html);
					$('.tablink').click(function(){
						var _link = $(this).attr('url');
						if(_link==null || _link == "null" || _link == ""){
							alert('没有配置链接');
							return false;
						}
						//$('.main .container-fluid').load(_link+'#contant');
										var config = {
						url: _link,
						title: $(this).text()
					};
					addTabs(config);
					});
				})				
			});
			
			function addTabs(config) {
			var b = $('#tt').tabs('exists', config.title);
			if(b) {
				$('#tt').tabs('select', config.title);
				return;
			}
			var _content = $('<div>').load(config.url);
			$('#tt').tabs('add', {
				title: config.title,
				content: _content,
				closable: true,
				tools: [{
					iconCls: 'icon-mini-refresh',
					handler: function() {
						alert('refresh');
					}
				}]
			});
		}

		</script>
	</body>

</html>