package org.bookrec.utils;

import org.apache.log4j.Logger;

/**
 * CastUtil
 *
 * @author a1311
 */
public class CastUtil {
    /**
     * 日志管理
     */
    private static final Logger LOG = Logger.getLogger(org.bookrec.utils.CastUtil.class);
    /**
     * 转为String类型
     *
     * @param obj obj
     * @return java.lang.String 如果参数为null则转为空字符串
     */
    public static String castString(Object obj) {
        return CastUtil.castString(obj, "");
    }

    /**
     * 转为String类型（提供默认值）
     *
     * @param obj          将obj转为string
     * @param defaultValue 如果obj为null则返回default
     * @return String
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为double类型，如果为null或者空字符串或者格式不对则返回0
     *
     * @param obj obj
     * @return String
     */
    public static double castDouble(Object obj) {
        return CastUtil.castDouble(obj, 0);
    }

    /**
     * 转为double类型 ，如果obj为null或者空字符串或者格式不对则返回defaultValue
     *
     * @param obj          obj
     * @param defaultValue obj为null或者空字符串或者格式不对返回defaultValue
     * @return String
     */
    public static double castDouble(Object obj, double defaultValue) {
        //声明结果，把默认值赋给结果
        double value = defaultValue;
        //判断是否为null
        if (obj != null) {
            //转换为String
            String strValue = castString(obj);
            //判断字符串是否为空（是否为空只能判断字符串，不能判断Object）
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    //不为空则把值赋给value
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    LOG.error(e.getMessage(), e);
                }

            }
        }
        return value;
    }

    /**
     * 转为long型，如果obj为null或者空字符串或者格式不对则返回0
     *
     * @param obj obj
     * @return long
     */
    public static long castLong(Object obj) {
        return CastUtil.castLong(obj, 0);
    }

    /**
     * 转为long型（提供默认数值），如果obj为null或者空字符串或者格式不对则返回defaultValue
     *
     * @param obj          obj
     * @param defaultValue defaultValue
     * @return obj为null或者空字符串或者格式不对返回defaultValue
     */
    public static long castLong(Object obj, long defaultValue) {
        //声明结果，把默认值赋给结果
        long value = defaultValue;
        //判断是否为null
        if (obj != null) {
            //转换为String
            String strValue = castString(obj);
            //判断字符串是否为空（是否为空只能判断字符串，不能判断Object）
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    //不为空则把值赋给value
                    value = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    //格式不对把默认值赋给value
                    LOG.error(e.getMessage(), e);
                }

            }
        }
        return value;
    }

    /**
     * 转为int型
     *
     * @param obj obj
     * @return 如果obj为null或者空字符串或者格式不对则返回0
     */
    public static int castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 转为int型(提供默认值)
     *
     * @param obj          obj
     * @param defaultValue defaultValue
     * @return 如果obj为null或者空字符串或者格式不对则返回defaultValue
     */
    public static int castInt(Object obj, int defaultValue) {
        //声明结果，把默认值赋给结果
        int value = defaultValue;
        //判断是否为null
        if (obj != null) {
            //转换为String
            String strValue = castString(obj);
            //判断字符串是否为空（是否为空只能判断字符串，不能判断Object）
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    //不为空则把值赋给value
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    //格式不对把默认值赋给value
                    LOG.error(e.getMessage(), e);
                }

            }
        }
        return value;
    }

    /**
     * 转为boolean型，不是true的返回为false
     *
     * @param obj obj
     * @return boolean
     */
    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }


    /**
     * 转为boolean型（提供默认值）
     *
     * @param obj          obj
     * @param defaultValue defaultValue
     * @return boolean
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean value = defaultValue;
        //为null则返回默认值
        if (obj != null) {
            //底层会把字符串和true对比，所以不用判断是否为空字符串
            value = Boolean.parseBoolean(castString(obj));
        }
        return value;
    }
}
