<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="inc/header.jsp">
	<jsp:param value="글쓰기" name="title"/>
</jsp:include>
<form:form commandName="boardDomain" action="getBoardInsert.do" enctype="multipart/form-data">
	<form:hidden path="pageNo"/>
	<form:hidden path="id"/>
	<form:hidden path="board_id"/>
	
	<form:label path="user_id">작성자</form:label>
	<form:input path="user_id" cssClass="input-xxlarge" maxlength="6"/>
	<form:errors path="user_id"></form:errors>
				
	<form:label path="title">제목</form:label>			
	<form:input path="title" cssClass="input-xxlarge" />
	<form:errors path="title"></form:errors>
	
	<br />
	<form:label path="content">내용</form:label>			
	<form:textarea path="content" />
	<form:errors path="content" element="p"></form:errors>

	<p>첨부 파일</p>
	<input type="file" name="upload_file">

	<p>비밀 번호</p>
	<form:password path="password" />
	<form:errors path="password" />
	
	<br/>
	
	<a href="#" class="btn btn-primary" id="btn_register_submit">전송</a>
	<br/>
	<a href="#" id="btn_back" class="btn btn-primary">목록으로</a>			
</form:form>
<script type="text/javascript">
	$(document).ready(function(){
		$('#content').cleditor({width:'100%'});
		
		$('#btn_register_submit').click(function(){
			$('#boardDomain').submit();
		});
		
		$('#btn_back').click(function(){
			$('#boardDomain').attr('action','getBoardListView.do');
			$('#boardDomain').submit();			
		});
	});
</script>
<jsp:include page="inc/footer.jsp"></jsp:include>