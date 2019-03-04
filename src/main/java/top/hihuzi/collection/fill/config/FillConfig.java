package top.hihuzi.collection.fill.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

import java.util.List;

/**
 * <p>: 填充工具 配置工具
 *
 * <p> 默认时间类型是 yyyy-MM-dd
 * @author hihuzi 2018/9/30 10:08
 **/
public class FillConfig implements Config {


    private SaveStyleEnum saveStyleEnum;


    private SortStyleEnum sortStyleEnum;


    private DateStyleEnum dateStyleEnum;


    private ReturnEnum returnEnum;

    /**
     * <p>: 默认
     *
     * <p> saveStyleEnum=SaveStyleEnum.DEFAULT
     * @param e e
     * @param <E> es
     * @author hihuzi 2018/9/30 10:59
     **/
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
            }
        }
    }

    @Override
    public SaveStyleEnum getSaveStyleEnum() {

        return null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
    }

    @Override
    public ReturnNameEnum getReturnNameEnum() {

        return null;
    }

    public FillConfig setSaveStyleEnum(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        return this;
    }

    public SortStyleEnum getSortStyleEnum() {

        return null != sortStyleEnum ? sortStyleEnum : SortStyleEnum.DEFAULT;
    }

    public void setSortStyleEnum(SortStyleEnum sortStyleEnum) {

        this.sortStyleEnum = sortStyleEnum;
    }

    @Override
    public DateStyleEnum getDateStyleEnum() {

        return null != dateStyleEnum ? dateStyleEnum : DateStyleEnum.DEFAULT.setFormartStyle(Constants.DATE_FORMART);
    }

    public void setDateStyleEnum(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

    @Override
    public ReturnEnum getReturnEnum() {

        return null != returnEnum ? returnEnum : ReturnEnum.DEFAULT;
    }

    public void setReturnEnum(ReturnEnum returnEnum) {

        this.returnEnum = returnEnum;
    }

    @Override
    public ReturnStyleEnum getReturnStyleEnum() {

        return null;
    }

}
