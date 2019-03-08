package top.hihuzi.collection.fill.core;

import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.ParameterCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.common.Invoke;
import top.hihuzi.collection.common.PublicMethod;
import top.hihuzi.collection.common.ValueHandleCache;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.fill.config.FillConfig;
import top.hihuzi.collection.fill.factory.FillMethodFactory;
import top.hihuzi.collection.utils.StrUtils;

import javax.servlet.ServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p> ServletRequest--MAP    是否舍弃空值 并且舍弃str特定字段
 *
 * @author hihuzi 2018/9/23 16:03
 */
abstract class AbstractFill implements FillMethodFactory {

    /**
     * <p> 缓存
     *
     * @param request ServletRequest
     * @param config  config
     * @param key     String[]
     * @return Map map
     * @author hihuzi 2018/9/24 9:36
     */
    Map fillDefault(ServletRequest request, FillConfig config, String... key) {

        Map map = new HashMap(request.getParameterMap().size());
        List<String> exclude = null;
        if (StrUtils.isNNoE(key)) {
            exclude = Arrays.asList(key);
        }
        Enumeration pars = request.getParameterNames();
        while (pars.hasMoreElements()) {
            String name = pars.nextElement().toString().trim();
            String value = request.getParameter(name);
            if (StrUtils.isNNoEC(exclude)) {
                if (!exclude.contains(name)) {
                    fillToMap(config, map, name, value);
                }
            } else {
                fillToMap(config, map, name, value);
            }
        }
        config.remove();
        return map;
    }

    /**
     * @param config
     * @param map
     * @param name
     * @param value
     */
    private void fillToMap(FillConfig config, Map map, String name, String value) {

        if (StrUtils.isNNoE(value)) {
            map.put(name, value);
        } else {
            if (config.getSaveStyleEnum().getHaving()) {
                map.put(name, value);
            }
        }
    }

    /**
     * <p> ServletRequest--> obj
     *
     * @param <E>     the type parameter
     * @param request ServletRequest
     * @param object  Object
     * @param config  the config
     * @return E e
     * @author hihuzi 2018/6/14 14:50
     */
    <E> E requestFillEntityDefault(ServletRequest request, Object object, FillConfig config) {

        Enumeration pars = request.getParameterNames();
        Class clazz = PublicMethod.getClazz(object);
        E obj = PublicMethod.getObj(object, clazz);
        while (pars.hasMoreElements()) {
            String name = pars.nextElement().toString().trim();
            String value = request.getParameter(name);
            if (StrUtils.isNNoE(value)) {
                Invoke.processResult(obj, config, name, value);
            } else {
                if (config.getSaveStyleEnum().getHaving()) {
                    Invoke.processResult(obj, config, name, value);
                }
            }
        }
        config.remove();
        return obj;
    }

    /**
     * <p>  对MAP数据装填--> 对象
     *
     * <p> 忽略掉不在对象中的属性
     *
     * @param <E>    the type parameter
     * @param map    map
     * @param object E
     * @param config the config
     * @return E e
     * @author hihuzi 2018/6/14 14:50
     */
    <E> E mapFillEntity(Map map, Object object, FillConfig config) {

        Iterator iterator = map.entrySet().iterator();
        Class clazz = PublicMethod.getClazz(object);
        E obj = PublicMethod.getObj(object, clazz);
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String name = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            if (StrUtils.isNNoE(value)) {
                Invoke.processResult(obj, config, name, value);
            } else {
                if (null != config && config.getSaveStyleEnum().getHaving()) {
                    Invoke.processResult(obj, config, name, value);
                }
            }
        }
        config.remove();
        return obj;
    }


    /**
     * <p> E -- Map  针对E与map进行填充
     *
     * @param <E>    E
     * @param e      e
     * @param map    map
     * @param config config
     * @return Map map
     * @author hihuzi 2018/6/26 14:51
     */
    public <E> Map fillMapDefault(E e, Map map, FillConfig config) {

        Map<String, TypeCache> caches = ClassCache.getCache(e.getClass());
        if (null != caches) {
            for (Map.Entry typeCache : caches.entrySet()) {
                TypeCache cache = (TypeCache) typeCache.getValue();
                Method methodGet = cache.getMethodGet();
                methodGet.setAccessible(true);
                Object invoke = null;
                try {
                    invoke = methodGet.invoke(e);
                } catch (Exception ex) {
                    throw new NoticeException("类获取属性值错误-->类是: " + e.getClass().getSimpleName() + " 方法名是: " + methodGet.getName(), ex);
                }
                if (config.getSaveStyleEnum().getHaving() && null != invoke) {
                    invoke = ValueHandleCache.processingTimeType(cache.getParamtertype(), config, invoke);
                    map.put(typeCache.getKey(), invoke);
                }
            }
        } else {
            Class<?> clazz = e.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                Method method = null;
                Object invoke = null;
                try {
                    method = clazz.getMethod(StrUtils.achieveGetFunction(field.getName()));
                    invoke = method.invoke(e);
                } catch (Exception ex) {
                    throw new NoticeException("类获取方法或者获取方法值错误-->类是: " + e.getClass().getSimpleName() + " 属性是名是: " + StrUtils.achieveGetFunction(field.getName()), ex);
                }
                ClassCache.get().add(e.getClass(), field.getName(), type);
                if (config.getSaveStyleEnum().getHaving() && null != invoke) {
                    invoke = ValueHandleCache.processingTimeType(type, config, invoke);
                    map.put(field.getName(), invoke);
                }
            }
        }
        config.remove();
        return map;
    }

    /**
     * <p>  对LIST数据装填-- 对象 (针对数据库)与实体类名有区别 value --t
     *
     * <p> !!!待优化效率!!!
     *
     * @param <E>    E
     * @param list   list
     * @param object e
     * @param config config
     * @return List list
     * @author hihuzi 2018/6/26 14:51
     */
    public <E> List<E> listToEntityDefault(List<String> list, Object object, FillConfig config) {

        Class clacc = PublicMethod.getClazz(object);
        E e = PublicMethod.getObj(object, clacc);
        List<E> result = new ArrayList<>(list.size());
        List<String> field = new ArrayList<>(clacc.getDeclaredFields().length);
        List<String> fieldsMap = new ArrayList<>(field.size());
        Class clazz = clacc;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field value : fields) {
                field.add(value.getName());
                ClassCache.get().add(clacc, value.getName(), value.getType());
            }
        }
        int i = 0;
        Integer[] sort = config.getSortStyleEnum().getSort();
        if (StrUtils.isNNoEE(config) && StrUtils.isNNoEE(sort) && 0 != sort.length) {
            for (Integer integer : sort) {
                if (integer < field.size() && i <= field.size()) {
                    fieldsMap.add(field.get(integer));
                } else {
                    fieldsMap.add("");
                }
                i++;
            }
        } else {
            fieldsMap = field;
        }

        Map map = new HashMap(list.size());
        i = 0;
        for (String fields : fieldsMap) {
            if (i < list.size()) {
                map.put(fields, list.get(i));
                i++;
            }
        }
        Object obj = null;
        try {
            obj = clacc.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            throw new NoticeException("类创建对象错误-->类名是: " + clacc.getSimpleName(), ex);
        }
        obj = mapFillEntity(map, obj, config);
        result.add((E) obj);
        config.remove();
        return result;
    }

    /**
     * <p> 数据库的元组转对象
     *
     * <p> 对象属性和表 遵循驼峰或者下划线命名
     *
     * @param <E>    e
     * @param list   list
     * @param config config
     * @param object e
     * @return Object object
     * @author hihuzi 2019/2/11 9:53
     */
    <E> Object listToClassDefault(List<Map> list, FillConfig config, Object... object) {

        List<Map> lm = new ArrayList<>(list.size());
        Object newClazz = null;
        Map<String, List<E>> mapClass;
        List<Object> obj0 = Arrays.asList(object);
        Object[] e = new Object[obj0.size()];
        for (int i = 0; i < obj0.size(); i++) {
            e[i] = PublicMethod.getClazz(obj0.get(i));
        }
        /*notice 加入缓存*/
        PublicMethod.tableNameMatchParameter(list.get(0), config, e);
        switch (config.getReturnEnum()) {
            case DEFAULT:
            case LISR:
                for (Map map : list) {
                    Map map0 = new HashMap(map.size());
                    for (Object obj : map.entrySet()) {
                        Map.Entry entry = (Map.Entry) obj;
                        String names = String.valueOf(entry.getKey());
                        String values = String.valueOf(entry.getValue());
                        PublicMethod.achieveMap(map0, names, values, config);
                    }
                    lm.add(map0);
                }
                config.remove();
                return lm;
            default:
                mapClass = new HashMap<>(e.length);
                break;
        }
        for (Object es : e) {
            Class<?> clazz = (Class<?>) es;
            for (Map map : list) {
                try {
                    newClazz = clazz.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    throw new NoticeException("类创建新对象错误-->类名是: " + e.getClass().getSimpleName(), ex);
                }
                for (Object obj : map.entrySet()) {
                    Map.Entry entry = (Map.Entry) obj;
                    String names = String.valueOf(entry.getKey());
                    String values = String.valueOf(entry.getValue());
                    ParameterCache pCache = ClassCache.getPCache(clazz, names);
                    if (null != pCache) {
                        Map<String, TypeCache> ptCache = pCache.getCache();
                        TypeCache cache = ptCache.get(names);
                        ValueHandleCache.invokeValue(newClazz, cache.getMethodSet(), values, null, config, cache.getType());
                    }
                }
                List<E> listClass = mapClass.get(clazz.getSimpleName());
                if (null != listClass) {
                    listClass.add((E) newClazz);
                } else {
                    List<E> listClass0 = new ArrayList<>(list.size());
                    listClass0.add((E) newClazz);
                    mapClass.put(newClazz.getClass().getSimpleName(), listClass0);
                }
            }
        }
        switch (config.getReturnEnum()) {
            case MAP_CLASS:
                config.remove();
                return mapClass;
            case FILL_LIST:
                int i = 0;
                try {
                    for (Object es : e) {
                        Class<?> clazz = (Class<?>) es;
                        config.getReturnEnum().getList()[i].addAll(mapClass.get(clazz.getSimpleName()));
                        i++;
                    }
                } catch (Exception ex) {
                    throw new NoticeException("从新配置list顺序有误", ex);
                }
                config.remove();
                return true;
            case FILL_CLASS:
                config.remove();
                return mapClass.get(((Class) e[0]).getSimpleName());
            default:
                throw new NoticeException("Sqlconfig.ReturnEnum未定义返回类型");
        }
    }

    /**
     * <p> 相同对象进行填充
     *
     * @param <E>    es
     * @param list   list
     * @param config the config
     * @param object e
     * @param param  the param
     * @return List <p>返回风格"Map(String, List(E))" <p> 对象属性和表 遵循驼峰或者下划线命名
     * @author hihuzi 2019/2/11 9:53
     */
    public <E> List<E> fillClassDefault(List<Object> list, FillConfig config, Object object, String... param) {

        List list0 = new ArrayList(list.size());
        Class clazz = PublicMethod.getClazz(list.get(0).getClass());
        Class clacc = PublicMethod.getClazz(object);
        List<String> params = Arrays.asList(param);
        int length = param.length;
        PublicMethod.addCache(clazz, clacc);
        for (Object entity : list) {
            E obj = PublicMethod.getObj(clacc, clacc);
            if (length == 0) {
                for (Field field : clazz.getDeclaredFields()) {
                    classFillClass(config, clazz, clacc, entity, field.getName(), obj);
                }
            } else {
                for (String name : params) {
                    classFillClass(config, clazz, clacc, entity, name, obj);
                }
            }
            list0.add(obj);

        }
        return list0;
    }

    private <E> void classFillClass(FillConfig config, Class clazz, Class clacc, Object entity, String name, E obj) {

        TypeCache typeCache = ClassCache.getCache(clazz, name);
        if (null != typeCache) {
            try {
                Method methodGet = typeCache.getMethodGet();
                methodGet.setAccessible(true);
                Object value = methodGet.invoke(entity);
                value = PublicMethod.changeChar(config, value);
                TypeCache cache = ClassCache.getCache(clacc, name);
                if (null != cache) {
                    ValueHandleCache.invokeValue(obj, cache.getMethodSet(), String.valueOf(value), null, config, cache.getType());
                }
            } catch (Exception e) {
                throw new NoticeException("类获取对象错误!!" + e);
            }
        }
    }
}
