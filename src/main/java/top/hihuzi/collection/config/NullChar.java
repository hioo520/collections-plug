package top.hihuzi.collection.config;

/**
 * <p> 空串处理
 *
 * @author hihuzi 2019/2/14 9:51
 */
public interface NullChar {

    /**
     * <p>: 空字符串
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum NullCharEnum {
        /**
         * 空字符串 所有都保存
         */
        DEFAULT(false),
        /**
         * null 空字符串 去掉
         */
        NULL_TO_CHAR(true);

        private Boolean isChang;

        NullCharEnum(Boolean isChang) {

            this.isChang = isChang;
        }

        /**
         * <p>: 获取标志位
         * <p>: 一个项目中或者一个App中自定义的 key 最好唯一
         *
         * @return the string
         * @author hihuzi 2018/9/30 8:52
         */
        public Boolean get() {

            return isChang;
        }


    }

}