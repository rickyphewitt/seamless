/*
* Script contains the common variables
*
*/

// Text contants
var NOW_PLAYING_TEXT = "Now Playing";
var SETTINGS_TEXT = "Settings";
var IMAGE_LOCATION = "images/";
var PLAY_IMG = IMAGE_LOCATION + "playLight.png";
var PAUSE_IMG = IMAGE_LOCATION + "pauseLight.png";
var SUCCESS = "Success";

// key prepended to all functional vs stylistic classes
var attrKey = "app";
var idAttrKey = "#" + attrKey;
var classAttrKey = "." + attrKey;

// entityId == id of entity (album/artist/song/ect)
var entityId = "entityId";
var primaryImage = "primaryImage";

// headings
var appMainContentHeading = idAttrKey + "MainContentHeading";

// content ids replaced by fragments
var sideBarNav = idAttrKey + "SidebarContent";
var mainContent = idAttrKey + "ContentContent";
var appSidebarContentArtists = classAttrKey + "ContentList";
var appSidebarContentMenu = classAttrKey + "MenuList";

var errorDiv = idAttrKey + "Error";
var successDiv = idAttrKey + "Success";

/* Urls */
var apiV1 = "/api/v1";
var homeUrl = "/home";
var artistsUrl = "/artists";
var albumsUrl = "/albums";
var albumUrl = "/album";
var genresUrl = "/genres";
var playlistsUrl = "/playlists";
var favoritesUrl = "/favorites";
var settingsUrl = "/settings";
var nowPlayingQueueUrl = "/queue";
var imagesUrl = "media/images";
var noAlbumImageUrl = IMAGE_LOCATION + "no_album_200.png"
var sourcesUrl = "/sources";

// nav
var hamNav = idAttrKey + "HamNav";
var navContentList = classAttrKey + "ContentList";
var navMenuList = classAttrKey + "MenuList";
var settingsNav = idAttrKey + "Settings";
var artistNavMenuItem = classAttrKey + "ArtistNav";
var album = classAttrKey + "Album";
var search = idAttrKey + "Search";
var addSourceId = idAttrKey + "AddSource";


//attributes
var artistIdAttr = "id";
var nowPlayingQueueId = "#nowPlayingQueue";

// css classes
var selectedText = "selectedText";
var secondaryText = "secondaryText";
var selectedTextFQ = "." + selectedText;
var secondaryTextFQ = "." + secondaryText;

var artistClass = "artist";
var albumClass = "album";
// fully qualified css classes
var artistClassFQ = "." + artistClass;
var albumClassFQ = "." + albumClass;

/*
* Player Globals
*/
// urls
var basePlayUrl = "/play";
var basePlayQueueUrl = basePlayUrl + "/queue";
var playNextUrl = basePlayQueueUrl + "/next";
var playPrevUrl = basePlayQueueUrl + "/prev";
var playSongUrl = basePlayQueueUrl + "/song/";
var playCurrentUrl = basePlayQueueUrl + "/playing"

var playArtistUrl = basePlayUrl + "/artist/";
var playAlbumUrl = basePlayUrl + "/album/";
var shuffleUrl = "/shuffle";
// queue
var appQueue = idAttrKey + "Queue";
var appQueueRow = classAttrKey + "QueueTr";

// attributes
var queueLineSongIdAttr = "songId";
var albumIdAttr = "albumId";
var trackNumberAttr = "trackNumber";
var srcAttribute = "src";
var songNameAttr = "songName";
var appActionAttr = attrKey + "Action";

var appPlayerId = idAttrKey + "Player";
var appNextId = idAttrKey + "Next";
var appPrevId = idAttrKey + "Prev";
var appPlayId = idAttrKey + "Play";
var appPlayingSongName = idAttrKey + "NowPlayingSong";
var appPlayingSongArtistName = idAttrKey + "NowPlayingArtist";
var appPlayingSongAlbumName = idAttrKey + "NowPlayingAlbum";
var appPlayingSongAlbumArt = idAttrKey + "NowPlayingAlbumArt";
var appActiveArtistName = "artist";
var appActiveAlbumName = "album";
var appActiveSongName = "song";
var appActiveInfo = idAttrKey + "ActiveInfo";
var appVolumeSlider = idAttrKey + "Volume";
var appNowPlayingSlider = idAttrKey + "NowPlayingTime";
var appGroupPlay = idAttrKey + "GroupPlay";
var appGroupShuffle = idAttrKey + "GroupShuffle";
var appGroupMix = idAttrKey + "GroupMix";
var appSettingsForm = idAttrKey + "SettingsForm";
var appSourcesForm = idAttrKey + "SourcesForm";
// css classes
var nowPlayingQueueItemClass = "nowPlayingQueueItem";
var nowPlayingQueueSongClass = classAttrKey + "PlaySong";
var nowPlayingHighlightClass = "bg-primary";
var albumPlayClass = "albumPlay";
// fully qualified css classes
var albumPlayClassFQ = "."+ albumPlayClass;
