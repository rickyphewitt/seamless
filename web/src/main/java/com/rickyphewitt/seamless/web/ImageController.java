package com.rickyphewitt.seamless.web;

import com.rickyphewitt.emby.api.services.clients.ApiV1Client;
import com.rickyphewitt.seamless.data.Album;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

@Controller
public class ImageController {

	@Autowired
	ImageService imageService;

	@RequestMapping("media/images/{id}")
	public @ResponseBody byte[] getImage(@PathVariable("id") String entityId, Model model)
			throws InterruptedException, ExecutionException {
		return imageService.getImage(entityId, IdSource.EMBY);
	}
}
