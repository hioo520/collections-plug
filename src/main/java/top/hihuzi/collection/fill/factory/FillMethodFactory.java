package top.hihuzi.collection.fill.factory;

import top.hihuzi.collection.fill.config.FillConfig;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p> 填充工具方法工厂
 *
 * @author hihuzi 2019/2/14 14:26
 */
public interface FillMethodFactory extends FillFactory {

    /**
     * <p> ServletRequest--MAP
     * 默认 方法一 保存空值
     *
     * @param request ServletRequest
     * @return Map map
     * @author hihuzi 2018/6/14 14:51
     */
    Map fill(ServletRequest request);

    /**
     * <p> ServletRequest--MAP
     * 方法二 保存空值 并且舍弃str特定字段
     *
     * @param request ServletRequest
     * @param key     String[]
     * @return Map map
     * @author hihuzi 2018/7/23 15:05
     */
    Map fill(ServletRequest request, String... key);

    /**
     * <p> ServletRequest--MAP
     * 方法三 是否舍弃空值 并且舍弃str特定字段(默认舍弃空值)
     *
     * @param request ServletRequest
     * @param config  FillConfig
     * @return Map map
     * @author hihuzi 2018/7/23 15:05
     */
    Map fill(ServletRequest request, FillConfig config);

    /**
     * <p> ServletRequest--MAP
     * 方法四 是否舍弃空值 并且舍弃str特定字段(默认保存空值)
     *
     * @param request ServletRequest
     * @param config  FillConfig
     * @param key     String[]
     * @return Map map
     * @author hihuzi 2018/7/23 15:05
     */
    Map fill(ServletRequest request, FillConfig config, String... key);

    /**
     * <p> ServletRequest-- obj
     *
     * @param <E>     e
     * @param request ServletRequest
     * @param e       E
     * @return E e
     * @author hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(ServletRequest request, E e);

    /**
     * <p> ServletRequest-- obj
     *
     * @param <E>     e
     * @param request ServletRequest
     * @param e       E
     * @param config  E
     * @return E e
     * @author hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(ServletRequest request, E e, FillConfig config);

    /**
     * <p> 对MAP数据装填-- 对象
     *
     * @param <E> e
     * @param map Map
     * @param e   E
     * @return E e
     * @author hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(Map map, E e);

    /**
     * <p> 对MAP数据装填-- 对象
     *
     * @param <E>    e
     * @param map    Map
     * @param e      E
     * @param config E
     * @return E e
     * @author hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(Map map, E e, FillConfig config);

    /**
     * <p> E -- Map  针对E的属性属性值填充到map
     *
     * @param <E> e
     * @param e   E
     * @param map map
     * @return map map
     * @author hihuzi 2018/6/26 14:51
     */
    <E> Map fillMap(E e, Map map);

    /**
     * <p> E -- Map  针对E的属性属性值填充到map
     *
     * <p> 属性值为空的舍弃
     *
     * @param <E>    E
     * @param e      E
     * @param map    map
     * @param config E
     * @return map map
     * @author hihuzi 2018/6/26 14:51
     */
    <E> Map fillMap(E e, Map map, FillConfig config);

    /**
     * <p> list(String) -- E -- list(E) 针对数据库与实体类名有区别
     *
     * @param <E>  e
     * @param list List(String)
     * @param e    E
     * @return List list
     * @author hihuzi 2018/6/26 14:51
     */
    <E> List<E> listToEntity(List<String> list, E e);

    /**
     * <p> E -- Map  针对E的属性属性值填充到map
     *
     * <p> 属性值为空的舍弃
     *
     * @param <E>    e
     * @param list   list
     * @param e      E
     * @param config config
     * @return List list
     * @author hihuzi 2018/6/26 14:51
     */
    <E> List<E> listToEntity(List<String> list, E e, FillConfig config);

    /**
     * <p> 数据库的元组转对象
     *
     * @param <E>  es
     * @param list list
     * @param e    e
     * @return Object <p>返回风格"Map(String, List(E))" <p> 对象属性和表 遵循驼峰或者下划线命名
     * @author hihuzi 2019/2/11 9:53
     */
    <E> Object listToClass(List<Map> list, E... e);

    /**
     * <p> 数据库的元组转对象
     *
     * <p>返回风格"Map(String, List(E))"
     * <p> 对象属性和表 遵循驼峰或者下划线命名
     *
     * @param <E>    es
     * @param list   list
     * @param config config
     * @param e      e
     * @return Object object
     * @author hihuzi 2019/2/11 9:53
     */
    <E> Object listToClass(List<Map> list, FillConfig config, E... e);

}
