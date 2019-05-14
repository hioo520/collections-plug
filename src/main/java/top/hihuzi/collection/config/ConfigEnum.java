package top.hihuzi.collection.config;

import top.hihuzi.collection.sql.config.SqlBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p> 规则菜单
 *
 * @author hihuzi 2019/2/14 9:51
 */
public interface ConfigEnum {

    /**
     * <p>清理线程 防止栈溢出(无论是弱引用 强引用 软引用)Remove.
     */
//    default void remove() {
//
//        MarkCache.MarkCacheEnum.remove();
//        SqlEeum.remove();
//        ReturnNameEnum.remove();
//        ReturnEnum.remove();
//        SortStyleEnum.remove();
//        DateStyleEnum.remove();
//    }

    /**
     * <p>: 返回值(value)规则
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum SaveStyleEnum {
        /**
         * 所有都保存
         */
        DEFAULT(true),

        /**
         * <p>: 对 (null or "")不处理 都保存
         *
         * <p> NULL
         *
         * @author hihuzi 2018/9/29 17:21
         */
        REMOVE_NULL_EMPTY(false);

        private Boolean isHaving;

        SaveStyleEnum(Boolean having) {

            this.isHaving = having;
        }

        /**
         * <p>: 判断是否存在Null empty "" "   "
         *
         * @return Boolean having
         * @author hihuzi 2018/9/30 8:52
         */
        public Boolean getHaving() {

            return isHaving;
        }
    }

    /**
     * <p>: 时间规则定制
     *
     * <p>默认 yyyy-MM-dd
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum DateStyleEnum {
        /**
         * <p>  默认时间风格规则
         */
        DEFAULT;

        /**
         * <p> 多线程并发时启用
         */
        public volatile static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>();

        private String value;

        /**
         * 清理线程 防止栈溢出(无论是弱引用 强引用 软引用)
         *
         * @author hihuzi 2019/3/8 10:40
         */
        public static void remove() {

            dateFormat.remove();
        }

        /**
         * Gets formart style.
         *
         * @return the formart style
         */
        public SimpleDateFormat getFormartStyle() {

            return dateFormat.get();
        }

        /**
         * Sets formart style.
         *
         * @param formartStyle the formart style
         * @return the formart style
         */
        public DateStyleEnum setFormartStyle(String formartStyle) {

            SimpleDateFormat res = dateFormat.get();
            if (null == res || !formartStyle.equals(res.toPattern())) {
                dateFormat.set(new SimpleDateFormat(formartStyle));
            }
            return this;
        }

    }


    /**
     * <p>: 排序规则定制
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum SortStyleEnum {
        /**
         * 默认排序规则
         */
        DEFAULT;

        /**
         * The constant values.
         */
        public volatile static ThreadLocal<Integer[]> values = new ThreadLocal<Integer[]>();

        /**
         * 清理线程 防止栈溢出(无论是弱引用 强引用 软引用)
         *
         * @author hihuzi 2019/3/8 10:40
         */
        public static void remove() {

            values.remove();
        }

        /**
         * Get sort integer [ ].
         *
         * @return the integer [ ]
         */
        public Integer[] getSort() {

            return values.get();
        }

        /**
         * Sets sort.
         *
         * @param sort the sort
         * @return the sort
         */
        public SortStyleEnum setSort(Integer[] sort) {

            Integer[] res = values.get();
            if (null == res || res.length != sort.length) {
                values.set(sort);
            }
            return this;
        }
    }

    /**
     * <p>: 返回数据泛型类型
     *
     * <p> 通用枚举
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum ReturnEnum {
        /**
         * 默认和LIST返回一致
         */
        DEFAULT,
        /**
         * 返回"list(map)"
         */
        LISR,
        /**
         * 返回"map(e.getClass().getSimpleName(),list(E))"
         */
        MAP_CLASS,
        /**
         * 返回 根据传入的list进行填充
         */
        FILL_LIST,

        /**
         * 返回 根据传入的list进行填充成单个对象
         */
        FILL_CLASS;

        /**
         * The constant values.
         */
        public volatile static ThreadLocal<List[]> values = new ThreadLocal<List[]>();

        /**
         * 清理线程 防止栈溢出(无论是弱引用 强引用 软引用)
         *
         * @author hihuzi 2019/3/8 10:40
         */
        public static void remove() {

            values.remove();
        }

        /**
         * Get list list [ ].
         *
         * @return List[] list [ ]
         * @author hihuzi 2018/9/30 8:52
         */
        public List[] getList() {

            return values.get();
        }

        /**
         * Sets list.
         *
         * @param list the list
         * @return the list
         */
        public ReturnEnum setList(List... list) {

            List[] res = values.get();
            if (null == res || res.length != list.length) {
                values.set(list);
            }
            return this;
        }
    }


    /**
     * <p>: 返回类型枚举
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum ReturnStyleEnum {
        /**
         * value=1: 返回类型:List(Map)
         *
         * @author hihuzi
         */
        DEFAULT,
        /**
         * value=0: 返回类型:List(Map)
         *
         * @author hihuzi
         */
        LIST_MAP,
        /**
         * value=2: 返回类型:Map
         *
         * @author hihuzi
         */
        MAP,
        /**
         * value=3: 返回类型:Set
         *
         * @author hihuzi
         */
        SET;

    }

    /**
     * <p>: 自定义返回Key
     *
     * <p> 通用枚举
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum ReturnNameEnum {
        /**
         * RenameKey="0":默认 属性名输出
         * <p>
         * RenameKey="XXXX":义可以的自定头缀
         *
         * @author hihuzi
         */
        DEFAULT,
        /**
         * RenameKey="1":首字母大写
         *
         * @author hihuzi
         */
        INITIAL_CAPITAL,
        /**
         * RenameKey="2":全小写
         *
         * @author hihuzi
         */
        LOWER_CASE,
        /**
         * RenameKey="3":全大写
         *
         * @author hihuzi
         */
        UPPER_CASE,
        /**
         * RenameKey="XXXX":义可以的自定头缀
         *
         * @author hihuzi
         */
        CUSTOM_SUFFIX,
        /**
         * RenameKey=class_param(首字母小写)
         *
         * @author hihuzi
         */
        CLASS_HUMP;

        /**
         * The constant values.
         */
        public volatile static ThreadLocal<String> values = new ThreadLocal<String>();

        private String value;

        /**
         * 清理线程 防止栈溢出(无论是弱引用 强引用 软引用)
         *
         * @author hihuzi 2019/3/8 10:40
         */
        public static void remove() {

            values.remove();
        }

        /**
         * Gets key.
         *
         * @return the key
         */
        public String getKey() {

            return values.get();
        }

        /**
         * Sets key.
         *
         * @param mark the mark
         * @return the key
         */
        public ReturnNameEnum setKey(String mark) {

            String res = values.get();
            if (null == res || !(res.equals(mark))) {
                values.set(mark);
            }
            return this;
        }
    }

    /**
     * <p> SQL+ 规则配置
     *
     * @author hihuzi 2019/2/15 10:04
     */
    enum SqlEeum {
        /**
         * <p> 规则定制
         *
         * @author hihuzi 2019/2/16 12:21
         */
        DEFAULT;

        /**
         * The constant values.
         */
        public volatile static ThreadLocal<SqlBean> values = new ThreadLocal<SqlBean>();

        /**
         * <p>清理线程 防止栈溢出(无论是弱引用 强引用 软引用)
         *
         * @author hihuzi 2019/3/8 10:40
         */
        public static void remove() {

            values.remove();
        }

        /**
         * Set sql eeum.
         *
         * @param sqlBean the sql bean
         * @return the sql eeum
         */
        public SqlEeum set(SqlBean sqlBean) {

            SqlBean res = values.get();
            if (null == res) {
                values.set(sqlBean);
            } else if (res.hashCode() != sqlBean.hashCode()) {
                values.set(sqlBean);
            }
            return this;
        }

        /**
         * Get sql bean.
         *
         * @return the sql bean
         */
        public SqlBean get() {

            return values.get();
        }

    }

}
