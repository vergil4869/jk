<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript" src="../../js/datepicker/WdatePicker.js"></script>
</head>
<body>
<form method="post">
<%-- 
<hr>
	<font color="blue"><b>隐藏区域</b></font>
<hr>

	<input type="hidden" name="id" value="${obj.id}"/>
 --%>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('print.action','_self');">打印</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		出货表月统计
    </div> 
    </div>
    </div>
<div>
 
<!-- <hr> -->
<!-- 	<font color="blue"><b>内容区域，表格布局</b></font> -->
<!-- <hr> -->
 
    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle_mustbe">船期：</td>
	            <td class="tableContent"><input type="text" style="width:90px;" name="inputDate"
	            	onclick="WdatePicker({el:this, isShowOthers:true, dateFmt:'yyyy/MM'});" value="2011/12"/></td>
	        </tr>
		</table>
	</div>
</div>
 
 
</form>
</body>
</html>

