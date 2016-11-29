package com.example.orkhan.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[] numberButtonIds = {R.id.button20, R.id.button16, R.id.button17, R.id.button18, R.id.button12,
            R.id.button13, R.id.button14, R.id.button2, R.id.button2, R.id.button4, R.id.button};

    private int[] operatorButtonIds = {R.id.button10, R.id.button11, R.id.button15, R.id.button19};

    private TextView screen;
    private String memory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView)findViewById(R.id.calc_screen);
        setNumberButtonActions();
        setOperatorButtonActions();
    }

    private void setNumberButtonActions()
    {
        View.OnClickListener listener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Button button = (Button)view;
                screen.append(button.getText());
            }
        };

        for(int buttonId : numberButtonIds)
            findViewById(buttonId).setOnClickListener(listener);
    }

    private void setOperatorButtonActions()
    {
        View.OnClickListener listener = new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Button button = (Button)view;
                screen.append(button.getText());
            }
        };

        findViewById(R.id.button22).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if(!screen.getText().toString().contains("."))
                    screen.append(".");
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String text = screen.getText().toString();
                if(text.length() > 0)
                    screen.setText(text.substring(0, text.length()-1));
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                screen.setText("");
                memory = "";
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                memory = screen.getText().toString();
            }
        });
        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                screen.append(memory);
            }
        });
        findViewById(R.id.button23).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                calculate();
            }
        });

        for(int buttonId : operatorButtonIds)
            findViewById(buttonId).setOnClickListener(listener);
    }

    private void calculate()
    {
        double res = 0.0;
        String text = screen.getText().toString();
        try
        {
            if(!text.contains("-") && !text.contains("÷") && !text.contains("×"))
            {
                String[] array = text.split("\\+");
                for(String s : array)
                    res += Double.parseDouble(s);
                screen.setText(Double.toString(res));
            }
            else if(!text.contains("+") && !text.contains("÷") && !text.contains("×"))
            {
                String[] array = text.split("-");
                res = Double.parseDouble(array[0]);
                for(int i=1;i<array.length;i++)
                    res -= Double.parseDouble(array[i]);
                screen.setText(Double.toString(res));
            }
            else if (!text.contains("+") && !text.contains("÷") && !text.contains("+"))
            {
                res = 1.0;
                String[] array = text.split("×");
                for (String s : array)
                    res *= Double.parseDouble(s);
                screen.setText(Double.toString(res));
            }
            else if(!text.contains("+") && !text.contains("×") && !text.contains("+"))
            {
                String[] array = text.split("÷");
                res = Double.parseDouble(array[0]);
                for(int i=1;i<array.length;i++)
                    res /= Double.parseDouble(array[i]);
                screen.setText(Double.toString(res));
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Invalid Expression.", Toast.LENGTH_LONG).show();
        }
    }
}