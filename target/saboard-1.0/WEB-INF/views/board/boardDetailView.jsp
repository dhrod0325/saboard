<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!empty theme }">
<jsp:include page="skin/${theme }/boardDetailView.jsp"></jsp:include>
</c:if>