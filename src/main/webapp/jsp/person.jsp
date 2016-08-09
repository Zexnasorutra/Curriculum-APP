<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><s:property value="person.name" /></title>
    <script type="text/javascript" src="<s:url value="/js/jquery-3.1.0.min.js"/>"></script>
    </head>
	<body>
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
	    
	    <input id="btnMostrarError" type="button" value="oculta"/>
	    
	    <input id="defaultLanguage" type="hidden" value="spanish">
	    

	    <script type="text/javascript">
			$(document).ready(function() {
				$(".language").hide();
				//$("."+$("#defaultLanguage").value).show();
				$("."+document.getElementById("defaultLanguage").value).show();
				/*$("#btnMostrarError").click(function() {
					$(".language").toggle();
				});
				$(".spanish").toggle();*/
			});
		</script>
    </body>
</html>
