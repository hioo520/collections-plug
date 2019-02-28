package top.hihuzi.collection.config;

import top.hihuzi.collection.sql.config.SQLBean;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * tips 规则菜单
 *
 * @author: hihuzi 2019/2/14 9:51
 */
public interface ConfigEnum {

    /**
     * tips: 返回值(value)规则
     *
     * @author: hihuzi 2018/9/29 14:54
     **/
    enum SaveStyleEnum {
        /**
         * 所有都保存
         */
        DEFAULT(true),

        /**
         * tips: 对 (null or "")不处理 都保存
         *
         * @notice: NULL
         * @author: hihuzi 2018/9/29 17:21
         **/
        REMOVE_NULL_EMPTY(false);

        private Boolean isHaving;

        SaveStyleEnum(Boolean having) {

            this.isHaving = having;
        }

        /**
         * tips: 判断是否存在Null empty "" "   "
         *
         * @author: hihuzi 2018/9/30 8:52
         **/
        public Boolean getHaving() {

            return isHaving;
        }
    }

    /**
     * tips: 时间规则定制
     *
     * @notice:默认 yyyy-MM-dd
     * @author: hihuzi 2018/9/29 14:54
     **/
    enum DateStyleEnum {
        /**
         * tips  默认时间风格规则
         */
        DEFAULT;

        private String value;

        /**
         * tips 多线程并发时启用
         */
        public static ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>();

        public SimpleDateFormat getFormartStyle() {

            return dateFormat.get();
        }

        public DateStyleEnum setFormartStyle(String formartStyle) {

            SimpleDateFormat format = dateFormat.get();
            if (null == format || !formartStyle.equals(format.toPattern())) {
                dateFormat.set(new SimpleDateFormat(formartStyle));
            }
            return this;
        }

    }

    /**
     * tips: 排序规则定制
     *
     * @author: hihuzi 2018/9/29 14:54
     **/
    enum SortStyleEnum {
        /**
         * 默认排序规则
         */
        DEFAULT;

        private Integer[] sort;

        public Integer[] getSort() {

            return sort;
        }

        public SortStyleEnum setSort(Integer[] sort) {

            this.sort = sort;
            return this;
        }

    }


    /**
     * tips: 返回数据泛型类型
     *
     * @notice: 通用枚举
     * @author: hihuzi 2018/9/29 14:54
     **/
    enum ReturnEnum {
        /**
         * 默认和LIST返回一致
         */
        DEFAULT,
        /**
         * 返回"list<map>"
         */
        LISR,
        /**
         * 返回"map<e.getClass().getSimpleName(),list<E>>"
         */
        MAP,
        /**
         * 返回 根据传入的list进行填充
         */
        FILL_LIST,

        /**
         * 返回 根据传入的list进行填充成单个对象
         */
        FILL_CLASS;


        private List[] list;

        /**
         * @author: hihuzi 2018/9/30 8:52
         **/
        public List[] getList() {

            return list;
        }

        public ReturnEnum setList(List... list) {

            this.list = list;
            return this;
        }
    }

    /**
     * tips: 返回类型枚举
     *
     * @author: hihuzi 2018/9/29 14:54
     **/
    enum ReturnStyleEnum {
        /**
         * value=1: 返回类型:List<Map>
         *
         * @author: hihuzi
         */
        DEFAULT,
        /**
         * value=0: 返回类型:List<Map>
         *
         * @author: hihuzi
         */
        LIST_MAP,
        /**
         * value=2: 返回类型:Map
         *
         * @author: hihuzi
         */
        MAP,
        /**
         * value=3: 返回类型:Set
         *
         * @author: hihuzi
         */
        SET;

    }


    /**
     * tips: 自定义返回Key
     *
     * @notice: 通用枚举
     * @author: hihuzi 2018/9/29 14:54
     **/
    enum ReturnNameEnum {
        /**
         * RenameKey="0":默认 属性名输出
         * <p>
         * RenameKey="XXXX":义可以的自定头缀
         *
         * @author: hihuzi
         */
        DEFAULT(null),
        /**
         * RenameKey="1":首字母大写
         *
         * @author: hihuzi
         */
        INITIAL_CAPITAL(null),
        /**
         * RenameKey="2":全小写
         *
         * @author: hihuzi
         */
        LOWER_CASE(null),
        /**
         * RenameKey="3":全大写
         *
         * @author: hihuzi
         */
        UPPER_CASE(null),
        /**
         * RenameKey="XXXX":义可以的自定头缀
         *
         * @author: hihuzi
         */
        CUSTOM_SUFFIX(null);

        private String key;

        ReturnNameEnum(String key) {

            this.key = key;
        }

        public String getKey() {

            return key;
        }

        public ReturnNameEnum setKey(String key) {

            this.key = key;
            return this;
        }
    }

    /**
     * tips SQL+ 规则配置
     *
     * @author: hihuzi 2019/2/15 10:04
     */
    enum SQLEeum {
        /**
         * tips 规则定制
         *
         * @author: hihuzi 2019/2/16 12:21
         */
        DEFAULT;

        private SQLBean bean;

        public SQLEeum set(SQLBean bean) {

            this.bean = bean;
            return this;
        }

        public SQLBean get() {

            return bean;
        }
    }


}
