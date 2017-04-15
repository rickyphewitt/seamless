/* 
* Script contains the JS used for navigation
*
*/

/* Global Vars */
var innerContentDiv = "#innerContent";
var nowPlayingQueueId = "#nowPlayingQueue";
var nowPlayingQueueUrl = "/queue";

/* Footer Nav */

// open now playing queue
$(nowPlayingQueueId).click(function(){
	$(innerContentDiv).load(nowPlayingQueueUrl);
});

