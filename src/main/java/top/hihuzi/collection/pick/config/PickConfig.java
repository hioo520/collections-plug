package top.hihuzi.collection.pick.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

/**
 * <p>:构造器
 *
 * <p>
 *
 * @author hihuzi 2018/9/29 16:43
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
     * <p>: 使用这个构造器所有值都是调用默认值
     *
     * <p> this.returnNameEnum = ReturnNameEnum.DEFAULT;
     * <p> this.saveStyleEnum = SaveStyleEnum.DEFAULT;
     * <p> this.returnNameEnum = ReturnNameEnum.DEFAULT;
     * <p> this.dateStyleEnum = DateStyleEnum.DEFAULT.setFormartStyle(DATE_FORMART);
     * @param <E> e
     * @param e e
     * @author hihuzi 2018/9/30 8:59
     */
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

    @Override
    public ReturnNameEnum getReturnNameEnum() {

        return null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
    }

    public PickConfig setReturnNameEnum(ReturnNameEnum returnNameEnum) {

        this.returnNameEnum = null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
        return this;
    }

    @Override
    public ReturnStyleEnum getReturnStyleEnum() {

        return null != returnStyleEnum ? returnStyleEnum : ReturnStyleEnum.DEFAULT;
    }

    public PickConfig setReturnStyleEnum(ReturnStyleEnum returnStyleEnum) {

        this.returnStyleEnum = null != returnStyleEnum ? returnStyleEnum : ReturnStyleEnum.DEFAULT;
        return this;
    }

    @Override
    public SaveStyleEnum getSaveStyleEnum() {

        return saveStyleEnum != null ? saveStyleEnum : SaveStyleEnum.DEFAULT;
    }

    public PickConfig setSaveStyleEnum(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
        return this;
    }

    @Override
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
