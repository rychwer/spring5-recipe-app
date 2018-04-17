package guru.springframework.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() {
		this.category = new Category();
	}
	
	@Test
	public void testGetId() {
		Long idValue = 4L;
		
		this.category.setId(idValue);
		assertEquals(idValue, this.category.getId());
	}

	@Test
	public void testGetDescription() {
	}

	@Test
	public void testGetRecipeCategories() {
	}

}
