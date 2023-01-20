<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
<!--
	//	设置冗余的生厂家名称
//-->
		function setFactoryName(factoryName) {
			var element = document.getElementById("factoryName");
			element.value = factoryName;
		}
	</script>
</head>
<body>
<form method="post">
<%-- 
<hr>
	<font color="blue"><b>隐藏区域</b></font>
<hr>

 --%>
 	<input type="text" name="id", value="${product.id}">
	<input type="text" name="contractId" value="${product.contractId}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('update.action','_self');">确定</a></li>
<li id="back"><a href="${ctx }/cargo/contractProduct/toCreate.action?contractId=${product.contractId}">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改货物信息
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
	            <td class="columnTitle_mustbe">厂家名称：</td>
	            <td class="tableContent">
	            	<select name="factoryId" onchange="setFactoryName(this.options[selectedIndex].text);">
	            		<option value="">--请选择--</option>
	            		<c:forEach items="${factoryList}" var="factory">
	            			<option value="${factory.id }" <c:if test="${product.factoryId==factory.id}">selected</c:if>>${factory.factoryName }</option>
	            		</c:forEach>
	            	</select>
	            	<!--为什么这里要写这一行?-->
	            	<input type="hidden" id="factoryName" name="factoryName" value="${product.factoryName }">
	            </td>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="productNo" value="${product.productNo }"/></td>
	        </tr>
	        <tr>
	        	<!-- 属于文件上传, 这里我们暂不处理, 当成普通字段 -->
	            <td class="columnTitle_mustbe">货物照片：</td>
	            <td class="tableContent"><input type="text" name="productImage" value="${product.productImage }"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent">
	            	<input type="text" name="cnumber" value="${product.cnumber }"/>
	            </td>
	            <td class="columnTitle_mustbe">包装单位：</td>
	            <td class="tableContent">
	            	<input type="text" name="packingUnit" value="${product.packingUnit }"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">装率：</td>
	            <td class="tableContent">
	            	<input type="text" name="loadingRate" value="${product.loadingRate }"/>
	            </td>
	            <td class="columnTitle_mustbe">箱数：</td>
	            <td class="tableContent">
	            	<input type="text" name="boxNum" value="${product.boxNum }"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="price" value="${product.price }"/></td>
	            <td class="columnTitle_mustbe">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value="${product.orderNo }"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea type="text" style="height:120px;" name="productDesc">${product.productDesc }</textarea></td>
	        </tr>
		</table>
	</div>
</div>

</body>
</html>

