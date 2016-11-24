package com.example.orkhan.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView screen;
    Button button_one, button_two, button_three, button_four,
            button_five, button_six, button_seven, button_eight,
            button_nine, button_zero;
    Button button_clear, button_mult, button_add, button_subt,
            button_div, button_dot, button_inv, button_res;
    Button button_in, button_out;

    Object[][] buttonNumbers;
    HashMap<Button, Integer> map = new HashMap<Button, Integer>();
    String memory = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        buttonNumbers = new Object[][]
                {
                        {button_one, 1}, {button_two, 2}, {button_three, 3}, {button_four, 4},
                        {button_five, 5}, {button_six, 6}, {button_seven, 4}, {button_eight, 8},
                        {button_nine, 9}, {button_zero, 0}
                };
        fillMap();
        setNumberActions();
        new Calculator();
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
        button_inv = (Button)findViewById(R.id.button3);
        button_out = (Button)findViewById(R.id.button9);
        button_dot = (Button)findViewById(R.id.button22);
        //operators
        button_add = (Button)findViewById(R.id.button19);
        button_subt = (Button)findViewById(R.id.button15);
        button_mult = (Button)findViewById(R.id.button11);
        button_div = (Button)findViewById(R.id.button10);
        button_res = (Button)findViewById(R.id.button23);

    }

    private void setNumberActions()
    {
        for(final Button button : map.keySet())
        {
            button.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    screen.setText(screen.getText() + String.valueOf(map.get(button)));
                }
            });
        }
    }

    private void fillMap()
    {
        for(Object[] buttonNumber : buttonNumbers)
        {
            map.put((Button)buttonNumber[0], (Integer) buttonNumber[1]);
        }
    }

    private class Calculator
    {
        public Calculator()
        {
            clear();
            dot();
            invalidate();
            in();
            out();
            arithmetics();
            calculate();
        }

        public void clear()
        {
            button_clear.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    screen.setText("");
                }
            });
        }

        public void dot()
        {
            button_dot.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    screen.setText(screen.getText() + ".");
                }
            });
        }

        public void invalidate()
        {
            button_inv.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    screen.setText("");
                    memory = "";
                }
            });
        }

        public void in()
        {
            button_in.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    String result = "";
                    String assignment = screen.getText().toString().replaceAll("\\s+", "");
                    String[] arr = null;
                    if(assignment.contains("+"))
                    {
                        arr = assignment.split("\\+");
                        if(arr.length > 1)
                        {
                            double number_one_double = 0.0;
                            double number_two_double = 0.0;
                            try
                            {
                                number_one_double = Double.parseDouble(arr[0]);
                                number_two_double = Double.parseDouble(arr[1]);
                                result = String.valueOf(number_one_double + number_two_double);
                            }
                            catch (NumberFormatException ex)
                            {
                                //excpetion
                            }
                        }
                    }
                    else if(assignment.contains("-"))
                    {
                        arr = assignment.split("-");
                    }
                    else if(assignment.contains("×"))
                    {
                        arr = assignment.split("×");
                    }
                    else if (assignment.contains("÷"))
                    {
                        arr = assignment.split("÷");
                    }
                    else
                    {
                        result = screen.getText().toString();
                    }
                    memory = result;
                }
            });
        }

        public void out()
        {
            button_out.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    screen.setText(screen.getText() + memory);
                }
            });
        }

        public void arithmetics()
        {
            button_add.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(!screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("×") &&
                            !screen.getText().toString().contains("÷"))
                        screen.setText(screen.getText() + " + ");
                }
            });

            button_subt.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(!screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("×") &&
                            !screen.getText().toString().contains("÷"))
                        screen.setText(screen.getText() + " - ");
                }
            });

            button_mult.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(!screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("×") &&
                            !screen.getText().toString().contains("÷"))
                        screen.setText(screen.getText() + " × ");
                }
            });

            button_div.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if(!screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("+") &&
                            !screen.getText().toString().contains("×") &&
                            !screen.getText().toString().contains("÷"))
                        screen.setText(screen.getText() + " ÷ ");
                }
            });
        }

        public void calculate()
        {
            button_res.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    String assignment = screen.getText().toString().replaceAll("\\s+", "");
                    String[] arr = null;
                    if(assignment.contains("+"))
                    {
                        arr = assignment.split("\\+");
                        if(arr.length > 1)
                        {
                            double number_one_double = 0.0;
                            double number_two_double = 0.0;
                            try
                            {
                                number_one_double = Double.parseDouble(arr[0]);
                                number_two_double = Double.parseDouble(arr[1]);
                                screen.setText(String.valueOf(number_one_double + number_two_double));
                            }
                            catch (NumberFormatException ex)
                            {
                                //excpetion
                            }
                        }
                    }
                    else if(assignment.contains("-"))
                    {
                        arr = assignment.split("-");
                    }
                    else if(assignment.contains("×"))
                    {
                        arr = assignment.split("×");
                    }
                    else if (assignment.contains("÷"))
                    {
                        arr = assignment.split("÷");
                    }
                    else
                    {
                        screen.setText(screen.getText().toString());
                    }
                }
            });
        }
    }
}