package top.hihuzi.collection.config;

/**
 * <p> 缓存标志位
 *
 * @author hihuzi 2019/2/14 9:51
 */
public interface MarkCache {

    /**
     * <p>: 缓存标志位
     *
     * @author hihuzi 2018/9/29 14:54
     */
    enum MarkCacheEnum {
        /**
         * 所有都保存
         */
        DEFAULT(null);

        public volatile static ThreadLocal<String> marks = new ThreadLocal<String>();

        MarkCacheEnum(String mark) {

        }

        /**
         * <p>: 获取标志位
         * <p>: 一个项目中或者一个App中自定义的 key 最好唯一
         *
         * @return the string
         * @author hihuzi 2018/9/30 8:52
         */
        public String get() {

            return marks.get();
        }

        /**
         * 清理线程 防止栈溢出
         *
         * @author hihuzi 2019/3/8 10:40
         */
        public static void remove() {

            marks.remove();
        }

        /**
         * <p>: 配置标志位
         *
         * @param mark the mark
         * @return the mark cache enum
         * @author hihuzi 2019/3/8 9:47
         */
        public MarkCacheEnum set(String mark) {

            String mark0 = marks.get();
            if (null == mark0 || !mark0.equals(mark)) {
                marks.set(mark);
            }
            return this;
        }


    }

}