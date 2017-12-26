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
	$(mainContent).load(location);
}

/* Artist Nav */
function goToArtist(artist) {
    $(mainContent).load(artistsUrl +"/"+ artist.attr(entityId), function() {
        setMainHeading(artist.text());
        accentArtist(artist);
        setActive(appActiveArtistName, artist.text());
        setGroupActions(artistClass, artist.attr(entityId));

        //loadAlbumPlayJs();
		 // open album
		 $(album).click(function() {
			 goToAlbum($(this));
		  });

		  //load image
		  $(album).each(function(index, value){
		    console.log($(value));
		    getPrimaryImage($(value).attr(entityId), $(value).attr(primaryImage));

		  })
	});


}
/* Album Nav */
function goToAlbum(album) {
	//console.log('AlbumId: ' + albumIdAttr);
	$(mainContent).load(albumUrl +"/"+ album.attr(entityId), function() {
        setMainHeading(album.text())
        setActive(appActiveAlbumName, album.text());
        setGroupActions(albumClass, album.attr(entityId));
        loadAlbumSongPlayJs();
	});
}

function setActive(attribute, value) {
    $(appActiveInfo).attr(attribute, value);
}

function setGroupActions(groupType, mediaId) {

    action = ''
    switch(groupType) {
        case artistClass:
        action = getGroupActionArtist(mediaId)
        break;
        case albumClass:
        action = getGroupActionAlbum(mediaId)
        break;
    }

    $(appGroupShuffle).attr(appActionAttr, action);
}

function getGroupActionArtist(mediaId) {
    return playArtistUrl + mediaId + shuffleUrl
}

function getGroupActionAlbum(mediaId) {
    return playAlbumUrl + mediaId + shuffleUrl
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

/* Settings Nav */
function goToSettings() {
    $(mainContent).load(settingsUrl, function(){
        setMainHeading(SETTINGS_TEXT);
        saveSettings();

    });
    //navGoTo(settingsUrl);


}

function saveSettings() {
    ajaxFormPost(appSettingsForm);
}

function ajaxFormPost(postHandle) {
    var $form = $(postHandle);
    $form.on('submit', function(e) {
        resetFormMessages();
        e.preventDefault();
        $.ajax({
            url: $form.attr('action'),
            type: 'post',
            data: $form.serialize(),
            success: function(response) {
                if(!response.success) {
                    $(errorDiv).fadeIn('slow', function(){
                        $(errorDiv).text(response.error);
                    });
                } else {
                    $(successDiv).fadeIn('slow', function(){
                        $(successDiv).text(SUCCESS);
                    });

                }
            }
        })
    });
}


function getPrimaryImage(entityId, imageTagId) {
    // returns default image if none exists
    image_url = imagesUrl + "/" + entityId
    $.ajax(image_url, {
        success: function () {
            $("#"+ entityId).find("img").attr("src", imagesUrl + "/" + entityId);
       }
    });

}

function resetFormMessages() {
    $(errorDiv).hide();
    $(errorDiv).text("");
    $(successDiv).hide();
    $(successDiv).text("");

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

    // opens settings
    $(settingsNav).click(function() {
        goToSettings();
    });

});
