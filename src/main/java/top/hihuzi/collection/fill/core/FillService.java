package top.hihuzi.collection.fill.core;

import top.hihuzi.collection.fill.config.FillConfig;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * tips 填充工具
 *
 * @author: hihuzi 2018/9/23 16:24
 */
public class FillService extends FillServiceImpl {


    /**
     * tips ServletRequest-->MAP    保存空值
     *
     * @parameter: ServletRequest request
     * @return: Map
     * @author: hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request) {

        return fillDefault(request, new FillConfig());
    }

    /**
     * tips ServletRequest-->MAP   str 去掉没用的字段
     *
     * @parameter: ServletRequest
     * @parameter: String[]
     * @return: Map
     * @author: hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request, String... str) {

        return fillDefault(request, new FillConfig(), str);
    }

    /**
     * tips ServletRequest-->MAP   是否舍弃空值  默认舍弃空字符
     *
     * @parameter: ServletRequest request
     * @parameter: FillConfig config
     * @return: Map
     * @author: hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request, FillConfig config) {

        return fillDefault(request, config);
    }

    /**
     * tips ServletRequest-->MAP    是否舍弃空值 并且舍弃str特定字段
     *
     * @parameter: ServletRequest request
     * @parameter: FillConfig config
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/6/14 14:51
     */
    @Override
    public Map fill(ServletRequest request, FillConfig config, String... key) {

        return fillDefault(request, config, key);
    }

    /**
     * tips ServletRequest--> obj
     *
     * @parameter: E
     * @parameter: ServletRequest
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(ServletRequest request, E e) {

        return requestFillEntityDefault(request, e, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * tips ServletRequest--> obj
     *
     * @parameter: E
     * @parameter: ServletRequest
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(ServletRequest request, E e, FillConfig config) {

        return requestFillEntityDefault(request, e, config);
    }

    /**
     * tips tips 对MAP数据装填--> 对象
     *
     * @parameter: map
     * @parameter: E
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(Map map, E e) {

        if (null == map || 0 == map.size()) return null;
        return mapFillEntity(map, e, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * tips tips 对MAP数据装填--> 对象
     *
     * @parameter: map
     * @parameter: E
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    @Override
    public <E> E fillEntity(Map map, E e, FillConfig config) {

        if (null == map || 0 == map.size()) return null;
        return mapFillEntity(map, e, config);
    }

    /**
     * tips E --> Map  针对E的属性属性值填充到map
     *
     * @parameter: E e
     * @parameter: map map
     * @return: map
     * @author: hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> Map fillMap(E e, Map map) {

        if (null == e) return null;
        return fillMapDefault(e, map, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * tips E --> Map  针对E的属性属性值填充到map
     *
     * @parameter: E e
     * @parameter: map map
     * @return: map
     * @author: hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> Map fillMap(E e, Map map, FillConfig config) {

        if (null == e) return null;
        return fillMapDefault(e, map, config);
    }

    /**
     * tips tips 对LIST数据装填--> 对象 (针对数据库)与实体类名有区别 value -->t
     *
     * @parameter: List<String>
     * @parameter: E
     * @return: List<E>
     * @author: hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> List<E> listToEntity(List<String> list, E e) {

        if (null == list || 0 == list.size()) return null;
        return listToEntityDefault(list, e, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
    }

    /**
     * tips tips 对LIST数据装填--> 对象 (针对数据库)与实体类名有区别 value -->t
     *
     * @parameter: List<String>
     * @parameter: E
     * @return: List<E>
     * @author: hihuzi 2018/6/26 14:51
     */
    @Override
    public <E> List<E> listToEntity(List<String> list, E e, FillConfig config) {

        if (null == list || 0 == list.size()) return null;
        return listToEntityDefault(list, e, config);
    }

    /**
     * tips 数据库的元组转对象(多对象时保证字段没有重复)
     *
     * @notice: 对象属性和表 遵循驼峰或者下划线命名
     * @author: hihuzi 2019/2/11 9:53
     */
    @Override
    public <E> Object listToClass(List<Map> list, E... e) {

        if (null == list || 0 == list.size() || null == e || 0 == e.length) return null;
        return listToClassDefault(list, new FillConfig(), e);
    }


    /**
     * tips 数据库的元组转对象(多对象时保证字段没有重复)
     *
     * @notice: 对象属性和表 遵循驼峰或者下划线命名
     * @author: hihuzi 2019/2/11 9:57
     */
    @Override
    public <E> Object listToClass(List<Map> list, FillConfig config, E... e) {

        if (null == list || 0 == list.size() || null == e || 0 == e.length) return null;
        return listToClassDefault(list, config, e);

    }

}
