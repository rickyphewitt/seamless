package test.com.rickyphewitt.seamless.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickyphewitt.seamless.data.enums.IdSource;
import com.rickyphewitt.seamless.data.exceptions.ConfigException;
import com.rickyphewitt.seamless.data.exceptions.ConfigNotFoundException;
import com.rickyphewitt.seamless.data.sources.WebApiSource;
import com.rickyphewitt.seamless.services.FileService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.com.rickyphewitt.seamless.services.config.TestConfig;

import java.io.*;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class FileServiceTests {

	private static Random random = new Random();

	private static File directory = new File("tmp");

	@After
	public void before() {
		deleteDir(FileServiceTests.directory);
	}


	@Test(expected = FileNotFoundException.class)
	public void noFileFound() throws Exception{
		File f = FileService.read(directory + "/anyFileName.txt");

	}

	@Test
	public void fileFound() throws Exception{
		// data setup
		WebApiSource source = this.generateTestWebApiSource();
		this.writeWebSourceApiConfig(source);

		// run code
		File f = FileService.read(directory + "/"+ source.getConfigFileName());
		Assert.assertNotNull(f);
	}

	@Test
	public void writeFile() throws IOException, ConfigException {
		// data setup
		WebApiSource source = this.generateTestWebApiSource();

		// write file
		FileService.write(WebApiSource.class, source, directory + "/"+ source.getConfigFileName());

		// read
		File f = FileService.read(directory + "/"+ source.getConfigFileName());
		Assert.assertNotNull(f);

	}

	// test helpers
	private void writeWebSourceApiConfig(WebApiSource webSource) {
		PrintWriter writer = null;
		this.createDirIfDoesNotExist();

		try {
			writer = new PrintWriter(FileServiceTests.directory + "/" + webSource.getConfigFileName(), "UTF-8");
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
		if (!FileServiceTests.directory.exists()) {
			FileServiceTests.directory.mkdirs();
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
