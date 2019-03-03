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
     * tips: 使用这个构造器所有值都是调用默认值
     *
     * @notice: this.returnNameEnum = ReturnNameEnum.DEFAULT;
     * @notice: this.saveStyleEnum = SaveStyleEnum.DEFAULT;
     * @notice: this.returnNameEnum = ReturnNameEnum.DEFAULT;
     * @notice: this.dateStyleEnum = DateStyleEnum.DEFAULT.setFormartStyle(DATE_FORMART);
     * @author: hihuzi 2018/9/30 8:59
     **/
    public <E> PickConfig(E... e) {

        for (E ex : e) {
            if (ex instanceof SaveStyleEnum) {
                this.saveStyleEnum = (SaveStyleEnum) ex;
            } else if (ex instanceof ReturnNameEnum) {
                this.returnNameEnum = (ReturnNameEnum) ex;
            } else if (ex instanceof DateStyleEnum) {
                this.dateStyleEnum = (DateStyleEnum) ex;
            } else if (ex instanceof ReturnStyleEnum) {
                this.returnStyleEnum = (ReturnStyleEnum) ex;
            }
        }
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
