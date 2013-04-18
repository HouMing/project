<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<base src="/questionnaire/" />
<link rel="stylesheet" href="resources/css/site.css" />
<script src="resources/js/jquery-1.8.0.min.js" ></script>
<body>
	<div id="page">
		<div id="header">
			<div class="container">
				<span id="logo"> <a href="#" title="Home"> <img
						src="resources/images/logo.gif"></a></span>
				<c:if test="${!empty(actions)}">
					<ul id="navigation">
						<c:forEach items="${actions}" var="item">
							<li class="home"><a href="javascript:load('${item.value}')">
									${item.key}</a></li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
		<div id="main">
			<c:if test="${user == null}">
			<%@ include file="login.jsp" %>
			</c:if>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>
<script>
function load(r) {
	$.ajax({
		url:r,
		type:'GET',
		success:function(data){
			if(r === "logout") {
				window.location = "index";
			}
			$('#main').empty();
			$('#main').append(data);
		}
	});
}
</script>
