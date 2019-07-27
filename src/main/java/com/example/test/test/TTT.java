package com.example.test.test;

import java.math.BigDecimal;

public class TTT {
    public static void main(String[] args){
        BigDecimal bigDecimal=new BigDecimal(100.00);
        System.out.print(bigDecimal.multiply(new BigDecimal(100)).toPlainString());
    }
}
