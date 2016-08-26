<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Examples header row -->
<div class="row top-buffer-20">

	<div class="col-md-8 col-md-offset-1">
		<s:iterator value="headers">
			<s:if test="%{fieldId == 'examples'}">
				<h2>
					<span class="<s:property value="iconCSS" />" aria-hidden="true">
						<s:iterator value="descriptive">
							<span class="language <s:property value="language" />">
								<s:property value="text" />
							</span>
						</s:iterator>
					</span>
				</h2>
			</s:if>
		</s:iterator>
	</div>
</div>


<!-- Row Examples list -->
<div class="row top-buffer-20">
	<div class="col-md-8 col-md-offset-1">
		<!-- Iterate skills -->
		<s:iterator value="person.examples">
			<p>
				<a href="<s:property value="url" />"><s:property value="name" /></a>
				<span>-</span>
				<!-- Iterate description languages -->
				<s:iterator value="description">
					<span class="language <s:property value="language" />">
						<s:property value="text" />
					</span>
				</s:iterator>
			</p>
		</s:iterator>

	</div>
</div>