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

    /**
     * tips: 默认
     *
     * @notice: saveStyleEnum=SaveStyleEnum.DEFAULT
     * @author: hihuzi 2018/9/30 10:59
     **/

    public <E> SQLConfig(E... e) {

        for (E ex : e) {
            if (ex instanceof SaveStyleEnum) {
                this.saveStyleEnum = (SaveStyleEnum) ex;
            } else if (ex instanceof ReturnNameEnum) {
                this.returnNameEnum = (ReturnNameEnum) ex;
            } else if (ex instanceof DateStyleEnum) {
                this.dateStyleEnum = (DateStyleEnum) ex;
            } else if (ex instanceof ReturnEnum) {
                this.returnEnum = (ReturnEnum) ex;
            } else if (ex instanceof SQLEeum) {
                this.sqlEeum = (SQLEeum) ex;
            }
        }
    }

    public SaveStyleEnum getSaveStyleEnum() {

        return null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
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
