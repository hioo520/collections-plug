package top.hihuzi.collection.fill;


import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.fill.factory.FillFactory;
import top.hihuzi.collection.fill.config.FillConfig;

import java.util.*;

/**
 * tips
 *
 * @author:hihuzi 2018/7/23 9:21
 */
public class FillFactoryTest implements Runnable {

    private MockHttpServletRequest request;

    private static Map map;

    private static String tip;

    public void setMap(Map map) {

        this.map = map;
    }

    public void setTip(String tip) {

        this.tip = tip;
    }

    @Before
    public void setUp() {

        request = new MockHttpServletRequest();
        request.setCharacterEncoding("utf-8");
    }

    /**
     * tips HttpServletRequest-->MAP
     *
     * @author:hihuzi 2018/7/23 15:05
     */
    @Test
    public void fill() {

        request.setParameter("integerMax", "123456");
        request.setParameter("stringMax", "你好师姐!!!");
        request.setParameter("longMax", "12.3");
        request.setParameter("intMin", "   ");
        request.setParameter("intMin", "   ");
        request.setParameter("doubleMin", "");
        /**tips 填充到request---->Map*/
        Map map = FillFactory.batch().fill(request);
        map.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println("");
        /**tips 舍弃掉特定字段*/
        Map map0 = FillFactory.batch().fill(request, "stringMax");
        map0.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println("");
        /**tips 舍弃掉空值*/
        Map map1 = FillFactory.batch().fill(request, new FillConfig(FillConfig.SaveStyleEnum.REMOVE_NULL_EMPTY));
        map1.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println("");
        /**tips 默认属性不舍弃空值*/
        Map map2 = FillFactory.batch().fill(request, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
        map2.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println("");
        /**tips 舍弃空值 并且去掉特定字段*/
        Map map3 = FillFactory.batch().fill(request, new FillConfig(FillConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "stringMax");
        map3.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));

    }

    /**
     * tips HttpServletRequest--> obj
     * tips 对于空字符串处理
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void fill_entity_request() throws Exception {

        request.setParameter("booleanMax", "");
        request.setParameter("byteMax", "");
        request.setParameter("shortMax", "");
        request.setParameter("integerMax", "");
        request.setParameter("longMax", "");
        request.setParameter("floatMax", "");
        request.setParameter("doubleMax", "");
        request.setParameter("stringMax", "           ");
        request.setParameter("bigdecimalMax", "");
        request.setParameter("dateMax", "");
        request.setParameter("booleanMin", "");
        request.setParameter("charMin", "");
        request.setParameter("byteMin", "");
        request.setParameter("shortMin", "");
        request.setParameter("intMin", "");
        request.setParameter("longMin", "");
        request.setParameter("floatMin", "");
        request.setParameter("doubleMin", "");
        long start = System.currentTimeMillis();
        TestBean map1 = null;
        for (int i = 0; i < 10000000; i++) {
            map1 = FillFactory.batch().fillEntity(request, new TestBean());
        }
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
        System.out.println(Arrays.asList(map1));
    }

    /**
     * tips HttpServletRequest--> obj
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void fill_entity_request1() throws Exception {

        request.setParameter("booleanMax", "true");
        request.setParameter("byteMax", "1");
        request.setParameter("shortMax", "129");
        request.setParameter("integerMax", "123456");
        request.setParameter("longMax", "132542435");
        request.setParameter("floatMax", "12.9");
        request.setParameter("doubleMax", "3.55");
        request.setParameter("stringMax", "你好师姐!!!");
        request.setParameter("bigdecimalMax", "9825485.61551");
        request.setParameter("dateMax", "2012-12-12");
        request.setParameter("booleanMin", "true");
        request.setParameter("charMin", "a");
        request.setParameter("byteMin", "2");
        request.setParameter("shortMin", "5");
        request.setParameter("intMin", "55");
        request.setParameter("longMin", "555");
        request.setParameter("floatMin", "0.9");
        request.setParameter("doubleMin", "1.94");
        TestBean map1 = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            map1 = FillFactory.batch().fillEntity(request, new TestBean());
        }
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");

        System.out.println(Arrays.asList(map1).toString());
    }

    /**
     * tips HttpServletRequest--> obj
     * tips 针对不同格式针对的处理
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void fill_entity_request2() throws Exception {

        request.setParameter("stringMax", "你好师姐!!!");
        request.setParameter("dateMax", "2012-12-12");
        TestBean map = null;
        map = FillFactory.batch().fillEntity(request, new TestBean(),
                new FillConfig());
        System.out.println(Arrays.asList(map).toString());
        map = FillFactory.batch().fillEntity(request, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")));
        System.out.println(Arrays.asList(map).toString());
        request.setParameter("dateMax", "2012-12-12 22:21:20");
        map = FillFactory.batch().fillEntity(request, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map).toString());
    }

    /**
     * tips Map--> obj
     * tips 针对不同格式针对的处理
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void fill_entity_map() throws Exception {

        Map map = new HashMap(20);
        map.put("booleanMax", "true");
        map.put("byteMax", "1");
        map.put("shortMax", "129");
        map.put("integerMax", "123456");
        map.put("longMax", "132542435");
        map.put("floatMax", "12.99");
        map.put("stringMax", "你好师姐!!!");
        map.put("bigdecimalMax", "9825485.6");
        map.put("dateMax", "2012-12-12");
        map.put("booleanMin", "true");
        map.put("charMin", "a");
        map.put("byteMin", "2");
        map.put("shortMin", "5");
        map.put("intMin", "55");
        map.put("longMin", "555");
        map.put("floatMin", "0.9");
        map.put("doubleMin", "1.94");
        TestBean bean = FillFactory.batch().fillEntity(map, new TestBean());
        Map map1 = new HashMap(5);
        /**tips 从对象中取出map*/
        Map map2 = FillFactory.batch().fillMap(bean, map1);
        System.out.println(bean.toString());
        map2.forEach((o, o2) -> System.out.print(o + " " + o2));
    }

    /**
     * tips 针对不同时间格式处理不同时间配置(错误的属性直接丢掉)
     */
    @Test
    public void fill_entity_map0() throws Exception {

        Map map = new HashMap(20);
        map.put("stringMax", "你好师姐!!!");
        map.put("dateMax", "2012-12-12");
        /**tips 错误的属性直接丢掉*/
        map.put("dat32eMax", "2012-12-12");

        TestBean bean = FillFactory.batch().fillEntity(map, new TestBean());
        System.out.println(bean.toString() + "hashCode" + bean.hashCode());

        map.put("dateMax", "2012-12-12 24:23:22");
        TestBean bean0 = FillFactory.batch().fillEntity(map, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(bean0.toString() + "hashCode" + bean.hashCode());


        Map map1 = new HashMap(5);
        /**tips 从对象中取出map*/
        Map map2 = FillFactory.batch().fillMap(bean0, map1,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")));
        map2.forEach((o, o2) -> System.out.print(o + "-->" + o2));
    }


    /**
     * tips List<Map> --> E --> List<E>
     * tips 针对不同格式对应处理
     *
     * @author: hihuzi 2018/6/26 14:51
     */
    @Test
    public void fill_entity_class() throws Exception {

        List list = new ArrayList();
        List list0 = new ArrayList();
        Map map = new HashMap(20);
        map.put("booleanMax", "true");
        map.put("byteMax", "1");
        map.put("shortMax", "129");
        map.put("integerMax", "123456");
        map.put("longMax", "132542435");
        map.put("floatMax", "12.99");
        map.put("doubleMax", "3.55");
        map.put("stringMax", "你好师姐!!!");
        map.put("bigdecimalMax", "9825485.6");
        map.put("dateMax", "2012-12-12");
        map.put("booleanMin", "true");
        map.put("charMin", "a");
        map.put("byteMin", "2");
        map.put("shortMin", "5");
        map.put("intMin", "55");
        map.put("longMin", "555");
        map.put("floatMin", "0.9");
        map.put("doubleMin", "1.94");
        list.add(map);
        List<TestBean> bean = (List<TestBean>) FillFactory.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), new TestBean());
        for (TestBean testBean : bean) {
            System.out.println(testBean.toString());
        }
        /**tips 特殊的时间格式处理*/

        map.put("dateMax", "2012!12@12#12-12:12");
        list0.add(map);
        List<TestBean> bean0 = (List<TestBean>) FillFactory.batch().listToClass(list0,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy!MM@dd#HH-mm:ss"), FillConfig.ReturnEnum.FILL_CLASS), new TestBean());
        System.out.println(bean.get(0).toString());
        System.out.println(bean0.get(0).toString());
//        long start = System.currentTimeMillis();
//        List<TestBean> bean3;
//        for (int i = 0; i < 1000000; i++) {
//            bean3 = FillFactory.batch().fillEntity(list, new TestBean(),
//                    new FillConfig(EnumConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy!MM@dd#HH-mm:ss")));
//        }
//        long end = System.currentTimeMillis();
//        System.err.println("------>一百万 耗时" + (end - start) / 1000 + "秒<------");
    }

    /**
     * tips list<String> --> E --> list<E> 针对数据库与实体类名有区别
     *
     * @author:hihuzi 2018/6/26 14:51
     */

    @Test
    public void fill_map() throws Exception {

        Map map = new HashMap(20);
        map.put("stringMax", "你好师姐!!!");
        map.put("dateMax", "2012-12-12");
        map.put("booleanMin", "");
        TestBean bean = FillFactory.batch().fillEntity(map, new TestBean());
        System.out.println(bean);
        Map map1 = new HashMap(5);
        /**tips 从对象中取出map*/
        map1 = FillFactory.batch().fillMap(bean, map1);
        map1.forEach((o, o2) -> System.out.print(o + "-->" + o2 + " "));
        System.out.println("");
        System.out.println("fillMap从对象中取出不为空的属性 并且时间自定义");
        map1 = FillFactory.batch().fillMap(bean, map1,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        map1.forEach((o, o2) -> System.out.print(o + "-->" + o2 + " "));
    }

    /**
     * tips E --> Map  针对E与map进行填充
     *
     * @parameter: E e
     * @parameter: Map map
     * @parameter: E
     * @Author: hihuzi 2018/6/26 14:51
     */

    @Test
    public void list_to_entity() throws Exception {

        List list = new ArrayList();
        list.add("true");
        list.add("1");
        list.add("129");
        list.add("123456");
        list.add("132542435");
        list.add("12.99");
        list.add("3.55");
        list.add("你好师姐!!!");
        list.add("9825485.6");
        list.add("2012-12-12");
        list.add("true");
        list.add("a");
        list.add("2");
        list.add("5");
        list.add("55");
        list.add("555");
        list.add("0.9");
        list.add("1.94");
        List<TestBean> bean = FillFactory.batch().listToEntity(list, new TestBean());
        System.out.println(bean.get(0).toString());
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 10000000; i++) {
//            List<TestBean> bean0 = FillFactory.batch().listToEntity(list, new TestBean());
//        }
//        long end = System.currentTimeMillis();
//        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
        Map<String, Map<String, TypeCache>> classCache = ClassCache.cache;
        classCache.forEach((s, typeCache) -> System.err.println(typeCache.size()));
    }

    /**
     * tips HttpServletRequest--> obj
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void list_to_class() throws Exception {

        List list = new ArrayList();
        Map map = new HashMap();
        map.put("boo_leanMax", "true");
        map.put("by_teMax", "1");
        map.put("shortMax", "129");
        map.put("integerMax", "123456");
        map.put("longMax", "1");
        map.put("floatMax", "12.9");
        map.put("doubleMax", "3.55");
        map.put("string_Max", "你好师姐!!!");
        map.put("bigdecimalMax", "9825485.61551");
        map.put("dateMax", "2012-12-12");
        map.put("booleanMin", "true");
        map.put("charMin", "a");
        map.put("byteMin", "2");
        map.put("shortMin", "5");
        map.put("intMin", "55");
        map.put("longMin", "555");
        map.put("flo_atMin", "0.9");
        map.put("doubleMin", "1.94");
        map.put("i_d", "ID_ID-ID-ID");
        list.add(map);
        long start = System.currentTimeMillis();
        for (int i = 1; i < 100000; i++) {
            list.add(map);
        }

        System.out.println("测试 ---> 第一种返回结果是List<Map>");
        List<Map> map1 = (List<Map>) FillFactory.batch().listToClass(list,
                new TestBean(), new TestBeanBean());
        List<Map> map2 = (List<Map>) FillFactory.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.LISR),
                new TestBean(), new TestBeanBean());
        List<Map> map3 = (List<Map>) FillFactory.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.DEFAULT,FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy")),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第一种返回结果是List<Map>");


        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");
        Map<String, List> map4 = (Map<String, List>) FillFactory.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.MAP),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");
        List<TestBean> testBeans = new ArrayList<>();
        List<TestBeanBean> testBeanBean = new ArrayList<>();
        FillFactory.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_LIST.setList(testBeans, testBeanBean)),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        List<TestBean> bean = (List<TestBean>) FillFactory.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), new TestBean());
        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
    }

    @Override
    public void run() {

        TestBean bean = null;
        try {
            bean = FillFactory.batch().fillEntity(map, new TestBean(),
                    new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle(this.tip)));
            System.out.println(bean.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * tips 多线程测试
     *
     * @parameter:
     * @return:
     * @author: hihuzi 2018/10/21 3:51
     */
    @Test
    public void mains() {

        Map map = new HashMap(1);
        map.put("dateMax", "333-33-33");
        FillFactoryTest test0 = new FillFactoryTest();
        test0.setMap(map);
        test0.setTip("yyyy-MM-dd");
        Map maps = new HashMap(1);
        maps.put("dateMax", "222!22@22#22-22:22");
        FillFactoryTest test = new FillFactoryTest();
        test0.setMap(maps);
        test0.setTip("yyyy!MM@dd#HH-mm:ss");
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 666; i++) {
            Thread thread;
            if (i % 2 == 0) {
                thread = new Thread(test0, "" + i);
            } else {
                thread = new Thread(test, "" + i);
            }
            threads.add(thread);
        }
        for (Thread thread : threads) {

            thread.start();
        }


    }

    public static void main(String[] args) {

        Map map = new HashMap(1);
        map.put("dateMax", "333-33-33");
        FillFactoryTest test0 = new FillFactoryTest();
        test0.setMap(map);
        test0.setTip("yyyy-MM-dd");
        Map maps = new HashMap(1);
        maps.put("dateMax", "222!22@22#22-22:22");
        FillFactoryTest test = new FillFactoryTest();
        test0.setMap(maps);
        test0.setTip("yyyy!MM@dd#HH-mm:ss");
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 666; i++) {
            Thread thread;
            if (i % 2 == 0) {
                thread = new Thread(test0, "" + i);
            } else {
                thread = new Thread(test, "" + i);
            }
            threads.add(thread);
        }
        for (Thread thread : threads) {

            thread.start();
        }

    }

}