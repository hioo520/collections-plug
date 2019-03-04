package top.hihuzi.collection.sql.factory;

import top.hihuzi.collection.sql.config.SqlBean;
import top.hihuzi.collection.sql.config.SqlConfig;

import java.util.List;
import java.util.Map;

/**
 * <p> sql+ 增强工具(带缓存) 自动填充对象
 *
 * @author hihuzi 2019/2/14 8:59
 */
public interface SqlMethodFactory extends SqlFactory {

    /**
     * <p> sql+ 增强工具(带缓存) 自动填充对象
     *
     * <p> 返回值  List(Map) "Map(String,List(E) list(E)
     * @return Object
     * @param <E>    e
     * @param list   e
     * @param config e
     * @author hihuzi 2019/2/14 9:08
     */
    public <E> Object listToEntity(List<Map> list, SqlConfig config);

    /**
     * <p> sql+ 增强工具(带缓存) 自动填充对象
     *
     * <p> 返回值  List(Map) "Map(String,List(E) list(E)
     * @return Object
     * @param <E>  e
     * @param e    e
     * @param list e
     * @author hihuzi 2019/2/14 9:08
     */
    public <E> Object listToEntity(List<Map> list, E... e);

    /**
     * <p> sql+ 增强工具(带缓存) 自动填充对象
     *
     * <p> 返回值  List(Map) "Map(String,List(E) list(E)
     *
     * @param list   list
     * @param config config
     * @param e      e
     * @param <E>    E
     * @return Object
     * @author hihuzi 2019/2/14 9:08
     */
    public <E> Object listToEntity(List<Map> list, SqlConfig config, E... e);

    /**
     * <p> sql+ 增强工具(带缓存) 自动填充对象
     *
     * <p> 返回值  List(Map) "Map(String,List(E) list(E)
     * @return Object
     * @param <E>    e
     * @param config config
     * @author hihuzi 2019/2/14 9:08
     */
    public <E> Object getSQL(SqlBean config);

}
