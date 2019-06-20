package top.hihuzi.collection.utils;


import top.hihuzi.collection.exception.CalculateException;

import java.math.BigDecimal;

/**
 * tips  通用工具计算
 *
 * @author: hihuzi 2019/4/23 19:23
 */
public class CalculateUtils {


    /**
     * tips BigDecimal的加法运算封装
     *
     * @param <T> the type parameter
     * @param bn  the bn
     * @return big decimal
     * <p> 只有为空null时才设置为0 其它情况直接报异常
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal add(final T... bn) {


        BigDecimal b0 = null;
        if (null != bn) {
            try {
                b0 = null == bn[0] ? BigDecimal.ZERO : new BigDecimal(String.valueOf(bn[0]));
                for (int i = 1; i < bn.length; i++) {
                    b0 = b0.add(null == bn[i] ? BigDecimal.ZERO : new BigDecimal(String.valueOf(bn[i])));
                }
            } catch (Exception e) {
                throw new CalculateException("There is an input error or not a data type to convert to BigDecimal error!", e);
            }
        } else {
            throw new CalculateException("The value is empty!");
        }
        return b0;
    }

    /**
     * tips BigDecimal的加法运算封装
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return the big decimal
     * <p> 为 " " or null时才设置为0 其它情况无法转换为number的也处理为0
     * @author hihuzi 2019/4/26 10:09
     */
    public static <T> BigDecimal safeAdd(final T... value) {


        BigDecimal result = null;
        if (null != value) {
            result = null == value[0] ? BigDecimal.ZERO : processTransform(value[0]);
            for (int i = 1; i < value.length; i++) {
                result = result.add(null == value[i] ? BigDecimal.ZERO : processTransform(value[i]));
            }
        } else {
            return BigDecimal.ZERO;
        }
        return result;
    }


    /**
     * tips BigDecimal的减法运算封装
     *
     * @param <T> the type parameter
     * @param bn  the bn
     * @return big decimal
     * <p> 必须保证第一个数是减数 只有为空null时才设置为0 其它情况直接报异常
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal subtract(final T... bn) {


        BigDecimal b0 = null;
        if (null != bn) {
            try {
                b0 = null == bn[0] ? BigDecimal.ZERO : new BigDecimal(String.valueOf(bn[0]));
                for (int i = 1; i < bn.length; i++) {
                    b0 = b0.subtract(null == bn[i] ? BigDecimal.ZERO : new BigDecimal(String.valueOf(bn[i])));
                }
            } catch (Exception e) {
                throw new CalculateException("There is an input error or not a data type to convert to BigDecimal error!", e);
            }
        } else {
            throw new CalculateException("The value is empty!");
        }
        return b0;
    }

    /**
     * tips BigDecimal的减法运算封装
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return the big decimal
     * <p> 必须保证第一个数是减数 只有为空null时才设置为0 其它情况直接报异常
     * @author hihuzi 2019/4/26 11:06
     */
    public static <T> BigDecimal safeSubtract(final T... value) {


        BigDecimal result = null;
        if (null != value) {
            result = null == value[0] ? BigDecimal.ZERO : processTransform(value[0]);
            for (int i = 1; i < value.length; i++) {
                result = result.subtract(null == value[i] ? BigDecimal.ZERO : processTransform(value[i]));
            }
        } else {
            return BigDecimal.ZERO;
        }
        return result;
    }

    /**
     * tips BigDecimal的除法运算封装
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return big decimal
     * <p> BigDecimal的除法运算封装 ，如果除数或者被除数为0，  直接报异常
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal divide(final T... value) {

        return divide(2, value);
    }

    /**
     * tips BigDecimal的除法运算封装
     *
     * @param <T>     the type parameter
     * @param reserve the reserve
     * @param value   the value
     * @return big decimal
     * <p> BigDecimal的除法运算封装 ，如果除数或者被除数为0，  直接报异常(
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal divides(final int reserve, final T... value) {

        BigDecimal b0;
        if ((null != value || null == value[0]) && (0 != processTransform(value[0]).compareTo(BigDecimal.ZERO))) {
            b0 = processTransform(value[0]);
            try {
                for (int i = 1; i < value.length; i++) {
                    if (null != value[i] && 0 != processTransform(value[i]).compareTo(BigDecimal.ZERO)) {
                        BigDecimal b1 = processTransform(value[i]);
                        b0 = b0.divide(b1, reserve, BigDecimal.ROUND_HALF_UP);
                    }
                }
            } catch (Exception e) {
                throw new CalculateException("The presence of zero molecules!", e);
            }
        } else {
            throw new CalculateException("The denominator is not a data type or is zero!");
        }
        return b0;
    }

    /**
     * tips BigDecimal的除法运算封装
     *
     * @param <T>   the type parameter
     * @param value the value
     * @return the big decimal
     * <p> BigDecimal的除法运算封装 ，如果除数或者被除数为0，返回null
     * @author hihuzi 2019/4/26 11:06
     */
    public static <T> BigDecimal safedivide(final T... value) {

        return safedivides(2, value);
    }

    /**
     * tips BigDecimal的除法运算封装
     *
     * @param <T>     the type parameter
     * @param reserve the reserve
     * @param value   the value
     * @return the big decimal @
     * <p> BigDecimal的除法运算封装 ，如果除数为0，返回null 如果被除数是0或者其他会被忽略(特例所有的分母都是0 返回分母)
     * @author hihuzi 2019/4/26 11:06
     */
    public static <T> BigDecimal safedivides(final int reserve, final T... value) {


        BigDecimal b0;
        if ((null != value || null != value[0]) && 0 != processTransform(value[0]).compareTo(BigDecimal.ZERO)) {
            b0 = processTransform(value[0]);
            for (int i = 1; i < value.length; i++) {
                BigDecimal b1 = processTransform(value[i]);
                if (null != value[i] && 0 != b1.compareTo(BigDecimal.ZERO)) {
                    b0 = b0.divide(b1, reserve, BigDecimal.ROUND_HALF_UP);
                }
            }

        } else {
            return null;
        }
        return b0;
    }

    /**
     * tips BigDecimal的乘法运算封装
     *
     * @param <T> the type parameter
     * @param bn  the bn
     * @return big decimal
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal multiply(final T... bn) {

        return multiplys(2, bn);
    }

    /**
     * tips BigDecimal的乘法运算封装
     *
     * @param <T>     the type parameter
     * @param reserve the reserve
     * @param bn      the bn
     * @return big decimal
     * @author hihuzi 2019/4/26 10:10
     */
    public static <T> BigDecimal multiplys(final int reserve, final T... bn) {


        BigDecimal b0 = null;
        if (null != bn) {
            b0 = null == bn[0] ? BigDecimal.ZERO : new BigDecimal(String.valueOf(bn[0]));
            for (int i = 1; i < bn.length; i++) {
                b0 = b0.multiply(null == bn[i] ? BigDecimal.ZERO : new BigDecimal(String.valueOf(bn[i]))).setScale(reserve, BigDecimal.ROUND_HALF_UP);
                ;
            }
        } else {
            throw new CalculateException("The value is empty!");
        }
        return b0;
    }

    /**
     * tips 要么转换成功  要么置为零
     *
     * @author hihuzi 2019/4/29 17:43
     */
    private static <T> BigDecimal processTransform(T t) {

        if (t == null) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(String.valueOf(t));
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

}
