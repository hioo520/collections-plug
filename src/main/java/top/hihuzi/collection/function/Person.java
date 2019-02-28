package top.hihuzi.collection.function;

import lombok.Data;
import top.hihuzi.collection.exception.NoticeException;

/**
 * tips
 *
 * @author: hihuzi 2018/10/26 11:16
 */
@Data
public class Person {

    private String name;

    private Integer age;

    public Person(String name, Integer age) {

        super();
        this.name = name;
        this.age = age;
    }

    public Person() {

    }

    public static void main(String[] args) {


         throw new NoticeException("菜农啊");
    }

}
