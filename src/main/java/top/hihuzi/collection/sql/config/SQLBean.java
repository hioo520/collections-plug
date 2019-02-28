package top.hihuzi.collection.sql.config;


import top.hihuzi.collection.exception.NoticeException;
import top.hihuzi.collection.utils.MD5;

import java.util.*;

/**
 * tips sql+ 规则定制
 *
 * @author: hihuzi 2019/2/15 17:13
 */
public class SQLBean {


    /**
     * tips 唯一性 用于此条SQL 缓存(不设置存在数据紊乱(情况:出现同样检索只是SQL不同时缓存的SQL容易被覆盖))
     *
     * @author: hihuzi 2019/2/15 10:13
     */
    private String unique;

    /**
     * tips 待查询的class对象
     *
     * @author: hihuzi 2019/2/15 10:13
     */
    private List<Class<?>> clazz;

    /**
     * tips 名称的表昵称 提取表的昵称作为 属性的前缀
     *
     * @author: hihuzi 2019/2/15 10:15
     */
    private Map<String, String> nickname;

    private List<String> nick;

    /**
     * tips 存在重复属性(也就是重复的列名)
     *
     * @author: hihuzi 2019/2/15 10:15
     */
    private List<String> repeat;

    /**
     * tips 需要检索出来的属性(也就是待显示的列名)
     *
     * @notice: 没有配置时 是查找全部
     * @author: hihuzi 2019/2/15 10:15
     */
    private List<String> display;

    /**
     * tips 名称的表昵称 (暂存区)
     *
     * @author: hihuzi 2019/2/15 10:15
     */
    public SQLBean(String unique, List<Class<?>> clazz, Map nickname, List<String> repeat, List<String> display) {

        this.unique = unique;
        this.clazz = clazz;
        this.nickname = nickname;
        this.repeat = repeat;
        this.display = display;
    }

    public SQLBean() {


    }

    public String getUnique() {

        return unique;
    }

    public SQLBean addUnique(Object unique) {

        this.unique = String.valueOf(unique);
        return this;
    }

    public List<Class<?>> getClazz() {

        return clazz;
    }

    public <E> SQLBean addClazz(E... e) {

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

    public <E> SQLBean addNickname(E... e) {

        nick = (List<String>) Arrays.asList(e);
        return this;
    }

    public List<String> getRepeat() {

        return repeat;
    }

    public <E> SQLBean addRepeat(E... e) {

        this.repeat = (List<String>) Arrays.asList(e);
        return this;
    }

    public List<String> getDisplay() {

        return display;
    }

    public <E> SQLBean addDisplay(E... e) {

        this.display = (List<String>) Arrays.asList(e);
        return this;
    }

    public SQLBean build() throws NoticeException {

        Map<String, String> map = null;
        if (1 == this.clazz.size()) {
            map = new HashMap<>(1);
            map.put(this.clazz.get(0).getName(), nick != null ? nick.get(0) : "");
            this.nickname = map;
            return this;
        }
        if (null == this.clazz || this.clazz.size() == 0) {
            throw new NoticeException("缺少带查询表对应的对象 addClass 为空");
        }
        map = new HashMap<>(this.clazz.size());
        if (null == this.nick) {
            for (int i = 0; i < this.clazz.size(); i++) {
                map.put(this.clazz.get(i).getName(), "");
            }
            this.nickname = map;
            return this;
        }
        for (int i = 0; i < this.clazz.size(); i++) {
            map.put(this.clazz.get(i).getName(), i <= nick.size() - 1 ? nick.get(i) : "");
        }

        this.nickname = map;
        return this;
    }

    public String key() {

        if (this.unique != null) {
            return this.unique.toString();
        }
        String s = String.valueOf(this.clazz) + String.valueOf(this.display) + String.valueOf(this.nick) + String.valueOf(this.repeat);
        return MD5.StringToMd5(s);

    }

}