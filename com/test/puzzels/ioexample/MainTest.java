package com.test.puzzels.ioexample;

import com.tenta.puzzles.ioexample.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MainTest {
    @Test
    void test1 (){
        assertEquals("100", Main.doTheOperation("4️2️+\uD83C\uDFB1+25✖️2"));
    }
    @Test
    void test2 (){
        assertEquals("\uD83D\uDCAF", Main.styleToEmoticon("100"));
    }

    @Test
    void test3 (){
        assertEquals("42", Main.doTheOperation("4️2"));
    }
    @Test
    void test4 (){
        assertEquals("4️2️", Main.styleToEmoticon("42"));
    }

    @Test
    void test5 (){
        assertEquals("10000", Main.doTheOperation("100✖\uD83D\uDCAF"));
    }
    @Test
    void test6 (){
        assertEquals("\uD83D\uDCAF0️0️", Main.styleToEmoticon("10000"));
    }

    @Test
    void test7 (){
        assertEquals("64", Main.doTheOperation("8️✖\uD83C\uDFB1"));
    }
    @Test
    void test8 (){
        assertEquals("6️4️", Main.styleToEmoticon("64"));
    }

    @Test
    void test9 (){
        assertEquals("100", Main.doTheOperation("\uD83D\uDD1Fx\uD83D\uDD1F"));
    }
    @Test
    void test10 (){
        assertEquals("\uD83D\uDCAF", Main.styleToEmoticon("100"));
    }

    @Test
    void test11 (){
        assertEquals("2", Main.doTheOperation("1 plus 1"));
    }
    @Test
    void test12 (){
        assertEquals("2️", Main.styleToEmoticon("2"));
    }

    @Test
    void test13 (){
        assertNull(Main.doTheOperation("1%0"));
    }
}
