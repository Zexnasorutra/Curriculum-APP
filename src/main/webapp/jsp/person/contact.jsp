<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Contact header row -->
<div class="row top-buffer-20">

	<div class="col-md-8 col-md-offset-1">
		<s:iterator value="headers">
			<s:if test="%{fieldId == 'contact'}">
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

<!-- Contact row -->
<div class="row top-buffer-20">
	<div class="col-md-8 col-md-offset-1">
		<p>
			<s:iterator value="headers">
				<s:if test="%{fieldId == 'mail'}">
					<s:iterator value="descriptive">
						<span class="language <s:property value="language" />"> <s:property
								value="text" />
						</span>
					</s:iterator>
				</s:if>
			</s:iterator>
			<span>:</span> <a
				href="mailto:<s:property value="person.contact.mail" />"><s:property
					value="person.contact.mail" /></a>
		</p>
		<p>
			<s:iterator value="headers">
				<s:if test="%{fieldId == 'phone'}">
					<s:iterator value="descriptive">
						<span class="language <s:property value="language" />"> <s:property
								value="text" />
						</span>
					</s:iterator>
				</s:if>
			</s:iterator>
			<span>:</span>
			<s:property value="person.contact.phone" />
		</p>
	</div>
</div>