package com.rickyphewitt.seamless.data.sources;

import com.rickyphewitt.seamless.data.enums.IdSource;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public abstract class BaseMediaSource {

	// Attributes
	private String name;
	private IdSource source;
	private String configFileName;
	private int version;

	// Constructor
	public BaseMediaSource(){}
	
	public BaseMediaSource(String name, IdSource source) {
		this.name = name;
		this.source = source;
		this.configFileName = source.name() + "." + this.generateTimestamp();
	}
	
	
	// Getters/Setters
	public String getName() {
		return name;
	}

	public IdSource getSource() {
		return source;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSource(IdSource source) {
		this.source = source;
	}

	public String getConfigFileName() {
		if(this.configFileName == null) {
			this.configFileName = generateConfigFileName();
		}
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	private String generateTimestamp() {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf.format(timestamp);
	}

	private String generateConfigFileName() {
		return this.source.name() + "." + this.generateTimestamp();

	}

}
