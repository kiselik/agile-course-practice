package ru.unn.agile.fractioncalculator.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.fractioncalculator.model.Fraction;
import ru.unn.agile.fractioncalculator.model.FractionCalculator;


public class ViewModel {

    public static final String EMPTY_CALC_RESULT = "";

    private StringProperty firstFraction = new SimpleStringProperty();
    private StringProperty secondFraction = new SimpleStringProperty();
    private StringProperty resultFraction = new SimpleStringProperty();

    public StringProperty firstFractionProperty() {
        return firstFraction;
    }

    public StringProperty secondFractionProperty() {
        return secondFraction;
    }

    public StringProperty resultFractionProperty() {
        return resultFraction;
    }

    public void setFirstFraction(String fraction) {
        firstFraction.set(fraction);
    }

    public void setSecondFraction(String fraction) {
        secondFraction.set(fraction);
    }

    public void calculateSum() {
        if (isCorrectInputFractions()) {
            Fraction firstFraction = stringToFraction(firstFractionProperty().get());
            Fraction secondFraction = stringToFraction(secondFractionProperty().get());
            Fraction res = FractionCalculator.reduce(FractionCalculator.sum(firstFraction, secondFraction));
            resultFraction.set(res.toString());
        } else {
            resultFraction.set(EMPTY_CALC_RESULT);
        }
    }

    public void calculateMinus() {
        if (isCorrectInputFractions()) {
            Fraction firstFraction = stringToFraction(firstFractionProperty().get());
            Fraction secondFraction = stringToFraction(secondFractionProperty().get());
            Fraction res = FractionCalculator.reduce(FractionCalculator.minus(firstFraction, secondFraction));
            resultFraction.set(res.toString());
        } else {
            resultFraction.set(EMPTY_CALC_RESULT);
        }
    }

    private boolean isCorrectInputFractions() {
        boolean isCorrect = false;
        if (firstFraction.get() != null && secondFraction.get() != null) {
            Fraction firstFraction = stringToFraction(firstFractionProperty().get());
            Fraction secondFraction = stringToFraction(secondFractionProperty().get());
            if (firstFraction != null && secondFraction != null) {
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    private Fraction stringToFraction(String fractionStr) {
        Fraction res = null;
        String[] args = fractionStr.split(Fraction.FRACTION_DELIMITER);
        if (args.length == 2) {
            try {
                int numerator = Integer.parseInt(args[0]);
                int denominator = Integer.parseInt(args[1]);
                res = new Fraction(numerator, denominator);
            } catch (NumberFormatException e) {
                res = null;
            }
        }
        return res;
    }
}
