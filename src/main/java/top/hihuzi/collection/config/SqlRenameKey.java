package top.hihuzi.collection.config;

/**
 * <p> sql key 命名风格
 *
 * @author hihuzi 2019/2/14 9:51
 */
public interface SqlRenameKey {


    /**
     * The enum Sql key enum.
     */
    enum SqlRenameKeyEnum {

        /**
         * Default 默认不处理(根据自定义)
         */
        DEFAULT,

        /**
         * Class dot key sql key enum.  类名.属性
         */
        CLASS_DOT_PARAM,
        /**
         * Class hump sql key enum.类名+属性(驼峰)
         */
        CLASS_HUMP;

    }

}