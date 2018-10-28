package com.zhengxyou.commonlibrary;

import com.zhengxyou.commonlibrary.utils.FormatUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testDateFormat() {
        System.out.println(FormatUtils.formatMoneyByBank(12463456.123));
        System.out.println(FormatUtils.formatMoneyByBank(12463456));
        System.out.println(FormatUtils.formatMoneyByBank("0.123"));
        System.out.println(FormatUtils.formatPercentage("0.236",2));
        System.out.println(FormatUtils.formatPercentage(0.00236,2));
    }
}