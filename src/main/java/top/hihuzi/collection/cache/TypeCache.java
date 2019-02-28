package top.hihuzi.collection.cache;

import top.hihuzi.collection.common.ValueHandleCache;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Method;

/**
 * tips 对象属性 缓存器
 *
 * @author: hihuzi 2018/9/24 9:23
 */
public class TypeCache {

    /**
     * class对象
     */
    private Class<?> clazz;

    /**
     * ParamterName的get方法
     */
    private Method methodGet;

    /**
     * ParamterName的set方法
     */
    private Method methodSet;

    /**
     * 属性
     */
    private String paramterName;

    /**
     * 参数
     */
    private String paramterGet;

    /**
     * 参数
     */
    private String paramterSet;

    /**
     * 参数类型
     */
    private Class<?> paramtertype;

    private ValueHandleCache.TypeEnum type;

    /**
     * tips 构造器实例化对象
     *
     * @parameter: Class<?> clazz
     * @parameter: String paramterName
     * @parameter: Class<?> paramtertype
     * @author: hihuzi 2018/9/24 23:45
     */
    public TypeCache(Class<?> clazz, String paramterName, Class<?> paramtertype) {

        this.clazz = clazz;
        this.paramterName = paramterName;
        this.paramtertype = paramtertype != null ? paramtertype : conversiontoparamterType(paramterName);
        this.paramterGet = StrUtils.achieveGetFunction(paramterName);
        this.paramterSet = StrUtils.achieveSetFunction(paramterName);
        this.methodGet = conversionToMethod(this.paramterGet, null);
        this.methodSet = conversionToMethod(this.paramterSet, this.paramtertype);
        this.type = conversionToType(this.paramtertype);
    }


    /**
     * tips 构造器实例化对象
     *
     * @parameter: Class<?> clazz
     * @parameter: String paramterName
     * @parameter: Class<?> paramtertype
     * @return: TypeCache
     * @author: hihuzi 2018/9/24 23:45
     */
    public static TypeCache add(Class<?> clazz, String paramterName, Class<?> paramtertype) {

        return new TypeCache(clazz, paramterName, paramtertype);
    }

    /**
     * tips 转化为对应的参数类型
     * 针对继承父类方法的解析
     *
     * @parameter: String paramterName
     * @return: Class<?> paramtertype
     * @author: hihuzi 2018/9/24 19:12
     */
    private Class<?> conversiontoparamterType(String paramterName) {

        Class<?> paramterType = null;
        try {
            paramterType = clazz.getDeclaredField(paramterName).getType();
        } catch (Exception e) {

        }
        if (paramterType != null) {
            return paramterType;
        } else {
            for (clazz = clazz.getSuperclass(); Object.class != clazz; clazz = clazz.getSuperclass()) {
                try {
                    paramterType = clazz.getDeclaredField(paramterName).getType();
                } catch (Exception e) {
                }
                if (paramterType != null) {
                    return paramterType;
                }
            }
        }

        try {
            throw new NoticeException(this.clazz + "中无此" + paramterName + "属性,从而无法确定参数类型");
        } catch (NoticeException e) {
        }
        return null;
    }

    /**
     * tips 转化为对应的方法类型
     * (待优化)
     * 针对继承父类方法的解析
     *
     * @parameter: String paramter
     * @parameter: Class<?> paramtertype
     * @return: Method
     * @author: hihuzi 2018/9/24 19:12
     */
    private Method conversionToMethod(String paramter, Class<?> paramtertype) {

        Method method = null;
        try {
            method = clazz.getMethod(paramter, paramtertype);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(paramter);
            } catch (NoSuchMethodException e1) {
            }
        }
        if (method != null) {
            return method;
        } else {
            for (clazz = clazz.getSuperclass(); Object.class != clazz; clazz = clazz.getSuperclass()) {
                try {
                    method = clazz.getMethod(paramter, paramtertype);
                } catch (NoSuchMethodException e) {
                    try {
                        method = clazz.getMethod(paramter);
                    } catch (NoSuchMethodException e1) {
                    }
                }
                if (null != method) {
                    return method;
                }
            }
        }
        try {
            throw new NoticeException(this.clazz + "中无此" + paramterName + "属性,从而无法确定参数类型");
        } catch (NoticeException e) {
        }
        return null;
    }

    /**
     * tips 转化为对应的方法类型
     *
     * @parameter: Class<?> paramtertype
     * @return: ValueHandleCache.TypeEnum
     * @author: hihuzi 2018/9/24 19:12
     */
    private ValueHandleCache.TypeEnum conversionToType(Class<?> paramtertype) {

        ValueHandleCache.TypeEnum[] values = ValueHandleCache.TypeEnum.values();
        for (ValueHandleCache.TypeEnum value : values) {
            if (value.getValue().equals(paramtertype.getSimpleName())) {
                return value;
            }
        }
        return null;
    }

    public Class<?> getClazz() {

        return clazz;
    }

    public Method getMethodGet() {

        return methodGet;
    }

    public Method getMethodSet() {

        return methodSet;
    }

    public String getParamterName() {

        return paramterName;
    }

    public String getParamterGet() {

        return paramterGet;
    }

    public String getParamterSet() {

        return paramterSet;
    }

    public Class<?> getParamtertype() {

        return paramtertype;
    }

    public ValueHandleCache.TypeEnum getType() {

        return type;
    }

}
