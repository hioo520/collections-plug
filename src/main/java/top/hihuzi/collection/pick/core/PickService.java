package top.hihuzi.collection.pick.core;

import top.hihuzi.collection.pick.config.PickConfig;
import top.hihuzi.collection.pick.factory.PickMethodFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>  方法的具体实现
 *
 * @author hihuzi 2018/7/19 17:53
 */
public class PickService extends AbstractPick {

    /**
     * <p> 从对象集合中取特定字段
     *
     * @param list      list
     * @param parameter parameter
     * @param <E>       obj
     * @return List
     * @author hihuzi 2018/7/12 8:03
     */
    @Override
    public <E> List<Map> pick(List<E> list, String... parameter) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return (List<Map>) batch(list, new PickConfig(), parameter);
    }

    /**
     * <p> 从对象集合中取特定字段(带控制返回值)
     *
     * @param <E>       obj
     * @param list      list
     * @param config    config
     * @param parameter parameter
     * @return List
     * @author hihuzi 2018/7/12 8:03
     */
    @Override
    public <E> List<Map> pick(List<E> list, PickConfig config, String... parameter) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return (List<Map>) batch(list, config, parameter);
    }

    /**
     * <p> 从对象集合中取特定字段的value(带控制返回值)(去重)
     *
     * @param list      list
     * @param parameter parameter
     * @param <E>       e
     * @return Set
     * @author hihuzi 2018/4/30 15:49
     */
    @Override
    public <E> Set pickValue(List<E> list, String... parameter) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return (Set) batch(list, new PickConfig(
                PickConfig.ReturnStyleEnum.SET,
                PickConfig.ReturnNameEnum.DEFAULT,
                PickConfig.SaveStyleEnum.DEFAULT), parameter);
    }

    /**
     * <p> 从对象集合中取特定字段的value(带控制返回值)(去重)
     *
     * @param <E>       obj
     * @param list      list
     * @param config    config
     * @param parameter parameter
     * @return Set
     * @author hihuzi 2018/4/30 15:49
     */
    @Override
    public <E> Set pickValue(List<E> list, PickConfig config, String... parameter) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return (Set) batch(list,
                config.setReturnStyleEnum(PickConfig.ReturnStyleEnum.SET), parameter);
    }

    /**
     * <p> 单个对象取出特定字段
     *
     * @param <E> obj
     * @param e   e
     * @param key key
     * @return Map
     * @author hihuzi 2018/4/30 15:49
     */
    @Override
    public <E> Map pickValue(E e, String... key) {

        if (null == e) {
            return null;
        }
        List<E> list = new ArrayList<E>(1);
        list.add(e);
        return ((List<Map>) batch(list, new PickConfig(
                PickConfig.ReturnStyleEnum.MAP,
                PickConfig.ReturnNameEnum.DEFAULT,
                PickConfig.SaveStyleEnum.DEFAULT), key)).get(0);
    }

    /**
     * <p> 单个对象 返回选定字段(带控制返回)
     *
     * @param <E>    obj
     * @param e      e
     * @param config config
     * @param key    key
     * @return Map
     * @author hihuzi 2018/4/30 15:49
     */
    @Override
    public <E> Map pickValue(E e, PickConfig config, String... key) {

        if (null == e) {
            return null;
        }
        List<E> list = new ArrayList<E>(1);
        list.add(e);
        return ((List<Map>) (batch(list, config.setReturnStyleEnum(PickConfig.ReturnStyleEnum.MAP), key))).get(0);
    }

    /**
     * <p> 从集合中取出特定key
     *
     * @param map       map
     * @param parameter parameter
     * @return Map
     * @author hihuzi 2018/8/3 17:09
     */
    @Override
    public Map pickMap(final Map map, String... parameter) {

        if (null == map || 0 == map.size()) {
            return null;
        }
        return ((List<Map>) batch(new ArrayList() {{
            add(map);
        }}, new PickConfig(
                PickConfig.ReturnStyleEnum.MAP,
                PickConfig.ReturnNameEnum.DEFAULT,
                PickConfig.SaveStyleEnum.DEFAULT), parameter)).get(0);
    }

    /**
     * <p> 从集合中取出特定Key(带返回控制)
     *
     * @param map       map
     * @param config    config
     * @param parameter parameter
     * @return Map
     * @author hihuzi 2018/8/3 17:09
     */
    @Override
    public Map pickMap(final Map map, PickConfig config, String... parameter) {

        if (null == map || 0 == map.size()) {
            return null;
        }
        return ((List<Map>) batch(new ArrayList() {{
            add(map);
        }}, new PickConfig(
                PickConfig.ReturnStyleEnum.MAP,
                PickConfig.ReturnNameEnum.DEFAULT,
                PickConfig.SaveStyleEnum.DEFAULT), parameter)).get(0);
    }

    /**
     * <p> 从集合中取出特定key
     *
     * @param list list
     * @param key  key
     * @return List
     * @author hihuzi 2018/8/3 17:09
     */
    @Override
    public List<Map> pickList(List<Map> list, String... key) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return (List<Map>) batch(list, new PickConfig(), key);
    }

    /**
     * <p> 从集合中取出特定Key(带返回控制)
     *
     * @param list   list
     * @param config config
     * @param key    key
     * @return Map
     * @author hihuzi 2018/8/3 17:09
     */
    @Override
    public List<Map> pickList(List<Map> list, PickConfig config, String... key) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return (List<Map>) batch(list, config, key);
    }


    @Override
    public PickMethodFactory batch() {

        return new PickService();
    }

}
