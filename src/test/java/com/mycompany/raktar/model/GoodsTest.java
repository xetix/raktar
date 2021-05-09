package com.mycompany.raktar.model;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Random;
import static org.mockito.Mockito.*;

public class GoodsTest
{
    Random rand = new Random();
    @Test
    @DisplayName("Testing the numericValidation method")
    public void numericValidationTest()
    {
        final TextField textField = new TextField ("32566");

        Goods good = mock(Goods.class);

        doNothing().when(good).numericValidation(textField);
        int rand_int1 = rand.nextInt(10);
        for (int i = 0; i < rand_int1; i++)
        {
            good.numericValidation(textField);
        }

        verify(good, times(rand_int1)).numericValidation(textField);
    }

    @Test
    @DisplayName("Testing the textvericValidation method")
    public void textValidationTest()
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        Platform.startup(runnable);
        final TextField textField = new TextField ("testtext");

        Goods good = mock(Goods.class);

        doNothing().when(good).textValidation(textField);
        int rand_int2 = rand.nextInt(10);
        for (int i = 0; i < rand_int2; i++)
        {
            good.textValidation(textField);
        }
        verify(good, times(rand_int2)).textValidation(textField);
    }

    @Test
    @DisplayName("Testing the descericValidation method")
    public void descValidationTest()
    {
        final TextArea textArea = new TextArea ("secondtesttext");

        Goods good = mock(Goods.class);

        doNothing().when(good).descValidation(textArea);


        // Generate random integers in range 0 to 999
        int rand_int3 = rand.nextInt(10);
        for (int i = 0; i < rand_int3; i++)
        {
            good.descValidation(textArea);
        }

        verify(good, times(rand_int3)).descValidation(textArea);
    }

}