var logLevelEnum = {
    'ERROR': 'alert alert-danger',
    'INFO': 'alert alert-success',
    'DEBUG': 'alert alert-info'
};

var key = 'bhushan';
var to = 0;
var from = -1;

$(window).load(function() {	
	$.ajax({
		url : "LogPuller?key="+key+"&to="+to+"&from="+from,
	}).done(function(data) {
		debugger;
		if(data.length == 0)
			alert("No logs found on key : "+key);
		renderLogs(JSON.parse(data));
	}).error(function(jqXHR, textStatus, errorThrown) {
		alert('err' + textStatus + errorThrown);
	});
});

function renderLogs(data) {
	$.each(data, function(i, log) {
		debugger;
		log = JSON.parse(log);
		$("#divLogListView").append("<div class='"+logLevelEnum[log.level]+"' role='alert'>"+formatLogMessage(log)+"</div>");
	});
}

function formatLogMessage(log) {
	debugger;
	var formattedMsg = log.timestamp;
	formattedMsg += ' '+log.level;
	formattedMsg += ' ['+log.method+']';
	formattedMsg += ' ('+log.class+'):'+log.line_number;
	formattedMsg += ' ('+log.class+'):'+log.line_number;
	formattedMsg += ' '+log.message;
	if(log.exception) {
		debugger;
		formattedMsg += '<br> '+log.exception.stacktrace.replace("\n","<br>");
	}
	return formattedMsg;
}