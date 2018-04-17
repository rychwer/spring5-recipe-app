package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	UnitOfMeasureRepository uomRepository;
	CategoryRepository categoryRepository;
	RecipeRepository recipeRepository;
	
	public RecipeBootstrap(UnitOfMeasureRepository uomRepository, CategoryRepository categoryRepository,
			RecipeRepository recipeRepository) {
		this.uomRepository = uomRepository;
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		this.recipeRepository.saveAll(getRecipes());
	}
	
	private List<Recipe> getRecipes() {
		
		List<Recipe> listRecipe = new ArrayList<>(2);
		
		Optional<UnitOfMeasure> tablespoon = this.uomRepository.findByDescription("Tablespoon");
		
		if(!tablespoon.isPresent()) {
			throw new RuntimeException("Tablespoon Not Found in Database");
		}
		
		Optional<UnitOfMeasure> teaspoon = this.uomRepository.findByDescription("Teaspoon");
		
		if(!teaspoon.isPresent()) {
			throw new RuntimeException("Teaspoon Not Found in Database");
		}
		
		Optional<UnitOfMeasure> dash = this.uomRepository.findByDescription("Dash");
		
		if(!dash.isPresent()) {
			throw new RuntimeException("Dash Not Found in Database");
		}
		
		Optional<UnitOfMeasure> each = this.uomRepository.findByDescription("Each");
		
		if(!each.isPresent()) {
			throw new RuntimeException("Each Not Found in Database");
		}
		
		Optional<UnitOfMeasure> cup = this.uomRepository.findByDescription("Cup");
		
		if(!cup.isPresent()) {
			throw new RuntimeException("Cup Not Found in Database");
		}

		Optional<UnitOfMeasure> pint = this.uomRepository.findByDescription("Pint");
		
		if(!pint.isPresent()) {
			throw new RuntimeException("Pint Not Found in Database");
		}

		UnitOfMeasure tableSpoonUom = tablespoon.get();
		UnitOfMeasure eachUom = each.get();
		UnitOfMeasure dashUom = dash.get();
		UnitOfMeasure teapoonUom = teaspoon.get();
		UnitOfMeasure cupsUom = cup.get();
		UnitOfMeasure pintUom = pint.get();

		Optional<Category> mexicanCategory = this.categoryRepository.findByDescription("Mexican");
		Optional<Category> americanCategory = this.categoryRepository.findByDescription("American");
		
		if(!mexicanCategory.isPresent()) {
			throw new RuntimeException("Mexican Category Not Found in Database");
		}
		
		if(!americanCategory.isPresent()) {
			throw new RuntimeException("American Category Not Found in Database");
		}
		
		Set<Category> categories = new HashSet<>();
		categories.add(mexicanCategory.get());
		categories.add(americanCategory.get());
		
		Recipe guacamole = new Recipe();
		guacamole.setCategories(categories);
		guacamole.setCookTime(0);
		guacamole.setDescription("Be careful handling chiles if using. "
				+ "Wash your hands thoroughly after handling and do not touch your eyes or "
				+ "the area near your eyes with your hands for several hours.");
		guacamole.setDifficulty(Difficulty.EASY);
		guacamole.setPrepTime(10);
		
		Notes guacamoleNote = new Notes();
		guacamoleNote.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.");
		guacamole.setNotes(guacamoleNote);
		
		guacamole.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), guacamole, eachUom));
		guacamole.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), guacamole, teaspoon.get()));
		guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), guacamole, eachUom));
		guacamole.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), guacamole, tablespoon.get()));
		guacamole.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), guacamole, eachUom));
		guacamole.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), guacamole, eachUom));
		guacamole.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), guacamole, dashUom));
		guacamole.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), guacamole, eachUom));
		
		listRecipe.add(guacamole);
		
		 //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos");
        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tacosRecipe, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), tacosRecipe, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), tacosRecipe, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), tacosRecipe, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), tacosRecipe, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), tacosRecipe, eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tacosRecipe, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tacosRecipe, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tacosRecipe, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tacosRecipe, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), tacosRecipe, eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), tacosRecipe, cupsUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), tacosRecipe, eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), tacosRecipe, eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), tacosRecipe, pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), tacosRecipe, eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), tacosRecipe, eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), tacosRecipe, cupsUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), tacosRecipe, eachUom));
        
        tacosRecipe.setCategories(categories);

        listRecipe.add(tacosRecipe);
		
		return listRecipe;
	}
	
	

}
