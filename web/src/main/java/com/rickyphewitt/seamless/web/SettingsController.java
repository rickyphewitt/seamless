package com.rickyphewitt.seamless.web;

import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.SimpleResponse;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.FragmentService;
import com.rickyphewitt.seamless.services.SettingsService;
import com.rickyphewitt.seamless.services.SimpleErrorService;
import com.rickyphewitt.seamless.services.SourceConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SettingsController {

	@Autowired 
	FragmentService fragmentService;

	@Autowired
	SettingsService settingsService;

	@Autowired
	SourceConfigService sourceConfigService;

	@Autowired
	SimpleErrorService simpleErrorService;
	
	@GetMapping("/settings")
	public String settings(Model model) {
		return fragmentService.getFragment("settings");
	}

	@PostMapping(value = "/settings", produces = {"application/JSON"})
	public @ResponseBody
	SimpleResponse save(@ModelAttribute @Valid Config config, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return simpleErrorService.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
		} else {
			try {
				settingsService.writeConfig(config);
			} catch (ConfigException e) {
				return simpleErrorService.error(e.getMessage());
			}
		}
		return simpleErrorService.success();

	}

	// model mappings
	@ModelAttribute("config")
	public Config returnConfig() {
		return this.settingsService.getConfig();
	}

	@ModelAttribute("sources")
	public List<WebApiSource> getSources() {
		List<WebApiSource> sources = new ArrayList();

		// load existing sources if any exist
		if(this.sourceConfigService.getWebSources() != null && this.sourceConfigService.getWebSources().size() > 0){
			for(IdSource source: this.sourceConfigService.getWebSources().keySet()) {
				sources.addAll(this.sourceConfigService.getWebSources().get(source));
			}
		}


		return sources;
	}
}
