package top.hihuzi.collection.common;

import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.fill.config.FillConfig;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Method;

/**
 * tips 对对象反射调用set方法配置数据
 *
 * @author: hihuzi 2018/7/19 10:29
 */

public class Invoke {

    /**
     * tips 处理结果 装填对象
     *
     * @author: hihuzi 2019/2/25 14:54
     */
    public static <E> void processResult(E e, FillConfig config, Class clazz, String name, String value) {


        TypeCache cache = ClassCache.getCache(clazz, name);
        if (null != cache) {
            ValueHandleCache.invokeValue(e, cache.getMethodSet(), value, null, config, cache.getType());
        } else {
            injectionParameters(e, name, value, config);
        }
    }

    /**
     * 遍历父类 所有 获取属性
     *
     * @parameter: E e
     * @parameter: String name
     * @parameter: String value
     * @parameter: Config config
     * @return:
     * @author: hihuzi 2018/6/22 9:22
     */
    public static <E> void injectionParameters(E e, String name, String value, Config config) {

        Class clazz = e.getClass();
        Class<?> paramtertype = null;
        try {
            paramtertype = clazz.getDeclaredField(name).getType();
        } catch (NoSuchFieldException e0) {
        }
        if (StrUtils.isNNoEE(paramtertype)) {
            putValue(e, name, value, paramtertype, config);
        } else {
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    paramtertype = clazz.getDeclaredField(name).getType();
                } catch (NoSuchFieldException e1) {
                }
                if (StrUtils.isNNoEE(paramtertype)) {
                    putValue(e, name, value, paramtertype, config);
                }
            }

        }
    }

    /**
     * tips 注入值 方法
     *
     * @parameter: E e
     * @parameter: String name
     * @parameter: String value
     * @parameter: Class<?> paramtertype
     * @parameter: Config config
     * @return:
     * @author: hihuzi 2018/7/19 10:26
     */

    private static <E> void putValue(E e, String name, String value, Class<?> paramtertype, Config config) {

        if (StrUtils.isNoEE(paramtertype)) {
            try {
                paramtertype = e.getClass().getDeclaredField(name).getType();
            } catch (Exception ex) {
                new NoticeException("类获取属性错误-->类是: " + e + " 属性是名是: " + name, ex);
            }
        }
        Method method = null;
        try {
            method = e.getClass().getMethod(StrUtils.achieveSetFunction(name), paramtertype);
        } catch (Exception ex) {
            new NoticeException("类获取方法名错误-->类是: " + e + " 方法名是: " + StrUtils.achieveSetFunction(name), ex);
        }
        method.setAccessible(true);
        ClassCache.get().add(e.getClass(), name, paramtertype);
        ValueHandleCache.invokeValue(e, method, value, paramtertype.getSimpleName(), config, null);
    }

}
