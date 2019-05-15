package top.hihuzi.collection.config;

/**
 * tips 主要用于防止多线程配置数据冲突
 *
 * @author: hihuzi 2019/5/14 9:27
 */
public abstract class ConfigRemove {

    /**
     * Remove.
     */
    public void remove() {

        MarkCache.MarkCacheEnum.remove();
        ConfigEnum.SqlEeum.remove();
        ConfigEnum.ReturnNameEnum.remove();
        ConfigEnum.ReturnEnum.remove();
        ConfigEnum.SortStyleEnum.remove();
        ConfigEnum.DateStyleEnum.remove();
    }

}
