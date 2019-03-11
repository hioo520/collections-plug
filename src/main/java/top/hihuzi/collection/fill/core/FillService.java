package top.hihuzi.collection.fill.core;

import top.hihuzi.collection.fill.config.FillConfig;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 填充工具
 *
 * @author hihuzi 2018/9/23 16:24
 */
public class FillService extends AbstractFill {


    /**
     * <p> ServletRequest--MAP    保存空值
     *
     * @param request ServletRequest
     * @return Map
     * @author hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request) {

        return fillDefault(request, new FillConfig());
    }

    /**
     * <p> ServletRequest--MAP   str 去掉没用的字段
     *
     * @param request ServletRequest
     * @param str     String[]
     * @return Map
     * @author hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request, String... str) {

        return fillDefault(request, new FillConfig(), str);
    }

    /**
     * <p> ServletRequest--MAP   是否舍弃空值  默认舍弃空字符
     *
     * @param request ServletRequest
     * @param config  FillConfig
     * @return Map
     * @author hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request, FillConfig config) {

        return fillDefault(request, config);
    }

    /**
     * <p> ServletRequest--MAP    是否舍弃空值 并且舍弃str特定字段
     *
     * @param request ServletRequest
     * @param config  FillConfig
     * @param key     String[]
     * @return Map
     * @author hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request, FillConfig config, String... key) {

        return fillDefault(request, config, key);
    }

    /**
     * <p> ServletRequest-- obj
     *
     * @param <E>     e
     * @param obj     obj
     * @param request ServletRequest
     * @return E
     * @author hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(ServletRequest request, Object obj) {

        return requestFillEntityDefault(request, obj, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * <p> ServletRequest-- obj
     *
     * @param e       E
     * @param <E>     e
     * @param request ServletRequest
     * @return E
     * @author hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(ServletRequest request, Object e, FillConfig config) {

        return requestFillEntityDefault(request, e, config);
    }

    /**
     * <p> 对MAP数据装填-- 对象
     *
     * @param map map
     * @param e   E
     * @return E
     * @author hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(Map map, Object e) {

        if (null == map || 0 == map.size()) {
            return null;
        }
        return mapFillEntity(map, e, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * <p> 对MAP数据装填-- 对象
     *
     * @param map Map
     * @param e   E
     * @return E
     * @author hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(Map map, Object e, FillConfig config) {

        if (null == map || 0 == map.size()) {
            return null;
        }
        return mapFillEntity(map, e, config);
    }

    /**
     * <p> E -- Map  针对E的属性属性值填充到map
     *
     * @param e   E
     * @param map map
     * @return map
     * @author hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> Map fillMap(E e, Map map) {

        if (null == e) {
            return null;
        }
        return fillMapDefault(e, map, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * <p> E -- Map  针对E的属性属性值填充到map
     *
     * @param e   E
     * @param map map
     * @return map
     * @author hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> Map fillMap(E e, Map map, FillConfig config) {

        if (null == e) {
            return null;
        }
        return fillMapDefault(e, map, config);
    }

    /**
     * <p>  对LIST数据装填-- 对象 (针对数据库)与实体类名有区别 value --t
     *
     * @param list List(String)
     * @param obj  E
     * @return List
     * @author hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> List<E> listToEntity(List<String> list, Object obj) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return listToEntityDefault(list, obj, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * <p>  对LIST数据装填-- 对象 (针对数据库)与实体类名有区别 value --t
     *
     * @param list List(String)
     * @param obj  E
     * @return List
     * @author hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> List<E> listToEntity(List<String> list, Object obj, FillConfig config) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return listToEntityDefault(list, obj, config);
    }

    /**
     * <p> 数据库的元组转对象(多对象时保证字段没有重复)
     *
     * <p> 处理对象生成的map(标准类对象字段) 否则需要设置key值
     *
     * @param list List(String)
     * @param obj  e
     * @return Object
     * @author hihuzi 2019/2/11 9:53
     */
    @Override
    public <E> Object listToClass(List<Map> list, Object... obj) {

        if (null == list || 0 == list.size() || null == obj || 0 == obj.length) {
            return null;
        }
        return listToClassDefault(list, new FillConfig(), obj);
    }


    /**
     * <p> 数据库的元组转对象(多对象时保证字段没有重复)
     *
     * <p> 处理对象生成的map(标准类对象字段) 否则需要设置key值
     *
     * @param list   List(String)
     * @param obj    e
     * @param config FillConfig
     * @return Object
     * @author hihuzi 2019/2/11 9:57
     */
    @Override
    public <E> Object listToClass(List<Map> list, FillConfig config, Object... obj) {

        if (null == list || 0 == list.size() || null == obj || 0 == obj.length) {
            return null;
        }
        return listToClassDefault(list, config, obj);

    }

    /**
     * <p> 相同对象进行填充
     *
     * @param <E>  es
     * @param list list
     * @param obj  e
     * @return Object <p>返回风格"Map(String, List(E))" <p> 对象属性和表 遵循驼峰或者下划线命名
     * @author hihuzi 2019/2/11 9:53
     */
    @Override
    public <E,T> List<E> fillClass(List<T> list, Object obj, String... param) {

        if (null == list || 0 == list.size() || null == obj) {
            return null;
        }
        return fillClassDefault(list, new FillConfig(), obj, param);
    }

    /**
     * <p> 相同对象进行填充
     *
     * @param <E>  es
     * @param list list
     * @param obj  e
     * @return Object <p>返回风格"Map(String, List(E))" <p> 对象属性和表 遵循驼峰或者下划线命名
     * @author hihuzi 2019/2/11 9:53
     */
    @Override
    public <E,T> List<E> fillClass(List<T> list, Object obj, FillConfig config, String... param) {

        if (null == list || 0 == list.size() || null == obj) {
            return null;
        }
        return fillClassDefault(list, config, obj, param);
    }

    /**
     * <p> 相同对象进行填充
     *
     * @param <E> es
     * @param e   e
     * @param obj e
     * @return Object <p>返回风格"Map(String, List(E))" <p> 对象属性和表 遵循驼峰或者下划线命名
     * @author hihuzi 2019/2/11 9:53
     */
    @Override
    public <E,T> E fillClass(T e, Object obj, String... param) {

        if (null == e || null == obj) {
            return null;
        }
        return (E) fillClassDefault(new ArrayList() {{
            add(e);
        }}, new FillConfig(), obj, param).get(0);
    }

    /**
     * <p> 相同对象进行填充
     *
     * @param <E> es
     * @param e   e
     * @param obj e
     * @return Object <p>返回风格"Map(String, List(E))" <p> 对象属性和表 遵循驼峰或者下划线命名
     * @author hihuzi 2019/2/11 9:53
     */
    @Override
    public <E,T> E fillClass(T e, Object obj, FillConfig config, String... param) {

        if (null == e || null == obj) {
            return null;
        }
        return (E) fillClassDefault(new ArrayList() {{
            add(e);
        }}, config, obj, param).get(0);
    }

}
