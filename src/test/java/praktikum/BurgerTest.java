package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Spy
    Ingredient ingredientCutlet = new Ingredient(IngredientType.FILLING, "cutlet", 100);

    @Spy
    Ingredient ingredientChili = new Ingredient(IngredientType.SAUCE, "chili sauce", 300);

    @Spy
    Ingredient ingredientSausage = new Ingredient(IngredientType.FILLING, "sausage", 300);

    @Spy
    private Bun bun = new Bun("white bun", 200);

    @Spy
    private Burger burger = new Burger();

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertNotNull(burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientCutlet);
        assertEquals(burger.ingredients.size(), 1);
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientChili);
        burger.removeIngredient(0);
        assertEquals(burger.ingredients.size(), 0);
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientChili);
        burger.addIngredient(ingredientSausage);
        burger.moveIngredient(burger.ingredients.indexOf(ingredientChili), burger.ingredients.indexOf(ingredientSausage));
        assertEquals(1, burger.ingredients.indexOf(ingredientChili));
        assertEquals(0, burger.ingredients.indexOf(ingredientSausage));
    }

    @Test
    public void testBurgerGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredientCutlet);
        assertEquals(500, burger.getPrice(), 0);
        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(ingredientCutlet, Mockito.times(1)).getPrice();
    }

    @Test
    public void testGetReceipt() {
        String expectedReceipt = "(==== white bun ====)\r\n" +
                "= filling cutlet =\r\n" +
                "(==== white bun ====)\r\n" +
                "\r\n" +
                "Price: 500,000000\r\n";
        burger.setBuns(bun);
        burger.addIngredient(ingredientCutlet);
        assertEquals(expectedReceipt, burger.getReceipt());
        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(ingredientCutlet, Mockito.times(1)).getName();
        Mockito.verify(ingredientCutlet, Mockito.times(1)).getType();
        Mockito.verify(burger, Mockito.times(1)).getPrice();
    }
}