package top.hihuzi.collection.fill;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tips 测试对象
 *
 * @author:hihuzi 2018/7/23 9:17
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TestBean {
    private Boolean booleanMax;
    private Byte byteMax;
    private Short shortMax;
    private Integer integerMax;
    private Long longMax;
    private Float floatMax;
    private Double doubleMax;
    private String stringMax;
    private BigDecimal bigdecimalMax;
    private Date dateMax;
    private boolean booleanMin;
    private char charMin;
    private byte byteMin;
    private short shortMin;
    private int intMin;
    private long longMin;
    private float floatMin;
    private double doubleMin;

    private Character character;

    public TestBean() {

    }

    public boolean getBooleanMin() {

        return booleanMin;
    }


}