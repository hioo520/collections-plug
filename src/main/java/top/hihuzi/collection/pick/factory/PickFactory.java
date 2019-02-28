package top.hihuzi.collection.pick.factory;


import top.hihuzi.collection.pick.core.PickService;

/**
 * tips 集合工具提取器 工厂
 *
 * @author: hihuzi  2018/6/27 6:50
 */
public interface PickFactory {

    /**
     * tips 工具
     *
     * @parameter:
     * @return: PickMethodFactory
     * @author: hihuzi 2018/9/23 22:59
     */
    static PickMethodFactory batch() {

        return new PickService();
    }

}