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
@EqualsAndHashCode(callSuper = false)
public class TestBeanBean extends SuperClass {

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

    public TestBeanBean() {

    }

    public boolean getBooleanMin() {

        return booleanMin;
    }

    @Override
    public String toString() {

        return "TestBeanBean{" +
                "booleanMax=" + booleanMax +
                ", byteMax=" + byteMax +
                ", shortMax=" + shortMax +
                ", integerMax=" + integerMax +
                ", longMax=" + longMax +
                ", floatMax=" + floatMax +
                ", doubleMax=" + doubleMax +
                ", stringMax='" + stringMax + '\'' +
                ", bigdecimalMax=" + bigdecimalMax +
                ", dateMax=" + dateMax +
                ", booleanMin=" + booleanMin +
                ", charMin=" + charMin +
                ", byteMin=" + byteMin +
                ", shortMin=" + shortMin +
                ", intMin=" + intMin +
                ", longMin=" + longMin +
                ", floatMin=" + floatMin +
                ", doubleMin=" + doubleMin +
                ", character=" + character +
                '}' + super.toString();
    }

}