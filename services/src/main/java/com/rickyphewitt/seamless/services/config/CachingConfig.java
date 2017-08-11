package com.rickyphewitt.seamless.services.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer {
	
	public final static String ARTIST_CACHE = "artists";
	public final static String ALBUM_CACHE = "albums";
	public final static String SONG_CACHE = "songs";
	public final static String RAW_SONG_CACHE = "raw_songs";
    
	@Bean
    @Override
    public CacheManager cacheManager() {    	
		GuavaCache artistCache = new GuavaCache(ARTIST_CACHE, 
    			CacheBuilder
    			.newBuilder()
    			.maximumSize(10000)
    			.expireAfterAccess(1, TimeUnit.DAYS)
    			.build());
    	GuavaCache AlbumCache = new GuavaCache(ALBUM_CACHE, 
    			CacheBuilder
    			.newBuilder()
    			.maximumSize(10000)
    			.expireAfterAccess(1, TimeUnit.DAYS)
    			.build());
    	GuavaCache SongCache = new GuavaCache(SONG_CACHE, 
    			CacheBuilder
    			.newBuilder()
    			.maximumSize(10000)
    			.expireAfterAccess(1, TimeUnit.DAYS)
    			.build());
    	GuavaCache rawSongCache = new GuavaCache(RAW_SONG_CACHE, 
    			CacheBuilder
    			.newBuilder()
    			.maximumSize(100)
    			.expireAfterAccess(2, TimeUnit.HOURS)
    			.build());
    	
    	SimpleCacheManager cacheManager = new SimpleCacheManager();
    	cacheManager.setCaches(Arrays.asList(artistCache, AlbumCache, SongCache, rawSongCache));
    	
    	return cacheManager;
    }

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}
}