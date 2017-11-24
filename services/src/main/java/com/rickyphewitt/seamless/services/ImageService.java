package com.rickyphewitt.seamless.services;

import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.enums.IdSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class ImageService {

	@Autowired
	Aggregator aggregatorService;

	@Autowired
	AlbumService albumService;

	/**
	 * Gets image by entityId and entity Id source
	 *
	 * @param entityId
	 * @param entityidSource
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public byte[] getImage(String entityId, IdSource entityidSource) throws InterruptedException, ExecutionException {
		Album album = albumService.getAlbumsMap().get(entityId);
		String imageUrl = aggregatorService.getPrimaryImageUrl(entityId, album.getPrimaryImage());
		return aggregatorService.getImage(imageUrl);
	}

	/**
	 *
	 * Gets primary Image url
	 *
	 * @param entityId
	 * @param primaryImageId
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private String getPrimaryImage(String entityId, String primaryImageId) throws ExecutionException, InterruptedException {
		return aggregatorService.getPrimaryImageUrl(entityId, primaryImageId);
	}
}
