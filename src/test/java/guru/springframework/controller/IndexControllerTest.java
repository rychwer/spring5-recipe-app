package guru.springframework.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.controllers.IndexController;
import guru.springframework.domain.Recipe;
import guru.springframework.service.impl.RecipeServiceImpl;

import static org.mockito.Mockito.*;

public class IndexControllerTest {
	
	IndexController indexController;
	
	@Mock
	RecipeServiceImpl recipeService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.indexController = new IndexController(recipeService);
	}
	
	@Test
	public void mockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.indexController).build();
		
		mockMvc.perform(get(""))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@Test
	public void testGetIndexPage() {
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(new Recipe());
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		recipes.add(recipe);
		
		when(this.recipeService.getRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argument = ArgumentCaptor.forClass(Set.class);
		
		String index = this.indexController.getIndexPage(model);
		assertEquals("index", index);
		verify(this.recipeService, times(1)).getRecipes();
		verify(this.model, times(1)).addAttribute(eq("recipes"), argument.capture());
		
		Set<Recipe> setInController = argument.getValue();
		assertEquals(2, setInController.size());
	}

}
