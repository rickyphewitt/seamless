package com.rickyphewitt.seamless.services.sources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.Artist;
import com.rickyphewitt.seamless.data.Song;

public interface AsyncSourceService {
	@Async
	CompletableFuture<List<Artist>> getAsyncArtists();
	@Async
	CompletableFuture<List<Album>> getAsyncAlbumsByArtist(String artistSourceId);
	@Async
	CompletableFuture<List<Album>> getAsyncAlbums();
	@Async
	CompletableFuture<List<Song>> getAsyncSongsInAlbum(String albumSourceId);
	@Async
	CompletableFuture<byte[]> playAsyncSong(String songSourceId);
	@Async
	CompletableFuture<String> getAsyncPrimaryImage(String entitySourceId, String primaryImageId);
	@Async
	CompletableFuture<byte[]> getAsyncImage(String url);
}
