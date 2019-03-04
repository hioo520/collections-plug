package top.hihuzi.collection.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 表和对象缓存器
 *
 * @author hihuzi 2019/2/13 17:16
 */
public class TableCache {

    private Map<String, String> cache;

    /**
     * Instantiates a new Table cache.
     *
     * @param paramterName the paramter name
     * @param tableName    the table name
     */
    TableCache(String paramterName, String tableName) {

        cache = new HashMap<>(1);
        cache.put(paramterName, tableName);

    }

    /**
     * Gets cache.
     *
     * @return the cache
     */
    public Map<String, String> getCache() {

        return cache;
    }

    /**
     * Gets cache.
     *
     * @param paramterName the paramter name
     * @return the cache
     */
    public String getCache(String paramterName) {

        return cache.get(paramterName);
    }

}
