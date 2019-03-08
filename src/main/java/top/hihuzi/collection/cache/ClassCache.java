package top.hihuzi.collection.cache;

import top.hihuzi.collection.config.CacheBean;
import top.hihuzi.collection.sql.config.SqlBean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> class缓存器 字典 饿汉式(内部类懒加载)线程安全行
 *
 * @author hihuzi 2018/9/23 23:37
 */
public class ClassCache {

    /**
     * <p> 对象属性缓存器(用于对象填充)--cache
     * 缓存class 全限定名 参数类型 参数
     * 第一个 String: class 全限定名
     * 第二个String: class 属性名
     *
     * <p> cache---"Map (class, 全限定名称,Map (属性名称,[各个属性的方法,属性类型])) "
     */
    public volatile static Map<String, Map<String, TypeCache>> cache = null;

    /**
     * <p> 表-对象属性缓存器(用于非自定义SQL)--paramCache
     * 缓存class 全限定名 参数类型 参数
     * 第一个 String: class 全限定名
     * 第二个String: class 表名
     * cache--->"Map(class 全限定名称,Map(表名称,(各个属性的方法,属性类型)))"
     */
    private volatile static Map<String, Map<String, ParameterCache>> paramCache = null;

    /**
     * <p> 对象属性-表缓存器(用于自定义多SQL)-->tableCache未来兼容非驼峰结构待用
     * 缓存class 全限定名 参数类型 参数
     * 第一个 String: class 全限定名
     * 第二个String: class 表名
     * <p>  cache--->"Map(class 全限定名称,Map(各个属性的方法,表名称))"
     */
    private volatile static Map<String, TableCache> tableCache = null;

    /**
     * <p> SqlBean-缓存器(解决重复计算问题)--objectCache
     * 缓存class 全限定名 参数类型 参数
     * 第一个 String: class 全限定名
     * 第二个String: class 表名
     * <p> cache--->"Map(class 全限定名称,Map(各个属性的方法,表名称))"
     */
    private volatile static Map<String, Object> objectCache = null;

    /**
     * <p> 单例
     *
     * @author hihuzi 2019/2/18 9:24
     */
    private static ClassCache classCache = null;


    /**
     * <p> 从缓存中取出数据(TypeCache)
     *
     * @param clazz Class
     * @return Map cache
     * @author hihuzi 2018/9/24 17:32
     */
    public static Map<String, TypeCache> getCache(Class<?> clazz) {

        Map<String, TypeCache> result = null;
        if (cache == null || (result = cache.get(clazz.getName())) == null) {
            return null;
        }
        return result;
    }

    /**
     * Gets cache.
     *
     * @param clazz        the clazz
     * @param paramterName the paramter name
     * @return the cache
     */
    public static TypeCache getCache(Class<?> clazz, String paramterName) {

        Map<String, TypeCache> result = null;
        if (cache == null || (result = cache.get(clazz.getName())) == null) {
            return null;
        }
        return result.get(paramterName);
    }

    /**
     * Gets p cache.
     *
     * @param clacc the clacc
     * @return the p cache
     */
    public static Map<String, ParameterCache> getPCache(Object clacc) {

        Class<?> clazz = null;
        if (clacc instanceof Class) {
            clazz = (Class<?>) clacc;
            if (paramCache == null || paramCache.get(clazz.getName()) == null) {
                return null;
            }
            return paramCache.get(clazz.getName());
        }
        if (clacc instanceof String) {
            if (paramCache == null || paramCache.get(clacc) == null) {
                return null;
            }
            return paramCache.get(clacc);
        }
        return null;
    }

    /**
     * Gets p cache.
     *
     * @param clacc        the clacc
     * @param paramterName the paramter name
     * @return the p cache
     */
    public static ParameterCache getPCache(Object clacc, String paramterName) {

        Class<?> clazz = null;
        if (clacc instanceof Class) {
            clazz = (Class<?>) clacc;
            Map<String, ParameterCache> result = null;
            if (null == paramCache || (result = paramCache.get(clazz.getName())) == null) {
                return null;
            }
            return paramCache.get(clazz.getName()).get(paramterName);
        }
        if (clacc instanceof String) {
            Map<String, ParameterCache> result = null;
            if (null == paramCache || (result = paramCache.get(clacc)) == null) {
                return null;
            }
            return paramCache.get(clacc).get(paramterName);
        }
        return null;
    }


    /**
     * Gets t cache.
     *
     * @param sqlKey the sql key
     * @return the t cache
     */
    public static TableCache getTCache(String sqlKey) {

        TableCache result = null;
        if (null == tableCache || (result = tableCache.get(sqlKey)) == null) {
            return null;
        }
        return result;
    }

    /**
     * Gets o cache.
     *
     * @param sqlKey the sql key
     * @return the o cache
     */
    public static CacheBean getOCache(String sqlKey) {

        CacheBean result = null;
        if (null == objectCache || (result = (CacheBean) objectCache.get(sqlKey)) == null) {
            return null;
        }
        return result;
    }

    /**
     * <p> 加入缓存机制 Cache
     *
     * @param clazz        Class
     * @param paramterName String                     <p> 添加规则 同一个类对象 只有一个 key 可以有多个 TypeCache 瞬时态
     * @author hihuzi 2018/9/24 18:22
     */
    public void add(Class<?> clazz, String paramterName) {

        add(clazz, paramterName, null);
    }

    /**
     * <p> 加入缓存机制 cache
     *
     * @param clazz        Class
     * @param paramterName String
     * @param paramtertype Class                     <p> 添加规则 同一个类对象 只有一个 key 可以有多个 TypeCache 瞬时态
     * @author hihuzi 2018/9/24 18:22
     */
    public void add(Class<?> clazz,
                    String paramterName,
                    Class<?> paramtertype) {

        Map<String, TypeCache> typeCacheMap = null;
        if (cache != null) {
            if (cache.containsKey(clazz.getName())) {
                typeCacheMap = cache.get(clazz.getName());
            } else {
                typeCacheMap = new HashMap(1);
            }
        } else {
            cache = new HashMap<>(20);
            typeCacheMap = new HashMap(1);
        }
        joinTheCache(clazz, paramterName, typeCacheMap, paramtertype);
    }

    /**
     * <p> 加入缓存机制 paramCache
     * <p> 加入缓存机制 如果是通用类的属性就用全限定名  存在定义的cache key  以定义为主
     * @param clazz        Class
     * @param paramterName String
     * @param paramtertype Class
     * @param tableName    String
     * @param sqlKey       String                     <p> 添加规则 同一个类对象 只有一个 key 可以有多个 TypeCache 瞬时态
     * @author hihuzi 2018/9/24 18:22
     */
    public void add(Class<?> clazz,
                    String paramterName,
                    Class<?> paramtertype,
                    String tableName,
                    String sqlKey) {

        Map<String, ParameterCache> parameterCacheMap = null;
        if (sqlKey != null) {
            if (null != paramCache) {
                if (paramCache.containsKey(sqlKey + clazz.getSimpleName())) {
                    parameterCacheMap = paramCache.get(sqlKey + clazz.getSimpleName());
                } else {
                    parameterCacheMap = new HashMap(1);
                }
            } else {
                paramCache = new HashMap<>(20);
                parameterCacheMap = new HashMap(1);
            }
        } else {
            if (null != paramCache) {
                if (paramCache.containsKey(clazz.getName())) {
                    parameterCacheMap = paramCache.get(clazz.getName());
                } else {
                    parameterCacheMap = new HashMap(1);
                }
            } else {
                paramCache = new HashMap<>(20);
                parameterCacheMap = new HashMap(1);
            }
        }
        joinTheCache(clazz, paramterName, parameterCacheMap, paramtertype, tableName, sqlKey);
    }

    /**
     * <p> 加入缓存机制 TableCache
     *
     * @param sqlKey       String
     * @param paramterName String
     * @param tableName    String                     <p> 添加规则 同一个类对象 只有一个 key 可以有多个 TypeCache 瞬时态
     * @author hihuzi 2018/9/24 18:22
     */
    public void add(String sqlKey,
                    String paramterName,
                    String tableName) {

        TableCache tableCacheMap = null;
        if (null == tableCache) {
            tableCache = new HashMap<>(20);
            tableCache.put(sqlKey, new TableCache(paramterName, tableName));
        } else {
            if (null == (tableCacheMap = tableCache.get(sqlKey))) {
                tableCache.put(sqlKey, new TableCache(paramterName, tableName));
            } else {
                Map<String, String> cache = tableCacheMap.getCache();
                cache.put(paramterName, tableName);
                tableCache.put(sqlKey, tableCacheMap);
            }

        }
    }

    /**
     * <p> 加入缓存机制 objectCache
     *
     * @param sqlKey String
     * @param bean   CacheBean               <p> 添加规则 同一个sql+ vo查询语句 只有一个 key 可以有多个
     * @author hihuzi 2018/9/24 18:22
     */
    public void add(String sqlKey,
                    CacheBean bean) {

        SqlBean sqlBean = null;
        if (bean instanceof SqlBean) {
            sqlBean = (SqlBean) bean;
            if (null == objectCache) {
                objectCache = new HashMap<>(20);
                objectCache.put(sqlKey, sqlBean);
            } else if (null == objectCache.get(sqlKey)) {
                objectCache.put(sqlKey, sqlBean);
            }

        }
    }

    /**
     * <p> 加入缓存(cache)
     *
     * @param clazz        Class
     * @param paramterName String
     * @param cacheTypeMap "Map<String, TypeCache>"
     * @param paramtertype Class
     * @return
     * @author hihuzi 2018/9/24 18:09
     */
    private void joinTheCache(Class<?> clazz, String
            paramterName, Map<String, TypeCache> cacheTypeMap, Class<?> paramtertype) {

        cacheTypeMap.put(paramterName, TypeCache.add(clazz, paramterName, paramtertype));
        cache.put(clazz.getName(), cacheTypeMap);
    }

    /**
     * <p> 加入缓存(paramCache)
     *
     * @param clazz        Class
     * @param paramterName String
     * @param paramterMap  "Map<String, TypeCache>"
     * @param paramtertype "Map<String, TypeCache>"
     * @param tableName    String
     * @param sqlKey       String
     * @param paramtertype Class
     * @return
     * @author hihuzi 2018/9/24 18:09
     */
    private void joinTheCache(Class<?> clazz, String paramterName, Map<String, ParameterCache> paramterMap,
                              Class<?> paramtertype, String tableName, String sqlKey) {

        paramterMap.put(tableName, ParameterCache.add(clazz, paramterName, paramtertype, tableName));
        if (sqlKey == null) {
            paramCache.put(clazz.getName(), paramterMap);
        } else {
            paramCache.put(sqlKey + clazz.getSimpleName(), paramterMap);

        }
    }

    /**
     * Get class cache.
     *
     * @return the class cache
     */
    public static ClassCache get() {

        if (null == classCache) {
            return CacheClazz.CLASS_CACHE;
        } else {
            return classCache;
        }
    }

    private ClassCache() {

    }

    /**
     * <p> 内部类(延时加载)
     *
     * @author hihuzi 2018/9/24 17:16
     */
    private static class CacheClazz {

        private static final ClassCache CLASS_CACHE = new ClassCache();

    }

}
