
/*
* Script contains the JS used for the HTML5 Player
*
*/

/* Global Vars */
// Urls
var basePlayUrl = "/play";
var basePlayQueueUrl = basePlayUrl + "/queue";
var playNextUrl = basePlayQueueUrl + "/next";
var playPrevUrl = basePlayQueueUrl + "/prev";
var playSongUrl = basePlayQueueUrl + "/song/";
var playCurrentUrl = basePlayQueueUrl + "/playing"

var playAlbumUrl = basePlayUrl + "/album/";

// attributes
var queueLineSongIdAttr = "songId";
var albumIdAttr = "albumId";
var trackNumberAttr = "trackNumber";
var srcAttribute = "src";
var songNameAttr = "songName";

// css ids
var playerId = "#player";
var nextId = "#controlsNext";
var prevId = "#controlsPrev";
var playId = "#controlsPlay";
var playingSongName = "#playingSongName";
var playingSongArtistName = "#playingSongArtistName";
var playingSongAlbumName = "#playingSongAlbumName";
var activeArtistName = "#artistName";
var activeAlbumName = "#albumName";

// css classes
var nowPlayingQueueItemClass = "nowPlayingQueueItem";
var nowPlayingQueueSongClass = "playSong";
var nowPlayingHighlightClass = "bg-primary";
var albumPlayClass = "albumPlay";
// fully qualified css classes
var nowPlayingQueueItemClassFQ = "." + nowPlayingQueueItemClass;
var nowPlayingQueueSongClassFQ = "."+ nowPlayingQueueSongClass; 
var albumPlayClassFQ = "."+ albumPlayClass;

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
			setNowPlayingQueueItem($("#"+data));
		});
}

function playSongFromQueue(nextOrPrevUrl) {
	$.ajax({
		  url: nextOrPrevUrl
		}).done(function(data) {
			playSong(data);
			setNowPlayingQueueItem($("#"+data));
		});
}

function setNowPlayingQueueItem(playedElement) {
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

function playAlbum(albumId) {
	$(playerId).attr(srcAttribute, playAlbumUrl + albumId);
	
}

function playSongFromAlbum(albumId, trackNumber) {
	$(playerId).attr(srcAttribute, playAlbumUrl + albumId + "/" + trackNumber);
}

function setPlayerInfo(artistName, albumName, songName) {
	$(playingSongName).text(songName);
	$(playingSongArtistName).text(artistName);
	$(playingSongAlbumName).text(albumName);
}

/* function that loads all queue required js */
function loadQueueJs() {
	setCurentlyPlaying();
	$(nowPlayingQueueSongClassFQ).click(function() {
		  playSong($(this).attr(queueLineSongIdAttr));
		  setNowPlayingQueueItem($(this));
	  });
}

/* function that loads all album play js */
function loadAlbumPlayJs() {
	 $(albumPlayClassFQ).click(function() {
		  playAlbum($(this).attr(albumIdAttr));
	  });
	 
}

/* function that loads all song play js */
function loadAlbumSongPlayJs() {
	$(nowPlayingQueueSongClassFQ).click(function() {
		playSongFromAlbum($(this).attr(albumIdAttr), $(this).attr(trackNumberAttr));
		setPlayerInfo($(activeArtistName).text(), $(activeAlbumName).text(), $(this).attr(songNameAttr));
		setNowPlayingQueueItem($(this));
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
