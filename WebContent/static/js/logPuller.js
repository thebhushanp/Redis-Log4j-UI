// Global variables
var logLevelEnum = {
	'ERROR' : 'alert alert-danger',
	'INFO' : 'alert alert-success',
	'DEBUG' : 'alert alert-info'
};

var key = 'bhushan';
var startIndex = 0;
var endIndex = -1;
var autoUpdateInterval = 4000;
var timeoutId;
var isInTailMode = false;

$(window).load(function() {
	autoUpdateUI();
});

function autoUpdateUI() {
	$.ajax(
			{
				dataType : "json",
				url : "LogPuller?key=" + key + "&startIndex=" + startIndex
						+ "&endIndex=" + endIndex,
			}).done(function(data) {
		startIndex = startIndex + data.length;
		renderLogs(data);
	}).error(function(jqXHR, textStatus, errorThrown) {
		alert('err' + textStatus + errorThrown);
	});
	timeoutId = setTimeout(function() {
		autoUpdateUI();
	}, autoUpdateInterval);
}

function renderLogs(data) {
	$.each(data, function(i, log) {
		log = JSON.parse(log);
		$("#divLogListView").append(
				"<div class='" + logLevelEnum[log.level] + "' role='alert'>"
						+ formatLogMessage(log) + "</div>");
	});
	if (isInTailMode)
		 $("html, body").animate({scrollTop : $("#divLogListView").height()},
		 1000);
}

function formatLogMessage(log) {
	var formattedMsg = log.timestamp;
	formattedMsg += ' ' + log.level;
	formattedMsg += ' [' + log.method + ']';
	formattedMsg += ' (' + log.class + '):' + log.line_number;
	formattedMsg += ' (' + log.class + '):' + log.line_number;
	formattedMsg += ' ' + log.message;
	if (log.exception) {
		formattedMsg += '<br> '
				+ log.exception.stacktrace.replace("\n", "<br>");
	}
	return formattedMsg;
}

function onClickTailMode() {
	isInTailMode = true;
	clearTimeout(timeoutId);
	autoUpdateInterval = 1000;
	autoUpdateUI();
}

function onClickPause() {
	var w_height = $(window).height();
	var d_height = $(document).height();
	isInTailMode = false;
	if (!isInTailMode) {
		$("html, body").animate({
			scrollBottom : w_height + d_height
		}, 'slow', function() {
		});
	}
}