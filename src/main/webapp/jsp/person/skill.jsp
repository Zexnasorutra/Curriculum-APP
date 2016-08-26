<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Skills header row -->
<div class="row top-buffer-20">

	<div class="col-md-8 col-md-offset-1">
		<s:iterator value="headers">
			<s:if test="%{fieldId == 'skills'}">
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

<!-- Skill list row -->
<div class="row top-buffer-20">
	<div class="col-md-8 col-md-offset-1">
		<ul class="nav nav-pills" role="skills">

			<!-- Iterate skills -->
			<s:iterator value="person.skills">
				<li><a> <s:property />
				</a></li>
			</s:iterator>
		</ul>
	</div>
</div>