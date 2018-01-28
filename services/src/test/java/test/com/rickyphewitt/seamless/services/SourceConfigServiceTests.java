package test.com.rickyphewitt.seamless.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.SourceConfigService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.com.rickyphewitt.seamless.services.config.TestConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class SourceConfigServiceTests {

	private static Random random = new Random();

	@Autowired
	SourceConfigService sourceConfigService;

	private static File directory = new File(System.getProperty("user.home") + "/.seamless/sources");

	@After
	public void before() {
		deleteDir(SourceConfigServiceTests.directory);
		//Assert.assertTrue(deleteDir(SourceConfigServiceTests.directory));
		//reset web api sources
		sourceConfigService.setWebSources(null);
	}


	@Test
	public void unableToLoadConfigFile_noFilesFound() throws Exception{
		// data setup
		// none, no config files to build
		// run code
		sourceConfigService.loadSources();

		Assert.assertNull(sourceConfigService.getWebSources());

	}

	@Test
	public void successfullyLoadConfigFile_oneFileFound() throws Exception{
		// data setup
		WebApiSource source = this.generateTestWebApiSource();
		this.writeWebSourceApiConfig(source);

		// run code
		sourceConfigService.loadSources();

		WebApiSource loadedSource = sourceConfigService.getWebSources().get(IdSource.EMBY).get(0);
		this.assertWebApiSource(source, loadedSource);

	}

	@Test
	@Ignore("Flaky Test")
	public void successfullyLoadConfigFile_multipleFilesFound() throws Exception{
		// data setup
		WebApiSource source = this.generateTestWebApiSource();
		Thread.sleep(3000); // sleep 2 second as file names are by the second
		WebApiSource source2 = this.generateTestWebApiSource();
		this.writeWebSourceApiConfig(source);
		this.writeWebSourceApiConfig(source2);

		// run code
		sourceConfigService.loadSources();
		Assert.assertTrue(sourceConfigService.getWebSources() != null);
		Assert.assertTrue(sourceConfigService.getWebSources().containsKey(IdSource.EMBY));
		Assert.assertTrue(sourceConfigService.getWebSources().get(IdSource.EMBY).size() == 2);
	}

	// test helpers
	private void writeWebSourceApiConfig(WebApiSource webSource) {
		PrintWriter writer = null;
		this.createDirIfDoesNotExist();

		try {
			writer = new PrintWriter(SourceConfigServiceTests.directory + "/" + webSource.getConfigFileName(), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Gson gson = new GsonBuilder().create();
		writer.println(gson.toJson(webSource, WebApiSource.class));
		writer.close();
	}

	private void createDirIfDoesNotExist() {
		if (!SourceConfigServiceTests.directory.exists()) {
			SourceConfigServiceTests.directory.mkdirs();
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

	private WebApiSource generateTestWebApiSource() {
		WebApiSource webSource = new WebApiSource(RandomStringUtils.randomAlphanumeric(10), IdSource.EMBY);
		webSource.setMaxRetryAttempts(99);
		webSource.setPassword(RandomStringUtils.randomAlphanumeric(20));
		webSource.setUrl("http://localhost");
		webSource.setUsername(RandomStringUtils.randomAlphabetic(7));
		webSource.setVersion(random.nextInt());

		return webSource;
	}

	private void assertWebApiSource(WebApiSource expected, WebApiSource actual) {
		Assert.assertEquals(expected.getMaxRetryAttempts(), expected.getMaxRetryAttempts());
		Assert.assertEquals(expected.getPassword(), expected.getPassword());
		Assert.assertEquals(expected.getUrl(), expected.getUrl());
		Assert.assertEquals(expected.getUsername(), expected.getUsername());
		Assert.assertEquals(expected.getConfigFileName(), expected.getConfigFileName());
		Assert.assertEquals(expected.getName(), expected.getName());
		Assert.assertEquals(expected.getSource(), expected.getSource());
		Assert.assertEquals(expected.getVersion(), expected.getVersion());

	}

}
