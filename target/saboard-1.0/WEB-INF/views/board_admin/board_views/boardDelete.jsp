<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/left_menu.jsp"></jsp:include>
<div class="span8">
	<h2 class="page-header">게시판 삭제</h2>
	<form action="getBoardDelete.do" id="delete_form" method="POST">
		<input type="hidden" name="id" id="id" />
	</form>
	<div class="row-fluid">
		<c:forEach items="${boardTableList }" var="boardTableDomain"
			varStatus="i">
			<div class="span4" style="margin-bottom: 15px;">
				<div class="thumbnail">
					<h3>${boardTableDomain.board_nm }</h3>
					<p>테이블 명 : ${boardTableDomain.board_id }</p>
					<p>테마 : ${boardTableDomain.theme }</p>
					<p>설명 : ${boardTableDomain.board_desc }</p>
				
					<a href="#" role="button" data-toggle="modal" class="btn btn-block btn-primary btn_delete" data-id="${boardTableDomain.id }">삭제</a>
				</div>
			</div>
			<c:if test="${i.count%3==0 }">
				</div>
				<div class="row-fluid">
			</c:if>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#deleteModal").modal({
			show:false,
			backdrop:true
		});
		
		$('.btn_delete').click(function() {
			if(confirm('정말로 삭제하시겠습니까? 삭제된 게시판은 복구할수 없습니다.')){
				$('#id').val($(this).attr('data-id'));
				$('#delete_form').submit();	
			}
		}); 
	});
</script>
<jsp:include page="../inc/footer.jsp"></jsp:include>