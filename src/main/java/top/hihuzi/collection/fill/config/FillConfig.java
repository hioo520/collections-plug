package top.hihuzi.collection.fill.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

import java.util.List;

/**
 * <p>: 填充工具 配置工具
 *
 * <p> 默认时间类型是 yyyy-MM-dd
 *
 * @author hihuzi 2018/9/30 10:08
 */
public class FillConfig implements Config {


    private SaveStyleEnum saveStyleEnum;


    private SortStyleEnum sortStyleEnum;


    private DateStyleEnum dateStyleEnum;

    private ReturnNameEnum returnNameEnum;


    private ReturnEnum returnEnum;

    /**
     * <p>: 默认
     *
     * <p> saveStyleEnum=SaveStyleEnum.DEFAULT
     *
     * @param e   e
     * @param <E> e
     * @author hihuzi 2018/9/30 10:59
     */
    public <E> FillConfig(E... e) {

        for (E ex : e) {
            if (ex instanceof SaveStyleEnum) {
                this.saveStyleEnum = (SaveStyleEnum) ex;
            } else if (ex instanceof SortStyleEnum) {
                this.sortStyleEnum = (SortStyleEnum) ex;
            } else if (ex instanceof DateStyleEnum) {
                this.dateStyleEnum = (DateStyleEnum) ex;
            } else if (ex instanceof ReturnEnum) {
                this.returnEnum = (ReturnEnum) ex;
            } else if (ex instanceof ReturnNameEnum) {
                this.returnNameEnum = (ReturnNameEnum) ex;
            }
        }
    }

    @Override
    public SaveStyleEnum getSaveStyleEnum() {

        return null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
    }

    /**
     * Sets save style enum.
     *
     * @param saveStyleEnum the save style enum
     * @return the save style enum
     */
    public FillConfig setSaveStyleEnum(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        return this;
    }

    @Override
    public ReturnNameEnum getReturnNameEnum() {

        return null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
    }

    public void setReturnNameEnum(ReturnNameEnum returnNameEnum) {

        this.returnNameEnum = returnNameEnum;
    }

    /**
     * Gets sort style enum.
     *
     * @return the sort style enum
     */
    public SortStyleEnum getSortStyleEnum() {

        return null != sortStyleEnum ? sortStyleEnum : SortStyleEnum.DEFAULT;
    }

    /**
     * Sets sort style enum.
     *
     * @param sortStyleEnum the sort style enum
     */
    public void setSortStyleEnum(SortStyleEnum sortStyleEnum) {

        this.sortStyleEnum = sortStyleEnum;
    }

    @Override
    public DateStyleEnum getDateStyleEnum() {

        return null != dateStyleEnum ? dateStyleEnum : DateStyleEnum.DEFAULT.setFormartStyle(Constants.DATE_FORMART);
    }

    /**
     * Sets date style enum.
     *
     * @param dateStyleEnum the date style enum
     */
    public void setDateStyleEnum(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

    @Override
    public ReturnEnum getReturnEnum() {

        return null != returnEnum ? returnEnum : ReturnEnum.DEFAULT;
    }

    /**
     * Sets return enum.
     *
     * @param returnEnum the return enum
     */
    public void setReturnEnum(ReturnEnum returnEnum) {

        this.returnEnum = returnEnum;
    }

    @Override
    public ReturnStyleEnum getReturnStyleEnum() {

        return null;
    }

}
