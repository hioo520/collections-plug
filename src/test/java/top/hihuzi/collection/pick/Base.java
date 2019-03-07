package top.hihuzi.collection.pick;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>:
 *
 * <p>
 *
 * @author hihuzi 2018/10/17 17:25
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Base extends Root {
    private Date date0 = new Date();
}
