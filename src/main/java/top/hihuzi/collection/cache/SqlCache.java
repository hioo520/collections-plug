package top.hihuzi.collection.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> sql+缓存 (关键字段 SQLKey sql)
 *
 * @author hihuzi 2019/2/13 16:36
 */
public class SqlCache {

    private static Map<String, String> cache;

    private static SqlCache sqlCache;

    /**
     * Gets cache.
     *
     * @param key the key
     * @return the cache
     */
    public static String getCache(String key) {

        if (cache == null) {
            return null;
        }
        return cache.get(key);
    }

    /**
     * Add cache.
     *
     * @param sqlKey the sql key
     * @param sql    the sql
     */
    public static void addCache(String sqlKey, String sql) {

        String isbeing = getCache(sqlKey);
        if (null != isbeing) {
            if (null == cache) {
                cache = new HashMap<>(50);
            }
            cache.put(sqlKey, sql);
        } else {
            if (null == cache) {
                cache = new HashMap<>(50);
            }
            cache.put(sqlKey, sql);
        }
    }

    private SqlCache() {

    }

    /**
     * Get sql cache.
     *
     * @return the sql cache
     */
    public static SqlCache get() {

        if (null == sqlCache) {
            sqlCache = CacheClacc.CLASS_CACHE;
        }
        return sqlCache;
    }

    /**
     * <p> 内部类(延时加载)
     *
     * @author hihuzi 2018/9/24 17:16
     */
    private static class CacheClacc {

        private static final SqlCache CLASS_CACHE = new SqlCache();

    }

}
