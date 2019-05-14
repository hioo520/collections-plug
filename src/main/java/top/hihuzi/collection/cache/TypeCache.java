package top.hihuzi.collection.cache;

import top.hihuzi.collection.common.ValueHandleCache;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Method;

/**
 * <p> 对象属性 缓存器
 *
 * @author hihuzi 2018/9/24 9:23
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
     * <p> 构造器实例化对象
     *
     * @param clazz        Class
     * @param paramterName String
     * @param paramtertype Class
     * @author hihuzi 2018/9/24 23:45
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
     * <p> 构造器实例化对象
     *
     * @param clazz        Class
     * @param paramterName String
     * @param paramtertype Class
     * @return TypeCache type cache
     * @author hihuzi 2018/9/24 23:45
     */
    public static TypeCache add(Class<?> clazz, String paramterName, Class<?> paramtertype) {

        return new TypeCache(clazz, paramterName, paramtertype);
    }

    /**
     * <p> 转化为对应的参数类型
     * 针对继承父类方法的解析
     *
     * @param paramterName String
     * @return Class
     * @author hihuzi 2018/9/24 19:12
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
        throw new NoticeException("类获取属性错误-->类名是: " + this.clazz.getSimpleName() + " 参数是: " + paramterName);
    }

    /**
     * <p> 转化为对应的方法类型
     * (待优化)
     * 针对继承父类方法的解析
     *
     * @param paramter     String
     * @param paramtertype Class
     * @return Method
     * @author hihuzi 2018/9/24 19:12
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
        throw new NoticeException("类获取方法名称错误-->类名是: " + this.clazz.getSimpleName() + " 方法名称是: " + paramterName+" 可能不存在!");
    }

    /**
     * <p> 转化为对应的方法类型
     *
     * @param paramtertype Class
     * @return ValueHandleCache.TypeEnum
     * @author hihuzi 2018/9/24 19:12
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

    /**
     * Gets clazz.
     *
     * @return the clazz
     */
    public Class<?> getClazz() {

        return clazz;
    }

    /**
     * Gets method get.
     *
     * @return the method get
     */
    public Method getMethodGet() {

        return methodGet;
    }

    /**
     * Gets method set.
     *
     * @return the method set
     */
    public Method getMethodSet() {

        return methodSet;
    }

    /**
     * Gets paramter name.
     *
     * @return the paramter name
     */
    public String getParamterName() {

        return paramterName;
    }

    /**
     * Gets paramter get.
     *
     * @return the paramter get
     */
    public String getParamterGet() {

        return paramterGet;
    }

    /**
     * Gets paramter set.
     *
     * @return the paramter set
     */
    public String getParamterSet() {

        return paramterSet;
    }

    /**
     * Gets paramtertype.
     *
     * @return the paramtertype
     */
    public Class<?> getParamtertype() {

        return paramtertype;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ValueHandleCache.TypeEnum getType() {

        return type;
    }

}
