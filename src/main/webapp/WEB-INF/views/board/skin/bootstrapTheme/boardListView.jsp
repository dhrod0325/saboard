<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tlds/pagination.tld"%>
<jsp:include page="inc/header.jsp">
	<jsp:param value="글 목록" name="title"/>
</jsp:include>
<form:form name="thisForm" commandName="boardDomain">
	<form:hidden path="id"/>
	<form:hidden path="pageNo"/>
	<table class="table table-bordered table-hover" id="board-list-table">
		<caption class="nodisplay">게시판 리스트</caption>
		<thead>
			<tr>
				<th class="center" style="width:5%;">순번</th>
				<th class="center" style="width:70%;">제목</th>
				<th class="center" style="width:10%;">작성자</th>
				<th class="center" style="width:15%;">작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardListDomain }" var="boardDomain">
				<tr class="tr-list-item">
					<td class="center">${boardDomain.rownum }</td>
					<td>
						${boardDomain.title }
						<c:if test="${boardDomain.has_File == 'YES'}"><i class="icon-file" ></i></c:if>
						<c:if test="${boardDomain.reply_cnt != '0'}">(${boardDomain.reply_cnt })</c:if>
					</td>
					<td>${boardDomain.user_id }</td>
					<td class="center"><fmt:formatDate value="${boardDomain.reg_date }" pattern="yyyy-MM-dd" /></td>
					<td class="nodisplay list-item-id">${boardDomain.id }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" class="center paging">
					<pagination:paging>
						<pagination:function>goListPage</pagination:function>
						<pagination:currentPage>${boardDomain.pageNo}</pagination:currentPage>
						<pagination:totalRows>${boardDomain.totalSize}</pagination:totalRows>
						<pagination:pageSize>${boardDomain.pageSize}</pagination:pageSize>
						<pagination:imgPrevPath>/resources/bootstrapTheme/images/common/paging_prev.png</pagination:imgPrevPath>
						<pagination:imgNextPath>/resources/bootstrapTheme/images/common/paging_next.png</pagination:imgNextPath>
					</pagination:paging>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="text-align-right">
					<a href="#" id="btn_write" class="btn btn-primary">글 작성</a>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>
<script type="text/javascript">
	function goListPage(pageNo) {
		$('#pageNo').val(pageNo);
		$('#boardDomain').submit();
	}
	
	$(document).ready(function(){
		$('#btn_write').click(function(){
			$('#id').val('-1');
			$('#boardDomain').attr('action','getBoardWriteView.do');
			$('#boardDomain').submit();
		});
		
		$('.tr-list-item').click(function(){
			$('#id').val($(this).children('.list-item-id').text());
			$('#boardDomain').attr('action','getBoardDetailView.do');
			$('#boardDomain').submit();
		});
	});
</script>

<jsp:include page="inc/footer.jsp"></jsp:include>