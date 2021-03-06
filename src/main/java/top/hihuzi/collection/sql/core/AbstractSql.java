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
import top.hihuzi.collection.utils.Constants;
import top.hihuzi.collection.utils.StrUtils;

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
     * @param e      the obj
     * @return the object
     * @author hihuzi 2019/2/11 9:53
     */
    <E> Object listToEntityDefault(List<Map> list, SqlConfig config, Object... e) {

        if (0 == e.length) {
            e = (E[]) config.getSqlEeum().get().getClazz().toArray();
        } else {
            List<Object> obj = Arrays.asList(e);
            Object[] es = new Object[obj.size()];
            for (int i = 0; i < obj.size(); i++) {
                es[i] = PublicMethod.getClazz(obj.get(i));
            }
            e = es;
        }
        List<Map> lm = null;
        Object newClazz = null;
        Map<String, List<E>> m = null;
        Map<String, ParameterCache> tableNameMatchParameter = PublicMethod.tableNameMatchParameter(config, e);
        switch (config.getReturnEnum()) {
            case DEFAULT:
            case LISR:
                lm = new ArrayList<>(list.size());
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
                config.remove();
                return lm;
            default:
                m = new HashMap<>(e.length);
                break;
        }
        String sqlKey = config.getSqlEeum().get().key();
        for (Object es : e) {
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
                    ParameterCache pCache = ClassCache.getPCache(sqlKey + clazz.getSimpleName(), names);
                    if (null != pCache) {
                        Map<String, TypeCache> ptCache = pCache.getCache();
                        TypeCache cache = ptCache.get(names);
                        ValueHandleCache.invokeValue(newClazz, cache.getMethodSet(), values, null, config, cache.getType());
                    }
                }
                List<E> lis = m.get(clazz.getSimpleName());
                if (null != lis) {
                    lis.add((E) newClazz);
                } else {
                    List<E> li = new ArrayList<>(list.size());
                    li.add((E) newClazz);
                    m.put(clazz.getSimpleName(), li);
                }
            }
        }
        switch (config.getReturnEnum()) {
            case MAP_CLASS:
                config.remove();
                return m;
            case FILL_LIST:
                int i = 0;
                try {
                    for (Object es : e) {
                        Class<?> clazz = (Class<?>) es;
                        config.getReturnEnum().getList()[i].addAll(m.get(clazz.getSimpleName()));
                        i++;
                    }
                } catch (Exception ex) {
                    throw new NoticeException("配置顺序有误", ex);
                }
                config.remove();
                return true;
            case FILL_CLASS:
                config.remove();
                return m.get(((Class) e[0]).getSimpleName());
            default:
                throw new NoticeException("未定义输出类型");
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

        String caches = SqlCache.getCache(config.key());
        StringBuffer sql = null;
        if (null != caches) {
            sql = new StringBuffer(caches.length());
            sql.append(caches);
        }
        List<Class<?>> clazz0 = config.getClazz();
        Map nickname = config.getNickname();
        List<String> display = config.getDisplay();
        List<String> repeat = config.getRepeat();
        Map<String, Map<String, String>> deployDisplayNickMap = config.getDeployDisplayNickMap();
        Map<String, String> displayNickname = config.getDisplayNickname();
        Map<String, String> specialHump = config.getSpecialHump();
        sql = new StringBuffer(Constants.SQL_INIT);
        int j = 0;
        for (Class clazz : clazz0) {
            Map<String, String> stringStringMap = deployDisplayNickMap!=null?deployDisplayNickMap.get(clazz.getSimpleName()):null;
            Map humpToLineMap = PublicMethod.getHumpToLine(clazz,stringStringMap,specialHump);
            Iterator iterator = humpToLineMap.entrySet().iterator();
            int i = 0, times = 0, size = humpToLineMap.size();
            if (null != display && 0 != display.size()) {
                times = PublicMethod.achieveTimes(clazz, display);
            }
            while (iterator.hasNext()) {
                Map.Entry humpToLine = (Map.Entry) iterator.next();
                String param = String.valueOf(humpToLine.getKey());
                String table = String.valueOf(humpToLine.getValue());
                String mark = String.valueOf(nickname.get(clazz.getName()));
                if (null == display) {
                    if (null != nickname && !"".equals(mark.trim())) {
                        sql.append(mark + Constants.POINT);
                    }
                    sql.append(table);
                    if (repeat != null && repeat.contains(param)) {
                        sql.append(Constants.BLANK_SPACE + mark + table);
                        ClassCache.get().add((Class<?>) clazz, param, null, mark + table, config.key());
                    } else {
                        ClassCache.get().add((Class<?>) clazz, param, null, table, config.key());
                    }
                    if (i < size - 1) {
                        sql.append(Constants.COMMA);
                    }
                } else if (display.contains(param)) {
                    if (null != displayNickname && !displayNickname.containsKey(mark + table) && null != repeat && repeat.contains(param)) {
                        times--;
                    } else {
                        if (!"".equals(mark.trim())) {
                            sql.append(mark + Constants.POINT);
                        }
                        sql.append(table);
                        if ((repeat != null && repeat.contains(param)||(specialHump!=null&&specialHump.containsValue(table)))) {
                            sql.append(Constants.BLANK_SPACE + mark + table);
                            ClassCache.get().add((Class<?>) clazz, param, null, mark + table, config.key());
                        } else {
                            ClassCache.get().add((Class<?>) clazz, param, null, table, config.key());
                        }
                        if (i < size - 1 && 0 < times - 1) {
                            sql.append(Constants.COMMA);
                            times--;
                        }
                    }
                }
                i++;
            }
            if (null == display) {
                if (j < clazz0.size() - 1) {
                    sql.append(Constants.COMMA);
                }
            } else {
                if (j < clazz0.size() - 1 && 0 != times) {
                    sql.append(Constants.COMMA);
                }
            }

            j++;

        }
        String sqls = String.valueOf(sql);
        if (sqls.contains(Constants.DOUB_COMMA)) {
            sqls = sqls.replaceAll(Constants.MORE_THEN_COMMA, Constants.COMMA);
        }
        sqls = StrUtils.deleteComma(sqls);
        SqlCache.addCache(config.key(), sqls);
        return sqls;
    }

}
