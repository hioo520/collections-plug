package top.hihuzi.collection.common;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;


/**
 * tips 对对象进行注入值(高效)
 *
 * @author: hihuzi 2018/9/24 20:57
 */
public class ValueHandleCache {

    /**
     * tips 对对象进行注入值
     *
     * @parameter: E e
     * @parameter: Method method
     * @parameter: String value
     * @parameter: String fieldType
     * @parameter: Config config
     * @return:
     * @author: hihuzi 2018/7/19 10:26
     */
    public static <E> void invokeValue(E e, Method method, String value, String fieldType, Config config, TypeEnum typeEnum) {

        Object obj = null;
        if (fieldType != null) {
            for (TypeEnum typeEnu : TypeEnum.values()) {
                if (typeEnu.getValue().equals(fieldType)) {
                    typeEnum = typeEnu;
                    break;
                }
            }
        }
        if (null != value && !"".equals(value)) {
            switch (typeEnum) {
                case STRING:
                    obj = value;
                    break;
                case DATE:
                    try {
                        obj = config.getDateStyleEnum().getFormartStyle().parse(value);
                    } catch (ParseException ex) {
                        new NoticeException("时间转换错误-->时间格式是: " + config.getDateStyleEnum().getFormartStyle() + " 待处理的数据是: " + value, ex);
                    }
                    break;
                case CHAR:
                    obj = value.toCharArray()[0];
                    break;
                case BYTE:
                case BYTE_MIN:
                    obj = Byte.valueOf(value);
                    break;
                case LONG:
                case LONG_MIN:
                    obj = Long.parseLong(value);
                    break;
                case SHORT:
                case SHORT_MIN:
                    obj = Short.parseShort(value);
                    break;
                case FLOAT:
                case FLOAT_MIN:
                    obj = Float.parseFloat(value);
                    break;
                case DOUBLE:
                case DOUBLE_MIN:
                    obj = Double.parseDouble(value);
                    break;
                case INTEGER:
                case INT:
                    obj = Integer.parseInt(value);
                    break;
                case BOOLEAN:
                case BOOLEAN_MIN:
                    obj = Boolean.parseBoolean(value);
                    break;
                case BIGDECIMAL:
                    obj = new BigDecimal(value);
                    break;
                default:
                    new NoticeException("未定义类型错误" + typeEnum.toString() + " 待处理数值: " + value);
                    break;

            }
        } else {
            switch (typeEnum) {
                case STRING:
                    obj = value;
                    break;
                case CHAR:
                    return;
                case SHORT_MIN:
                    obj = (short) 0;
                    break;
                case BYTE_MIN:
                    obj = Byte.parseByte("0");
                    break;
                case BOOLEAN:
                case BOOLEAN_MIN:
                    obj = false;
                    break;
                case INT:
                case FLOAT_MIN:
                case LONG_MIN:
                case DOUBLE_MIN:
                    obj = 0;
                    break;
                case BYTE:
                case INTEGER:
                case SHORT:
                case FLOAT:
                case LONG:
                case DOUBLE:
                case BIGDECIMAL:
                case DATE:
                    obj = null;
                    break;
                default:
                    new NoticeException("未定义类型错误" + typeEnum.toString() + " 待处理数值: " + value);
                    break;
            }
        }
        try {
            method.invoke(e, obj);
        } catch (Exception ex) {
            new NoticeException("填塞对象值错误-->对象名是: " + e.getClass().getSimpleName() + " 类型可能未在:" + typeEnum.toString() + " 待处理数值: " + value + " 转换为: " + obj);
        }
    }

    /**
     * tips 只针对时间类型转化
     *
     * @parameter: "Class<?>" clazz
     * @parameter: Config config
     * @parameter: Object obj
     * @author: hihuzi 2018/10/10 19:30
     */
    public static Object processingTimeType(Class<?> clazz, Config config, Object obj) {

        if (TypeEnum.DATE.getValue().equals(clazz.getSimpleName())) {
            return config.getDateStyleEnum().getFormartStyle().format(obj);
        }
        return obj;
    }

    /**
     * tips 数据类型转化
     *
     * @notice : 0 是预留数据类型 表示没有匹配
     * @author: hihuzi 2018/9/24 19:30
     */
    public enum TypeEnum {
        /**
         * 数据类型
         */
        BOOLEAN("Boolean"),
        /**
         * 数据类型
         */
        BYTE("Byte"),
        /**
         * 数据类型
         */
        SHORT("Short"),
        /**
         * 数据类型
         */
        INTEGER("Integer"),
        /**
         * 数据类型
         */
        LONG("Long"),
        /**
         * 数据类型
         */
        FLOAT("Float"),
        /**
         * 数据类型
         */
        DOUBLE("Double"),
        /**
         * 数据类型
         */
        STRING("String"),
        /**
         * 数据类型
         */
        BIGDECIMAL("BigDecimal"),
        /**
         * 数据类型
         */
        DATE("Date"),
        /**
         * 数据类型
         */
        CHAR("char"),
        /**
         * 数据类型
         */
        INT("int"),
        /**
         * 数据类型
         */
        BOOLEAN_MIN("boolean"),
        /**
         * 数据类型
         */
        BYTE_MIN("byte"),
        /**
         * 数据类型
         */
        SHORT_MIN("short"),
        /**
         * 数据类型
         */
        LONG_MIN("long"),
        /**
         * 数据类型
         */
        FLOAT_MIN("float"),
        /**
         * 数据类型
         */
        DOUBLE_MIN("double");

        private String value;

        TypeEnum(String value) {

            this.value = value;

        }

        public String getValue() {

            return value;
        }
    }

}
