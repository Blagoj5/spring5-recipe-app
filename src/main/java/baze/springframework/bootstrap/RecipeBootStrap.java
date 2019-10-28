package baze.springframework.bootstrap;

import baze.springframework.domain.*;
import baze.springframework.repositories.CategoryRepository;
import baze.springframework.repositories.RecipeRepository;
import baze.springframework.repositories.UnitOfMeassureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeassureRepository unitOfMeassureRepository;

    public RecipeBootStrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeassureRepository unitOfMeassureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeassureRepository = unitOfMeassureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(listRecipes());
    }

    public List<Recipe> listRecipes(){

        // CHECK CATEGORIES

        Optional<Category> americanOptionalCategory = categoryRepository.findByDescription("American");

        if (!americanOptionalCategory.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }

        Optional<Category> italianOptionalCategory = categoryRepository.findByDescription("Italian");

        if (!italianOptionalCategory.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }


        // CHECK UOM

        Optional<UnitOfMeassure> poundUomOptional = unitOfMeassureRepository.findByDescription("Pound");

        if (!poundUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeassure> tbspUomOptional = unitOfMeassureRepository.findByDescription("Tbsp");

        if (!tbspUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeassure> cupsUomOptional = unitOfMeassureRepository.findByDescription("Cups");

        if (!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeassure> clovesUomOptional = unitOfMeassureRepository.findByDescription("Cloves");

        if (!clovesUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeassure> teaspoonUomOptional = unitOfMeassureRepository.findByDescription("Teaspoon");

        if (!teaspoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeassure> dashUomOptional = unitOfMeassureRepository.findByDescription("Dash");

        if (!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        // Checked categories and uoms

        //get uoms
        UnitOfMeassure poundUom = poundUomOptional.get();
        UnitOfMeassure tbspUom = tbspUomOptional.get();
        UnitOfMeassure cupsUom = cupsUomOptional.get();
        UnitOfMeassure clovesUom = clovesUomOptional.get();
        UnitOfMeassure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeassure dashUom = dashUomOptional.get();


        // get categories
        Category mexicanCategory = italianOptionalCategory.get();
        Category americanCategory = americanOptionalCategory.get();

        // Recipe1 SETUP
        List<Recipe> recipes = new ArrayList<>();

        Recipe pastaRecipe = new Recipe();
        pastaRecipe.setPrepTime(10);
        pastaRecipe.setCookTime(45);
        pastaRecipe.setDifficulty(Difficulty.MODERATE);
        pastaRecipe.setDescription("Penne Pasta with Meat Sauce");
        pastaRecipe.setDirections("1/2 pound penne pasta (use rice pasta for gluten-free version)\n" +
                "Salt\n" +
                "2 Tbsp extra virgin olive oil\n" +
                "2 cups chopped onion (from about 1 large onion)\n" +
                "2 cloves garlic, chopped\n" +
                "1 teaspoon Italian seasoning (blend of dried basil, oregano, rosemary, thyme, marjoram, and savory)\n" +
                "Dash red pepper flakes\n" +
                "1/2 teaspoon fresh thyme (or a pinch of dried)\n" +
                "Salt and freshly ground black pepper\n" +
                "1 pound (16% fat) ground beef\n" +
                "3 fresh basil leaves, chopped (or a 1/2 teaspoon of dried)\n" +
                "2 1/2 cups canned chunky tomato sauce (almost 1 28-ounce can, Muir Glen brand if you can get it)\n" +
                "1 teaspoon sugar\n" +
                "1 Tbsp chopped fresh parsley");
        pastaRecipe.setSource("https://www.simplyrecipes.com/recipes/penne_pasta_with_meat_sauce/");
        pastaRecipe.setUrl("https://www.simplyrecipes.com/recipes/penne_pasta_with_meat_sauce/");
        pastaRecipe.getIngredients().add(new Ingredient("penne pasta", new BigDecimal(".5"), poundUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("", new BigDecimal(".5"), poundUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("virgin olive oil", new BigDecimal(2), tbspUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("onion", new BigDecimal(2), cupsUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("garlic", new BigDecimal(2), clovesUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("Italian seasoning", new BigDecimal(1), teaspoonUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("pepper flakes", new BigDecimal(1), dashUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("fresh thyme", new BigDecimal(".5"), teaspoonUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("ground beef", new BigDecimal(1), poundUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("basil leaves", new BigDecimal(".5"), teaspoonUom, pastaRecipe));
        pastaRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), teaspoonUom, pastaRecipe));

        // Notes for the pasta
        Notes notesPastaRecipe = new Notes();
        notesPastaRecipe.setRecipeNotes("Leftovers of this penne pasta will keep for about five days and reheat well in the microwave. If the mixture seems dry, stir in a splash of water or milk to loosen it up again.\n" +
                "\n" +
                "The meat sauce also freezes quite well, though not with the pasta. If you want to freeze some or all of the meat sauce for future meals, just skip adding the pasta.\n" +
                "\n" +
                "To freeze the sauce by itself, cook the sauce completely, then let it cool completely. Transfer to freezer containers or bags, and freeze for up to three months. Thaw overnight in the fridge before reheating, or reheat directly from the freezer over low heat in a sauce pan. Add a splash or two of water or milk to loosen up the sauce if it seems dry.");
        notesPastaRecipe.setRecipe(pastaRecipe);
        pastaRecipe.setNotes(notesPastaRecipe);

        // Categories for pasta
        pastaRecipe.getCategories().add(americanCategory);
        pastaRecipe.getCategories().add(mexicanCategory);

        //Recipe 2 SETUP

        Recipe chickenRecipe = new Recipe();
        chickenRecipe.setDescription("Goldfish Chicken Tenders ");
        chickenRecipe.setCookTime(20);
        chickenRecipe.setPrepTime(15);
        chickenRecipe.setDifficulty(Difficulty.EASY);
        chickenRecipe.setDirections("1 Preheat the oven to 350°F. Line a baking sheet with foil and spray with nonstick spray (or brush with oil).\n" +
                "\n" +
                "2 Crush the crackers: Place the crackers in a zip-top plastic bag and seal it. Using your hands, a rolling pin, a mallet, or the hands of your children, crush the Goldfish as much as you can, but don't turn them into flour. You want some inconsistencies in shape; it'll make for better texture.\n" +
                "\n" +
                "Baked Goldfish Chicken Tenders crush the Goldfish\n" +
                "\n" +
                "3 Prep the assembly line: Find three medium bowls. Stir together the flour, salt, and pepper in one. Whisk the eggs together in the second bowl. Add the crushed Goldfish crackers to the last one.\n" +
                "\n" +
                "4 Bread the chicken: Working with a few pieces of chicken at a time, add to seasoned flour and coat well. Then shake off any extra flour, move to the eggs and coat well, and finally add to crushed Goldfish. Press the Goldfish on so they really stick and form a good crust.\n" +
                "\n" +
                "Transfer coated chicken to the baking sheet. Repeat until all the chicken tenders are coated.\n" +
                "\n" +
                "Oven-Fried Chicken Tenders bread the chickenCrispy Chicken Tenders in the Oven transfer to a baking sheet\n" +
                "\n" +
                "5 Bake the chicken: Bake chicken tenders for about 20 minutes until they are cooked through, or reach an internal temperature of 165°F in the thickest part of the tenders. You can also test one of the thicker ones by cutting it in half and making sure it’s cooked through.\n" +
                "\n" +
                "There’s no need to flip these, but you can flip them halfway through if they are browning too much on one side.\n" +
                "\n" +
                "Serve tenders while warm with a veggie and/or side salad and any dips you want, such as ketchup or bbq sauce. My kids loved honey! You can also make the Dad Add and have a sandwich!");
        chickenRecipe.setSource("https://www.simplyrecipes.com/recipes/goldfish_chicken_tenders/");
        chickenRecipe.setUrl("https://www.simplyrecipes.com/recipes/goldfish_chicken_tenders/");
        chickenRecipe.getIngredients().add(new Ingredient("Goldfist cheese crackes", new BigDecimal(2), cupsUom, chickenRecipe));
        chickenRecipe.getIngredients().add(new Ingredient("Nonstick spray", new BigDecimal(1), tbspUom, chickenRecipe));
        chickenRecipe.getIngredients().add(new Ingredient("Flour", new BigDecimal(1), cupsUom, chickenRecipe));
        chickenRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(1), teaspoonUom, chickenRecipe));
        chickenRecipe.getIngredients().add(new Ingredient("Black pepper", new BigDecimal(1), teaspoonUom, chickenRecipe));
        chickenRecipe.getIngredients().add(new Ingredient("Chicken Breasts", new BigDecimal(2), poundUom, chickenRecipe));

        // Notes for chicken
        Notes noteschickenRecipe = new Notes();
        noteschickenRecipe.setRecipeNotes("DAD ADD: Goldfish Chicken Sandwich\n" +
                "\n" +
                "1 6-inch sub roll\n" +
                "1 tablespoon mayonnaise\n" +
                "1/2 cup shredded iceberg lettuce\n" +
                "3 to 4 Goldfish chicken tenders\n" +
                "Pickled banana peppers");
        noteschickenRecipe.setRecipe(chickenRecipe);
        chickenRecipe.setNotes(noteschickenRecipe);

        //Categories for chicken
        chickenRecipe.getCategories().add(americanCategory);


        // Add the recipes to list
        recipes.add(chickenRecipe);
        recipes.add(pastaRecipe);

        return recipes;
    }

}
