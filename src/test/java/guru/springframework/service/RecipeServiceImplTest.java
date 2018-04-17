package guru.springframework.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.service.impl.RecipeServiceImpl;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.recipeService = new RecipeServiceImpl(this.recipeRepository);
	}
	
	@Test
	public void testGetRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		Recipe recipe = new Recipe();
		recipe.setId(2L);
		recipes.add(recipe);
		
		when(this.recipeService.getRecipes()).thenReturn(recipes);
		
		assertEquals(2, this.recipeService.getRecipes().size());
	}

}
