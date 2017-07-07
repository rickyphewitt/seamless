
/*
* Script contains the JS used for the HTML5 Player
*
*/

/* Global Vars */
// Urls
// var basePlayUrl = "/play";
// var basePlayQueueUrl = basePlayUrl + "/queue";
// var playNextUrl = basePlayQueueUrl + "/next";
// var playPrevUrl = basePlayQueueUrl + "/prev";
// var playSongUrl = basePlayQueueUrl + "/song/";
// var playCurrentUrl = basePlayQueueUrl + "/playing"
//
// var playAlbumUrl = basePlayUrl + "/album/";
//
// // attributes
// var queueLineSongIdAttr = "songId";
// var albumIdAttr = "albumId";
// var trackNumberAttr = "trackNumber";
// var srcAttribute = "src";
// var songNameAttr = "songName";
//
// // css ids
// var appPlayerId = "#player";
// var nextId = "#controlsNext";
// var prevId = "#controlsPrev";
// var playId = "#controlsPlay";
// var playingSongName = "#playingSongName";
// var playingSongArtistName = "#playingSongArtistName";
// var playingSongAlbumName = "#playingSongAlbumName";
// var activeArtistName = "#artistName";
// var activeAlbumName = "#albumName";
//
// // css classes
// var nowPlayingQueueItemClass = "nowPlayingQueueItem";
// var nowPlayingQueueSongClass = "playSong";
// var nowPlayingHighlightClass = "bg-primary";
// var albumPlayClass = "albumPlay";
// // fully qualified css classes
// var nowPlayingQueueItemClassFQ = "." + nowPlayingQueueItemClass;
// var nowPlayingQueueSongClassFQ = "."+ nowPlayingQueueSongClass;
// var albumPlayClassFQ = "."+ albumPlayClass;

/* Glyphicon classes */
var playGlyph = "glyphicon-play";
var pauseGlyph = "glyphicon-pause";

/* Global instances */
var playerDomElement = $(appPlayerId);
var player = playerDomElement[0];
var playDomElement = $(appPlayId);

function getQueueSongFromEntityId(songId) {
    return $(appQueueRow).find("[" + entityId + "='" + songId + "']");
}

function next() {
	playSongFromQueue(playNextUrl);
}

function prev() {
	playSongFromQueue(playPrevUrl);
}

function setCurentlyPlaying() {
	$.ajax({
		  url: playCurrentUrl
      }).done(function(songId) {
            playedElement = getQueueSongFromEntityId(songId);
			setNowPlayingQueueItem($(playedElement));
		});
}

function playSongFromQueue(nextOrPrevUrl) {
	$.ajax({
		  url: nextOrPrevUrl
      }).done(function(songId) {
			playSong(songId);
            playedElement = getQueueSongFromEntityId(songId);
			setNowPlayingQueueItem($(playedElement));
		});
}

function setNowPlayingQueueItem(playedElement) {
    $(appQueueRow).each(function(index, value) {
        $(value).removeClass(selectedText);
    })
	playedElement.closest("tr").addClass(selectedText);
    setPlayerInfo($(appActiveInfo).attr(appActiveArtistName), $(appActiveInfo).attr(appActiveAlbumName), $(playedElement).attr(songNameAttr));
}

function play() {
    console.log(player);
	if(player.paused) {
		player.play();
        setPlayPauseImg(PAUSE_IMG);
    } else {
		player.pause();
        setPlayPauseImg(PLAY_IMG);
	}
}

function setPlayPauseImg(IMG_SRC) {
    playDomElement.children("img").attr("src", IMG_SRC);
}

function playSong(songId) {
	$(appPlayerId).attr(srcAttribute, playSongUrl + songId);
}

function playAlbum(albumId) {
	$(appPlayerId).attr(srcAttribute, playAlbumUrl + albumId);

}

function playSongFromAlbum(albumId, trackNumber) {
	$(appPlayerId).attr(srcAttribute, playAlbumUrl + albumId + "/" + trackNumber);
}

function setPlayerInfo(artistName, albumName, songName) {
	$(appPlayingSongName).text(songName);
	$(appPlayingSongArtistName).text(artistName);
	$(appPlayingSongAlbumName).text(albumName);
}

/* function that loads all queue required js */
function loadQueueJs() {
	setCurentlyPlaying();
	$(nowPlayingQueueSongClass).click(function() {
		  playSong($(this).attr(entityId));
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
	$(nowPlayingQueueSongClass).click(function() {
		playSongFromAlbum($(this).attr(albumIdAttr), $(this).attr(trackNumberAttr));
		setNowPlayingQueueItem($(this));
	  });
}

/* Volume Slider functions */
function initVolumeSilder() {
    $(appVolumeSlider).slider({
               value: 50,
               animate:"slow",
               orientation: "horizontal",
               slide: function(event, ui) {
                   player.volume = (ui.value / 100);
               }
            });
}

function initNowPlayingSlider() {
    $(appNowPlayingSlider).slider({
               value: 0,
               animate:"fast",
               orientation: "horizontal"
            });
}

function doTimeUpdate(track) {
    var totalDurration = track.duration;
    var currentDurration = track.currentTime;

    var percentOf = (currentDurration / totalDurration) * 100;
    $(appNowPlayingSlider).children("span").css("left", percentOf + "%");
}

/* DOM Ready Loading done here */
$(function() {
	  $(appNextId).click(function() {
		  next();
	  });
	  $(appPrevId).click(function() {
		  prev();
	  });
	  $(appPlayId).click(function() {
		  play();
	  });
	  playerDomElement.on("ended", function(){
		 next();
	  });
      initVolumeSilder();
      initNowPlayingSlider();
	});
