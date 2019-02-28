package top.hihuzi.collection.pick.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

/**
 * tips:构造器
 *
 * @notices:
 * @author: hihuzi 2018/9/29 16:43
 **/
public class PickConfig implements Config {

    /**
     * 返回名自定义
     */
    private ReturnNameEnum returnNameEnum;

    /**
     * 返回风格
     */
    private ReturnStyleEnum returnStyleEnum;

    /**
     * 对于空值得管理
     */
    private SaveStyleEnum saveStyleEnum;

    /**
     * 对于时间管理器
     */
    private DateStyleEnum dateStyleEnum;


    /**
     * 重置枚举对象
     * 对枚举静态变量一定要初始化
     */
    public static void reset() {

        DateStyleEnum.DEFAULT.dateFormat.remove();
        ReturnNameEnum.CUSTOM_SUFFIX.setKey("");
    }

    /**
     * tips: 使用这个构造器所有值都是调用默认值
     *
     * @notice: this.returnNameEnum = ReturnNameEnum.DEFAULT;
     * @notice: this.saveStyleEnum = SaveStyleEnum.DEFAULT;
     * @notice: this.returnNameEnum = ReturnNameEnum.DEFAULT;
     * @notice: this.dateStyleEnum = DateStyleEnum.DEFAULT.setFormartStyle(DATE_FORMART);
     * @author: hihuzi 2018/9/30 8:59
     **/
    public PickConfig() {
        reset();
    }

    public PickConfig(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
    }

    public PickConfig(ReturnStyleEnum returnStyleEnum, ReturnNameEnum returnNameEnum, SaveStyleEnum saveStyleEnum) {

        this.returnNameEnum = returnNameEnum;
        this.returnStyleEnum = returnStyleEnum;
        this.saveStyleEnum = saveStyleEnum;
    }

    public PickConfig(ReturnNameEnum returnNameEnum) {

        this.returnNameEnum = returnNameEnum;
    }

    public PickConfig(SaveStyleEnum saveStyleEnum, DateStyleEnum dateStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        this.dateStyleEnum = dateStyleEnum;
    }

    public PickConfig(ReturnNameEnum returnNameEnum, SaveStyleEnum saveStyleEnum) {

        this.returnNameEnum = returnNameEnum;
        this.saveStyleEnum = saveStyleEnum;
    }

    public PickConfig(ReturnNameEnum returnNameEnum, SaveStyleEnum saveStyleEnum, DateStyleEnum dateStyleEnum) {

        this.returnNameEnum = returnNameEnum;
        this.saveStyleEnum = saveStyleEnum;
        this.dateStyleEnum = dateStyleEnum;
    }

    public PickConfig(ReturnStyleEnum returnStyleEnum) {

        this.returnStyleEnum = returnStyleEnum;
    }

    public PickConfig(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

    public PickConfig(ReturnNameEnum returnNameEnum, ReturnStyleEnum returnStyleEnum, SaveStyleEnum saveStyleEnum, DateStyleEnum dateStyleEnum) {

        this.returnNameEnum = returnNameEnum;
        this.returnStyleEnum = returnStyleEnum;
        this.saveStyleEnum = saveStyleEnum;
        this.dateStyleEnum = dateStyleEnum;
    }

    public ReturnNameEnum getReturnNameEnum() {

        return null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
    }

    public PickConfig setReturnNameEnum(ReturnNameEnum returnNameEnum) {

        this.returnNameEnum = null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
        return this;
    }

    public ReturnStyleEnum getReturnStyleEnum() {

        return null != returnStyleEnum ? returnStyleEnum : ReturnStyleEnum.DEFAULT;
    }

    public PickConfig setReturnStyleEnum(ReturnStyleEnum returnStyleEnum) {

        this.returnStyleEnum = null != returnStyleEnum ? returnStyleEnum : ReturnStyleEnum.DEFAULT;
        return this;
    }

    public SaveStyleEnum getSaveStyleEnum() {

        return saveStyleEnum != null ? saveStyleEnum : SaveStyleEnum.DEFAULT;
    }

    public PickConfig setSaveStyleEnum(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
        return this;
    }

    public DateStyleEnum getDateStyleEnum() {

        return null != dateStyleEnum ? dateStyleEnum : DateStyleEnum.DEFAULT.setFormartStyle(Constants.DATE_FORMART);
    }

    @Override
    public ReturnEnum getReturnEnum() {

        return null;
    }

    public void setDateStyleEnum(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

}
