package top.hihuzi.collection.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * tips sql+缓存 (关键字段 SQLKey sql)
 *
 * @author: hihuzi 2019/2/13 16:36
 */
public class SQLCache {

    private static Map<String, String> cache;

    private static SQLCache sqlCache;

    public static String getCache(String key) {

        if (cache == null) {
            return null;
        }
        return cache.get(key);
    }

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

    private SQLCache() {

    }

    public static SQLCache get() {

        if (null == sqlCache) {
            sqlCache = CacheClacc.CLASS_CACHE;
        }
        return sqlCache;
    }

    /**
     * tips 内部类(延时加载)
     *
     * @author: hihuzi 2018/9/24 17:16
     */
    private static class CacheClacc {

        private static final SQLCache CLASS_CACHE = new SQLCache();

    }

}
