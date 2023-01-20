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
 	<input type="text" name="id", value="${obj.id }">
	<input type="text" name="contractProductId" value="${obj.contractProductId}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
    <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('update.action','_self');">确定</a></li>
<li id="back"><a href="${ctx }/cargo/contract/list.action">返回</a></li>
</ul>
    </div>
</div>
</div>
</div>
     
<div class="textbox" id="centerTextbox">
    
    <div class="textbox-header">
    <div class="textbox-inner-header">
    <div class="textbox-title">
		修改附件信息
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
	            			<option value="${factory.id }" <c:if test="${factory.id==obj.factoryId }">selected</c:if>>${factory.factoryName }</option>
	            		</c:forEach>
	            	</select>
	            	<!-- 下面这个隐藏域是干啥的? -->
	            	<input type="hidden" id="factoryName" name="factoryName" value="${obj.factoryName}">
	            </td>
	            <td class="columnTitle_mustbe">货号：</td>
	            <td class="tableContent"><input type="text" name="productNo"  value="${obj.productNo}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">分类：</td>
	            <td class="tableContent">
	            	<select name="cType">
	            	<option value="">--请选择--</option>
	            		<c:forEach items="${ctypeList}" var="c">
	            			<option value="${c.orderNo }" <c:if test="${obj.cType==c.orderNo}">selected</c:if>>${c.name }</option>
	            		</c:forEach>
	            	</select>
	            </td>
	        	<!-- 属于文件上传, 这里我们暂不处理, 当成普通字段 -->
	            <td class="columnTitle_mustbe">货物照片：</td>
	            <td class="tableContent"><input type="text" name="productImage"  value="${obj.productImage}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">数量：</td>
	            <td class="tableContent">
	            	<input type="text" name="cnumber" value="${obj.cnumber}"/>
	            </td>
	            <td class="columnTitle_mustbe">包装单位：</td>
	            <td class="tableContent">
	            	<input type="text" name="packingUnit" value="${obj.packingUnit}"/>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">单价：</td>
	            <td class="tableContent"><input type="text" name="price" value="${obj.price}"/></td>
	            <td class="columnTitle_mustbe">排序号：</td>
	            <td class="tableContent"><input type="text" name="orderNo" value="${obj.orderNo}"/></td>
	        </tr>
	        <tr>
	            <td class="columnTitle_mustbe">要求：</td>
	            <td class="tableContent"><textarea type="text" style="height:120px;" name="productRequest">${obj.productRequest}</textarea></td>
	            <td class="columnTitle_mustbe">货物描述：</td>
	            <td class="tableContent"><textarea type="text" style="height:120px;" name="productDesc">${obj.productDesc}</textarea></td>
	        </tr>
		</table>
	</div>
</div>

<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
货物列表
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
	<!-- 以什么原则来挑列表的字段? 
	重要的信息, 客户最想看到的. 稍微次要的, 或者备注的信息, 
	让客户在查看中去看 -->
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">序号</td>
		<td class="tableHeader">厂家名称</td>
		<td class="tableHeader">货号</td>
		<td class="tableHeader">数量</td>
		<td class="tableHeader">包装单位</td>
		<td class="tableHeader">单价</td>
		<td class="tableHeader">总金额</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.factoryName}</td>
		<td>${o.productNo}</td>
		<td>${o.cnumber}</td>
		<td>${o.packingUnit}</td>
		<td>${o.price }</td>
		<td>${o.amount}</td>
		<td>
			<a href="toUpdate.action?id=${o.id }">[修改]</a>
			<a href="deleteById.action?id=${o.id }&contractProductId=${o.contractProductId}">[删除]</a>
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

