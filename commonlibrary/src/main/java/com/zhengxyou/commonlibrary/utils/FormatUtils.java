package com.zhengxyou.commonlibrary.utils;


import android.support.annotation.Size;

import java.text.DecimalFormat;

public class FormatUtils {
    /**
     * 不带逗号,保留2位小数
     */
    public static String formatMoney(double money) {

        DecimalFormat df = new DecimalFormat();
        String style = "#0.00";
        df.applyPattern(style);

        return df.format(money);
    }

    /**
     * 不带逗号,保留2位小数
     */
    public static String formatMoney(String money) {
        return formatMoney(Double.parseDouble(money));
    }

    /**
     * 不带逗号,自定义保留位数
     *
     * @param numberOfDigits 保留的位数
     */
    public static String formatMoney(double money, @Size(min = 0) int numberOfDigits) {

        DecimalFormat df = new DecimalFormat();
        StringBuilder builder = new StringBuilder("#");
        if (numberOfDigits > 0) {
            builder.append("0.");
        }
        for (int i = 0; i < numberOfDigits; i++) {
            builder.append("0");
        }
        df.applyPattern(builder.toString());

        return df.format(money);
    }

    /**
     * 不带逗号,自定义保留位数
     *
     * @param numberOfDigits 保留的位数
     */
    public static String formatMoney(String money, int numberOfDigits) {
        return formatMoney(Double.parseDouble(money), numberOfDigits);
    }

    /**
     * 不带逗号,如果与整型相等则返回整型
     */
    public static String formatMoneyIntOrDouble(double money) {//不带逗号

        DecimalFormat df = new DecimalFormat();
        String style;
        if ((int) money == money) {
            style = "#";
        } else {
            style = "#0.00";
        }
        df.applyPattern(style);

        return df.format(money);
    }

    /**
     * 带百分号的字符串,必须0-1
     *
     * @param data 要转换的值
     * @param zero 保留的位数
     */
    public static String formatPercentage(@Size(max = 1, min = 0) double data, int zero) {

        DecimalFormat df = new DecimalFormat();
        String style;
        if (zero == 0) {
            style = "#0%";
        } else if (zero == 1) {
            style = "#0.0%";
        } else {
            style = "#0.00%";
        }
        df.applyPattern(style);

        return df.format(data);
    }

    /**
     * 带百分号的字符串,必须0-1
     *
     * @param data 要转换的值
     * @param zero 保留的位数
     */
    public static String formatPercentage(String data, int zero) {

        return formatPercentage(Double.parseDouble(data), zero);
    }

    /**
     * 每隔3个字符带逗号，保留2位小数
     */
    public static String formatMoneyByBank(String money) {
        if (money == null) return "0.00";
        return formatMoneyByBank(Double.parseDouble(money));
    }

    /**
     * 每隔3个字符带逗号，保留2位小数
     */
    public static String formatMoneyByBank(double money) {
        DecimalFormat df = new DecimalFormat();
        String style;
        style = "###,###0.00";
        df.applyPattern(style);

        return df.format(money);
    }
}
