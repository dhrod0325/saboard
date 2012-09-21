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
	<form:hidden path="board_id"/>
	
	<c:forEach items="${boardListDomain }" var="boardDomain">
		<p>순번 : ${boardDomain.rownum }</p>
		<p>제목 : ${boardDomain.title }<br/>
		   파일 유무 체크 : <c:if test="${boardDomain.has_File == 'YES'}"><i class="icon-file" ></i></c:if><br />
		   댓글 숫자 : <c:if test="${boardDomain.reply_cnt != '0'}">(${boardDomain.reply_cnt })</c:if>
		</p>
		<p>작성자 : ${boardDomain.user_id }</p>
		<p>작성일 : <fmt:formatDate value="${boardDomain.reg_date }" pattern="yyyy-MM-dd" /></p>
		<p class="board-item-id" style="color:blue;cursor: pointer;">게시물 고유번호 : <span class="item-id">${boardDomain.id }</span></p>
		--------------------------------------------------------------------------
	</c:forEach>
			
	<pagination:paging>
		<pagination:function>goListPage</pagination:function>
		<pagination:currentPage>${boardDomain.pageNo}</pagination:currentPage>
		<pagination:totalRows>${boardDomain.totalSize}</pagination:totalRows>
		<pagination:pageSize>${boardDomain.pageSize}</pagination:pageSize>
		<pagination:imgPrevPath>/resources/bootstrapTheme/images/common/paging_prev.png</pagination:imgPrevPath>
		<pagination:imgNextPath>/resources/bootstrapTheme/images/common/paging_next.png</pagination:imgNextPath>
	</pagination:paging>
	
	<a href="#" id="btn_write">글 작성</a>
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
		
		$('.board-item-id').click(function(){
			$('#id').val($(this).children('.item-id').text());
			$('#boardDomain').attr('action','getBoardDetailView.do');
			$('#boardDomain').submit();
		});
	});
</script>
<jsp:include page="inc/footer.jsp"></jsp:include>