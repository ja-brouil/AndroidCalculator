package com.example.calculator.com.example.calulator.main;

import android.widget.TextView;

public class Calculation {

    // Current Value
    private float currentValue;
    private float changingValue;

    private boolean isDecimal;
    private float decimalSpots;

    private TextView textView;

    public enum CalculatorState {
        OperatorStatus, Numberstatus
    }
    private CalculatorState calculatorState;

    private String operatorSymbol;

    public Calculation(){
        currentValue = 0;
        calculatorState = CalculatorState.Numberstatus;
        isDecimal = false;
        decimalSpots = 10;
        operatorSymbol = "";
    }

    public void updateNumber(float number){
        if (calculatorState.equals(CalculatorState.Numberstatus)){
            if (isDecimal && currentValue != 0){
                number /= decimalSpots;
                currentValue += number;
                decimalSpots *= 10;
            } else {
                currentValue *= 10;
                currentValue += number;
            }
        } else {
            if (isDecimal && changingValue != 0){
                number /= decimalSpots;
                changingValue += number;
                decimalSpots *= 10;
            } else {
                changingValue *= 10;
                changingValue += number;
            }
        }

        // Update View
        if (calculatorState.equals(CalculatorState.OperatorStatus)){
            textView.setText(operatorSymbol + Float.toString(changingValue));
        } else {
            textView.setText(operatorSymbol + Float.toString(currentValue));
        }
    }

    // Reset view
    public void reset(){
        isDecimal = false;
        calculatorState = CalculatorState.Numberstatus;
        currentValue = 0;
        changingValue = 0;
        operatorSymbol = "";
        decimalSpots = 10;
        textView.setText("0");
    }

    // Calculate Changes
    public void equal(){
        switch (operatorSymbol){
            case "+":
                currentValue = changingValue + currentValue;
                break;
            case "-":
                currentValue = currentValue - changingValue;
                break;
            case "*":
                currentValue = changingValue * currentValue;
                break;
            case "/":
                currentValue = currentValue / changingValue;
                break;
        }

        // Set new view and reset variables
        textView.setText(Float.toString(currentValue));
        changingValue = 0;
        operatorSymbol = "";
        isDecimal = false;
        decimalSpots = 10;
        setCalculatorState(CalculatorState.Numberstatus);
    }

    // Setters and Getters
    public CalculatorState getCalculatorState(){
        return  calculatorState;
    }

    public void setCalculatorState(CalculatorState calculatorState){
        this.calculatorState = calculatorState;
    }

    public void setCalculatorView(TextView textView){
        this.textView = textView;
    }

    public void setDecimalMode(boolean isDecimal){
        this.isDecimal = isDecimal;
    }

    public boolean isDecimal(){
        return  isDecimal;
    }

    public void setOperatorSymbol(String operatorSymbol){
        this.operatorSymbol = operatorSymbol;
    }

    public void setDecimalSpots(float decimalSpots){
        this.decimalSpots = decimalSpots;
    }
}
