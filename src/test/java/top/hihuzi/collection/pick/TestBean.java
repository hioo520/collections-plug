package top.hihuzi.collection.pick;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * tips 测试bean
 *
 * @author: hihuzi  2018/6/27 6:39
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class TestBean extends Base {

    private String id;

    private String name;

    private String email;

    private String address;
    private Date date = new Date();
}