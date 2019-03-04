package top.hihuzi.collection.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 表-对象属性缓存器
 *
 * @author hihuzi 2019/2/13 8:39
 */
public class ParameterCache {

    /**
     * 缓存class 全限定名 参数类型 参数
     * 第一个 String: class 全限定名
     * 第二个String: class 属性名
     * cache---"Map(属性名称,[各个属性的方法,属性类型])"
     *
     * @author hihuzi 2019/2/11 11:14
     */
    public Map<String, TypeCache> paramCache = null;

    /**
     * <p> 构造器实例化对象
     *
     * @param clazz        Class
     * @param paramterName String
     * @param paramtertype Class
     * @param tableName    Class
     * @author hihuzi 2019/2/11 11:14
     */
    public ParameterCache(Class<?> clazz, String paramterName, Class<?> paramtertype, String tableName) {


        TypeCache caches = ClassCache.getCache(clazz, paramterName);
        if (null == caches) {
            ClassCache.get().add(clazz, paramterName, paramtertype);
            caches = ClassCache.getCache(clazz, paramterName);
        }
        if (null == this.paramCache) {
            this.paramCache = new HashMap<>(clazz.getDeclaredFields().length);
        }
        this.paramCache.put(tableName, caches);

    }

    /**
     * <p> 构造器实例化对象
     *
     * @param clazz        Class
     * @param paramterName String
     * @param paramtertype Class
     * @param tableName    Class
     * @return TypeCache parameter cache
     * @author hihuzi 2018/9/24 23:45
     */
    public static ParameterCache add(Class<?> clazz, String paramterName, Class<?> paramtertype, String tableName) {

        return new ParameterCache(clazz, paramterName, paramtertype, tableName);
    }

    /**
     * <p> 获取缓存
     *
     * @return Map cache
     * @author hihuzi 2019/2/12 10:23
     */
    public Map<String, TypeCache> getCache() {

        return paramCache;
    }


    /**
     * <p> 获取属性缓存
     *
     * @param tableName tableName
     * @return TypeCache cache
     * @author hihuzi 2019/2/12 10:23
     */
    public TypeCache getCache(String tableName) {

        return paramCache.get(tableName);
    }


}
