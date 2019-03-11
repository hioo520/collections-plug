package top.hihuzi.collection.fill;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * tips 基本entity结构
 *
 * @author: hihuzi 2018/6/15 16:45
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public abstract class RootBean {

    private String id;

    private Date createDate;

    private Date updateDate;

    private Integer deleteFlag;


}
