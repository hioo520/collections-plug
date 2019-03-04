package top.hihuzi.collection.config;

/**
 * <p> Collection 配置工具
 *
 * @author hihuzi 2019/2/14 9:47
 */
public interface Config extends ConfigEnum {

    /**
     * Gets date style enum.
     *
     * @return the date style enum
     */
    DateStyleEnum getDateStyleEnum();

    /**
     * Gets return enum.
     *
     * @return the return enum
     */
    ReturnEnum getReturnEnum();

    /**
     * Gets return style enum.
     *
     * @return the return style enum
     */
    ReturnStyleEnum getReturnStyleEnum();

    /**
     * Gets save style enum.
     *
     * @return the save style enum
     */
    SaveStyleEnum getSaveStyleEnum();

    /**
     * Gets return name enum.
     *
     * @return the return name enum
     */
    ReturnNameEnum getReturnNameEnum();

}
