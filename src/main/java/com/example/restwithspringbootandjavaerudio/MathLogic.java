package com.example.restwithspringbootandjavaerudio;

import com.example.restwithspringbootandjavaerudio.Excepitions.UnsupportedMathOperationException;

public class MathLogic {
    public Double sum(String numberOne, String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Plese set a numeric value");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }
    public Double sub(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Plese set a numeric value");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }
    public Double multi(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Plese set a numeric value");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public Double div(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Plese set a numeric value");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public Double med(String numberOne, String numberTwo) {
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
            throw new UnsupportedMathOperationException("Plese set a numeric value");
        }
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    public Double raiz(String numberOne) throws Exception{
        if(!isNumeric(numberOne)){
            throw new UnsupportedMathOperationException("Plese set a numeric value");
        }
        return Math.sqrt(convertToDouble(numberOne));
    }

    private Double convertToDouble(String strnumber) {
        if (strnumber == null) return 0D;
        String number = strnumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }
    private boolean isNumeric(String strnumber) {
        if (strnumber == null) return false;
        String number = strnumber.replaceAll(",", ".");

        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
