<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../inc/header.jsp"></jsp:include>
        
        <div>
            <label for="username">사용자ID : <input type="text" id="username" size="16"/></label>
            <label for="password">비밀번호 : <input type="password" id="password" size="16" /></label>
            <label for="reg_userName">등록이름 : <input type="text" id="reg_userName" size="16" /></label>
            
            <input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus }" />
            <input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent }" />

            <a href="#" class="btn btn-primary" id="btn_join">가입하기</a>
            <a href="login.do" class="btn btn-primary" id="btn_login">로그인화면으로</a>
        </div>
        
        <form id="securedLoginForm" name="securedLoginForm" action="<%=request.getContextPath()%>/join.do" method="post" style="display: none;">
            <input type="hidden" name="securedUsername" id="securedUsername" value="" />
            <input type="hidden" name="securedPassword" id="securedPassword" value="" />
            <input type="hidden" name="secured_reg_userName" id="secured_reg_userName" value="" />
        </form>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/common/lib/rsa/jsbn.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/common/lib/rsa/rsa.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/common/lib/rsa/prng4.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/common/lib/rsa/rng.js"></script>
        <script type="text/javascript">
	        function validateEncryptedForm() {
	        	var username = document.getElementById("username").value;
	        	var password = document.getElementById("password").value;
	        	var reg_userName = document.getElementById("reg_userName").value;
	        	
	        	if (!username || !password || !reg_userName) {
	        		alert("모두 입력해주세요.");
	        		return false;
	        	}
	
	        	try {
	        		var rsaPublicKeyModulus = document.getElementById("rsaPublicKeyModulus").value;
	        		var rsaPublicKeyExponent = document.getElementById("rsaPublicKeyExponent").value;
	        		submitEncryptedForm(username, password, rsaPublicKeyModulus,rsaPublicKeyExponent);
	        	} catch (err) {
	        		alert(err);
	        	}
	        	return false;
	        }
	
	        function submitEncryptedForm(username, password, rsaPublicKeyModulus,rsaPpublicKeyExponent) {
	        	var rsa = new RSAKey();
	        	rsa.setPublic(rsaPublicKeyModulus, rsaPpublicKeyExponent);
	
	        	var securedUsername = rsa.encrypt(username);
	        	var securedPassword = rsa.encrypt(password);
				
	        	var securedLoginForm = document.getElementById("securedLoginForm");
	        	securedLoginForm.securedUsername.value = securedUsername;
	        	securedLoginForm.securedPassword.value = securedPassword;
	        	
	        	securedLoginForm.secured_reg_userName.value = document.getElementById("reg_userName").value;
	        	
	        	securedLoginForm.submit();
	        }
	        
        	$(document).ready(function(){
        		$('#btn_join').click(function(e){
        			e.preventDefault();
        			
        			validateEncryptedForm();
        		});
        	});
        </script>
<jsp:include page="../inc/footer.jsp"></jsp:include>