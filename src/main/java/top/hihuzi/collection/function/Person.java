package top.hihuzi.collection.function;

import top.hihuzi.collection.exception.NoticeException;

/**
 * <p>
 *
 * @author hihuzi 2018/10/26 11:16
 */
public class Person {

    private String name;

    private Integer age;

    /**
     * Instantiates a new Person.
     *
     * @param name the name
     * @param age  the age
     */
    public Person(String name, Integer age) {

        super();
        this.name = name;
        this.age = age;
    }

    /**
     * Instantiates a new Person.
     */
    public Person() {

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {


        throw new NoticeException("菜农啊");
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public Integer getAge() {

        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(Integer age) {

        this.age = age;
    }

}
