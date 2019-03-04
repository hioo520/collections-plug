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
 * <p> 带缓存机制
 *
 * @author hihuzi 2018/7/19 17:54
 */
abstract class AbstractPick implements PickMethodFactory {

    /**
     * <p> 同一对象集合 返回选定字段 集合返回
     *
     * @param <T>    List
     * @param list   List
     * @param config String[]
     * @param key    PickEnum
     * @return List collection
     * @author hihuzi 2018/7/12 8:03
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
                        PublicMethod.achieveMap(map0, String.valueOf(obj), map.get(obj), config);
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
                PublicMethod.achieveMap(map, property, invoke, config);
                break;
            case MAP:
                PublicMethod.achieveMap(maps, property, invoke, config);
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
     * <p> 添加缓存
     *
     * @param <T> e
     * @param
     * @return
     * @author hihuzi  2018/10/18 11:43
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
