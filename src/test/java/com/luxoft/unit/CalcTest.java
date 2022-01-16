package com.luxoft.unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalcTest {
    private Calculator calc =new Calculator();

    @Order(1)
    @Test
    void CalcPlusTest(){
        Assertions.assertTrue(Objects.equals(calc.sum(1, 1), 2),"Message");
        Assertions.assertEquals(calc.sum(1,1), 2,"Суммирование не работает!");
    }

    @Order(2)
    @Test
    void CalcMinusTest(){
        Assertions.assertTrue(Objects.equals(calc.minus(3, 1), 2), "Вычитание не работает!");
    }
    @Order(3)
    @Test
    void CalcDivideTest(){
        Assertions.assertTrue(Objects.equals(calc.divide(6, 1), 6), "Вычитание не работает!");
    }
    @Order(4)
    @Test
    void CalcMultyTest(){
        Assertions.assertEquals(calc.multiply(2,2), 6, "Умножение не работает!");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,2",  "2,1,3",  "6,3,9",  "3,3,8"})
    void multySumTest(int arg1, int arg2, int res){
        Assertions.assertTrue(Objects.equals(calc.sum(arg1, arg2), res), "Сложение не работает!");    }

}
