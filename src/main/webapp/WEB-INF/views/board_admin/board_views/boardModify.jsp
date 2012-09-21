<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/left_menu.jsp"></jsp:include>

<form:form commandName="boardTableDomain" action="getBoardModify.do">
	<form:hidden path="board_nm"/>
	<form:hidden path="board_id"/>
	<form:hidden path="board_desc"/>
	<form:hidden path="id"/>	
	<form:hidden path="theme"/>
<div class="span8">
	<h2 class="page-header">게시판 수정</h2>
	<div class="row-fluid">
		<c:forEach items="${boardTableList }" var="boardTableDomain" varStatus="i">
			<div class="span4 data-id-${boardTableDomain.id }" style="margin-bottom: 15px;">
				<div class="thumbnail">
					<h3>테이블 명 : <input type="text" class="Tboard_id" value="${boardTableDomain.board_id }"/></h3>
					<p>게시판 명 : <input type="text" class="Tboard_nm" value="${boardTableDomain.board_nm }" /></p>
					<p>테마 변경 : 
						<select class="Ttheme">
							<c:forEach items="${skinList }" var="skin">
								<c:if test="${skin == boardTableDomain.theme}">
									<option value="${skin }" selected="selected">${skin }</option>	
								</c:if>
								<c:if test="${skin != boardTableDomain.theme}">
									<option value="${skin }">${skin }</option>	
								</c:if>
							</c:forEach>
						</select>
					</p>
					<p>설명</p>
					<p><textarea style="width:92%;" rows="5" class="Tboard_desc">${boardTableDomain.board_desc }</textarea></p>
					<input type="hidden" value="${boardTableDomain.id }" class="Tid"/>
					<a href="#" class="btn btn-block btn-primary btn_modify" role="button" data-toggle="modal">변경하기</a>
				</div>
			</div>
			<c:if test="${i.count%3==0 }">
	</div>
	<div class="row-fluid">
		</c:if>
		</c:forEach>
	</div>
</div>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#modifyModal").modal({
			show:false,
			backdrop:true
		});
		
		$('#modifyModal').on('show',function(e,a){
			console.log(e,a);
		});
		
		$('.btn_modify').click(function(e) {
			var $form = $(this).parent(); 
			
			if(confirm("변경 하시겠습니까?")){
				$('#boardTableDomain #board_nm').val($form.find('.Tboard_nm').val());
				$('#boardTableDomain #board_id').val($form.find('.Tboard_id').val());
				$('#boardTableDomain #theme').val($form.find('.Ttheme').val());
				$('#boardTableDomain #board_desc').val($form.find('.Tboard_desc').val());
				$('#boardTableDomain #id').val($form.find('.Tid').val());
				$('#boardTableDomain').submit();	
			}
		}); 
	});
</script>
<jsp:include page="../inc/footer.jsp"></jsp:include>