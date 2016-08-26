<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Studies header row -->
<div class="row top-buffer-20">

	<div class="col-md-8 col-md-offset-1">
		<s:iterator value="headers">
			<s:if test="%{fieldId == 'studies'}">
				<h2>
					<span class="<s:property value="iconCSS" />" aria-hidden="true">
						<s:iterator value="descriptive">
							<span class="language <s:property value="language" />"> <s:property
									value="text" />
							</span>
						</s:iterator>
					</span>
				</h2>
			</s:if>
		</s:iterator>
	</div>
</div>

<!-- Studies list row -->
<div class="row top-buffer-20">
	<div class="col-md-8 col-md-offset-1">
		<div class="list-group">
			<s:iterator value="person.studies">
				<a class="list-group-item">
					<h3 class="list-group-item-heading">
						<s:iterator value="name">
							<div class="language <s:property value="language" />">
								<s:property value="text" escapeHtml="false" />
							</div>
						</s:iterator>
					</h3>
					<h4 class="list-group-item-heading">
						<s:property value="startDate" />
						-
						<s:property value="endDate" />
					</h4>
					<p class="list-group-item-text">
						<s:iterator value="description">
							<div class="language <s:property value="language" />">
								<s:property value="text" escapeHtml="false" />
							</div>
						</s:iterator>
					</p>
				</a>
			</s:iterator>
		</div>
	</div>
</div>