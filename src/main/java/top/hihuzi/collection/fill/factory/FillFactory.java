package top.hihuzi.collection.fill.factory;


import top.hihuzi.collection.fill.core.FillService;

/**
 * <p> 填充工具工厂
 *
 * @author hihuzi 2018/9/23 16:03
 */
public interface FillFactory {

    /**
     * <p> 获取填充工具工厂
     *
     * @return FillMethodFactory fill method factory
     * @author hihuzi 2018/9/23 22:59
     */
    static FillMethodFactory batch() {

        return new FillService();
    }

}