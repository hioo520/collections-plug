package top.hihuzi.collection.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * tips 表和对象缓存器
 *
 * @author: hihuzi 2019/2/13 17:16
 */
public class TableCache {

    private Map<String, String> cache;

    TableCache(String paramterName, String tableName) {

        cache = new HashMap<>(1);
        cache.put(paramterName, tableName);

    }

    public Map<String, String> getCache() {

        return cache;
    }

    public String getCache(String paramterName) {

        return cache.get(paramterName);
    }

}
