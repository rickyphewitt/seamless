package test.com.mini.emby.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rickyphewitt.emby.api.data.Item;
import com.rickyphewitt.emby.mini.music.services.DisplayOrganizerService;

import test.com.rickyphewitt.emby.mini.music.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class DisplayOrganizerServiceTests {

	private int expectedKeys = 0;
	private List<String> expectedKeyOrder = Arrays.asList("2", "3", "6", "c", "d", "g", "s", "z");
	private List<String> expectedValuesAlpha = Arrays.asList("cat", "chicken", "cow");
	private List<String> expectedValuesNumberic = Arrays.asList("2 Chainz", "2 Doors Down");
	
	@Autowired
	DisplayOrganizerService displayOrganizerService;
	
	
	@Test
	public void organizeAllAlphabetic() {
		// Data setup
		ArrayList<Item> toBeOrganized = buildListToOrganizeAlphabeticItems();
		
		// Organize
		HashMap<String, ArrayList<Item>> organizedItems = new HashMap<String, ArrayList<Item>>();
		organizedItems = displayOrganizerService.organizeItems(toBeOrganized);
		
		// Assert
		assertAlphabeticKeys(organizedItems);
		
	}
	
	@Test
	public void organizeAlphaNumeric() {
		// Data setup
		ArrayList<Item> toBeOrganized = buildListToOrganizeAlphaNumericItems();
		
		// Organize
		HashMap<String, ArrayList<Item>> organizedItems = new HashMap<String, ArrayList<Item>>();
		organizedItems = displayOrganizerService.organizeItems(toBeOrganized);
		
		// Assert
		assertAlphabeticKeys(organizedItems);
		
		
	}
	
	@Test
	public void organizeEmptyList() {
		// Data setup
		ArrayList<Item> toBeOrganized = new ArrayList<Item>();
		
		// Organize
		HashMap<String, ArrayList<Item>> organizedItems = new HashMap<String, ArrayList<Item>>();
		organizedItems = displayOrganizerService.organizeItems(toBeOrganized);
		
		// Assert
		Assert.assertEquals(expectedKeys, organizedItems.size());
	}
	
	@Test
	public void verifyKeyOrder() {
		ArrayList<Item> toBeOrganized = buildListToOrganizeAlphaNumericItems();
		
		// Organize
		HashMap<String, ArrayList<Item>> organizedItems = new HashMap<String, ArrayList<Item>>();
		organizedItems = displayOrganizerService.organizeItems(toBeOrganized);
		
		Set sortedKeys = displayOrganizerService.getSortedKeys();
		
		for(int i = 0; i < expectedKeyOrder.size(); i++) {
			
			
			Assert.assertEquals(expectedKeyOrder.toArray()[i], displayOrganizerService.getSortedKeys().toArray()[i]);
		}
	}
	
//	@Test
//	public void verifyValuesAlphabetOrder() {
//		
//		ArrayList<Item> toBeOrganized = buildListToOrganizeAlphaNumericItems();
//		
//		// Organize
//		HashMap<String, ArrayList<Item>> organizedItems = new HashMap<String, ArrayList<Item>>();
//		organizedItems = displayOrganizerService.organizeItems(toBeOrganized);
//		
//		List<Item> keyValues = organizedItems.get("c");
//		
//		for(int i = 0; i < expectedValuesAlpha.size(); i++) {
//			String name = keyValues.get(i).getName();
//			Assert.assertTrue(expectedValuesAlpha.get(i).equals(name));
//		}
//		
//	}
	
	// Helper methods
	private ArrayList<Item> buildListToOrganizeAlphabeticItems() {
		expectedKeys = 5;
		List<String> animals = Arrays.asList("cow", "goat", "chicken", "dog", "cat", "sheep", "zebra");
		ArrayList<Item> toBeOrganized = new ArrayList<Item>();
	
		addToList(animals, toBeOrganized);
		return toBeOrganized;
		
	}
	
	private ArrayList<Item> buildListToOrganizeAlphaNumericItems() {
		ArrayList<Item> toBeOrganized = buildListToOrganizeAlphabeticItems();
		List<String> numbers = Arrays.asList("2 Chainz", "3", "6", "2 Doors Down", "6");
		expectedKeys = expectedKeys + 3;
		
		addToList(numbers, toBeOrganized);
		return toBeOrganized;
	}
	
	private void addToList(List<String> itemsToAdd, List<Item> listsToBeAddedTo) {
		
		for(String name: itemsToAdd) {
			Item item = new Item();
			item.setName(name);
			listsToBeAddedTo.add(item);
		}
	}
	
	private void assertAlphabeticKeys(HashMap<String, ArrayList<Item>> organizedItems) {
		
		Assert.assertEquals(expectedKeys, organizedItems.size());
		Assert.assertFalse(organizedItems.containsKey("f"));
		Assert.assertEquals(3, organizedItems.get("c").size());
		Assert.assertEquals(1, organizedItems.get("d").size());
		Assert.assertEquals(1, organizedItems.get("g").size());
		Assert.assertEquals(1, organizedItems.get("s").size());
		Assert.assertEquals(1, organizedItems.get("z").size());

	}
	
	private void assertNumericKeys(HashMap<String, ArrayList<Item>> organizedItems) {
		
		Assert.assertEquals(expectedKeys, organizedItems.size());
		Assert.assertFalse(organizedItems.containsKey("10"));
		Assert.assertEquals(2, organizedItems.get("2").size());
		Assert.assertEquals(3, organizedItems.get("c").size());
		Assert.assertEquals(1, organizedItems.get("3").size());
		Assert.assertEquals(2, organizedItems.get("6").size());

	}
	
}
