package top.hihuzi.collection.sql.factory;

import top.hihuzi.collection.sql.config.SQLBean;
import top.hihuzi.collection.sql.config.SQLConfig;

import java.util.List;
import java.util.Map;

/**
 * tips sql+ 增强工具(带缓存) 自动填充对象
 *
 * @author: hihuzi 2019/2/14 8:59
 */
public abstract class SQLMethodFactory implements SQLFactory {

    /**
     * tips sql+ 增强工具(带缓存) 自动填充对象
     *
     * @notice: 返回值  "List<Map>" "Map<String,List<E>" "list<E>"
     * @author: hihuzi 2019/2/14 9:08
     */
    public abstract <E> Object listToEntity(List<Map> list, SQLConfig config) throws Exception;

    /**
     * tips sql+ 增强工具(带缓存) 自动填充对象
     *
     * @notice: 返回值  "List<Map>" "Map<String,List<E>" "list<E>"
     * @author: hihuzi 2019/2/14 9:08
     */
    public abstract <E> Object listToEntity(List<Map> list, E... e) throws Exception;

    /**
     * tips sql+ 增强工具(带缓存) 自动填充对象
     *
     * @notice: 返回值  "List<Map>" "Map<String,List<E>" "list<E>"
     * @author: hihuzi 2019/2/14 9:08
     */
    public abstract <E> Object listToEntity(List<Map> list, SQLConfig config, E... e) throws Exception;

    /**
     * tips sql+ 增强工具(带缓存) 自动填充对象
     *
     * @notice: 返回值  "List<Map>" "Map<String,List<E>" "list<E>"
     * @author: hihuzi 2019/2/14 9:08
     */
    public abstract <E> Object getSQL(SQLBean config) throws Exception;

}
