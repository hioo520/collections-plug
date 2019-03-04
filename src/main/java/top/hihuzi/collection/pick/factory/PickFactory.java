package top.hihuzi.collection.pick.factory;


import top.hihuzi.collection.pick.core.PickService;

/**
 * <p> 集合工具提取器 工厂
 *
 * @author hihuzi 2018/6/27 6:50
 */
public interface PickFactory {

    /**
     * <p> 工具
     *
     * @return PickMethodFactory pick method factory
     * @author hihuzi 2018/9/23 22:59
     */
    static PickMethodFactory batch() {

        return new PickService();
    }

}