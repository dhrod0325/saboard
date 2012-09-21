<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagination" uri="/WEB-INF/tlds/pagination.tld"%>
<jsp:include page="inc/header.jsp">
	<jsp:param value="글 상세보기" name="title"/>
</jsp:include>
<form:form name="thisForm" commandName="boardDomain">
	<form:hidden path="pageNo" />
	<form:hidden path="id" />
	<form:hidden path="password" id="boardDomainPassword"/>
	<form:hidden path="board_id"/>
	<form:hidden path="board_table"/>
	<table class="table table-bordered table-hover">
		<tr>
			<th style="width:10%;">작성자</th>
			<td colspan="3">${boardDomain.user_id }</td>
		</tr>
		<tr>
			<th style="width:10%;">제목</th>
			<td colspan="3">${boardDomain.title }</td>
		</tr>
		<tr>
		 	<th>내용</th>
			<td colspan="3">${boardDomain.content }</td>
		</tr>
		<c:forEach items="${boardFileListDomain }" var="boardFileDomain">
			<tr>
				<th style="width:10%;">파일 명</th>
				<td>
					<a href="file?fileName=${boardFileDomain.file_name }">${boardFileDomain.file_name }</a>
				</td>
				<th style="width:10%;">
					파일 크기
				</th>
				<td>${boardFileDomain.file_size }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" class="text-align-right">
				<a href="#deleteModal" role="button" class="btn btn-primary" data-toggle="modal">삭 제 </a>
				<div class="modal hide fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-header">
				    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				    <h3 id="myModalLabel">비밀번호를 입력하세요</h3>
				  </div>
				  <div class="modal-body">
				    <p>
				    	<input type="text" id="delete_password"/>
				    </p>
				  </div>
				  <div class="modal-footer">
   				    <a href="#" class="btn btn-primary" id="btn_delete">삭 제</a>
				    <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">취 소</a>
				  </div>
				</div>	
				
				<a href="#modifyModal" role="button" class="btn btn-primary" data-toggle="modal">수 정 </a>
				<div class="modal hide fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				  <div class="modal-header">
				    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				    <h3 id="myModalLabel">비밀번호를 입력하세요</h3>
				  </div>
				  <div class="modal-body">
				    <p>
				    	<input type="text" id="modify_password" />
				    </p>
				  </div>
				  <div class="modal-footer">
   				    <a href="#" class="btn btn-primary" id="btn_modify">수 정</a>
				    <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">취 소</a>
				  </div>
				</div>
			</td>
		</tr>
	</table>
</form:form>
	<c:if test="${!empty boardReplyListDomain}">
			<c:forEach items="${boardReplyListDomain }" var="boardReplyDomain">
			<table class="table table-bordered table-hover">
				<tr>
					<th style="width:10%">아이디</th>
					<td style="width:40%">${boardReplyDomain.user_id }</td>
					<th style="width:10%">이메일</th>
					<td style="width:40%">${boardReplyDomain.email }</td>
				</tr>
				<tr>
					<th colspan="4">내용</th>
				</tr>
				<tr>
					<td colspan="4">
						${boardReplyDomain.content }
					</td>
				</tr>
				</table>
			</c:forEach>
	</c:if>
	<form:form action="getBoardInsertReply.do" method="post" commandName="boardReplyDomain">
		<input type="hidden" name="no" value="${boardDomain.id }">
		<form:hidden path="board_id"/>
		<table class="table table-bordered table-hover">
			<tr>
				<th colspan="4">댓글 작성</th>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<form:input path="user_id"/>
					<form:errors path="user_id" />	
				</td>
				<th>E-MAIL</th>
				<td>
					<form:input path="email"/>
					<form:errors path="email" />	
				</td>
			</tr>
			<tr>
				<th>비밀 번호</th>
				<td colspan="3">
					<form:input path="password" cssClass="input-xxlarge" />
					<form:errors path="password" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<form:textarea path="content" cssStyle="width:98%;" rows="4" />
					<form:errors path="content" />
				</td>
			</tr>
			<tr>
				<td colspan="4" class="text-align-right">
					<a href="#register_modal" role="button" class="btn btn-primary" data-toggle="modal">전 송 </a>
					<div class="modal hide fade" id="register_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					  <div class="modal-header">
					    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					    <h3 id="myModalLabel">알 림</h3>
					  </div>
					  <div class="modal-body">
					    <p>
					    	전송 하시겠습니까?
					    </p>
					  </div>
					  <div class="modal-footer">
	   				    <a href="#" class="btn btn-primary" id="btn_reply_submit">전송</a>
					    <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">취 소</a>
					  </div>
					</div>
					<a href="#" id="btn_back" class="btn btn-primary">뒤 로</a>
				</td>
			</tr>
		</table>
	</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn_back').click(function(e){
			e.preventDefault();
			
			$('#boardDomain').attr('action','getBoardListView.do');
			$('#boardDomain').submit();
		});		
		
		$('#btn_reply_submit').click(function(){
			$('#boardReplyDomain').submit();
		});
		
		$("#deleteModal").modal({
			show:false,
			backdrop:true
		}).hide();
		
		$("#modifyModal").modal({
			show:false,
			backdrop:true
		}).hide();
		
		$("#register_modal").modal({
			show:false,
			backdrop:true
		}).hide();
		
		$('#btn_delete').click(function(){
			$(".close").trigger('click');
			
			$('#boardDomainPassword').val($('#delete_password').val());
			$('#boardDomain').attr('action','getBoardDelete.do');
			$('#boardDomain').submit();
		});
		
		$('#btn_modify').click(function(){
			$(".close").trigger('click');
			
			$('#boardDomainPassword').val($('#modify_password').val());
			$('#boardDomain').attr('action','getBoardModifyView.do');
			$('#boardDomain').submit();
		});
	});
</script>
<jsp:include page="inc/footer.jsp"></jsp:include>