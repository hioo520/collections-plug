package top.hihuzi.collection.function;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * @author hihuzi 2018/10/26 11:17
 */
public class FunctionTest {

    /**
     * Gets add.
     *
     * @param x          the x
     * @param y          the y
     * @param z          the z
     * @param myFunction the my function
     * @return the add
     */
    public Long getAdd(Long x, Long y, Long z, ParallerEcho<Long, Long, Long> myFunction) {

        return myFunction.handler(x, y);
    }

    /**
     * Test get add.
     */
//    @Test
    public void testGetAdd() {

        Long result = getAdd(1L, 2L, 2L, (x, y) -> {
            return x + y;
        });
        System.out.println("计算得出的结果为：" + result);
    }

    /**
     * Gets int to string.
     *
     * @param x          the x
     * @param y          the y
     * @param myFunction the my function
     * @return the int to string
     */
    public String getIntToString(int x, int y, ParallerEcho<Integer, String, String> myFunction) {

        return myFunction.handler(x, y);
    }

    /**
     * Test get int to string.
     */
//    @Test
    public void testGetIntToString() {

        String result = getIntToString(18, 20, (x, y) -> {
            return "小明的年龄在".concat(String.valueOf(x)).concat("岁到").concat(String.valueOf(y)).concat("岁之间");
        });
        System.out.println(result);
    }

    /**
     * Gets persons by name and age.
     *
     * @param x          the x
     * @param y          the y
     * @param myFunction the my function
     * @return the persons by name and age
     */
    public List<Person> getPersonsByNameAndAge(String x, String y, ParallerEcho<String, List<Person>, String> myFunction) {

        return myFunction.handler(x, y);
    }

    /**
     * Test get persons by name and age.
     */
//    @Test
    public void testGetPersonsByNameAndAge() {

        List<Person> list = Arrays.asList(
                new Person("刘亚壮", 20),
                new Person("张三", 26),
                new Person("李四", 27),
                new Person("小明", 18),
                new Person("小李", 30)
        );

        String name = "小明";
        String age = "18";
        List<Person> persons = getPersonsByNameAndAge(name, age, (x, y) -> {
            List<Person> subPersons = list.stream().filter(p -> p.getAge() >= Integer.parseInt(y) && p.getName().equals(x)).collect(Collectors.toList());
            return subPersons;
        });
        persons.stream().forEach(System.out::println);
    }

}
