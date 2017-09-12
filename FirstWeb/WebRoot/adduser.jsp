<%@page import="com.xk.bean.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
 	Object object=session.getAttribute("user");
 	if(object==null){
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
</style>
<script type="text/javascript" src="js/prototype-1.7.js"></script>
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
	var id;
	function removView() {
		$("move").style.backgroundColor = "red";
		//window.open("index.html", "index");
		id = setInterval(close(), "1000");
	}
	var i = 0;
	function close() {
		window.console.log("+1");
		var v = $F("move");
		document.getElementById("move").value = i++ + "";
		if (i == 10) {
			clearInterval(id);
		}
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
	window.onload = provinceChange;
	function getXMLHttpRequest(){
		var xhr=null;
		if((typeof XMLHttpRequest)!="undefined"){
			xhr=new XMLHttpRequest();
		}else {
			xhr=new ActiveXObject("Microsoft.XMLHttp");
		}
		return xhr;
	}
	function f1(){
	    var xhr=getXMLHttpRequest();
	    alert(xhr);
	}
</script>

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
<body>
	<canvas id="cav" style="display: none;"></canvas>
	<div style="text-align: right; margin-right: 10px;"><span>当前在线人数：</span><span><%=application.getAttribute("count")%></span></div>
	<div id="bd">
		<form action="first.do" method="post">
			<div class="con">
				<span class="sp">name</span><input type="text" name="name" />
			</div>
			<div class="con">
				<span class="sp">pwd</span><input type="password" name="pwd" />
			</div>
			<div class="con">
				<span class="sp">性别</span><input type="radio" name="sex">男<input
					type="radio" name="sex" />女
			</div>
			<div class="con">
				<span class="sp">地址</span><select name="select" id="sele"
					onchange="provinceChange()">
					<option value="bj">广东</option>
					<option value="sh">湖北</option>
					<option value="gz">湖南</option>
					<option value="sz">江苏</option>
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
					<span class="sp">照片</span>
					<input type="file" value="选择">
			</div>
			<div>
				<input type="submit" value="添加" /> <a href="list.do">查看</a> <a
					onclick="queryCity()">all</a> <input type="button" id="ad"
					onclick="add1()" value="add" />
			</div>
		</form>
		<input type="button" value="返回" id="move" onclick="removView()"
			style="left: 100px;" /> <a href="javascript;"
			onclick="getBrowser()">get Browser message</a>
		<a href="javascript;" onclick="f1()">get XMLHttpRequest</a>	
	</div>
	
</body>
</html>