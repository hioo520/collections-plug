package top.hihuzi.collection.fill.factory;

import top.hihuzi.collection.fill.core.FillService;

/**
 * tips  collections-plus
 *
 * @author: hihuzi  2019/5/14 10:17
 */
public abstract class FillTool {

    public static FillMethodFactory batch() {

        return new FillService();
    }

}
