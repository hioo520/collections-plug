package top.hihuzi.collection.fill.factory;

import top.hihuzi.collection.fill.config.FillConfig;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * tips 填充工具方法工厂
 *
 * @author: hihuzi 2019/2/14 14:26
 */
public interface FillMethodFactory extends FillFactory {

    /**
     * tips ServletRequest-->MAP
     * 默认 方法一 保存空值
     *
     * @parameter: ServletRequest request
     * @return: Map
     * @author: hihuzi 2018/6/14 14:51
     */
    Map fill(ServletRequest request);

    /**
     * tips ServletRequest-->MAP
     * 方法二 保存空值 并且舍弃str特定字段
     *
     * @parameter: ServletRequest request
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/7/23 15:05
     */
    Map fill(ServletRequest request, String... key);

    /**
     * tips ServletRequest-->MAP
     * 方法三 是否舍弃空值 并且舍弃str特定字段(默认舍弃空值)
     *
     * @parameter: ServletRequest
     * @parameter: FillConfig
     * @return: Map
     * @author: hihuzi 2018/7/23 15:05
     */
    Map fill(ServletRequest request, FillConfig config);

    /**
     * tips ServletRequest-->MAP
     * 方法四 是否舍弃空值 并且舍弃str特定字段(默认保存空值)
     *
     * @parameter: ServletRequest request
     * @parameter: FillConfig config
     * @parameter: String[] key
     * @return: Map
     * @author: hihuzi 2018/7/23 15:05
     */

    Map fill(ServletRequest request, FillConfig config, String... key);

    /**
     * tips ServletRequest--> obj
     *
     * @parameter: ServletRequest request
     * @parameter: E e
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(ServletRequest request, E e);

    /**
     * tips ServletRequest--> obj
     *
     * @parameter: ServletRequest request
     * @parameter: E e
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(ServletRequest request, E e, FillConfig config);

    /**
     * tips 对MAP数据装填--> 对象
     *
     * @parameter: Map map
     * @parameter: E e
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(Map map, E e);

    /**
     * tips 对MAP数据装填--> 对象
     *
     * @parameter: Map map
     * @parameter: E e
     * @return: E
     * @author: hihuzi 2018/6/14 14:50
     */
    <E> E fillEntity(Map map, E e, FillConfig config);

    /**
     * tips E --> Map  针对E的属性属性值填充到map
     *
     * @parameter: E e
     * @parameter: map map
     * @return: map
     * @author: hihuzi 2018/6/26 14:51
     */
    <E> Map fillMap(E e, Map map);

    /**
     * tips E --> Map  针对E的属性属性值填充到map
     *
     * @notice: 属性值为空的舍弃
     * @parameter: E e
     * @parameter: map map
     * @return: map
     * @author: hihuzi 2018/6/26 14:51
     */
    <E> Map fillMap(E e, Map map, FillConfig config);

    /**
     * tips list<String> --> E --> list<E> 针对数据库与实体类名有区别
     *
     * @parameter: List<String> list
     * @parameter: E e
     * @return: List<E>
     * @author: hihuzi 2018/6/26 14:51
     */

    <E> List<E> listToEntity(List<String> list, E e);

    /**
     * tips E --> Map  针对E的属性属性值填充到map
     *
     * @notice: 属性值为空的舍弃
     * @parameter: E e
     * @parameter: map map
     * @return: map
     * @author: hihuzi 2018/6/26 14:51
     */
    <E> List<E> listToEntity(List<String> list, E e, FillConfig config);
    /**
     * tips E --> Map  针对数据库查询*数据  数据是驼峰的自动填充工具
     *
     * @notice: 属性值为空的舍弃
     * @parameter: E e
     * @parameter: map map
     * @return: map
     * @author: hihuzi 2018/6/26 14:51
     */

    /**
     * tips 数据库的元组转对象
     *
     * @notice:返回风格"Map<String, List<E>>"
     * @notice: 对象属性和表 遵循驼峰或者下划线命名
     * @author: hihuzi 2019/2/11 9:53
     */
    <E> Object listToClass(List<Map> list, E... e);

    /**
     * tips 数据库的元组转对象
     *
     * @notice:返回风格"Map<String, List<E>>"
     * @notice: 对象属性和表 遵循驼峰或者下划线命名
     * @author: hihuzi 2019/2/11 9:53
     */
    <E> Object listToClass(List<Map> list, FillConfig config, E... e);

}
