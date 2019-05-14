package top.hihuzi.collection.sql.factory;


import top.hihuzi.collection.sql.core.SqlService;

/**
 * tips  envision-web
 *
 * @author: hihuzi  2019/5/10 16:13
 */
public abstract class SqlTool {


    public static SqlMethodFactory batch() {

        return new SqlService();
    }

}
