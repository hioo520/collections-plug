package top.hihuzi.collection.sql.config;

import top.hihuzi.collection.config.Config;
import top.hihuzi.collection.utils.Constants;

/**
 * <p>: 填充工具控制器
 *
 * <p> 默认时间类型是 yyyy-MM-dd
 *
 * @author hihuzi 2018/9/30 10:08
 */
public class SqlConfig implements Config {


    private SaveStyleEnum saveStyleEnum;

    private ReturnNameEnum returnNameEnum;

    private DateStyleEnum dateStyleEnum;

    private ReturnEnum returnEnum;

    private SqlEeum sqlEeum;

    /**
     * <p>: 默认
     *
     * @param e obj
     * @param <E> obj
     * @author hihuzi 2018/9/30 10:59
     */
    public <E> SqlConfig(E... e) {

        for (E ex : e) {
            if (ex instanceof SaveStyleEnum) {
                this.saveStyleEnum = (SaveStyleEnum) ex;
            } else if (ex instanceof ReturnNameEnum) {
                this.returnNameEnum = (ReturnNameEnum) ex;
            } else if (ex instanceof DateStyleEnum) {
                this.dateStyleEnum = (DateStyleEnum) ex;
            } else if (ex instanceof ReturnEnum) {
                this.returnEnum = (ReturnEnum) ex;
            } else if (ex instanceof SqlEeum) {
                this.sqlEeum = (SqlEeum) ex;
            }
        }
    }

    @Override
    public SaveStyleEnum getSaveStyleEnum() {

        return null != saveStyleEnum ? saveStyleEnum : SaveStyleEnum.DEFAULT;
    }


    @Override
    public ReturnNameEnum getReturnNameEnum() {

        return null != returnNameEnum ? returnNameEnum : ReturnNameEnum.DEFAULT;
    }

    /**
     * Sets return name enum.
     *
     * @param returnNameEnum the return name enum
     */
    public void setReturnNameEnum(ReturnNameEnum returnNameEnum) {

        this.returnNameEnum = returnNameEnum;
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

    /**
     * Gets sql eeum.
     *
     * @return the sql eeum
     */
    public SqlEeum getSqlEeum() {

        return sqlEeum;
    }

    /**
     * Sets sql eeum.
     *
     * @param sqlEeum the sql eeum
     */
    public void setSqlEeum(SqlEeum sqlEeum) {

        this.sqlEeum = sqlEeum;
    }

    @Override
    public ReturnStyleEnum getReturnStyleEnum() {

        return null;
    }

}
