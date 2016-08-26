$(document).ready(
		function() {
			$(".language").hide();
			// Show default language texts
			$("." + document.getElementById("defaultLanguage").value).show();
			// Set button of default language active
			$(
					"#buttonLanguage"
							+ document.getElementById("defaultLanguage").value)
					.attr('class', 'btn btn-primary btn-lg');
			;
		});

function changeLanguage(language) {
	$(".language").hide();
	// Set button of language deactivated
	$('button[id^="buttonLanguage"]').each(function() {
		$(this).attr('class', 'btn btn-default btn-lg');
	});
	// Show language texts
	$("." + language).show();
	// Set button of language active
	$("#buttonLanguage" + language).attr('class', 'btn btn-primary btn-lg');
	;
}