<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="inc/header.jsp">
	<jsp:param value="글쓰기" name="title"/>
</jsp:include>
<form:form commandName="boardDomain" action="getBoardInsert.do" enctype="multipart/form-data">
	<form:hidden path="pageNo"/>
	<form:hidden path="id"/>
	<table class="table table-bordered table-hover">
		<tr>
			<th>
				<form:label path="user_id">작성자</form:label>
			</th>
			<td>
				<form:input path="user_id" cssClass="input-xxlarge" maxlength="6"/>
				<form:errors path="user_id"></form:errors>
			</td>
		</tr>
		<tr>
			<th>
				<form:label path="title">제목</form:label>			
			</th>
			<td>
				<form:input path="title" cssClass="input-xxlarge" />
				<form:errors path="title"></form:errors>
			</td>
		</tr>
		<tr>
			<th>
				<form:label path="content">내용</form:label>			
			</th>
			<td>
				<form:textarea path="content" />
				<form:errors path="content" element="p"></form:errors>
			</td>
		</tr>
		<tr>
			<th>
				<p>첨부 파일</p>
			</th>
			<td>
				<input type="file" name="upload_file">
			</td>
		</tr>
		<tr>
			<th>
				<p>비밀 번호</p>
			</th>
			<td>
				<form:password path="password" />
				<form:errors path="password" />
			</td>
		</tr>
		<tr>
			<td colspan="2" class="text-align-right">
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
	   				    <a href="#" class="btn btn-primary" id="btn_register_submit">전송</a>
					    <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">취 소</a>
					  </div>
				</div>
				<a href="#" id="btn_back" class="btn btn-primary">목록으로</a>			
			</td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$('#content').cleditor({width:'100%'});
		
		$("#register_modal").modal({
			show:false,
			backdrop:true
		}).hide();
		
		$('#btn_register_submit').click(function(){
			$(".close").click();
			$('#boardDomain').submit();
		});
		
		$('#btn_back').click(function(){
			$('#boardDomain').attr('action','getBoardListView.do');
			$('#boardDomain').submit();			
		});
	});
</script>
<jsp:include page="inc/footer.jsp"></jsp:include>