package top.hihuzi.collection.common;

import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.ParameterCache;
import top.hihuzi.collection.cache.SecondCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.sql.config.SQLBean;
import top.hihuzi.collection.sql.config.SQLConfig;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tips 通用公共方法
 *
 * @author: hihuzi 2019/2/15 15:03
 */
public class PublicMethod {

    /**
     * tips 根据对象属性 驼峰转下划线 对应表
     *
     * @author: hihuzi 2019/2/15 11:24
     */
    public static <E> Map getHumpToLine(E e) {

        Map map = new HashMap(((Class) e).getDeclaredFields().length);
        Class<?> clazz = null;
        clazz = (Class) e;
        for (; Object.class != clazz; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                map.put(field.getName(), StrUtils.humpToLine(field.getName()));
                ClassCache.get().add((Class<?>) e, field.getName(), field.getType());
            }
        }
        return map;
    }

    /**
     * tips 无线递归上级找属性(表和对象属性匹配)
     *
     * @author: hihuzi 2019/2/12 14:06
     */
    public static <E> Map<String, ParameterCache> tableNameMatchParameter(Map list, E... e) {

        if (!isBeingCache(e)) {
            addCache(list, e);
        }
        Map<String, ParameterCache> map = SecondCache.getCache(StrUtils.splicingObjectName(e));
        if (null == map || 0 == map.size()) {
            map = new HashMap<>(e.length);
            for (E es : e) {
                Map<String, ParameterCache> pCache = ClassCache.getPCache(es.getClass());
                map.putAll(pCache);
            }
            SecondCache.addCache(StrUtils.splicingObjectName(e), map);
        }
        return map;
    }

    /**
     * tips 无线递归上级找属性(表和对象属性匹配)(带缓存)
     *
     * @author: hihuzi 2019/2/12 14:06
     */
    public static <E> Map<String, ParameterCache> tableNameMatchParameter(SQLConfig config, E... e) {

        String sqlKey = config.getSqlEeum().get().key();
        Map<String, ParameterCache> map = SecondCache.getCache(sqlKey);
        if (null == map) {
            map = new HashMap(e.length);
            for (E es : e) {
                Map<String, ParameterCache> pCache = ClassCache.getPCache(sqlKey + ((Class) es).getSimpleName());
                map.putAll(pCache);
            }
            SecondCache.addCache(sqlKey, map);
        }
        return map;
    }

    /**
     * tips 初级缓存
     *
     * @author: hihuzi 2019/2/14 13:00
     */
    private static <E> boolean isBeingCache(E... e) {


        for (E es : e) {
            Map<String, ParameterCache> pCache = ClassCache.getPCache(es.getClass());
            if (null == pCache) return false;
        }
        return true;
    }

    /**
     * tips 加入缓存(TypeCache)
     *
     * @author: hihuzi 2019/2/15 11:24
     */
    private static <E> void addCache(Map list, E... e) {

        for (E es : e) {
            Class<?> clazz = es.getClass();
            for (Object obj : list.keySet()) {
                for (; Object.class != clazz; clazz = clazz.getSuperclass()) {
                    for (Field field : clazz.getDeclaredFields()) {
                        if (StrUtils.isEquals(String.valueOf(obj), field.getName())) {
                            ClassCache.get().add(es.getClass(), field.getName(), field.getType());
                            ClassCache.get().add(es.getClass(), field.getName(), field.getType(), String.valueOf(obj), null);
                            break;
                        }
                    }
                }
                clazz = es.getClass();
            }
        }
    }

    /**
     * tips 只针对时间类型 和字符串类型 转化
     *
     * @author: hihuzi 2018/10/10 19:30
     */
    public static Object processingTimeType(Class<?> type, Config config, Object obj) {

        if (ValueHandleCache.TypeEnum.DATE.getValue().equals(type.getSimpleName())) {
            if (null == obj) return null;
            return config.getDateStyleEnum().getFormartStyle().format(obj);
        }
        if (ValueHandleCache.TypeEnum.STRING.getValue().equals(type.getSimpleName())) {
            if (config.getSaveStyleEnum().getHaving()) {
                return obj;
            } else {
                return "".equals(String.valueOf(obj).trim()) ? null : obj;
            }
        }
        return obj;
    }

    /**
     * tips 只针对时间类型 和字符串类型 转化
     *
     * @author: hihuzi 2018/10/10 19:30
     */
    public static Object processTimeType(Class<?> type, Config config, Object obj) {

        if (ValueHandleCache.TypeEnum.DATE.getValue().equals(type.getSimpleName())) {
            if (null == obj) return null;
            try {
                return obj.toString().substring(0, config.getDateStyleEnum().getFormartStyle().toPattern().length());
            } catch (Exception e) {
                return obj;
            }
        }
        if (ValueHandleCache.TypeEnum.STRING.getValue().equals(type.getSimpleName())) {
            if (config.getSaveStyleEnum().getHaving()) {
                return obj;
            } else {
                return "".equals(String.valueOf(obj).trim()) ? null : obj;
            }
        }
        return obj;
    }

    /**
     * tips 获取对象中和待展示的数据重复的个数
     *
     * @author: hihuzi 2019/2/19 17:39
     */
    public static int achieveTimes(Class clazz, List<String> display) {

        int i = 0;
        for (; Object.class != clazz; clazz = clazz.getSuperclass()) {
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (display.contains(declaredField.getName())) {
                    i++;
                }
            }
        }
        return i;
    }


    /**
     * tips sql+ 处理key  处理value
     *
     * @author: hihuzi 2019/3/3 16:10
     */
    public static void achieveMap(Map map0, String name, Object value, SQLConfig config, TypeCache typeCache) {

        SQLBean sqlBean = config.getSqlEeum().get();
        Map<String, String> displayNickname = sqlBean.getDisplayNickname();
        String paramterName = typeCache.getParamterName();
        /**notice 查询所有属性**/
        if (null == displayNickname) {
            if (sqlBean.getRepeat().contains(paramterName))
                name = typeCache.getClazz().getSimpleName() + StrUtils.capsHead(paramterName);
            else
                name = paramterName;
        } else {
            if (!sqlBean.getRepeat().contains(paramterName)) {
                name = paramterName;
            } else if (sqlBean.getRepeat().contains(paramterName) && !displayNickname.containsValue(name)) {
                name = typeCache.getClazz().getSimpleName() + StrUtils.capsHead(paramterName);
            }
        }
        Object val = PublicMethod.processTimeType(typeCache.getParamtertype(), config, value);
        achieveMap(map0, name, val, config);

    }

    /**
     * tips 从新命名key
     *
     * @parameter:
     * @return:
     * @author: hihuzi 2018/9/28 16:03
     */
    public static void achieveMap(Map map, String key, Object invoke, Config config) {


        if (null != invoke) {
            map.put(achieveKey(key, config), invoke);
        } else if (config.getSaveStyleEnum().getHaving()) {
            map.put(achieveKey(key, config), "");
        }
    }

    /**
     * tips 从新命名key
     *
     * @parameter:
     * @return:
     * @author: hihuzi 2018/9/28 16:03
     */
    private static String achieveKey(String property, Config config) {

        switch (config.getReturnNameEnum()) {
            case DEFAULT:
                return property;
            case LOWER_CASE:
                return property.toLowerCase();
            case UPPER_CASE:
                return property.toUpperCase();
            case INITIAL_CAPITAL:
                return StrUtils.capsHead(property);
            case CUSTOM_SUFFIX:
                return config.getReturnNameEnum().getKey() + property;
            case CLASS_HUMP:
            return property.substring(0, 1).toLowerCase() + property.substring(1);
            default:
                throw new NoticeException("命名风格未定义");
        }
    }


}
