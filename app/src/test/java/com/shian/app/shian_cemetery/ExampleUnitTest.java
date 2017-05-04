package com.shian.app.shian_cemetery;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("s");
        list.add("g");
        list.add("h");
        String[] strings = list.toArray(new String[0]);
        for (String d:strings) {
            System.out.print(d);
        }
        System.out.print("len:"+strings.length);
    }
}