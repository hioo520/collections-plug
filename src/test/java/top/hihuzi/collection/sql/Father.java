package top.hihuzi.collection.sql;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The type Father.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Father {

    private String naMe;

    private Integer aGe;

    private String sEx;

    private BigDecimal hiGht;

    private Date birthday;

}
