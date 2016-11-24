package com.example.orkhan.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    Button button_one, button_two, button_three, button_four,
            button_five, button_six, button_seven, button_eight,
            button_nine, button_zero;
    Button button_clear, button_mult, button_add, button_subt,
            button_div, button_dot;
    Button button_in, button_out;

    Object[][] buttonNumbers;
    HashMap<Button, Double> map = new HashMap<Button, Double>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        buttonNumbers = new Object[][]
                {
                        {button_one, 1.0}, {button_two, 2.0}, {button_three, 3.0}, {button_four, 4.0},
                        {button_five, 5.0}, {button_six, 6.0}, {button_seven, 4.0}, {button_eight, 8.0},
                        {button_nine, 9.0}, {button_zero, 0.0}
                };
        fillMap();
        setNumberActions();
        button_clear.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                screen.setText("");
            }
        });
    }

    private void init()
    {
        screen = (TextView)findViewById(R.id.calc_screen);
        //numbers
        button_one = (Button)findViewById(R.id.button16);
        button_two = (Button)findViewById(R.id.button17);
        button_three = (Button)findViewById(R.id.button18);
        button_four = (Button)findViewById(R.id.button12);
        button_five = (Button)findViewById(R.id.button13);
        button_six = (Button)findViewById(R.id.button14);
        button_seven = (Button)findViewById(R.id.button);
        button_eight = (Button)findViewById(R.id.button2);
        button_nine = (Button)findViewById(R.id.button4);
        button_zero = (Button)findViewById(R.id.button20);
        //functionalities
        button_clear = (Button)findViewById(R.id.button6);
        button_in = (Button)findViewById(R.id.button7);
        button_out = (Button)findViewById(R.id.button9);
        button_dot = (Button)findViewById(R.id.button22);
        //operators
        button_add = (Button)findViewById(R.id.button19);
        button_subt = (Button)findViewById(R.id.button15);
        button_mult = (Button)findViewById(R.id.button11);
        button_div = (Button)findViewById(R.id.button10);
    }

    private void setNumberActions()
    {
        for(final Button button : map.keySet())
        {
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    screen.setText(String.valueOf(map.get(button)));
                }
            });
        }
    }

    private void fillMap()
    {
        for(Object[] buttonNumber : buttonNumbers)
        {
            map.put((Button)buttonNumber[0], (Double)buttonNumber[1]);
        }
    }
}