package top.hihuzi.collection.pick.factory;

import top.hihuzi.collection.pick.core.PickService;

/**
 * tips  collections-plus
 *
 * @author: hihuzi  2019/5/14 10:15
 */
public class PickTool {

    public static PickMethodFactory batch() {

        return new PickService();
    }

}
