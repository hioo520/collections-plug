package top.hihuzi.collection.sql.core;

import top.hihuzi.collection.sql.config.SqlBean;
import top.hihuzi.collection.sql.config.SqlConfig;
import top.hihuzi.collection.sql.factory.SqlMethodFactory;

import java.util.List;
import java.util.Map;

/**
 * <p> sql+增强工具(带缓存)
 *
 * @author hihuzi 2019/2/14 9:02
 */
public class SqlService extends AbstractSql {

    /**
     * <p> 自定义sel 查询条件 自动填充对象
     *
     * @param <E> obj
     *            <p> 返回值  List(Map) Map(String,List(E) list(E)
     * @author hihuzi 2019/2/14 9:08
     */
    @Override
    public <E> Object listToEntity(List<Map> list, SqlConfig config) {

        if (null == list || 0 == list.size()) {
            return null;
        }
        return listToEntityDefault(list, config);
    }

    /**
     * <p> 自定义sel 查询条件 自动填充对象
     *
     * @param <E> obj
     * @param obj obj
     *            <p> 返回值  List(Map) Map(String,List(E) list(E)
     * @author hihuzi 2019/2/14 9:08
     */
    @Override
    public <E> Object listToEntity(List<Map> list, Object... obj) {

        if (null == list || 0 == list.size() || null == obj || 0 == obj.length) {
            return null;
        }
        return listToEntityDefault(list, new SqlConfig(), obj);
    }

    /**
     * <p> 自定义sel 查询条件 自动填充对象
     *
     * @param <E> obj
     *            <p> 返回值  List(Map) Map(String,List(E) list(E)
     * @author hihuzi 2019/2/14 9:08
     */
    @Override
    public <E> Object listToEntity(List<Map> list, SqlConfig config, Object... obj) {

        if (null == list || 0 == list.size() || null == obj || 0 == obj.length) {
            return null;
        }
        return listToEntityDefault(list, config, obj);
    }

    @Override
    public <E> Object getSQL(SqlBean config) {

        return getSQLDefault(config);
    }

    @Override
    public SqlMethodFactory batch() {

        return new SqlService();
    }

}
