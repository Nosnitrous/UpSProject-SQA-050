package com.luxoft.unit;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class HireTest {
    private static Hire hire;

    @BeforeAll
    static void setUp(){
        hire = new Hire();

    }

    @ParameterizedTest
    @ValueSource (ints = {11,14,18})
    void testAge(int valSource){
        Assertions.assertTrue(hire.isHired(valSource).equalsIgnoreCase("half"));

    }
    @ParameterizedTest
//    @CsvSource(value = {"13,half","11,no","18,no"})
    @CsvSource(value = {"13$half","11$no","18$no"}, delimiter = '$')
    void testAgeRespint (int age, String response){
        Assertions.assertTrue(hire.isHired(age).equalsIgnoreCase(response));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData.csv", delimiter = '$', numLinesToSkip = 1)
    void ht2(int age, String response){
        Assertions.assertTrue(hire.isHired(age).equalsIgnoreCase(response));
    }

    @ParameterizedTest
    @EnumSource(value = TestEnum.class)
    void EnumT(TestEnum data){
        Assertions.assertTrue(hire.isHired(data.getAge()).equalsIgnoreCase(data.getResponse()));

    }
    @ParameterizedTest
    @EnumSource(value = TestEnum.class, names = {"KID","TEEN"}, mode=EnumSource.Mode.INCLUDE)
    void EnumT2(TestEnum data){
        Assertions.assertTrue(hire.isHired(data.getAge()).equalsIgnoreCase(data.getResponse()));
    }

    @ParameterizedTest
    @MethodSource(value = "getDataTN")
    void methTest(int age, String response){
        Assertions.assertTrue(hire.isHired(age).equalsIgnoreCase(response));
        ConfigProps props = ConfigFactory.create(ConfigProps.class); // позволяет читать из properties файлов
        System.out.println("Возраст = " + props.age()+" ответ = "+ props.response()); // проще
    }

    @Test
    void propertiesT(){
        ConfigProps props = ConfigFactory.create(ConfigProps.class); // позволяет читать из properties файлов
        Assertions.assertTrue(hire.isHired(props.age()).equalsIgnoreCase(props.response()));
        System.out.println("Возраст = " + props.age()+" ответ = "+ props.response()); // проще

    }
//    @ParameterizedTest
//    @ArgumentsSource(value = ArgsProvider.class)
//    void ArgTest2(int age, String response){
//        Assertions.assertTrue(hire.isHired(age).equalsIgnoreCase(response));
//
//    }

    //// Аналог DATA PROVIDER'а в TESTNG
    private static Stream<Arguments> getDataTN(){
        return Stream.of(
        Arguments.of(4,"no"),
        Arguments.of(11,"half"),
        Arguments.of(18,"yes"));
    }
}
