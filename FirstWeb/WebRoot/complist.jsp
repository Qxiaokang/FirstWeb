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
<base href="<%=basePath%>">

<title>购买商品</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/style.css">
<style type="text/css">
#t2 {
	background-color: green;
	height: 40px;
	border: 2px solid blue;
	padding-left: 20px;
	font-size: 15px;
}

tr.title {
	color: white;
	text-align: center;
	font-size: 20px;
	background-color: blue;
	height: 40px;
}

body {
	background-image: url("img/systembg1.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: 100%;
}

.tableborder {
	text-align: center;
	border: 2px solid blue;
}

.line {
	width: 100%;
	height: 1px;
	background-color: green;
}

.bt {
	text-align: right;
	margin-right: 20px;
}

input.buy {
	background-color: blue;
	color: white;
	height: 40px;
}

input[type=button]:HOVER {
	background-color: green;
	color: red;
}
</style>
<script type="text/javascript" src="js/prototype-1.7.js"></script>
<script type="text/javascript">
	var array=new Array();
	//add computer
	function addCt(index,max) {
	    var  b=false;
	    var isi=-1;
		window.console.log("index"+index+"  max:"+max);
		var num=$F("comnum"+index);
		if(num==max){
			$("comnum"+index).value=num;
		}else{
		var n=parseInt(num)+1;
		window.console.log("num"+n);
		$("comnum"+index).value=n;
			if(array.length==0){
			window.console.log("arraylength==0");
			array[0]=[index,n,";"];
			}else{
			  for(var i=0; i<array.length; i++){
				window.console.log("i:"+i+"array:"+array[i]);
				if(array[i][0]==index){
					b=true;
					isi=i;
					break;
				}
			}
			if(b){
				array[isi]=[index,n,";"];}
			else{
				array[array.length]=[index,n,";"];
			    }
		}
	}}
	//delete computer
	function delCt(index) {
		var num=$F("comnum"+index);
		if(num==0){
			$("comnum").value=0;
		}else{
			var n=parseInt(num)-1;
			$("comnum"+index).value=n;
			if(array.length==0){
			window.console.log("arraylength==0");
			array[0]=[index,n,";"];
			}else{
			  for(var i=0; i<array.length; i++){
				window.console.log("i:"+i+"array:"+array[i]);
				if(array[i][0]==index){
					b=true;
					isi=i;
					break;
				}
			}
			if(b){
				array[isi]=[index,n,";"];}
			else{
				array[array.length]=[index,n,";"];
			    }
		}
		}
	}
	function addBuy() {
		window.console.log("messages:"+array.length);
		location.href="buy.do?array="+array;
	}
</script>
</head>
<body>
	<table cellspacing="6" cellpadding="2" width="100%" border="0">
		<tr>
			<td>
				<table width="100%" id="t2">
					<tr>
						<td><a href="#">主页</a>&nbsp;|&nbsp;<a href="#">笔记本订购</a>&nbsp;|&nbsp;商品列表</td>
					</tr>
				</table> <br>
				<table class="tableborder" cellpadding="0" cellspacing="0"
					width="100%">
					<tr class="title">
						<td class="bg1" width="10%"><b>型号</b></td>
						<td class="bg1" width="30%"><b>产品图片</b></td>
						<td class="bg1" width="20%"><b>产品说明</b></td>
						<td class="bg1" width="10%"><b>产品报价</b></td>
						<td class="bg1" width="10%"><b>库存</b></td>
						<td class="bg1" width="20%"><b>-</b></td>
					</tr>
					<%
						List<Computer> list = (List<Computer>) request
								.getAttribute("computers");
						for (int i = 0; i < list.size(); i++) {
							Computer computer = list.get(i);
					%>
					<tr class="trcomp">
						<td><%=computer.getModel()%></td>
						<td><img src="img/<%=computer.getPic()%>" width="200"
							height="150" /></td>
						<td><%=computer.getDescriptionString()%></td>
						<td><%=computer.getPrice()%></td>
						<td><%=computer.getRepertory()%></td>
						<td><input type="button" value="-"
							onclick="delCt(<%=computer.getId()%>)" /><input
							id="comnum<%=computer.getId()%>" type="text" readonly="readonly"
							size="1" value="<%=computer.getBuynum()%>"><input type="button" value="+"
							onclick="addCt(<%=computer.getId()%>,<%=computer.getRepertory()%>)">
						</td>
					</tr>
					<tr>
						<td colspan="6" class="line"></td>
					</tr>

					<%
						}
					%>
				</table>
			</td>
		</tr>
	</table>
	<div class="bt">
		<input class="buy" type="button" value="立即购买" title="点击进入下一步，确认购买信息！" /><input
			class="buy" title="加入购物车" type="button" value="加入购物车"
			style="margin-left: 10px;" onclick="addBuy()" />
	</div>
</body>
</html>
