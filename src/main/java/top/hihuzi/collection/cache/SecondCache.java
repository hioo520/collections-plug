package top.hihuzi.collection.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * tips 二段缓存
 *
 * @author: hihuzi 2019/2/13 16:36
 */
public class SecondCache {

    private static Map<String, Map<String, ParameterCache>> cache;

    public static Map<String, ParameterCache> getCache(String key) {

        if (cache == null) {
            return null;
        }
        return cache.get(key);
    }

    public static void addCache(String key, Map<String, ParameterCache> pcache) {

        Map<String, ParameterCache> isbeing = getCache(key);
        if (null != isbeing) {
            if (null == cache) {
                cache = new HashMap<>(50);
            }
            cache.put(key, pcache);
        } else {
            if (null == cache) {
                cache = new HashMap<>(50);
            }
            cache.put(key, pcache);
        }
    }

}
