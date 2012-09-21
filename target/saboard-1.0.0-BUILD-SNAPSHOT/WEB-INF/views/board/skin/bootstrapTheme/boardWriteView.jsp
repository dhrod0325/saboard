<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="inc/header.jsp">
	<jsp:param value="글쓰기" name="title"/>
</jsp:include>
<form:form commandName="boardDomain" action="getBoardInsert.do" enctype="multipart/form-data">
	<form:hidden path="pageNo"/>
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
			<td colspan="2" class="text-align-right">
				<form:button class="btn btn-primary">전송</form:button>
				<a href="#" id="btn_back" class="btn btn-primary">목록으로</a>			
			</td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$('#content').cleditor({width:'100%'});
	      
		$('#btn_back').click(function(){
			$('#boardDomain').attr('action','getBoardListView.do');
			$('#boardDomain').submit();			
		});
	});
</script>
<jsp:include page="inc/footer.jsp"></jsp:include>