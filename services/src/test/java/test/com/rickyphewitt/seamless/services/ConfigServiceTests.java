package test.com.rickyphewitt.seamless.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.seamless.data.Config;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import com.rickyphewitt.seamless.services.ConfigService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.com.rickyphewitt.seamless.services.config.TestConfig;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ConfigServiceTests {

	@Autowired
	ConfigService configService;

	private static File directory = new File(System.getProperty("user.home") + "/.seamless");

	@After
	public void cleanup() {
		Assert.assertTrue(deleteDir(ConfigServiceTests.directory));
	}

	@Test
	public void unableToLoadConfigFile_unexpectedExceptionLoadDefaults() throws Exception{
		// data setup
		PrintWriter writer = null;
		this.createDirIfDoesNotExist();

		try {
			writer = new PrintWriter(directory + "/.config.json", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		writer.println("blab");
		writer.close();

		// run code
		configService.loadConfigFromFile();

		assertAppDefaults();

	}

	@Test
	public void customConfigFile_successfulLoad() throws IOException {
		// data setup
		int prefetchSongCount = 100;
		String source = "blab";
		String configDir = "new/config/dir/here";
		Config c = new Config();

		c.setPrefetchSongCount(prefetchSongCount);
		c.setSource(source);
		c.setConfigDirectory(configDir);
		writeConfigfile(c);


		// run
		configService.loadConfigFromFile();

		// assert
		Assert.assertTrue(configService.getConfig().getPrefetchSongCount() == prefetchSongCount);
		Assert.assertTrue(configService.getConfig().getSource().equals(source));
		Assert.assertTrue(configService.getConfig().getConfigDirectory().equals(configDir));

	}

	@Test
	public void customConfigFile_successfulWrite() throws ConfigException {
		// data setup
		int prefetchSongCount = 100;
		String source = "blab";
		String configDir = "new/config/dir/here";
		Config c = new Config();

		c.setPrefetchSongCount(prefetchSongCount);
		c.setSource(source);
		c.setConfigDirectory(configDir);

		// run
		configService.writeConfigfile(c);

		// load
		configService.loadConfigFromFile();

		// assert
		Assert.assertTrue(configService.getConfig().getPrefetchSongCount() == prefetchSongCount);
		Assert.assertTrue(configService.getConfig().getSource().equals("file"));
		Assert.assertTrue(configService.getConfig().getConfigDirectory().equals(configDir));

	}

	// test helpers
	private void writeConfigfile(Config c) {
		PrintWriter writer = null;
		this.createDirIfDoesNotExist();

		try {
			writer = new PrintWriter(directory + "/.config.json", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Gson gson = new GsonBuilder().create();
		writer.println(gson.toJson(c, Config.class));
		writer.close();
	}

	private void createDirIfDoesNotExist() {
		if (!ConfigServiceTests.directory.exists()) {
			ConfigServiceTests.directory.mkdir();
		}
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	private void assertAppDefaults() {
		Assert.assertTrue("default".equals(configService.getConfig().getSource()));
		Assert.assertTrue(5 == configService.getConfig().getPrefetchSongCount());
	}


}
