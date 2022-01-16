package com.luxoft.unit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    private static int result;
    private static int count = 0;

    @BeforeAll
    static void setUp(){        // static needed
        result = 5;
        System.out.println("Befor All Method");
    }

    @BeforeEach
    void everyMeth(){           // static not needed
        System.out.println("Befor Every");
    }

//    DisabledOnOs(OS.LINUX)
    @Order(2)
    @Test
    public void firstTest(){
        System.out.println("Test1");
        Assertions.assertEquals(result,5,"Result # 5");
    }
    @Order(1)
//    @Test
    @RepeatedTest(4)
    public void secondTest(){
        System.out.println("Test2 прогон "+count++);
        Assertions.assertEquals(result,2+3,"Result # 5");
    }
    @Disabled
    @Test
    public void thirdTest(){
        System.out.println("Test3");
        Assertions.assertEquals(result,2+7,"Result # 5");
    }
    @AfterEach
    void afterEveryMeth(){          // static not needed
        System.out.println("After Every Method");
    }
    @AfterAll
    static void finishM(){          // static needed
        System.out.println("AfterAll Method");
    }
}
