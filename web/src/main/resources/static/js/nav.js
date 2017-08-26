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


function filterArtists(filterBy) {
    $(artistNavMenuItem).each(function(index, value){
        if(filterBy == '') {
            $(this).show();
        }
        if($(this).text().toLowerCase().indexOf(filterBy.toLowerCase()) !== -1) {
            $(this).show();
        } else {
            $(this).hide();
        }
    })
}

/* Footer Nav */


/* DOM Ready Loading done here */
$(function() {
	// onclick nav events
    $(hamNav).click(function() {
        toggleMenu();
    });

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

	// filter for artists
	$(search).on("input", function(){
        filterArtists($(this).val());
	});

});
