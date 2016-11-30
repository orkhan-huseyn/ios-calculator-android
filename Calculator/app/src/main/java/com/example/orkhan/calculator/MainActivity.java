package com.example.orkhan.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private int[] numberButtonIds = {R.id.button20, R.id.button16, R.id.button17, R.id.button18, R.id.button12,
            R.id.button13, R.id.button14, R.id.button2, R.id.button2, R.id.button4, R.id.button};

    private int[] operatorButtonIds = {R.id.button10, R.id.button11, R.id.button15, R.id.button19};

    private TextView screen;
    private String memory;

    Expression expression;
    String errorMessage;
    double result = 0.0;

    protected void onCreate(Bundle savedInstanceState) 
	{
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
                String[] textExpr = screen.getText().toString().split("[-+÷×]");
                if(!textExpr[textExpr.length-1].contains("."))
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
                double mem = new Expression(screen.getText().toString()).evaluate();
                memory = Double.toString(mem);
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
                expression = new Expression(screen.getText().toString());
                errorMessage = expression.getErrorMessage();
                if(errorMessage != null && errorMessage != "")
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                else
                    result = expression.evaluate();
                screen.setText(Double.toString(result));
            }
        });

        for(int buttonId : operatorButtonIds)
            findViewById(buttonId).setOnClickListener(listener);
    }
}