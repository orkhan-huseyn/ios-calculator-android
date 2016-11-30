package com.example.orkhan.calculator;

public class Expression
{
    private String expression;
    private String errorMessage;

    public Expression(String expression)
    {
        setExpression(expression);
    }

    public double evaluate()
    {
        double result = 0.0;
        try
        {
            String newExpression = getExpression().replace("-", "+-");
            String[] additions = newExpression.split("\\+");

            for(String multip : additions)
            {
                String[] multiplications = multip.split("×");
                double multRes = 1.0;
                for (String operand : multiplications)
                {
                    if(operand.contains("÷"))
                    {
                        String[] divisions = operand.split("÷");
                        double dividient = Double.parseDouble(divisions[0]);
                        for(int i=1;i<divisions.length;i++)
                            dividient /= Double.parseDouble(divisions[i]);
                        multRes *= dividient;
                    }
                    else
                    {
                       multRes *= Double.parseDouble(operand);
                    }
                }
                result += multRes;
            }
        }
        catch (NumberFormatException ex)
        {
            setErrorMessage("Ops! Something went wrong.");
        }
        return result;
    }


    public String getExpression()
    {
        return this.expression;
    }

    public void setExpression(String expression)
    {
        this.expression = expression;
    }

    public String getErrorMessage()
    {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}