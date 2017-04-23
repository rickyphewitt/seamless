
/*
* Script contains the JS used for the HTML5 Player
*
*/

/* Global Vars */
// Urls
var basePlayQueueUrl = "/play/queue";
var playNextUrl = basePlayQueueUrl + "/next";
var playPrevUrl = basePlayQueueUrl + "/prev";
var playSongUrl = basePlayQueueUrl + "/song/";
var playCurrentUrl = basePlayQueueUrl + "/playing"
// attributes
var queueLineSongIdAttr = "songId";
var srcAttribute = "src";
// css ids
var playerId = "#player";
var nextId = "#controlsNext";
var prevId = "#controlsPrev";
var playId = "#controlsPlay";
// css classes
var nowPlayingQueueItemClass = "nowPlayingQueueItem";
var nowPlayingQueueSongClass = "playQueueSong";
var nowPlayingHighlightClass = "bg-primary";
// fully qualified css classes
var nowPlayingQueueItemClassFQ = "." + nowPlayingQueueItemClass;
var nowPlayingQueueSongClassFQ = "."+ nowPlayingQueueSongClass; 


/* Glyphicon classes */
var playGlyph = "glyphicon-play";
var pauseGlyph = "glyphicon-pause";

/* Global instances */
var playerDomElement = $(playerId);
var player = playerDomElement[0];
var playDomElement = $(playId);

function next() {
	playSongFromQueue(playNextUrl);
}

function prev() {
	playSongFromQueue(playPrevUrl);
}

function setCurentlyPlaying() {
	$.ajax({
		  url: playCurrentUrl
		}).done(function(data) {
			setNowPlayingQueue($("#"+data));
		});
}

function playSongFromQueue(nextOrPrevUrl) {
	$.ajax({
		  url: nextOrPrevUrl
		}).done(function(data) {
			playSong(data);
			setNowPlayingQueue($("#"+data));
		});
}

function setNowPlayingQueue(playedElement) {
	$(nowPlayingQueueItemClassFQ).removeClass(nowPlayingHighlightClass);
	playedElement.closest("tr").addClass(nowPlayingHighlightClass);
}

function play() {
	if(player.paused) {
		player.play();
		playDomElement.addClass(pauseGlyph);
		playDomElement.removeClass(playGlyph);
	} else {
		console.log(player);
		player.pause();
		playDomElement.addClass(playGlyph);
		playDomElement.removeClass(pauseGlyph);
	}
}

function playSong(songId) {
	$(playerId).attr(srcAttribute, playSongUrl + songId);
}

/* function that loads all queue required js */
function loadQueueJs() {
	setCurentlyPlaying();
	$(nowPlayingQueueSongClassFQ).click(function() {
		  playSong($(this).attr(queueLineSongIdAttr));
		  setNowPlayingQueue($(this));
	  });
}



/* DOM Ready Loading done here */
$(function() {
	  $(nextId).click(function() {
		  next();
	  });
	  $(prevId).click(function() {
		  prev();
	  });
	  $(playId).click(function() {
		  play();
	  });
	  playerDomElement.on("ended", function(){
		 next();
	  });
	  
	});
