<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- Description Row -->
<div class="row top-buffer-20">
	<div class="col-md-8 col-md-offset-1">
		<!-- Iterate description languages -->
		<s:iterator value="person.description">
			<div class="language <s:property value="language" />">
				<s:property value="text" escapeHtml="false"/>
			</div>
		</s:iterator>
	</div>
</div>
