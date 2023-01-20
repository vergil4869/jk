<%@ page language="java" pageEncoding="UTF-8"%>
<!-- <hr> -->
<!-- 	<font color="blue"><b>引入CSS、JS区域</b></font> -->
<!-- <hr> -->

<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<li id="view"><a href="#" onclick="formSubmit('toView.action','_self');this.blur();">查看</a></li>
<!-- this.blur() 事件, 是因为点击后会出现一个小虚框, 默认点击后小虚框仍然存在, 这个事件正是为了去除小虚框.
(而我的浏览器并没有出现小虚框)
可以同时写多个js函数 -->
<li id="new"><a href="#" onclick="formSubmit('toCreate.action','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="formSubmit('toUpdate.action','_self');this.blur();">修改</a></li>
<li id="delete"><a href="#" onclick="formSubmit('delete.action','_self');this.blur();">删除</a></li>
<li id="new"><a href="#" onclick="formSubmit('submit.action','_self');this.blur();">上报</a></li>
<li id="new"><a href="#" onclick="formSubmit('cancel.action','_self');this.blur();">取消</a></li>
 
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

    购销合同列表
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
		<td class="tableHeader">客户名称</td><!-- 这重要, 这领导一看, 这是哪个大客户的. 这个放到最前面 -->
		<td class="tableHeader">合同号</td>
		<td class="tableHeader">货物数/附件数</td>
		<td class="tableHeader">制单人</td><!-- 人, 我们也是往后放 -->
		<td class="tableHeader">审单人</td>
		<td class="tableHeader">验货员</td>
		<td class="tableHeader">签单日期</td><!-- 日期类的一般爱往后排, 这都是规则, 不是随便排位置的 -->
		<td class="tableHeader">交货期限</td>
		<td class="tableHeader">船期</td>
		<td class="tableHeader">总金额</td><!-- 总金额, 是最重要的, 要看到这单赚了多少钱, 我们一般放到最最后, 当然一般要放到状态前面 -->
		<td class="tableHeader">状态</td>
		<td class="tableHeader">操作</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	
	<c:forEach items="${dataList}" var="o" varStatus="status">
	<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
		<td><input type="checkbox" name="id" value="${o.id}"/></td>
		<td>${status.index+1}</td>
		<td>${o.customName}</td>
		<td><a href="toView.action?id=${o.id }">${o.contractNo}</a></td>
		<td align="center">${o.cpnum }/${o.extnum }</td>
		<td>${o.inputBy}</td>
		<td>${o.checkBy}</td>
		<td>${o.inspector}</td>
		<td><fmt:formatDate pattern="yyyy/MM/dd" value="${o.signingDate}"></fmt:formatDate></td>
		<td><fmt:formatDate pattern="yyyy/MM/dd" value="${o.deliveryPeriod}"></fmt:formatDate></td>
		<td><fmt:formatDate pattern="yyyy/MM/dd" value="${o.shipTime }"></fmt:formatDate></td>
		<td>${o.totalAmount}</td>
		<td>
			<c:if test="${o.state==1}"><font color="green">已上报</font></c:if>
			<c:if test="${o.state==0 }">草稿</c:if>
		</td>
		<td>
			<a href="../contractProduct/toCreate.action?contractId=${o.id }"
				title="新增货物信息">[货物]</a>
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

