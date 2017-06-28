/* 
* Script contains the JS used for navigation
*
*/

/* Global Vars */
/* Urls */
var homeUrl = "/home"
var artistsUrl = "/artists";
var albumsUrl = "/albums"
var albumUrl = "/album"
var genresUrl = "/genres"
var playlistsUrl = "/playlists"
var favoritesUrl = "/favorites"
var settingsUrl = "/settings"
var nowPlayingQueueUrl = "/queue";
//attributes
var artistIdAttr = "id";
//css ids
var artistsTab = "#artistsTab";
var albumsTab = "#albumsTab";
var genresTab = "#genresTab";
var playlistsTab = "#playlistsTab";
var favoritesTab = "#favoritesTab";
var settingsTab = "#settingsTab";
var innerContentDiv = "#innerContent";
var nowPlayingQueueId = "#nowPlayingQueue";

// css classes
var artistClass = "artist";
var artistsAllClass = "artistsAll";
var albumClass = "album";
// fully qualified css classes
var artistClassFQ = "." + artistClass;
var albumClassFQ = "." + albumClass;

/* Top Nav */
function navGoTo(location) {
	$(innerContentDiv).load(location);
}

/* Artist Nav */
function gotToArtist(artist) {
    $(innerContentDiv).toggleClass(artistsAllClass);
	$(innerContentDiv).load(artistsUrl +"/"+ artist.attr(artistIdAttr), function() {
		loadAlbumPlayJs();
		 // open album
		 $(albumClassFQ).click(function() {
			 console.log($(this));
			 goToAlbum($(this));
		  });
	});
	
    
}
/* Album Nav */
function goToAlbum(album) {
	console.log('AlbumId: ' + albumIdAttr);
	$(innerContentDiv).load(albumUrl +"/"+ album.attr(albumIdAttr), function() {
		loadAlbumSongPlayJs();
	});
}

/* Footer Nav */


/* DOM Ready Loading done here */
$(function() {
	// onclick nav events
	$(artistsTab).click(function(){
		navGoTo(homeUrl);
	})
	$(albumsTab).click(function(){
		navGoTo(albumsUrl);
	})
	$(genresTab).click(function(){
		navGoTo(genresUrl);
	})
	$(playlistsTab).click(function(){
		navGoTo(playlistsUrl);
	})
	$(favoritesTab).click(function(){
		navGoTo(favoritesUrl);
	})
	$(settingsTab).click(function(){
		navGoTo(settingsUrl);
	})
	
	// opens now playing queue
	$(nowPlayingQueueId).click(function(){
		$(innerContentDiv).load(nowPlayingQueueUrl, function(){
			loadQueueJs();
		});
	});
	
	// open artist
	 $(artistClassFQ).click(function() {  
		  gotToArtist($(this));
	  });

});

