package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.service.impl.RecipeServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

	RecipeServiceImpl recipeService;

	public IndexController(RecipeServiceImpl recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {
		log.debug("Getting index page");
		model.addAttribute("recipes", this.recipeService.getRecipes());
		return "index";
	}

}
