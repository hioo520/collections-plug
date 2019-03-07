package top.hihuzi.collection.sql.core;

import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.ParameterCache;
import top.hihuzi.collection.cache.SqlCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.common.PublicMethod;
import top.hihuzi.collection.common.ValueHandleCache;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.sql.config.SqlBean;
import top.hihuzi.collection.sql.config.SqlConfig;
import top.hihuzi.collection.sql.factory.SqlMethodFactory;

import java.util.*;

/**
 * <p> sql+增强工具(带缓存)
 *
 * @author hihuzi 2019/2/14 9:02
 */
public abstract class AbstractSql implements SqlMethodFactory {

    /**
     * <p> 数据库的元组转对象
     *
     * @param <E>    obj <p> 对象属性和表 遵循驼峰或者下划线命名
     * @param list   the list
     * @param config the config
     * @param e      the e
     * @return the object
     * @author hihuzi 2019/2/11 9:53
     */
    <E> Object listToEntityDefault(List<Map> list, SqlConfig config, E... e) {

        List<Map> lm = new ArrayList<>(list.size());
        Object newClazz = null;
        Map<String, List<E>> m = null;
        if (0 == e.length) {
            e = (E[]) config.getSqlEeum().get().getClazz().toArray();
        } else {
            Class[] ee = new Class[e.length];
            for (int i = 0; i < e.length; i++) {
                ee[i] = e[i].getClass();
            }
            e = (E[]) ee;
        }
        Map<String, ParameterCache> tableNameMatchParameter = PublicMethod.tableNameMatchParameter(config, e);
        switch (config.getReturnEnum()) {
            case DEFAULT:
            case LISR:
                for (Map map : list) {
                    Map map0 = new HashMap(map.size());
                    for (Object obj : map.entrySet()) {
                        Map.Entry entry = (Map.Entry) obj;
                        String names = String.valueOf(entry.getKey());
                        Object value = entry.getValue();
                        /**notice 查看 getSQLDefault()**/
                        ParameterCache parameterCache = tableNameMatchParameter.get(names);
                        if (null != parameterCache) {
                            TypeCache typeCache = parameterCache.getCache().get(names);
                            PublicMethod.achieveMap(map0, names, value, config, typeCache);
                        }
                    }
                    lm.add(map0);
                }
                return lm;
            default:
                m = new HashMap<>(e.length);
                break;
        }
        String sqlKey = config.getSqlEeum().get().key();
        for (E es : e) {
            Class<?> clazz = (Class<?>) es;
            for (Map map : list) {
                try {
                    newClazz = clazz.getDeclaredConstructor().newInstance();
                } catch (Exception ex) {
                    throw new NoticeException("类创建对象错误-->类名是: " + clazz.getSimpleName(), ex);
                }
                for (Object obj : map.entrySet()) {
                    Map.Entry entry = (Map.Entry) obj;
                    String names = String.valueOf(entry.getKey());
                    String values = String.valueOf(entry.getValue());
                    ParameterCache pCache = ClassCache.getPCache(sqlKey + ((Class) es).getSimpleName(), names);
                    if (null != pCache) {
                        Map<String, TypeCache> ptCache = pCache.getCache();
                        TypeCache cache = ptCache.get(names);
                        ValueHandleCache.invokeValue(newClazz, cache.getMethodSet(), values, null, config, cache.getType());
                    }
                }
                List<E> lis = m.get(newClazz.getClass().getSimpleName());
                if (null != lis) {
                    lis.add((E) newClazz);
                } else {
                    List<E> li = new ArrayList<>(list.size());
                    li.add((E) newClazz);
                    m.put(newClazz.getClass().getSimpleName(), li);
                }
            }
        }
        switch (config.getReturnEnum()) {
            case MAP:
                return m;
            case FILL_LIST:
                int i = 0;
                try {
                    for (E es : e) {
                        config.getReturnEnum().getList()[i].addAll(m.get(((Class) es).getSimpleName()));
                        i++;
                    }
                } catch (Exception ex) {
                    throw new NoticeException("配置顺序有误", ex);
                }
                return true;
            case FILL_CLASS:
                return m.get(((Class) e[0]).getSimpleName());
            default:
                return null;
        }
    }


    /**
     * <p> 生成SQL 自动 添加  对象的 缓存 和 ParameterCache 和 ClassCache
     *
     * <p>
     *
     * @param config the config
     * @return the sql default
     * @author hihuzi 2019/2/18 14:07
     */
    String getSQLDefault(SqlBean config) {

        StringBuffer sql = null;
        String caches = SqlCache.getCache(config.key());
        if (null == caches || "".equals(caches)) {
            sql = new StringBuffer(500);
            int j = 0;
            for (Class clazz : config.getClazz()) {
                Map humpToLineMap = PublicMethod.getHumpToLine(clazz);
                Iterator iterator = humpToLineMap.entrySet().iterator();
                int i = 0, size = humpToLineMap.size();
                int times = 0;
                if (null != config.getDisplay() && 0 != config.getDisplay().size()) {
                    times = PublicMethod.achieveTimes(clazz, config.getDisplay());
                }
                while (iterator.hasNext()) {
                    Map.Entry humpToLine = (Map.Entry) iterator.next();
                    String param = String.valueOf(humpToLine.getKey());
                    String table = String.valueOf(humpToLine.getValue());
                    String mark = String.valueOf(config.getNickname().get(clazz.getName()));
                    if (null == config.getDisplay()) {
                        if (null != config.getNickname() && !"".equals(mark.trim())) {
                            sql.append(mark + ".");
                        }
                        sql.append(table);
                        if (config.getRepeat() != null && config.getRepeat().contains(param)) {
                            sql.append(" " + mark + table);
                            ClassCache.get().add((Class<?>) clazz, param, null, mark + table, config.key());
                        } else {
                            ClassCache.get().add((Class<?>) clazz, param, null, table, config.key());
                        }
                        if (i < size - 1) {
                            sql.append(",");
                        }
                    } else if (config.getDisplay().contains(param)) {
                        if (null != config.getNickname() && !"".equals(mark.trim())) {
                            sql.append(mark + ".");
                        }
                        sql.append(table);
                        if (config.getRepeat() != null && config.getRepeat().contains(param)) {
                            sql.append(" " + mark + table);
                            ClassCache.get().add((Class<?>) clazz, param, null, mark + table, config.key());
                        } else {
                            ClassCache.get().add((Class<?>) clazz, param, null, table, config.key());
                        }
                        if (i < size - 1 && 0 < times - 1) {
                            sql.append(",");
                            times--;
                        }
                    }
                    i++;
                }
                if (1 == size) {
                    break;
                }
                if (j < config.getClazz().size() - 1 && 0 != times) {
                    sql.append(",");
                }
                j++;

            }
        } else {
            sql = new StringBuffer(caches.length());
            sql.append(caches);
        }
        SqlCache.addCache(config.key(), String.valueOf(sql));
        return String.valueOf(sql);
    }

}
