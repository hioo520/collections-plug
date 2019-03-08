package top.hihuzi.collection.fill;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> 测试对象
 *
 * @author hihuzi 2018/7/23 9:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PigBean {

    private String stringMax;

    private int intMin;

    private String age;

    private String hight;

    private Character character;

    /**
     * Instantiates a new Test bean.
     */
    public PigBean() {

    }


}