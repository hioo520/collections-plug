package top.hihuzi.collection.sql.factory;

import top.hihuzi.collection.sql.core.SqlService;

/**
 * <p> sql+ 增强工具(带缓存) 自动填充对象
 *
 * <p> 获取对象(对象工厂)
 *
 * @author hihuzi 2019/2/14 8:59
 */
public interface SqlFactory {


    /**
     * <p> sql+ 获取对象
     *
     * @return SQLMethodFactory
     * @author hihuzi 2018/9/23 22:59
     */
    static SqlMethodFactory batch() {

        return new SqlService();
    }


}
