package top.hihuzi.collection.function;

import lombok.Data;

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

}
