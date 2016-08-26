<%@ taglib prefix="s" uri="/struts-tags"%>


<div class="row top-buffer-20">
	<div class="col-md-8 col-md-offset-4">

		<s:iterator value="person.availableLanguages">
			<button type="button" id="buttonLanguage<s:property/>" onclick="changeLanguage('<s:property />');"
				class="btn btn-default btn-lg">
				<s:property />
			</button>
		</s:iterator>
	</div>
</div>