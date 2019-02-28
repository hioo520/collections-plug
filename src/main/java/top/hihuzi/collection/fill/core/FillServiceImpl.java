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

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * tips HttpServletRequest-->MAP    是否舍弃空值 并且舍弃str特定字段
 *
 * @author: hihuzi 2018/9/23 16:03
 */
abstract class FillServiceImpl implements FillMethodFactory {

    /**
     * tips 缓存
     *
     * @parameter: HttpServletRequest request
     * @parameter: config config
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/9/24 9:36
     */
    Map fillDefault(HttpServletRequest request, FillConfig config, String... key) {

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
        return map;
    }

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
     * tips HttpServletRequest--> obj
     *
     * @parameter: E
     * @parameter: HttpServletRequest
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    <E> E requestFillEntityDefault(HttpServletRequest request, E e, FillConfig config) throws Exception {

        Enumeration pars = request.getParameterNames();
        Class clazz = e.getClass();
        while (pars.hasMoreElements()) {
            String name = pars.nextElement().toString().trim();
            String value = request.getParameter(name);
            if (StrUtils.isNNoE(value)) {
                Invoke.processResult(e, config, clazz, name, value);
            } else {
                if (config.getSaveStyleEnum().getHaving()) {
                    Invoke.processResult(e, config, clazz, name, value);
                }
            }
        }
        return e;
    }

    /**
     * tips  对MAP数据装填--> 对象
     *
     * @notice: 忽略掉不在对象中的属性
     * @parameter: map
     * @parameter: E
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    <E> E mapFillEntity(Map map, E e, FillConfig config) throws Exception {

        Iterator iterator = map.entrySet().iterator();
        Class clazz = e.getClass();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String name = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            if (StrUtils.isNNoE(value)) {
                Invoke.processResult(e, config, clazz, name, value);
            } else {
                if (null != config && config.getSaveStyleEnum().getHaving()) {
                    Invoke.processResult(e, config, clazz, name, value);
                }
            }
        }
        return e;
    }


    /**
     * tips E --> Map  针对E与map进行填充
     *
     * @author:hihuzi 2018/6/26 14:51
     */
    public <E> Map fillMapDefault(E e, Map map, FillConfig config) throws Exception {

        Map<String, TypeCache> caches = ClassCache.getCache(e.getClass());
        if (null != caches) {
            for (Map.Entry typeCache : caches.entrySet()) {
                TypeCache cache = (TypeCache) typeCache.getValue();
                Method methodGet = cache.getMethodGet();
                methodGet.setAccessible(true);
                Object invoke = methodGet.invoke(e);
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
                Method method = clazz.getMethod(StrUtils.achieveGetFunction(field.getName()));
                Object invoke = method.invoke(e);
                ClassCache.get().add(e.getClass(), field.getName(), type);
                if (config.getSaveStyleEnum().getHaving() && null != invoke) {
                    invoke = ValueHandleCache.processingTimeType(type, config, invoke);
                    map.put(field.getName(), invoke);
                }
            }
        }
        return map;
    }

    /**
     * tips  对LIST数据装填--> 对象 (针对数据库)与实体类名有区别 value -->t
     *
     * @notice: !!!待优化效率!!!
     * @parameter: List<String>
     * @parameter: E
     * @return: List<E>
     * @author: hihuzi 2018/6/26 14:51
     */
    public <E> List<E> listToEntityDefault(List<String> list, E e, FillConfig config) throws Exception {

        List<E> result = new ArrayList<>(list.size());
        List<String> field = new ArrayList<>(e.getClass().getDeclaredFields().length);
        List<String> fieldsMap = new ArrayList<>(field.size());
        Class clazz = e.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field value : fields) {
                field.add(value.getName());
                ClassCache.get().add(e.getClass(), value.getName(),value.getType());
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
        Object obj = e.getClass().getDeclaredConstructor().newInstance();
        obj = mapFillEntity(map, obj, config);
        result.add((E) obj);
        return result;
    }

    /**
     * tips 数据库的元组转对象
     *
     * @notice: 对象属性和表 遵循驼峰或者下划线命名
     * @author: hihuzi 2019/2/11 9:53
     */
    <E> Object listToClassDefault(List<Map> list, FillConfig config, E... e) throws Exception {

        List<Map> lm = new ArrayList<>(list.size());
        Object newClazz ;
        Map<String, List<E>> m ;
        Map<String, ParameterCache> tableNameMatchParameter = PublicMethod.tableNameMatchParameter(list.get(0), e);
        switch (config.getReturnEnum()) {
            case DEFAULT:
            case LISR:
                for (Map map : list) {
                    Map map0 = new HashMap(map.size());
                    for (Object obj : map.entrySet()) {
                        Map.Entry entry = (Map.Entry) obj;
                        String names = String.valueOf(entry.getKey());
                        String values = String.valueOf(entry.getValue());
                        ParameterCache parameterCache = tableNameMatchParameter.get(names);
                        if (null != parameterCache) {
                            TypeCache typeCache = parameterCache.getCache().get(names);
                            map0.put(typeCache.getParamterName(), PublicMethod.processTimeType(typeCache.getParamtertype(), config, values));
                        }
                    }
                    lm.add(map0);
                }
                return lm;
            default:
                m = new HashMap<>(e.length);
                break;
        }
        for (E es : e) {
            Class<?> clazz = es.getClass();
            for (Map map : list) {
                newClazz = clazz.getDeclaredConstructor().newInstance();
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
                List<E> lis = m.get(newClazz.getClass().getSimpleName());
                if (null != lis) {
                    lis.add((E) newClazz);
                } else {
                    List<E> li = new ArrayList<>(list.size());
                    li.add((E) newClazz);
                    m.put(newClazz.getClass().getSimpleName(), li);
                }
            }
        }
        switch (config.getReturnEnum()) {
            case MAP:
                return m;
            case FILL_LIST:
                int i = 0;
                try {
                    for (E es : e) {
                        config.getReturnEnum().getList()[i].addAll(m.get(es.getClass().getSimpleName()));
                        i++;
                    }
                } catch (Exception ex) {
                    throw new NoticeException("从新配置list顺序有误");
                }
                return true;
            case FILL_CLASS:
                return m.get(e[0].getClass().getSimpleName());
            default:
                return null;
        }
    }


}
