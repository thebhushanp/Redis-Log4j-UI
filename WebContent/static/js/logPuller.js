$(window).load(function() {
	$.ajax({
		url : "LogPuller?bucket=bhushan&to=0&from=-1",
	}).done(function(data) {
		renderLogs(data);
	}).error(function(jqXHR, textStatus, errorThrown) {
		alert('err' + textStatus + errorThrown);
	});
});

function renderLogs(data) {
	$.each(data, function(i, log) {
		debugger;
		$("#divLogListView").append("<div class='alert alert-success' role='alert'>"+log+"</div>");
	});

}