package top.hihuzi.collection.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class Son extends Mother {

    private String naMe;

    private Integer aGe;

    private String sEx;

    private BigDecimal hiGht;

    @Override
    public String toString() {

        return "Son{" +
                "name='" + naMe + '\'' +
                ", age=" + aGe +
                ", sex='" + sEx + '\'' +
                ", hight=" + hiGht +
                '}'+super.toString();
    }

}
