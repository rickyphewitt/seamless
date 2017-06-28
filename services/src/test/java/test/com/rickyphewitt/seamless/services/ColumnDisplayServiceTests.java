package test.com.rickyphewitt.seamless.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.seamless.services.ColumnDisplayService;

import test.com.rickyphewitt.seamless.services.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ColumnDisplayServiceTests {
	
	@Autowired
	ColumnDisplayService columnDisplayService;
	
	@Test
	public void evenLinesAndColumns() {
		columnDisplayService.setNumberOfColumns(10);
		columnDisplayService.calcLinesPerColumn(100);
		Assert.assertEquals(10, columnDisplayService.getLinesPerColumn());
	}

	@Test
	public void unEvenLinesAndColumnsExpectRoundUpConventionRoundsDown() {
		columnDisplayService.setNumberOfColumns(9);
		columnDisplayService.calcLinesPerColumn(100);
		Assert.assertEquals(12, columnDisplayService.getLinesPerColumn());
	}
	
	@Test
	public void unEvenLinesAndColumnsExpectRoundUpConventionRoundsUp() {
		columnDisplayService.setNumberOfColumns(8);
		columnDisplayService.calcLinesPerColumn(100);
		Assert.assertEquals(13, columnDisplayService.getLinesPerColumn());
	}

	@Test
	public void unEvenLinesAndColumnsExpectRoundUpConventionRoundsUp2() {
		columnDisplayService.setNumberOfColumns(6);
		columnDisplayService.calcLinesPerColumn(100);
		Assert.assertEquals(17, columnDisplayService.getLinesPerColumn());
	}
}
