package top.hihuzi.collection.pick.factory;


import top.hihuzi.collection.pick.config.PickConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * tips 集合提取器 方法工厂
 *
 * @author: hihuzi  2018/6/27 6:50
 */
public interface PickMethodFactory extends PickFactory {


    /**
     * tips 从对象集合中取特定字段
     *
     * @parameter: List<E> list
     * @parameter: String[] parameter
     * @return: List<Map>
     * @author: hihuzi 2018/7/12 8:03
     */
    <E> List<Map> pick(List<E> list, String... parameter) throws Exception;

    /**
     * tips 从对象集合中取特定字段(带控制返回值)
     *
     * @parameter: List<E> list
     * @parameter: PickConfig config
     * @parameter: String[] parameter
     * @return: List<Map>
     * @author: hihuzi 2018/7/12 8:03
     */
    <E> List<Map> pick(List<E> list, PickConfig config, String... parameter) throws Exception;

    /**
     * tips 从对象集合中取特定字段的value(带控制返回值)(去重)
     *
     * @parameter: List<E> list
     * @parameter: String[] parameter
     * @return: Set<String>
     * @author: hihuzi 2018/4/30 15:49
     */
    <E> Set pickValue(List<E> list, String... parameter) throws Exception;

    /**
     * tips 从对象集合中取特定字段的value(带控制返回值)(去重)
     *
     * @parameter: List<E> list
     * @parameter: PickConfig enmu
     * @parameter: String[] parameter
     * @return: Set<String>
     * @author: hihuzi 2018/4/30 15:49
     */
    <E> Set pickValue(List<E> list, PickConfig config, String... parameter) throws Exception;

    /**
     * tips 单个对象取出特定字段
     *
     * @parameter: E e
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/4/30 15:49
     */
    <E> Map pickValue(E e, String... key) throws Exception;

    /**
     * tips 单个对象 返回选定字段(带控制返回)
     *
     * @parameter: E e
     * @parameter: PickConfig config
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/4/30 15:49
     */
    <E> Map pickValue(E e, PickConfig config, String... key) throws Exception;

    /**
     * tips 从集合中取出特定key
     *
     * @parameter: Map map
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/8/3 17:09
     */
    Map pickMap(Map map, String... key)throws Exception;

    /**
     * tips 从集合中取出特定Key(带返回控制)
     *
     * @parameter: Map map
     * @parameter: PickConfig config
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/8/3 17:09
     */
    Map pickMap(Map map, PickConfig config, String... key)throws Exception;

    /**
     * tips 从集合中取出特定key
     *
     * @parameter: Map map
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/8/3 17:09
     */
    List<Map> pickList(List<Map> list, String... key) throws Exception;

    /**
     * tips 从集合中取出特定Key(带返回控制)
     *
     * @parameter: Map map
     * @parameter: PickConfig config
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/8/3 17:09
     */
    List<Map> pickList(List<Map> list, PickConfig config, String... key) throws Exception;

}
