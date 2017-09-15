<%@page import="com.xk.bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用 户 注 册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="css/style.css">
<%
	Object object = session.getAttribute("user");
	if (object == null) {
		response.sendRedirect("logina.html");
	}
%>
</head>
<style type="text/css">
<!--
-->
body {
	background-image: url("img/systembg1.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100%;
}

#bk {
	text-decoration: blink;
}

select {
	width: 50px;
	text-align: center;
}

.sp {
	display: -moz-inline-box;
	display: inline-block;
	width: 100px;
	text-align: center;
	color: white;
	margin-right: 10px;
}

.con {
	width: 500px;
	height: 60px;
	text-align: left;
}

#d1 {
	width: 200px;
	height: 100px;
	background-color: gray;
	display: none;
}

#d2 {
	width: 100px;
	height: 100px;
	background-color: green;
	position: relative;
}
</style>
<script type="text/javascript" src="js/prototype-1.7.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	// 说明：获取鼠标位置
	// 整理：http://www.codebit.cn
	// 来源：http://www.webreference.com
	function mousePosition(ev) {
		if (ev.pageX || ev.pageY) {
			return {
				x : ev.pageX,
				y : ev.pageY
			};
		}
		return {
			x : ev.clientX + document.body.scrollLeft
					- document.body.clientLeft,
			y : ev.clientY + document.body.scrollTop - document.body.clientTop
		};
	}
	document.onmousemove = mouseMove;
	function mouseMove(ev) {
		ev = ev || window.event;
		var mousePos = mousePosition(ev);
		window.console.log("X:" + mousePos.x + "  Y:" + mousePos.y);
		//document.getElementById('mouseXPosition').value = mousePos.x;
		//document.getElementById('mouseYPosition').value = mousePos.y;
		//cav(mousePos.x,mousePos.y);
	}

	function cav(x, y) {
		var w = screen.width;
		var h = screen.height;
		var cav = document.getElementById("cav");
		cav.setAttribute("width", w);
		cav.setAttribute("height", h);
		var c = cav.getContext("2d");
		c.clearRect(0, 0, cav.width, cav.height);
		c.strokeStyle = "red";
		c.beginPath();
		c.arc(x, y, 10, 0, 2 * Math.PI);
		c.stroke();
	}
</script>
<script type="text/javascript">
	
	//add div
	function add() {
		var ad = document.getElementById("ad");
		var addiv = document.createElement("div");
		addiv.innerHTML = "add  div";

		document.getElementById("bd").appendChild(addiv);
	}
	//add div
	function add1() {
		var ad = $("ad");
		var addiv = document.createElement("div");
		addiv.innerHTML = "new div";
		$("bd").appendChild(addiv);
	}
	//添加选项
	function queryCity() {
		var opt = $("sele").options;
		for (i = 0; i < opt.length; i++) {
			//alert("text:"+opt[i].text+"  value:"+opt[i].value);
		}
		var op = new Option("武汉", "wh");
		$("sele").options[$("sele").length] = op;
	}
	//地址下拉框
	function provinceChange() {
		var arry = new Array();
		arry[0] = [ new Option("广州", "gz"), new Option("深圳", "gz"),
				new Option("东莞", "dg") ];
		arry[1] = [ new Option("武汉", "wh"), new Option("荆州", "jz"),
				new Option("襄阳", "xy") ];
		arry[2] = [ new Option("岳阳", "yy"), new Option("衡阳", "hy"),
				new Option("邵阳", "sy") ];
		arry[3] = [ new Option("苏州", "sz"), new Option("常州", "cz"),
				new Option("昆山", "ks") ];

		var index = $("sele").selectedIndex;
		$("sc").innerHTML = "";
		for (var int = 0; int < arry[index].length; int++) {
			$("sc").options[int] = arry[index][int];
		}
	}
	//
	var i = 0;
	function removView() {
		$("move").style.backgroundColor = "red";
		//window.open("index.html", "index");
		var id = setInterval(function() {
			window.console.log("+1");
			var v = $F("move");
			document.getElementById("move").value = i++;
			if (i == 100) {
				clearInterval(id);
			}
		}, 1000);
	}
	//获取Browser message
	function getBrowser() {
		var nav = window.navigator;
		var msg = "Browser message:<br/>";
		for (propName in nav) {
			msg += "属性名：" + propName + "  属性值：" + nav[propName] + "<br/>";
		}
		document.write(msg);
	}
	//get XMLHttpRequest
	function getXMLHttpRequest() {
		var xhr = null;
		if ((typeof XMLHttpRequest) != "undefined") {
			xhr = new XMLHttpRequest();
		} else {
			xhr = new ActiveXObject("Microsoft.XMLHttp");
		}
		return xhr;
	}
	//changeCity from province
	function changeCity(v1) {
		var xhr = getXMLHttpRequest();
		xhr.open("get", "changeCity.do?province=" + v1, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					var txt = xhr.responseText;
					var arr = txt.split(";");
					$("sc").innerHTML = "";
					for (var int = 0; int < arr.length; int++) {
						var arr1 = arr[int];
						var arr2 = arr1.split(",");
						if (arr2.length == 2) {
							var op = new Option(arr2[1], arr2[0]);
							$("sc").options[int] = op;
						}
					}
				}
			}
		};
		xhr.send(null);
	}
	//jquery修改样式
	function changeJquery() {
		var $j = jQuery.noConflict();
		$j("#now").css("font-size", "30px").css("color", "red");
	}
	$(function() {
		var $j = jQuery.noConflict();
		$j("#now").click(function() {
			$j("#now").css("font-size", "30px").css("color", "red");
		});
	});
	//jquery animation
	$(function() {
		var $j = jQuery.noConflict();
		$j("#a1").toggle(function() {
			//$j("#d1").slideDown("slow");
			$j("#d1").fadeIn("slow", function() {
				alert("显示");
			});
		}, function() {
			//$j("#d1").slideUp("slow");
			$j("#d1").fadeOut();
		});
	});
	$(function() {
		var $j = jQuery.noConflict();
		$j("#d1").click(function() {
			$j("#d2").animate({
				"left" : "300px",
				"top" : "300px"
			}, 3000);
			$j("#d2").animate({
				"top" : "500"
			}, 2000).hide("slow");
		});
	});
	$(function() {
		var $q = jQuery.noConflict();
		$q("#d2").click(function() {
			var $obj = $q("#bd span");
			//alert($obj.length);
			$obj.each(function(index) {
				if (index == 0) {
					$q(this).css("color", "red");
				} else if (index == 1) {
					$q(this).css("color", "blue");
				}
			});
		});
	});

	window.onload = changeCity("gd");
</script>


<body>
	<canvas id="cav" style="display: none;"></canvas>
	<div style="text-align: right; margin-right: 10px;">
		<span id="now">当前在线人数：</span><span id="nown"><%=application.getAttribute("count")%></span>
	</div>
	<div id="bd">
		<form action="first.do" method="post">
			<div class="con" id="con">
				<span class="sp">name</span><input type="text" name="name" id="name" />
			</div>
			<div class="con">
				<span class="sp">pwd</span><input type="password" name="pwd" />
			</div>
			<div class="con">
				<span class="sp">性别</span><input type="radio" name="sex">男<input
					type="radio" name="sex" />女
			</div>
			<div class="con">
				<span class="sp">地址</span><select name="selectP" id="sele"
					onchange="changeCity(this.value)">
					<option value="gd">广东</option>
					<option value="hb">湖北</option>
					<option value="hn">湖南</option>
					<option value="js">江苏</option>
				</select><select id="sc" style="margin-left: 10px;"></select>
			</div>
			<div class="con">
				<span class="sp">验证码</span><input type="text" id="codetext"
					size="10" /> <img src="checkCode.do" id="img1"> <a
					href="javascript:;"
					onclick="$('img1').src='checkCode.do?'+Math.random();"
					style="font-size: 10px;">看不清，换一张？</a>
			</div>
			<div class="con">
				<span class="sp">照片</span> <input type="file" value="选择">
			</div>
			<div>
				<input type="button" id="btadd" value="添加" /> <a href="list.do">查看</a>
				<a onclick="queryCity()">all</a> <input type="button" id="ad"
					onclick="add1()" value="add" />
			</div>
		</form>
		<input type="button" value="返回" id="move" onclick="removView()"
			style="left: 100px;" /> <a href="javascript;" onclick="getBrowser()">get
			Browser message</a> <a href="javascript;" onclick="f1()">get
			XMLHttpRequest</a>
	</div>
	<a id="a1">animation</a>
	<div id="d1">very good</div>
	<div id="d2"></div>
</body>
</html>