
/* 
* Script contains the JS used for the HTML5 Player
*
*/

/* Global Vars */
var basePlayQueueUrl = "/play/queue";
var playNextUrl = basePlayQueueUrl + "/next";
var playPrevUrl = basePlayQueueUrl + "/prev";
var playSongUrl = basePlayQueueUrl + "/song/";
var playerId = "#player";
var srcAttribute = "src";



function next() {	
	playSongFromQueue(playNextUrl);
}

function prev() {	
	playSongFromQueue(playPrevUrl);
}

function playSongFromQueue(nextOrPrevUrl) {
	$.ajax({
		  url: nextOrPrevUrl
		}).done(function(data) {
			playSong(data);
		});
}

function playSong(songId) {
	$(playerId).attr(srcAttribute, playSongUrl + songId);
}

/* DOM Ready Loading done here */
$(function() {                      
	  $("#next").click(function() { 
		  next();
	  });
	  $("#prev").click(function() { 
		  prev();
	  });
	});

