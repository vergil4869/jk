<%@ page language="java" pageEncoding="UTF-8"%>
<!-- <hr> -->
<!-- 	<font color="blue"><b>引入CSS、JS区域</b></font> -->
<!-- <hr> -->

<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>

<!-- <hr> -->
<!-- 	<font color="blue"><b>按钮区域</b></font> -->
<!-- <hr> -->

<li id="view"><a href="#" onclick="formSubmit('toview.action','_self');this.blur();">查看</a></li>
<!-- this.blur() 事件, 是因为点击后会出现一个小虚框, 默认点击后小虚框仍然存在, 这个事件正是为了去除小虚框.
(而我的浏览器并没有出现小虚框)
可以同时写多个js函数 -->
<li id="new"><a href="#" onclick="formSubmit('toCreate.action','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('toUpdate.action','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('deleteById.action','_self');this.blur();">删除</a></li>
<li id="delete"><a href="#" onclick="formSubmit('delete.action','_self');this.blur();">删除N</a></li>
<li id="new"><a href="#" onclick="formSubmit('start.action','_self');this.blur();">启用</a></li>
<li id="new"><a href="#" onclick="formSubmit('stop.action','_self');this.blur();">停用</a></li>
 
</ul>
  </div>
</div>
</div>
</div>
   
<!-- 页面主体部分（列表等） -->  
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">

<!-- <hr> -->
<!-- 	<font color="blue"><b>标题</b></font> -->
<!-- <hr> -->

    生产厂家列表
  </div> 
  </div>
  </div>
  
<div>
	
<!-- <hr> -->
<!-- 	<font color="blue"><b>列表区域</b></font> -->
<!-- <hr> -->

<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">厂家全称</td>
		<td class="tableHeader">缩写</td>
		<td class="tableHeader">联系人</td>
		<td class="tableHeader">电话</td>
		<td class="tableHeader">手机</td>
		<td class="tableHeader">传真</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td><a href="toview.action?id=${o.id}">${o.fullName}</a></td>
		<td>${o.factoryName}</td>
		<td>${o.contacts}</td>
		<td>${o.phone}</td>
		<td>${o.mobile}</td>
		<td>${o.fax}</td>
		<td>${o.inspector}</td>
		<td>
		<!-- 为什么我这里写c:if标签, 不显示结果?
		因为el表达式, 在evualute expression时, 会将Character类型
		的数据, 得到其中的char类型的值, 并将char类型的值
		强转为short类型的值, 而'0'对应的short类型值是数字48, 
		这样, 自然永远也匹配不上 -->
<%-- 			<c:if test="${o.state.toString()==1}"><a href="stop.action?id=${o.state}"><font color="green">启用</font></a></c:if> --%>
<%-- 			<c:if test="${o.state.toString()==0 }"><a href="start.action?id=${o.state}">停用</a></c:if> --%>
			
			<c:if test="${o.state==1}"><a href="stop.action?id=${o.state}"><font color="green">启用</font></a></c:if>
			<c:if test="${o.state==0 }"><a href="start.action?id=${o.state}">停用</a></c:if>
			
		</td>
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

