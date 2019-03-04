package top.hihuzi.collection.sql.config;


import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.config.CacheBean;
import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.utils.Constants;
import top.hihuzi.collection.utils.Md5;
import top.hihuzi.collection.utils.StrUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * <p> sql+ 规则定制
 *
 * @author hihuzi 2019/2/15 17:13
 */
public class SqlBean implements CacheBean {


    /**
     * <p> 唯一性 用于此条SQL 缓存(不设置存在数据紊乱(情况:出现同样检索只是SQL不同时缓存的SQL容易被覆盖))
     *
     * @author hihuzi 2019/2/15 10:13
     */
    private String unique;

    /**
     * <p> 待查询的class对象
     *
     * @author hihuzi 2019/2/15 10:13
     */
    private List<Class<?>> clazz;

    /**
     * <p> 名称的表昵称 提取表的昵称作为 属性的前缀
     *
     * @data: <类名的全称 ,类名的标识mark>
     * @author hihuzi 2019/2/15 10:15
     */
    private Map<String, String> nickname;

    /**
     * @data: 类名的标识mark
     * notice 暂存(做nickname数据处理)
     **/

    private List<String> nick;

    /**
     * <p> 存在重复属性(也就是重复的列名)
     *
     * @author hihuzi 2019/2/15 10:15
     */
    private List<String> repeat;

    /**
     * <p> 需要检索出来的属性(也就是待显示的列名)
     *
     * <p> 没有配置时 是查找全部
     *
     * @author hihuzi 2019/2/15 10:15
     */
    private List<String> display;

    /**
     * <p> 需要检索出来的属性(自定义)
     *
     * <p> 没有配置时 是查找全部
     *
     * @author hihuzi 2019/2/15 10:15
     */
    private List<String> displayDiy;

    /**
     * notice 暂存(做displayNickname数据处理)
     *
     * @data: <类名的简称 ,< 属性名 , 昵称名>>
     **/
    private Map<String, Map<String, String>> displayParamAndNickname;

    /**
     * notice 待处理数据
     *
     * @data: <类名标志+属性名 , 昵称名>
     **/
    private Map<String, String> displayNickname;

    /**
     * <p> 名称的表昵称 (暂存区)
     *
     * @param unique   unique
     * @param clazz    clazz
     * @param nickname nickname
     * @param repeat   repeat
     * @param display  display
     * @author hihuzi 2019/2/15 10:15
     */
    public SqlBean(String unique, List<Class<?>> clazz, Map nickname, List<String> repeat, List<String> display) {

        this.unique = unique;
        this.clazz = clazz;
        this.nickname = nickname;
        this.repeat = repeat;
        this.display = display;
    }

    public SqlBean() {


    }

    public SqlBean addUnique(Object unique) {

        this.unique = String.valueOf(unique);
        return this;
    }

    public List<Class<?>> getClazz() {

        return clazz;
    }

    public <E> SqlBean addClazz(E... e) {

        List<Class<?>> list = new ArrayList<>();
        for (E es : e) {
            list.add(es.getClass());
        }
        this.clazz = list;
        return this;
    }

    public Map getNickname() {

        return nickname;
    }

    public <E> SqlBean addNickname(E... e) {

        nick = (List<String>) Arrays.asList(e);
        return this;
    }

    public List<String> getRepeat() {

        return repeat;
    }

    public <E> SqlBean addRepeat(E... e) {

        this.repeat = (List<String>) Arrays.asList(e);
        return this;
    }

    public List<String> getDisplay() {

        return display;
    }

    public Map<String, String> getDisplayNickname() {

        return displayNickname;
    }

    public Map<String, Map<String, String>> getDisplayParamAndNickname() {

        return displayParamAndNickname;
    }

    public <E> SqlBean addDisplay(E... e) {

        List<String> displayTemp;
        try {
            displayTemp = (List<String>) Arrays.asList(e);
            this.display = new ArrayList<>(15);
        } catch (Exception ex) {
            throw new NoticeException("待展示数据必须是String类型");
        }
        for (String disp : displayTemp) {
            String dis = disp.trim();
            if (!(dis.contains(".") || dis.contains(" "))) {
                this.display.add(dis);
                /**notice 下面处理自定义昵称1.没有昵称是也就是没有 eg.1.Class.param nickname 2.class.param**/
            } else if (dis.contains(".") && !dis.contains(" ")) {
                String[] clazztableName = dis.split(Constants.POINT_FORMAT);
                this.display.add(clazztableName[1]);
                deployDisplayNickMap(clazztableName[0], clazztableName[1], clazztableName[1]);
            } else if (dis.contains(".") && dis.contains(" ")) {
                String[] clazzTableName = dis.split(Constants.POINT_FORMAT);
                String[] tableName = clazzTableName[1].split(Constants.BLANK);
                this.display.add(tableName[0]);
                deployDisplayNickMap(clazzTableName[0], tableName[0], tableName[1]);
            } else {
                throw new NoticeException("未定义的规则!请重新个配置display");
            }
        }
        return this;
    }

    /**
     * <p> 配置待显示属性
     *
     * @param className className
     * @param param     param
     * @param nickName  nickName
     * @author hihuzi 2019/3/3 9:02
     */
    private void deployDisplayNickMap(String className, String param, String nickName) {


        if (null == this.displayParamAndNickname || 0 == this.displayParamAndNickname.size()) {
            this.displayParamAndNickname = new HashMap<>(10);
            this.displayParamAndNickname.put(className, StrUtils.map(param, nickName));
        } else {
            Map<String, String> paramNickNameMap = this.displayParamAndNickname.get(className);
            if (null == paramNickNameMap) {
                this.displayParamAndNickname.put(className, StrUtils.map(param, nickName));
            } else {
                paramNickNameMap.put(param, nickName);
                this.displayParamAndNickname.put(className, paramNickNameMap);
            }
        }
    }

    public SqlBean build() {

        CacheBean oCache = ClassCache.getOCache(key());
        if (null != oCache) {
            return (SqlBean) oCache;
        }
        Map<String, String> map = null;
        if (1 == this.clazz.size()) {
            map = new HashMap<>(1);
            map.put(this.clazz.get(0).getName(), nick != null ? nick.get(0) : "");
            this.nickname = map;
            return this;
        }
        if (null == this.clazz || this.clazz.size() == 0) {
            throw new NoticeException("addClass不可为空");
        }
        Set<String> repeatTemp = new HashSet<>(this.clazz.size());
        Set<String> repeats = new HashSet<>(this.clazz.size());
        map = new HashMap<>(this.clazz.size());
        for (int i = 0; i < this.clazz.size(); i++) {
            String mark = (nick != null && i <= nick.size() - 1) ? nick.get(i) : "";
            /**notice 处理没有传重复的字段**/
            map.put(this.clazz.get(i).getName(), mark);
            achieveClassFields(repeatTemp, repeats, this.clazz.get(i));
            this.repeat = new ArrayList<>(repeats);
            /**notice 处理自定义昵称1.没有昵称是也就是没有 eg.1.Class.param nickname 2.class.param**/
            Map<String, String> paramNickname = displayParamAndNickname == null ? null : displayParamAndNickname.get(this.clazz.get(i).getSimpleName());
            if (null != paramNickname) {
                addMark(paramNickname, mark);
            }
        }
        this.nickname = map;
        ClassCache.get().add(key(), this);
        return this;
    }

    /**
     * <p> 处理数据成 --MAP<class的匿名+属性名称,属性的昵称>
     *
     * @param paramNickname
     * @param mark
     * @author hihuzi 2019/3/3 9:46
     */
    private void addMark(Map<String, String> paramNickname, String mark) {

        if (null == this.displayNickname || 0 == displayNickname.size()) {
            displayNickname = new HashMap<>(this.clazz.size() * 5);
        }
        for (String param : paramNickname.keySet()) {
            displayNickname.put(mark + StrUtils.humpToLine(param), paramNickname.get(param));
        }
    }

    /**
     * <p> 获取并添加属性到Set
     *
     * @param repeatTemp
     * @param repeats
     * @param clazz
     * @author hihuzi 2019/2/28 14:58
     */
    private void achieveClassFields(Set<String> repeatTemp, Set<String> repeats, Class<?> clazz) {


        this.repeat = new ArrayList<>(this.clazz.size() * 5);
        for (; Object.class != clazz; clazz = clazz.getSuperclass()) {
            for (Field field : clazz.getDeclaredFields()) {
                boolean state = repeatTemp.add(field.getName());
                if (!state) {
                    repeats.add(field.getName());
                }
            }
        }
    }


    public String key() {

        if (this.unique != null) {
            return this.unique.toString();
        }
        String s = String.valueOf(this.clazz) + String.valueOf(this.display) + String.valueOf(this.nick) + String.valueOf(this.repeat) + String.valueOf(this.displayNickname);
        return Md5.stringToMd5(s);

    }


}