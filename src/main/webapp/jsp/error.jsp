<%@page import="org.apache.logging.log4j.LogManager"%>
<%@page import="org.apache.logging.log4j.Logger"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Application error</title>
<script type="text/javascript" src="<s:url value="/js/jquery-3.1.0.min.js"/>"></script>
</head>
<body>
	<div class="span9 offset1">
		<h4>There was an application error.</h4>
		<p></p>
		
		<%
			Logger log = LogManager.getLogger();
			log.error("There was an application error\n"
					+ request.getAttribute("exceptionStack"));
		%>


		<input id="btnShowError" type="button" class="btn"
			value="Show error" />

		<script type="text/javascript">
			$(document).ready(function() {
				$("#btnShowError").click(function() {
					$("#descError").toggle();
				});
			});
		</script>

		<div id="descError" style="display: none;">

			<h3>Exception:</h3>
			<s:property value="exception" />
			<p><%=new Date()%></p>

			<h3>Stack trace:</h3>
			<pre>
		        <s:property value="exceptionStack" />
		    </pre>
		</div>
	</div>
</body>
</html>