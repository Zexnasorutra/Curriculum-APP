<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="person.name" /></title>
<script type="text/javascript"
	src="<s:url value="/js/jquery-3.1.0.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/js/person/person.js"/>"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<!-- Main container -->
	<div class="container-fluid bottom-buffer-80">

		<!-- Language buttons -->
		<s:if test="%{person.availableLanguages.size > 1}">
			<%@include file="languageButtons.jsp"%>
		</s:if>


		<!-- Header Row -->
		<%@include file="header.jsp"%>

		<!-- Description Row -->
		<s:if test="%{person.description.size > 0}">
			<%@include file="description.jsp"%>
		</s:if>

		<!-- Education Row -->
		<s:if test="%{person.studies.size > 0}">
			<%@include file="studies.jsp"%>
		</s:if>

		<!-- Jobs Row -->
		<s:if test="%{person.jobs.size > 0}">
			<%@include file="job.jsp"%>
		</s:if>

		<!-- Skills Row -->
		<s:if test="%{person.skills.size > 0}">
			<%@include file="skill.jsp"%>
		</s:if>

		<!-- Examples Row -->
		<s:if test="%{person.examples.size > 0}">
			<%@include file="example.jsp"%>
		</s:if>

		<!-- Contact Row -->
		<%@include file="contact.jsp"%>

		<!-- Field of default language -->
		<s:hidden key="defaultLanguage" id="defaultLanguage" />




	</div>


	<!-- Row Education header 
		<div class="row top-buffer-20">

			<div class="col-md-8 col-md-offset-1">
				<h2>
					<span class="glyphicon glyphicon-education" aria-hidden="true"></span>
					Educación
				</h2>
			</div>
		</div>





	<s:iterator value="person.description">
		<div class="language <s:property value="language" />">
			<s:property value="text" />
		</div>
	</s:iterator>

	<s:iterator value="person.description">
		<div class="language <s:property value="language" />">
			<s:property value="text" />
		</div>
	</s:iterator>

	<input id="btnMostrarError" type="button" value="oculta" />

	<input id="defaultLanguage" type="hidden" value="spanish">


	<script type="text/javascript">
		$(document).ready(function() {
			$(".language").hide();
			//$("."+$("#defaultLanguage").value).show();
			$("." + document.getElementById("defaultLanguage").value).show();
			/*$("#btnMostrarError").click(function() {
				$(".language").toggle();
			});
			$(".spanish").toggle();*/
		});
	</script>-->
</body>
</html>
