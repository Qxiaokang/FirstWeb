<%@page import="com.xk.bean.ComputerDao"%>
<%@page import="com.xk.bean.Computer"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>主 页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(e) {
		var counter = 0;
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function() {
				window.history.pushState('forward', null, '#');
				window.history.forward(1);
				showConfirm();
			});
		}

		window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
		window.history.forward(1);
	});
	/* jQuery(document).ready(function($) {

	 if (window.history && window.history.pushState) {

	 $(window).on('popstate', function() {
	 var hashLocation = location.hash;
	 var hashSplit = hashLocation.split("#!/");
	 var hashName = hashSplit[1];

	 if (hashName !== '') {
	 var hash = window.location.hash;
	 if (hash === '') {
	 window.history.back(-1);
	 }
	 }
	 });

	 window.history.pushState('forward', null, './#forward');
	 }

	 }); */
	 function showConfirm(){
	 	var con=confirm("确认退出系统？");
	 	if(con==true){
	 		window.location.href="logina.html";
	 	}else{
	 		
	 	}
	 }
</script>
<%
	Object object = session.getAttribute("user");
	if (object == null) {
%>

<script type="text/javascript">
	alert("请先登录！");
	window.location = "logina.html";
</script>

<%
	//response.sendRedirect("logina.html");
	}
%>

<style type="text/css">
body {
	background-image: url("img/systembg1.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100%;
}

* {
	margin: 0;
	padding: 0
}

@font-face {
	font-family: 'icon';
	src: url("font/icons.eot"), url("font/icons.svg"), url("font/icons.ttf"),
		url("font/icons.woff");
}

.sb-container {
	position: relative;
	width: 50%;
	margin: 10% auto;
	left: 20%;
	text-align: center;
}

.sb-container div {
	width: 130px;
	height: 400px;
	position: absolute;
	top: 0;
	left: 0;
	background: #ffffff;
	cursor: pointer;
	-webkit-transform-origin: 25% 90%;
	-moz-transform-origin: 25% 90%;
	-o-transform-origin: 25% 90%;
	-ms-transform-origin: 25% 90%;
	transform-origin: 25% 90%;
	-webkit-transition: -webkit-transform .5s ease;
	-moz-transition: -moz-transform 1.5s ease;
	-o-transition: -o-transform .5s ease;
	transition: transform .5s ease;
}

.sb-container div:nth-child(1) {
	background-color: #EA2A29;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, .1), 2px 2px 1px
		rgba(0, 0, 0, .1), inset 0 3px 0 rgba(255, 255, 255, 0.2)
}

.sb-container div:nth-child(2) {
	background-color: #f16729;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 2px 2px 1px
		rgba(0, 0, 0, 0.1), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(3) {
	background-color: #f89322;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 3px 3px 2px
		rgba(0, 0, 0, 0.2), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(4) {
	background-color: #ffcf14;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 4px 4px 4px
		rgba(0, 0, 0, 0.2), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(5) {
	background-color: #ffea0d;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 5px 5px 6px
		rgba(0, 0, 0, 0.3), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(6) {
	background-color: #87b11d;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 6px 6px 8px
		rgba(0, 0, 0, 0.3), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(7) {
	background-color: #008253;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 7px 7px 10px
		rgba(0, 0, 0, 0.4), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(8) {
	background-color: #3277b5;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 8px 8px 12px
		rgba(0, 0, 0, 0.4), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(9) {
	background-color: #4c549f;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 9px 9px 14px
		rgba(0, 0, 0, 0.4), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(10) {
	background-color: #764394;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 10px 10px 16px
		rgba(0, 0, 0, 0.4), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:nth-child(11) {
	background-color: #ca0d86;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.1), 11px 11px 18px
		rgba(0, 0, 0, 0.4), inset 0 3px 0 rgba(255, 255, 255, 0.2);
}

.sb-container div:last-child {
	background: #645b5c url(img/cover11.jpg) repeat center center;
	box-shadow: -1px -1px 3px rgba(0, 0, 0, 0.2), 12px 12px 20px
		rgba(0, 0, 0, 0.6), inset 2px 2px 0 rgba(255, 255, 255, 0.1);
	color: purple;
}

.sb-container div:last-child:after {
	content: '';
	position: absolute;
	bottom: 15px;
	left: 15px;
	width: 20px;
	height: 20px;
	border-radius: 50%;
	background: #dddddd;
	background: -moz-linear-gradient(-45deg, #dddddd 0%, #58535e 48%, #889396 100%);
	background: -webkit-gradient(linear, left top, right bottom, color-stop(0%, #dddddd),
		color-stop(48%, #58535e), color-stop(100%, #889396));
	background: -webkit-linear-gradient(-45deg, #dddddd 0%, #58535e 48%, #889396 100%);
	background: -o-linear-gradient(-45deg, #dddddd 0%, #58535e 48%, #889396 100%);
	background: -ms-linear-gradient(-45deg, #dddddd 0%, #58535e 48%, #889396 100%);
	background: linear-gradient(135deg, #dddddd 0%, #58535e 48%, #889396 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#dddddd',
		endColorstr='#889396', GradientType=1);
	box-shadow: -1px -1px 1px rgba(0, 0, 0, 0.5), 1px 1px 1px
		rgba(255, 255, 255, 0.1);
}

.sb-container div:last-child h5 {
	font-size: 40px;
	white-space: nowrap;
	position: absolute;
	top: 0;
	left: 0;
	line-height: 40px;
	color: purple;
	text-shadow: -1px -1px 1px rgba(255, 255, 255, 0.1);
	-webkit-transform: rotate(-90deg) translateX(-157%) translateY(73px);
	-o-transform: rotate(-90deg) translateX(-157%) translateY(73px);
	-moz-transform: rotate(-90deg) translateX(-157%) translateY(73px);
	-ms-transform: rotate(-90deg) translateX(-157%) translateY(73px);
	transform: rotate(-90deg) translateX(-157%) translateY(73px);
	-webkit-transform-origin: 0 0;
	-moz-transform-origin: 0 0;
	-o-transform-origin: 0 0;
	-ms-transform-origin: 0 0;
	transform-origin: 0 0;
	-webkit-touch-callout: none; /*不允许选中文本*/
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

div .sb-icon:before {
	font-family: 'icon';
	font-style: normal;
	font-weight: normal;
	font-size: 60px;
	display: block;
	text-decoration: inherit;
	text-align: center;
	text-shadow: 1px 1px 1px rgba(127, 127, 127, 0.3), 0 0 1px #000;
	line-height: 64px;
	width: 100%;
	color: #000;
}

.icon-cog:before {
	content: '\41';
} /* 'A' */
.icon-flight:before {
	content: '\42';
} /* 'B' */
.icon-eye:before {
	content: '\43';
} /* 'C' */
.icon-install:before {
	content: '\44';
} /* 'D' */
.icon-bag:before {
	content: '\45';
} /* 'E' */
.icon-globe:before {
	content: '\46';
} /* 'F' */
.icon-picture:before {
	content: '\47';
} /* 'G' */
.icon-video:before {
	content: '\48';
} /* 'H' */
.icon-download:before {
	content: '\49';
} /* 'I' */
.icon-mobile:before {
	content: '\50';
} /* 'J' */
.icon-camera:before {
	content: '\51';
} /* 'K' */
</style>
<script type="text/javascript">
	function index(current, obj) { //获取元素索引值
		for (var i = 0; i < obj.length; i++) {
			if (current == obj[i]) {
				return i;
			}
		}
	}
	window.onload = function() {
		var container = document.getElementById('sb-container');
		var cards = container.getElementsByTagName('div');
		var n = cards.length;
		var deg = 180 / n;
		var click = 0;
		var idx = 0;
		var lastindex = -1;
		for (var i = 0; i < n; i++) { //页面加载完成展开卡片
			var tdeg = (i <= n / 2) ? -(n / 2 - i) * deg : (i - n / 2) * deg;
			cards[i].style.webkitTransform = "rotate(" + tdeg + "deg)";
			cards[i].style.msTransform = "rotate(" + tdeg + "deg)";
			cards[i].style.MozTransform = "rotate(" + tdeg + "deg)";
			cards[i].style.OTransform = "rotate(" + tdeg + "deg)";
			cards[i].style.transform = "rotate(" + tdeg + "deg)";
		}
		for (var i = 0; i < n - 1; i++) {
			cards[i].onclick = function() { //卡片点击事件
				change(index(this, cards)); //更改卡片位置函数
			};
		}
		cards[n - 1].onclick = function() {
			click++;
			for (var i = 0; i < n; i++) {
				if (click % 2)//判断点击奇数次与点击偶数次
				{
					var tdeg = (i <= n / 2) ? -(n / 2 - i) * deg : (i - n / 2)
							* deg;
				} else {
					var tdeg = 0;
				}
				cards[i].style.webkitTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.msTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.MozTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.OTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.transform = "rotate(" + tdeg + "deg)";

			}

		};
		function change(index) {
			idx++;
			for (var i = 0; i <= index; i++) {
				var tdeg = -(index - i) * deg;
				cards[i].style.webkitTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.msTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.MozTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.OTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.transform = "rotate(" + tdeg + "deg)";
			}
			for (var i = index + 1; i < n; i++) {//该卡片之后的卡片多旋转几度，便于该卡片文字的展示
				var tdeg = (i - index) * deg + 5;
				cards[i].style.webkitTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.msTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.MozTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.OTransform = "rotate(" + tdeg + "deg)";
				cards[i].style.transform = "rotate(" + tdeg + "deg)";
			}
			//连续点击再次的事件
			window.console.log("index:" + index + "   lastindex:" + lastindex);
			if (lastindex == index && idx % 2 == 0) {
				idx = 0;
				if (index == 1) {
					window.location.href = "adduser.jsp";
				} else if (index == 2) {

					window.location.href = "complist.do";
				}
			}
			lastindex = index;
		}
	};
</script>
</head>
<body>

	<div id="sb-container" class="sb-container">
		<div>
			<span class="sb-icon icon-cog"></span>
			<h4>
				<span>设置</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-flight"></span>
			<h4>
				<span>管理</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-eye"></span>
			<h4>
				<span>浏览</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-install"></span>
			<h4>
				<span>软件</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-bag"></span>
			<h4>
				<span>产品</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-globe"></span>
			<h4>
				<span>选项</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-picture"></span>
			<h4>
				<span>图片</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-video"></span>
			<h4>
				<span>视频</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-download"></span>
			<h4>
				<span>下载</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-mobile"></span>
			<h4>
				<span>主题</span>
			</h4>
		</div>
		<div>
			<span class="sb-icon icon-camera"></span>
			<h4>
				<span>数码</span>
			</h4>
		</div>
		<div>
			<h4>
				<span>点击展开/折叠</span>
			</h4>
			<h5>
				<span> &hearts; COLORFUL</span>
			</h5>
		</div>
	</div>
</body>
</html>
