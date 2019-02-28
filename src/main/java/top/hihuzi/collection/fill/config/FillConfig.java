package top.hihuzi.collection.fill.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

import java.util.List;

/**
 * tips: 填充工具 配置工具
 *
 * @notices: 默认时间类型是 yyyy-MM-dd
 * @author: hihuzi 2018/9/30 10:08
 **/
public class FillConfig implements Config {


    private SaveStyleEnum saveStyleEnum;


    private SortStyleEnum sortStyleEnum;


    private DateStyleEnum dateStyleEnum;


    private ReturnEnum returnEnum;

    public FillConfig(DateStyleEnum dateStyleEnum, ReturnEnum returnEnum) {

        this.dateStyleEnum = dateStyleEnum;
        this.returnEnum = returnEnum;

    }

    public FillConfig(ReturnEnum returnEnum, DateStyleEnum dateStyleEnum) {

        this.returnEnum = returnEnum;
        this.dateStyleEnum = dateStyleEnum;

    }


    /**
     * 重置枚举对象
     * 对枚举静态变量一定要初始化
     */
    public static void reset() {

        SortStyleEnum.DEFAULT.setSort(new Integer[]{});
        DateStyleEnum.DEFAULT.dateFormat.remove();
        ReturnEnum.DEFAULT.setList(new List[]{});
    }

    /**
     * tips: 默认
     *
     * @notice: saveStyleEnum=SaveStyleEnum.DEFAULT
     * @author: hihuzi 2018/9/30 10:59
     **/
    public FillConfig() {

    }

    public FillConfig(SaveStyleEnum saveStyleEnum, SortStyleEnum sortStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        this.sortStyleEnum = sortStyleEnum;
    }

    public FillConfig(SaveStyleEnum saveStyleEnum, SortStyleEnum sortStyleEnum, DateStyleEnum dateStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        this.sortStyleEnum = sortStyleEnum;
        this.dateStyleEnum = dateStyleEnum;
    }

    public FillConfig(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
    }

    public FillConfig(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

    public FillConfig(ReturnEnum returnEnum) {

        this.returnEnum = returnEnum;
    }

    public SaveStyleEnum getSaveStyleEnum() {

        return null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
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

    public DateStyleEnum getDateStyleEnum() {

        return null != dateStyleEnum ? dateStyleEnum : DateStyleEnum.DEFAULT.setFormartStyle(Constants.DATE_FORMART);
    }

    public void setDateStyleEnum(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

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
