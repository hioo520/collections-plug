package top.hihuzi.collection.pick.core;


import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.common.PublicMethod;
import top.hihuzi.collection.config.ConfigEnum;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.pick.config.PickConfig;
import top.hihuzi.collection.pick.factory.PickMethodFactory;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * tips 带缓存机制
 *
 * @Author:hihuzi 2018/7/19 17:54
 */
abstract class PickServiceImpl implements PickMethodFactory {

    /**
     * tips 同一对象集合 返回选定字段 集合返回
     *
     * @parameter: List<E> list
     * @parameter: String[] strs
     * @parameter: PickEnum enums
     * @return: List<Map>
     * @author: hihuzi 2018/7/12 8:03
     */
    public <T> Collection batch(List<T> list, PickConfig config, String... key) {

        Class clacc = list.get(0).getClass();
        Class clazz = clacc;
        List<Map> lists = new ArrayList<>(list.size());
        if (clacc.getSimpleName().contains(Map.class.getSimpleName())) {
            List<String> strs = Arrays.asList(key);
            for (T t : list) {
                Map map = (Map) t;
                Map map0 = new HashMap(map.size());
                for (Object obj : map.keySet()) {
                    if (strs.contains(obj)) {
                        achieveMap(map0, String.valueOf(obj), map.get(obj), config);
                    }
                }
                lists.add(map0);
            }
            return lists;
        }
        Set sets = new HashSet<>(list.size() * key.length);
        Map maps = new HashMap(list.size() * key.length);
        for (T t : list) {
            Map map = new HashMap<>(key.length);
            for (String property : key) {
                String name = StrUtils.achieveGetFunction(property);
                Object invoke = null;
                Method method = null;
                TypeCache cache = ClassCache.getCache(t.getClass(), property);
                if (null != cache) {
                    method = cache.getMethodGet();
                    method.setAccessible(true);
                    try {
                        invoke = method.invoke(t);
                    } catch (Exception ex) {
                        throw new NoticeException("获取类属性值错误-->类名是: " + t.getClass().getSimpleName() + " 方法名是: " + method.getName(), ex);
                    }
                    invoke = PublicMethod.processingTimeType(cache.getParamtertype(), config, invoke);
                } else {
                    try {
                        invoke = achieveInvoke(name, t, t.getClass(), method, invoke, property, config);
                    } catch (Exception e) {
                        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                            try {
                                invoke = achieveInvoke(name, t, clazz, method, invoke, property, config);
                                clazz = clacc;
                                break;
                            } catch (Exception e0) {
                                continue;
                            }
                        }
                    }
                }
                definitionReturn(list, config, sets, maps, map, property, invoke);
            }
            if (config.getReturnStyleEnum() == ConfigEnum.ReturnStyleEnum.DEFAULT
                    || config.getReturnStyleEnum() == ConfigEnum.ReturnStyleEnum.LIST_MAP) {
                lists.add(map);
            }
        }
        if (0 != sets.size()) {
            return sets;
        }
        if (0 != lists.size()) {
            return lists;
        }
        if (0 != maps.size()) {
            lists.add(maps);
            return lists;
        }
        return null;
    }

    private <T> void definitionReturn(List<T> list,
                                      PickConfig config,
                                      Set sets,
                                      Map maps,
                                      Map map,
                                      String property,
                                      Object invoke) {

        switch (config.getReturnStyleEnum()) {
            case DEFAULT:
            case LIST_MAP:
                achieveMap(map, property, invoke, config);
                break;
            case MAP:
                achieveMap(maps, property, invoke, config);
                break;
            case SET:
                if (invoke != null) {
                    sets.add(invoke);
                } else if (config.getSaveStyleEnum().getHaving()) {
                    sets.add(invoke);
                }
                break;
            default:
                throw new NoticeException("数据输出超出配置范围: " + config.getReturnStyleEnum().toString());

        }
    }


    /**
     * tips 从新命名key
     *
     * @parameter:
     * @return:
     * @author: hihuzi 2018/9/28 16:03
     */
    private void achieveMap(Map map, String key, Object invoke, PickConfig config) {


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
    private String achieveKey(String property, PickConfig config) {

        switch (config.getReturnNameEnum()) {
            case DEFAULT:
                return property;
            case LOWER_CASE:
                return property.toLowerCase();
            case UPPER_CASE:
                return property.toUpperCase();
            case INITIAL_CAPITAL:
                return property.substring(0, 1).toUpperCase() + property.substring(1);
            case CUSTOM_SUFFIX:
                return config.getReturnNameEnum().getKey() + property;
            default:
                throw new NoticeException("命名风格未定义");
        }
    }

    /**
     * tips 添加缓存
     *
     * @parameter:
     * @return:
     * @author: hihuzi  2018/10/18 11:43
     */
    private <T> Object achieveInvoke(String name, T t,
                                     Class clazz,
                                     Method method,
                                     Object invoke,
                                     String property,
                                     PickConfig config) {

        try {
            method = clazz.getMethod(name);
            method.setAccessible(true);
            invoke = method.invoke(t);
            invoke = PublicMethod.processingTimeType(clazz.getDeclaredField(property).getType(), config, invoke);
        } catch (Exception e) {
            throw new NoticeException("类获取方法或属性或属性值错误-->类名是: " + clazz.getSimpleName() + " 方法是: " + method.getName() + " 属性名是: " + name, e);
        }
        ClassCache.get().add(t.getClass(), property);
        return invoke;
    }


}
