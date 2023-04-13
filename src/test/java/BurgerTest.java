import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient firstIngredient;
    @Mock
    private Ingredient secondIngredient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100.0f, 50.0f, 80.0f, 330.0f},
                {150.0f, 75.0f, 120.0f, 465.0f},
                {80.0f, 40.0f, 60.0f, 220.0f},
        });
    }

    private float bunPrice;
    private float firstIngredientPrice;
    private float secondIngredientPrice;
    private float expectedPrice;

    public BurgerTest(float bunPrice, float firstIngredientPrice, float secondIngredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}