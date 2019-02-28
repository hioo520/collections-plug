package top.hihuzi.collection.config;

/**
 * tips Collection 配置工具
 *
 * @author: hihuzi 2019/2/14 9:47
 */
public interface Config extends ConfigEnum {

    DateStyleEnum getDateStyleEnum();

    ReturnEnum getReturnEnum();

    ReturnStyleEnum getReturnStyleEnum();

    SaveStyleEnum getSaveStyleEnum();

}
