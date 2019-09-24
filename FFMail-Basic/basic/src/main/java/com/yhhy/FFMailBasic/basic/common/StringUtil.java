package com.yhhy.FFMailBasic.basic.common;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * String工具类
 */
public class StringUtil {

    /**
     * 实现将'2011-1-1'转换为'2011-01-01'，将'2011-1'转换为'2011-01'
     * 
     * @author yucs
     * @param date yyyy-MM-dd...
     * @return
     */
    public static String getFormatDateStr(String date) {
        String ret = "";

        String y = date.substring(0, date.indexOf("-"));
        String m = "";
        String d = "";

        if (CharCounter(date, "-") == 1) {// 只有年和月
            m = date.substring(date.indexOf("-") + 1);
        } else {// 包含了年、月和日
            m = date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"));
            d = date.substring(date.lastIndexOf("-") + 1);
        }

        if (m.length() == 1) {
            m = "0" + m;

        }

        if (CharCounter(date, "-") == 2) {
            if (d.length() == 1) {
                d = "0" + d;
            }
            ret = y + "-" + m + "-" + d;
        } else {
            ret = y + "-" + m;
        }

        return ret;
    }

    /**
     * 将日期进行格式化
     * 
     * @param dateStr      日期字符串
     * @param formatterIn  输入的格式：yyyy-MM-dd...
     * @param formatterOut 输出的格式：yyyy-MM-dd...
     * @return
     * @throws ParseException
     */
    public static String getFormatDateStr(String dateStr, String formatterIn, String formatterOut)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatterIn);
        Date date = formatter.parse(dateStr);
        return new SimpleDateFormat(formatterOut).format(date);
    }

    /**
     * 获取传入日期的前/后某个日期的日期
     * 
     * @param dateStr        日期字符串
     * @param formatterIn    输入的格式：yyyy-MM-dd...
     * @param formatterOut   输出的格式：yyyy-MM-dd...
     * @param daysPreOrAfter 前、后几天
     * @return 格式化后的日期
     * @throws ParseException
     */
    public static String getFormatDateStr(String dateStr, String formatterIn, String formatterOut, int daysPreOrAfter)
            throws ParseException {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        if (isEmpty(dateStr)) {
            ca.setTime(new Date()); // 设置时间为当前时间
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(formatterIn);
            Date date = formatter.parse(dateStr);
            ca.setTime(date);
        }
        ca.add(Calendar.DATE, daysPreOrAfter); // 年份减1
        Date myDate = ca.getTime(); // 结果
        return getFormatDateStr(myDate, formatterOut);
    }

    /**
     * 将日期进行格式化
     * 
     * @param date         日期，空的话为当天
     * @param formatterOut 输出的格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static String getFormatDateStr(Date date, String formatterOut) throws ParseException {
        return new SimpleDateFormat(formatterOut).format(date == null ? new Date() : date);
    }

    /**
     * 取某个字符串中某个字符出现的个数
     * 
     * @param pStr   被计算的字符串
     * @param subStr 要查询的该出现的字符串
     * @return
     */
    public static int CharCounter(String pStr, String subStr) {
        String[] pArr = pStr.split(subStr);
        return pArr.length - 1;
    }

    /**
     * 格式化字符串并四舍五入保留两位小数
     * 
     * @param source
     * @return
     */
    public static String formatWith2(String source) {
        double tmpDouble = 0.00;
        try {
            tmpDouble = Double.parseDouble(source);
        } catch (Exception ex) {
        }

        return String.format("%.2f", tmpDouble);
    }

    /**
     * 用于验证数组中是否包含对应的字符
     * 
     * @param arr
     * @param str
     * @return
     */
    public static boolean valiStrInArr(String[] arr, String str) {
        boolean flag = false;

        if (null != arr && null != str) {
            for (String string : arr) {
                if (string.trim().equals(str.trim())) {
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
     */
    public static String converToString(String[] ig) {
        String str = "";
        if (ig != null && ig.length > 0) {
            for (int i = 0; i < ig.length; i++) {
                str += ig[i] + ",";
            }
        }
        str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * @Description:把list转换为一个用逗号分隔的字符串
     */
    public static String listToString(List<? extends Object> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean isNotEmpty(Object str) {
        return str != null && !"".equals(str.toString().trim());
    }

    /**
     * 如果v为空则返回setV的值
     * 
     * @param v    判断的值
     * @param setV 设置的值
     * @return
     */
    public static String getValueIfEmpty(String v, String setV) {
        if (isEmpty(v)) {
            return setV;
        }
        return v;
    }

    /**
     * 如果存在空值，则返回false ，全部不为空则返回true
     * 
     * @param values
     * @return
     */
    public static boolean isNotEmptyAll(String... values) {
        for (String v : values) {
            if (isEmpty(v)) {
                return false;
            }
        }
        return true;
    }

    public static String formatNull(String str) {
        return str == null ? "" : str;
    }

    /**
     * 字符串加数字
     * 
     * @param str
     * @param num
     * @param defaultNum 转换失败时，使用的值
     * @return
     */
    public static int add(String str, int num, int defaultNum) {
        int n;
        try {
            n = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            n = defaultNum;
        }

        return n + num;
    }

    /**
     * 分割字符串,比如，("111,111,111,111,111",7,",") -- > ["111,111","111,111","111"]
     * 
     * @param str       字符串
     * @param maxLength 分割后每个字符串的最大长度
     * @param splitReg  分隔符
     * @return
     */
    public static List<String> splitByLength(String str, int maxLength, String splitReg) {
        List<String> arr = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return arr;
        }
        int start = 0;
        int end;
        while (true) {
            end = Math.min(str.length(), start + maxLength);
            if (end == str.length()) {
                break;
            }

            end = str.lastIndexOf(splitReg, end);
            arr.add(str.substring(start, end));
            // System.out.println(str.substring(start, end));
            start = end + 1;
        }
        arr.add(str.substring(start, end));
        // System.out.println(str.substring(start, end));

        return arr;
    }

    public static String getString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 获取大写数字,最大到千万
     * 
     * @author zhengyy 2018年3月26日
     * @return 金额的大写
     */
    public static String getDigitUppercase(String number) {
        List<List<String>> units = new ArrayList<List<String>>();
        units.add(Arrays.asList(new String[] { "圆", "拾", "佰", "仟" }));
        units.add(Arrays.asList(new String[] { "万", "拾", "佰", "仟" }));
        units.add(Arrays.asList(new String[] { "亿", "拾", "佰", "仟", "万" }));
        String[] digitStr = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[] numberArr = number.split("\\.");
        StringBuilder digitFormat = new StringBuilder();
        if (isZero(number) || numberArr.length == 0) {
            return "零元整";
        }
        // 1、先处理整数
        int index = 0, numLen = numberArr[0].length();
        if (!isZero(numberArr[0])) {
            // 把数字按照单位进行分组，从位数小的开始处理
            for (List<String> unit : units) {
                String numStr = numberArr[0].substring(Math.max(numLen - unit.size() - index, 0), numLen - index);
                // System.out.println(numStr);
                setUpperCase(numStr, unit, digitFormat, digitStr);
                index += unit.size();
                if (index > numLen) {
                    break;
                }
            }
            digitFormat.reverse();
        }
        // System.out.println(digitFormat.toString());

        // 2、处理小数
        if (numberArr.length == 2 && !isZero(numberArr[1])) {
            List<String> decimalUnit = Arrays.asList(new String[] { "分", "角" });
            StringBuilder sb = new StringBuilder();
            setUpperCase(numberArr[1].substring(0, Math.min(numberArr[1].length(), 2)), decimalUnit, sb, digitStr);
            // System.out.println(sb.toString());
            digitFormat.append(sb.reverse().toString());
        } else {
            digitFormat.append("整");
        }
        String fmt = digitFormat.toString();
        // 各种替换
        String[][] replaceStr = new String[][] { { "零零", "零" }, { "零万", "万" }, { "零圆", "圆" }, { "零亿", "亿" },
                { "亿万", "亿" }, { "零分", "" } };
        for (String[] rp : replaceStr) {
            while (fmt.indexOf(rp[0]) != -1) {
                fmt = fmt.replaceAll(rp[0], rp[1]);
            }
        }
        // 把最前面的0替换掉
        if (fmt.substring(0, 1).equals("零")) {
            fmt = fmt.substring(1, fmt.length());
        }
        // System.out.println(fmt);
        // System.out.println(digitFormat.toString());
        return fmt;
    }

    public static String getMoneyFormat(String value) {
        try {
            if (StringUtil.isEmpty(value)) {
                value = "0";
            }
            DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
            return fmt.format(Double.parseDouble(value));
        } catch (Exception e) {
            return value;
        }
    }

    /**
     * str字符串是否在数组中
     * 
     * @param val
     * @param str
     * @return
     */
    public static boolean isStringInArr(String str, String... arr) {
        for (String v : arr) {
            String trim = v.trim();
            if (trim != null && trim.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param numStr      4321
     * @param unit        "圆", "拾", "佰", "仟"
     * @param digitFormat
     * @param digitStr    大写数字
     * @return 圆壹拾贰佰叁仟肆
     */
    private static String setUpperCase(String numStr, List<String> unit, StringBuilder digitFormat, String[] digitStr) {
        for (int i = 0; i < numStr.length(); i++) {
            char num = numStr.charAt(numStr.length() - i - 1);
            if (num == '0') {
                if (i == 0) {
                    digitFormat.append(unit.get(0) + "零");
                } else {
                    digitFormat.append("零");
                }
            } else {
                String unitStr = unit.get(i);
                String digitUpper = digitStr[num - '0'];
                digitFormat.append(unitStr + digitUpper);
            }
            // digitFormat.append(")
        }
        // System.out.println(digitFormat.toString());
        return null;
    }

    /**
     * 将字符串转为html字符串
     * 
     * @param in
     * @return html字符串
     */
    public static String toHTMLString(String in) {
        StringBuffer out = new StringBuffer(
                "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
        for (int i = 0; in != null && i < in.length(); i++) {
            char c = in.charAt(i);
            if (c == '\'')
                out.append("&#039;");
            else if (c == '\"')
                out.append("&#034;");
            else if (c == '<')
                out.append("&lt;");
            else if (c == '>')
                out.append("&gt;");
            else if (c == '&')
                out.append("&amp;");
            else if (c == ' ')
                out.append("&nbsp;");
            else if (c == '\n')
                out.append("<br/>");
            else
                out.append(c);
        }
        out.append("</html>");
        return out.toString();
    }

    private static boolean isZero(String num) {
        String replaceAll = num.trim().replaceAll("0", "");
        if (replaceAll.equals("") || replaceAll.equals(".")) {
            return true;
        }
        return false;
    }

    public static String nvl(String... strings) {
        for (String s : strings) {
            if (isNotEmpty(s)) {
                return s;
            }
        }
        return "";
    }
}
