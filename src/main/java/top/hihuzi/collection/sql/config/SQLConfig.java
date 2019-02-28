package top.hihuzi.collection.sql.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

import java.util.List;

/**
 * tips: 填充工具控制器
 *
 * @notices: 默认时间类型是 yyyy-MM-dd
 * @author: hihuzi 2018/9/30 10:08
 **/
public class SQLConfig implements Config {


    private SaveStyleEnum saveStyleEnum;

    private ReturnNameEnum returnNameEnum;

    private DateStyleEnum dateStyleEnum;

    private ReturnEnum returnEnum;

    private SQLEeum sqlEeum;

    public SQLConfig(DateStyleEnum dateStyleEnum, ReturnEnum returnEnum) {

        this.dateStyleEnum = dateStyleEnum;
        this.returnEnum = returnEnum;

    }

    public SQLConfig(ReturnEnum returnEnum, DateStyleEnum dateStyleEnum) {

        this.returnEnum = returnEnum;
        this.dateStyleEnum = dateStyleEnum;

    }

    public SQLConfig(SQLEeum sqlEeum, DateStyleEnum dateStyleEnum) {

        this.sqlEeum = sqlEeum;
        this.dateStyleEnum = dateStyleEnum;

    }

    public SQLConfig(ReturnEnum returnEnum, SQLEeum sqlEeum) {

        this.sqlEeum = sqlEeum;
        this.returnEnum = returnEnum;
    }

    public SQLConfig(SQLEeum sqlEeum, ReturnEnum returnEnum) {

        this.sqlEeum = sqlEeum;
        this.returnEnum = returnEnum;
    }

    public SQLConfig(ReturnEnum returnEnum, DateStyleEnum dateStyleEnum, SQLEeum sqlEeum) {
        this.sqlEeum = sqlEeum;
        this.returnEnum = returnEnum;
        this.dateStyleEnum = dateStyleEnum;

    }


    /**
     * 重置枚举对象
     * 对枚举静态变量一定要初始化
     */
    public static void reset() {

        DateStyleEnum.DEFAULT.dateFormat.remove();
        ReturnEnum.DEFAULT.setList(new List[]{});
        SQLEeum.DEFAULT.set(null);
    }

    /**
     * tips: 默认
     *
     * @notice: saveStyleEnum=SaveStyleEnum.DEFAULT
     * @author: hihuzi 2018/9/30 10:59
     **/
    public SQLConfig() {

    }

    public SQLConfig(SaveStyleEnum saveStyleEnum, ReturnNameEnum returnNameEnum) {

        this.saveStyleEnum = saveStyleEnum;
        this.returnNameEnum = returnNameEnum;
    }

    public SQLConfig(SaveStyleEnum saveStyleEnum, ReturnNameEnum returnNameEnum, DateStyleEnum dateStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        this.returnNameEnum = returnNameEnum;
        this.dateStyleEnum = dateStyleEnum;
    }

    public SQLConfig(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
    }

    public SQLConfig(DateStyleEnum dateStyleEnum) {

        this.dateStyleEnum = dateStyleEnum;
    }

    public SQLConfig(ReturnEnum returnEnum) {

        this.returnEnum = returnEnum;
    }

    public SQLConfig(SQLEeum sqlEeum) {

        this.sqlEeum = sqlEeum;
    }

    public SaveStyleEnum getSaveStyleEnum() {

        return null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
    }

    public SQLConfig setSaveStyleEnum(SaveStyleEnum saveStyleEnum) {

        this.saveStyleEnum = saveStyleEnum;
        return this;
    }

    public ReturnNameEnum getReturnNameEnum() {

        return null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
    }

    public void setReturnNameEnum(ReturnNameEnum returnNameEnum) {

        this.returnNameEnum = returnNameEnum;
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

    public SQLEeum getSqlEeum() {

        return sqlEeum;
    }

    public void setSqlEeum(SQLEeum sqlEeum) {

        this.sqlEeum = sqlEeum;
    }

    @Override
    public ReturnStyleEnum getReturnStyleEnum() {

        return null;
    }

}
