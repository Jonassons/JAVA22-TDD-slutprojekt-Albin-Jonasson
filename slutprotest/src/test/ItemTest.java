package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
	private MockItem item;

	@BeforeEach
	public void setUp() {
		item = new MockItem("testid");
	}

	@Test
	@DisplayName("Test if default item id is set correctly")
	public void testIdIsSet() {
		assertEquals("testid", item.getId());
	}

	@Test
	@DisplayName("Test constructor when item is empty string")
	public void testConstructorWithEmptyId() {
		MockItem emptyIdItem = new MockItem("");
		assertEquals("", emptyIdItem.getId());
	}

	@Test
	@DisplayName("Test setId when id is upper cases")
	public void testSetIdUpperCaseId() {
		item.setId("TESTITEM");
		assertEquals("testitem", item.getId());
	}

	@Test
	@DisplayName("Test if item id is very long")
	public void testVeryLongItemId() {
		String longItemId = "a".repeat(10000);
		item.setId(longItemId);
		assertEquals(longItemId, item.getId());
	}

	@Test
	@DisplayName("test if setId = null")
	void setIdCheckNull() {
		assertThrows(NullPointerException.class, () -> item.setId(null));
	}

	@Test
	@DisplayName("toString upper case")
	void toStringUpperCase() {
		String id = "ABCD";
		item.setId(id);
		assertEquals(id.toLowerCase(), item.toString());
	}

	@Test
	@DisplayName("toString lower case")
	void toStringLowerCase() {
		String id = "abcd";
		item.setId(id);
		assertEquals("abcd", item.toString());
	}
}
