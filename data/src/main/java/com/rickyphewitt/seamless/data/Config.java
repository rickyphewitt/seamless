package com.rickyphewitt.seamless.data;

public class Config {

	private String source;
	private int prefetchSongCount;
	private String configDirectory;
	private int version;

	public int getPrefetchSongCount() {
		return prefetchSongCount;
	}

	public void setPrefetchSongCount(int prefetchSongCount) {
		this.prefetchSongCount = prefetchSongCount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getConfigDirectory() {
		return configDirectory;
	}

	public void setConfigDirectory(String configDirectory) {
		this.configDirectory = configDirectory;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
