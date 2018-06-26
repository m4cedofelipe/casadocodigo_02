<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais - Casa do Código</title>

<c:url value="/resources/bootstrap" var="cssPath" />

<link rel="stylesheet" href="${cssPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/css/bootstrap-theme.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">

	h2{
		margin-top: 100px;
	}
</style>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel-heading">
					<h2 class="text-center login-title">
					Login Casa do Código
					</h2>
				</div>
				
				<form:form servletRelativeAction="/login" method="POST" cssClass="form-signin">
				    <div class="input-group">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>   
				      <input id="email" type="text" class="form-control" name="username" placeholder="Email">
				    </div>
				    <div class="input-group">
				      <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
				      <input id="password" type="password" class="form-control" name="password" placeholder="Senha">
				    </div>
				    <br>
				    <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				 </form:form>
				
			</div>
		</div>
	</div>
</body>
</html>