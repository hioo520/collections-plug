package top.hihuzi.collection.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * tips
 *
 * @author: hihuzi 2018/10/26 11:17
 */
public class FunctionTest {

    public Long getAdd(Long x, Long y,Long z, ParallerEcho<Long, Long, Long> myFunction) {

        return myFunction.handler(x, y);
    }

    @Test
    public void testGetAdd() {

        Long result = getAdd(1L, 2L, 2L, (x, y) -> {
            return x + y;
        });
        System.out.println("计算得出的结果为：" + result);
    }

    public String getIntToString(int x, int y, ParallerEcho<Integer, String, String> myFunction) {

        return myFunction.handler(x, y);
    }
    @Test
    public void testGetIntToString() {
        String result = getIntToString(18, 20, (x, y) -> {
            return "小明的年龄在".concat(String.valueOf(x)).concat("岁到").concat(String.valueOf(y)).concat("岁之间");
        });
        System.out.println(result);
    }

    public List<Person> getPersonsByNameAndAge(String x, String y, ParallerEcho<String, List<Person>,String > myFunction){
        return myFunction.handler(x, y);
    }
    @Test
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
