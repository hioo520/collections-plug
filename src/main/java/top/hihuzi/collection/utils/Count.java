package top.hihuzi.collection.utils;


import top.hihuzi.collection.exception.CalculateException;

import java.math.BigDecimal;

/**
 * tips  通用工具计算
 *
 * @author: hihuzi 2019/4/23 19:23
 */
public class Count {

    /**
     * tips 进行数值转换
     * 在不报错的情况下转换为数字类型否则报错处理 convertSafe
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return the big decimal
     * @author hihuzi 2019/8/16 9:19
     */
    public static <T> BigDecimal convertCheck(T t) {

        if (null == t) return null;
        else if (t.getClass().isArray()) {
            return convert(((Object[]) t)[0]);
        }
        return null;
    }

    /**
     * 非安全型类型转换
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return big decimal
     */
    public static <T> BigDecimal convert(T t) {

        if (t instanceof Number) return new BigDecimal(((Number) t).doubleValue());
        else if (t instanceof String) {
            char[] chars = ((String) t).toCharArray();
            /* 存在报错可能*/
            for (char isNum : chars) {
                if (Character.isDigit(isNum)) return new BigDecimal((String) t);
                else throw new CalculateException("The value is can not convert NUMBER!");
            }
            throw new CalculateException("The value is can not convert NUMBER!");
        } else throw new CalculateException("The value is can not convert NUMBER!");
    }

    /**
     * tips BigDecimal 非安全型 加法
     * 支持返回null  和无法转换为数值类型 返回异常
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return big decimal <p> 争取转换成数值类型
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal add(final T... t) {

        return addPrecision(2, t);
    }

    /**
     * Add precision big decimal.
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the t
     * @return the big decimal
     */
    public static <T> BigDecimal addPrecision(final int precision, final T... t) {


        BigDecimal b0 = convertCheck(t);
        if (null == b0) return null;
        for (int i = 1; i < t.length; i++) {
            b0 = b0.add(convert(t[i]));
        }
        return b0.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips BigDecimal 非安全型 减法
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return big decimal <p> 必须保证第一个数是减数 只有为空null时才设置为0 其它情况直接报异常
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal minus(final T... t) {

        return minusPrecision(2, t);
    }

    /**
     * Minus precision big decimal.
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the t
     * @return the big decimal
     */
    public static <T> BigDecimal minusPrecision(final int precision, final T... t) {

        BigDecimal b0 = convertCheck(t);
        if (null == b0) return null;
        for (int i = 1; i < t.length; i++) {
            b0 = b0.subtract(convert(t[i]));
        }
        return b0.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips BigDecimal 非安全型 乘法
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return big decimal
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal multiply(final T... t) {

        return multiplyPrecision(2, t);
    }

    /**
     * Multiply precision big decimal.
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the t
     * @return the big decimal
     */
    public static <T> BigDecimal multiplyPrecision(final int precision, final T... t) {

        BigDecimal b0 = convertCheck(t);
        if (null == b0) return null;
        for (int i = 1; i < t.length; i++) {
            b0 = b0.multiply(convert(t[i]));
        }
        return b0.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * tips BigDecimal 非安全型 除法
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return big decimal <p> BigDecimal的除法运算封装 ，如果除数或者被除数为0，  直接报异常
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal divide(final T... value) {

        return dividePrecision(2, value);
    }

    /**
     * Divide precision big decimal.
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the t
     * @return the big decimal
     */
    public static <T> BigDecimal dividePrecision(final int precision, final T... t) {

        BigDecimal b0 = convertCheck(t);
        if (null == b0) return null;
        for (int i = 1; i < t.length; i++) {
            b0 = b0.divide(convert(t[i]), precision, BigDecimal.ROUND_HALF_UP);
        }
        return b0.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips BigDecimal 安全型 加法
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the t
     * @return the big decimal <p> 为 " " or null时才设置为0 其它情况无法转换为number的也处理为0
     * @author hihuzi 2019/4/26 10:09
     */
    public static <T> BigDecimal addSafe(final int precision, final T... t) {


        BigDecimal result = castSafeCheck(t, 0);
        if (null == result) return null;
        for (int i = 1; i < t.length; i++) {
            result = result.add(castSafe(t[i], 0));
        }
        return result.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips BigDecimal 安全型 减法
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the value
     * @return the big decimal <p> 必须保证第一个数是减数 只有为空null时才设置为0 其它情况直接报异常
     * @author hihuzi 2019/4/26 11:06
     */
    public static <T> BigDecimal minusSafe(final int precision, final T... t) {

        BigDecimal result = castSafeCheck(t, 0);
        if (null == result) return null;
        for (int i = 1; i < t.length; i++) {
            result = result.subtract(castSafe(t[i], 0));
        }
        return result.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips BigDecimal 安全型 乘法
     * 如果计算中出现null 直接置换成0
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the t
     * @return the big decimal
     * @author hihuzi 2019/4/29 17:43
     */
    public static <T> BigDecimal multiplySafe(final int precision, T... t) {

        BigDecimal result = castSafeCheck(t, 0);
        if (null == result) return null;
        for (int i = 1; i < t.length; i++) {
            result = result.multiply(castSafe(t[i], 1));
        }
        return result.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips BigDecimal 安全型 除法
     *
     * @param <T>       the type parameter
     * @param precision the precision
     * @param t         the value
     * @return the big decimal @ <p> BigDecimal的除法运算封装 ，如果除数为0，返回null 如果被除数是0或者其他会被忽略(特例所有的分母都是0 返回分母)
     * @author hihuzi 2019/4/26 11:06
     */
    public static <T> BigDecimal divideSafe(final int precision, final T... t) {

        BigDecimal result = castSafeCheck(t, 0);
        if (null == result) return null;
        for (int i = 1; i < t.length; i++) {
            result = result.divide(castSafe(t[i], 1), precision, BigDecimal.ROUND_HALF_UP);
        }
        return result.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * tips 进行数值转换 无法转换直接设置为BigDecimal.ZERO
     * type 标识计算风格 加法减法无法转换为数字类型设置为BigDecimal.ZERO或者 乘法除法设置为 new bigDecimal("1")
     * cash
     *
     * @param <T>  the type parameter
     * @param t    the t
     * @param type the type
     * @return the big decimal
     * @author hihuzi 2019/8/16 9:19
     */
    public static <T> BigDecimal castSafe(T t, int type) {


        if (t == null) {
            return type == 1 ? new BigDecimal("1") : BigDecimal.ZERO;
        } else if (t instanceof Number) {
            return new BigDecimal(t.toString());
        } else if (t instanceof String) {
            char[] chars = ((String) t).toCharArray();
            for (char isNum : chars) {
                if (Character.isDigit(isNum)) return new BigDecimal((String) t);
                else return type == 1 ? new BigDecimal("1") : BigDecimal.ZERO;
            }
            return type == 1 ? new BigDecimal("1") : BigDecimal.ZERO;
        } else return type == 1 ? new BigDecimal("1") : BigDecimal.ZERO;

    }

    /**
     * tips 进行数值转换
     * 存在数字开头不是后面不是数据类型的无法处理 建议用安全类型 convertSafe
     * 可能存在报错
     *
     * @param <T>  the type parameter
     * @param t    the t
     * @param type the type
     * @return the big decimal
     * @author hihuzi 2019/8/16 9:19
     */
    public static <T> BigDecimal castSafeCheck(T t, int type) {

        if (null == t) return null;
        else if (t.getClass().isArray()) {
            return castSafe(((Object[]) t)[0], type);
        }
        return null;
    }

}
