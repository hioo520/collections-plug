package top.hihuzi.collection.function;

import top.hihuzi.collection.exception.NoticeException;

/**
 * tips
 *
 * @author: hihuzi 2018/10/26 11:16
 */
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Integer getAge() {

        return age;
    }

    public void setAge(Integer age) {

        this.age = age;
    }

}
