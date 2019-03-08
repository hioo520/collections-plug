package top.hihuzi.collection.config;

/**
 * <p> 缓存标志位
 *
 * @author hihuzi 2019/2/14 9:51
 */
public interface ReturnValue {

    /**
     * <p>: 缓存标志位
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum ReturnValueEnum {
        /**
         * 所有都保存
         */
        DEFAULT(false),
        /**
         * null 转 空字符串
         */
        NULL_TO_CHAR(true);

        private Boolean isChang;

        ReturnValueEnum(Boolean isChang) {

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