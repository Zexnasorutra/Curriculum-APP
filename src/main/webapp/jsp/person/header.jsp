<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Header row -->
<div class="row top-buffer-20">
	<!-- Name -->
	<div class="col-md-6 col-md-offset-1 top-buffer-60">
		<h1>
			<s:property value="person.name" />
		</h1>
	</div>
	<!-- Photo -->
	<div class="col-md-4 col-md-offset-1">

		<!-- Generate photo URL -->
		<s:url var="url" action="findPhoto">
			<s:param name="photoId">
				<s:property value="person.photo" />
			</s:param>
		</s:url>
		<img src="<s:url action='%{url}'/>" alt="photo" class="img-circle">
	</div>
</div>