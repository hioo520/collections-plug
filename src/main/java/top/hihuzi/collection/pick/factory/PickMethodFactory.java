package top.hihuzi.collection.pick.factory;


import top.hihuzi.collection.pick.config.PickConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p> 集合提取器 方法工厂
 *
 * @author hihuzi 2018/6/27 6:50
 */
public interface PickMethodFactory extends PickFactory {


    /**
     * <p> 从对象集合中取特定字段
     *
     * @param <E>       e
     * @param list      list
     * @param parameter parameter
     * @return List list
     * @author hihuzi 2018/7/12 8:03
     */
    <E> List<Map> pick(List<E> list, String... parameter);

    /**
     * <p> 从对象集合中取特定字段(带控制返回值)
     *
     * @param <E>       e
     * @param list      list
     * @param config    config
     * @param parameter parameter
     * @return List list
     * @author hihuzi 2018/7/12 8:03
     */
    <E> List<Map> pick(List<E> list, PickConfig config, String... parameter);

    /**
     * <p> 从对象集合中取特定字段的value(带控制返回值)(去重)
     *
     * @param <E>       e
     * @param list      list
     * @param parameter parameter
     * @return Set set
     * @author hihuzi 2018/4/30 15:49
     */
    <E> Set pickValue(List<E> list, String... parameter);

    /**
     * <p> 从对象集合中取特定字段的value(带控制返回值)(去重)
     *
     * @param <E>       e
     * @param list      list
     * @param config    config
     * @param parameter parameter
     * @return Set set
     * @author hihuzi 2018/4/30 15:49
     */
    <E> Set pickValue(List<E> list, PickConfig config, String... parameter);

    /**
     * <p> 单个对象取出特定字段
     *
     * @param <E> e
     * @param e   e
     * @param key key
     * @return Map map
     * @author hihuzi 2018/4/30 15:49
     */
    <E> Map pickValue(E e, String... key);

    /**
     * <p> 单个对象 返回选定字段(带控制返回)
     *
     * @param <E>    e
     * @param e      e
     * @param config config
     * @param key    key
     * @return Map map
     * @author hihuzi 2018/4/30 15:49
     */
    <E> Map pickValue(E e, PickConfig config, String... key);

    /**
     * <p> 从集合中取出特定key
     *
     * @param map       map
     * @param parameter parameter
     * @return Map map
     * @author hihuzi 2018/8/3 17:09
     */
    Map pickMap(Map map, String... parameter);

    /**
     * <p> 从集合中取出特定Key(带返回控制)
     *
     * @param map       map
     * @param config    config
     * @param parameter parameter
     * @return Map map
     * @author hihuzi 2018/8/3 17:09
     */
    Map pickMap(Map map, PickConfig config, String... parameter);

    /**
     * <p> 从集合中取出特定key
     *
     * @param list list
     * @param key  key
     * @return List list
     * @author hihuzi 2018/8/3 17:09
     */
    List<Map> pickList(List<Map> list, String... key);

    /**
     * <p> 从集合中取出特定Key(带返回控制)
     *
     * @param list   list
     * @param config config
     * @param key    key
     * @return Map list
     * @author hihuzi 2018/8/3 17:09
     */
    List<Map> pickList(List<Map> list, PickConfig config, String... key);

}
