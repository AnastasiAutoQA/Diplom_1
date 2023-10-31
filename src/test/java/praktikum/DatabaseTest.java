package praktikum;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class DatabaseTest {
    List<String> expectedBuns = new ArrayList<>(List.of("black bun", "white bun", "red bun"));
    List<String> expectedIngredients = new ArrayList<>(List.of("hot sauce", "sour cream", "chili sauce", "cutlet", "dinosaur", "sausage"));
    Database database = new Database();
    int expectedAvailableBunsAmount = 3;
    int expectedAvailableIngredientsAmount = 6;

    @Test
    public void testAvailableBuns() {
        assertEquals(expectedAvailableBunsAmount, database.availableBuns().size());
        assertEquals(expectedBuns.toString(), database.availableBuns().toString());
    }

    @Test
    public void testAvailableIngredients() {
        assertEquals(expectedAvailableIngredientsAmount, database.availableIngredients().size());
        assertEquals(expectedIngredients.toString(), database.availableIngredients().toString());
    }
}