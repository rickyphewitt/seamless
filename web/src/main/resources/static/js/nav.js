/*
* Script contains the JS used for navigation
*
*/

/*
* Toggles menu from artists to menu
*/
function toggleMenu() {
    $(appSidebarContentArtists).toggle();
    $(appSidebarContentMenu).toggle();

}

/*
* Sets main heading
*/
function setMainHeading(heading) {
    $(appMainContentHeading).text(heading);
}

function accentArtist(artist) {
    //remove existing accents
    $("li" + selectedTextFQ).each(function(index, value){
        $(value).removeClass(selectedText);
        $(value).removeClass(secondaryText);
    })
    artist.addClass(selectedText);
    artist.addClass(secondaryText);

}

function navGoTo(location) {
	$(innerContentDiv).load(location);
}

/* Artist Nav */
function goToArtist(artist) {
    $(mainContent).load(artistsUrl +"/"+ artist.attr(entityId), function() {
        setMainHeading(artist.text());
        accentArtist(artist);
        setActive(appActiveArtistName, artist.text());
        //loadAlbumPlayJs();
		 // open album
		 $(album).click(function() {
			 goToAlbum($(this));
		  });
	});


}
/* Album Nav */
function goToAlbum(album) {
	//console.log('AlbumId: ' + albumIdAttr);
	$(mainContent).load(albumUrl +"/"+ album.attr(entityId), function() {
        setMainHeading(album.text())
        setActive(appActiveAlbumName, album.text());
        loadAlbumSongPlayJs();
	});
}

function setActive(attribute, value) {
    $(appActiveInfo).attr(attribute, value);
}

/* Footer Nav */


/* DOM Ready Loading done here */
$(function() {
	// onclick nav events
    $(hamNav).click(function() {
        toggleMenu();
    })

	// $(artistsTab).click(function(){
	// 	navGoTo(homeUrl);
	// })
	// $(albumsTab).click(function(){
	// 	navGoTo(albumsUrl);
	// })
	// $(genresTab).click(function(){
	// 	navGoTo(genresUrl);
	// })
	// $(playlistsTab).click(function(){
	// 	navGoTo(playlistsUrl);
	// })
	// $(favoritesTab).click(function(){
	// 	navGoTo(favoritesUrl);
	// })
	// $(settingsTab).click(function(){
	// 	navGoTo(settingsUrl);
	// })

	// opens now playing queue
	$(appQueue).click(function(){
		$(mainContent).load(nowPlayingQueueUrl, function(){
            setMainHeading(NOW_PLAYING_TEXT);
			loadQueueJs();
		});
	});

	// open artist
	 $(artistNavMenuItem).click(function() {
		  goToArtist($(this));
	  });

});
