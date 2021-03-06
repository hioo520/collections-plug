package top.hihuzi.collection.fill;


import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.fill.config.FillConfig;
import top.hihuzi.collection.fill.factory.FillTool;
import top.hihuzi.collection.utils.ParamUtils;

import java.util.*;

/**
 * <p>
 *
 * @author hihuzi 2018/7/23 9:21
 */
public class FillFactoryTest implements Runnable {

    private static Map map;

    private static String tip;

    private MockHttpServletRequest request;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(Map map) {

        this.map = map;
    }

    /**
     * Sets tip.
     *
     * @param tip the tip
     */
    public void setTip(String tip) {

        this.tip = tip;
    }

    /**
     * Sets up.
     */
    @Before
    public void setUp() {

        request = new MockHttpServletRequest();
        request.setCharacterEncoding("utf-8");
    }

    /**
     * <p> HttpServletRequest--MAP
     *
     * @author hihuzi 2018/7/23 15:05
     */
    @Test
    public void fill() {

        request.setParameter("integerMax", "123456");
        request.setParameter("stringMax", "你好师姐!!!");
        request.setParameter("longMax", "12.3");
        request.setParameter("intMin", "   ");
        request.setParameter("intMin", "   ");
        request.setParameter("doubleMin", "");
        /**<p> 填充到request----MAP*/
        Map map = FillTool.batch().fill(request);
//        map.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println(map.values().toString());
        System.out.println("");
        /**<p> 舍弃掉特定字段*/
        Map map0 = FillTool.batch().fill(request, "stringMax");
//        map0.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println(map0.values().toString());
        System.out.println("");
        /**<p> 舍弃掉空值*/
        Map map1 = FillTool.batch().fill(request, new FillConfig(FillConfig.SaveStyleEnum.REMOVE_NULL_EMPTY));
//        map1.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println(map1.values().toString());
        System.out.println("");
        /**<p> 默认属性不舍弃空值*/
        Map map2 = FillTool.batch().fill(request, new FillConfig(FillConfig.SaveStyleEnum.DEFAULT));
//        map2.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println(map2.values().toString());
        System.out.println("");
        /**<p> 舍弃空值 并且去掉特定字段*/
        Map map3 = FillTool.batch().fill(request, new FillConfig(FillConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "stringMax");
//        map3.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println(map3.values().toString());

    }

    /**
     * <p> HttpServletRequest--> obj
     * <p> 对于空字符串处理
     *
     * @author hihuzi 2018/6/14 14:50
     */
    @Test
    public void fillEntity008() {

        request.setParameter("booleanMax", "");
        request.setParameter("byteMax", "");
        request.setParameter("shortMax", "");
        request.setParameter("integerMax", "");
        request.setParameter("longMax", "");
        request.setParameter("floatMax", "");
        request.setParameter("doubleMax", "");
//        request.setParameter("stringMax", "           ");
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
        for (int i = 0; i < 1000000; i++) {
            FillTool.batch().fillEntity(request, new TestBean());
        }
        TestBean map1map1 = FillTool.batch().fillEntity(request, "top.hihuzi.collection.fill.TestBean");
        TestBean map1map2 = FillTool.batch().fillEntity(request, TestBean.class);
        TestBean testBean = new TestBean();
        testBean.setStringMax("love 皮皮");
        TestBean map1map3 = FillTool.batch().fillEntity(request, testBean);
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
        System.out.println(Arrays.asList(map1map1));
    }

    /**
     * <p> HttpServletRequest--> obj
     *
     * @author hihuzi 2018/6/14 14:50
     */
    @Test
    public void fillEntity001() {

        request.setParameter("booleanMax", "true");
        request.setParameter("byteMax", "1");
        request.setParameter("shortMax", "129");
        request.setParameter("integerMax", "123456");
        request.setParameter("longMax", "132542435");
        request.setParameter("floatMax", "12.9");
        request.setParameter("doubleMax", "3.55");
//        request.setParameter("stringMax", "你好师姐!!!");
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
        for (int i = 0; i < 1000000; i++) {
            map1 = FillTool.batch().fillEntity(request, new TestBean());
        }
        TestBean map1map1 = FillTool.batch().fillEntity(request, "top.hihuzi.collection.fill.TestBean");
        TestBean map1map2 = FillTool.batch().fillEntity(request, TestBean.class);
        TestBean testBean = new TestBean();
        testBean.setStringMax("love 皮皮");
        TestBean map1map3 = FillTool.batch().fillEntity(request, testBean);
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");

        System.out.println(Arrays.asList(map1).toString());
    }

    /**
     * <p> HttpServletRequest--> obj
     * <p> 针对不同格式针对的处理
     *
     * @author hihuzi 2018/6/14 14:50
     */
    @Test
    public void fillEntity002() {

        request.setParameter("stringMax", "你好师姐!!!");
        request.setParameter("dateMax", "2012-12-12");
        TestBean map = null;
        map = FillTool.batch().fillEntity(request, new TestBean(),
                new FillConfig());
        System.out.println(Arrays.asList(map).toString());
        map = FillTool.batch().fillEntity(request, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")));
        System.out.println(Arrays.asList(map).toString());
        request.setParameter("dateMax", "2012-12-12 22:21:20");
        map = FillTool.batch().fillEntity(request, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map).toString());
        TestBean map1 = FillTool.batch().fillEntity(request, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map1).toString());
        TestBean map2 = FillTool.batch().fillEntity(request, TestBean.class,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map2).toString());
        TestBean map3 = FillTool.batch().fillEntity(request, "top.hihuzi.collection.fill.TestBean",
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map3).toString());
        TestBean map4 = FillTool.batch().fillEntity(request, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map4).toString());
    }

    /**
     * <p> Map--> obj
     * <p> 针对不同格式针对的处理
     *
     * @author hihuzi 2018/6/14 14:50
     */
    @Test
    public void fillEntity003() {

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
        TestBean bean = FillTool.batch().fillEntity(map, new TestBean());
        TestBean bean2 = FillTool.batch().fillEntity(map, "top.hihuzi.collection.fill.TestBean");
        TestBean bean3 = FillTool.batch().fillEntity(map, TestBean.class);
        TestBean testBean = new TestBean();
        testBean.setStringMax("love皮皮");
        TestBean bean4 = FillTool.batch().fillEntity(map, testBean);
        TestBean bean6 = FillTool.batch().fillEntity(map, testBean, new FillConfig());
        Map map1 = new HashMap(5);
        /**<p> 从对象中取出map*/
        Map map2 = FillTool.batch().fillMap(bean, map1);
        System.out.println(bean.toString());
//        map2.forEach((o, o2) -> System.out.print(o + " " + o2));
        System.out.println(map2.values().toString());
    }

    /**
     * <p> 针对不同时间格式处理不同时间配置(错误的属性直接丢掉)
     */
    @Test
    public void fillEntity() {

        Map map = new HashMap(20);
        map.put("stringMax", "你好师姐!!!");
        map.put("dateMax", "2012-12-12");
        /**<p> 错误的属性直接丢掉*/
        map.put("dat32eMax", "2012-12-12");

        TestBean bean = FillTool.batch().fillEntity(map, new TestBean());
        System.out.println(bean.toString() + "hashCode" + bean.hashCode());

        map.put("dateMax", "2012-12-12 24:23:22");
        TestBean bean0 = FillTool.batch().fillEntity(map, new TestBean(),
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(bean0.toString() + "hashCode" + bean.hashCode());


        Map map1 = new HashMap(5);
        /**<p> 从对象中取出map*/
        Map map2 = FillTool.batch().fillMap(bean0, map1,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")));
//        map2.forEach((o, o2) -> System.out.print(o + "-->" + o2));
        System.out.println(map2.values().toString());

    }

    /**
     * <p> List<Map> --> E --> List<E>
     * <p> 针对不同格式对应处理
     *
     * @author hihuzi 2018/6/26 14:51
     */
    @Test
    public void list_To_Class() {

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
        List<TestBean> bean = (List<TestBean>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), new TestBean());
        for (TestBean testBean : bean) {
            System.out.println(testBean.toString());
        }
        /**<p> 特殊的时间格式处理*/

        map.put("dateMax", "2012!12@12#12-12:12");
        list0.add(map);
        List<TestBean> bean0 = (List<TestBean>) FillTool.batch().listToClass(list0,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy!MM@dd#HH-mm:ss"), FillConfig.ReturnEnum.FILL_CLASS), new TestBean());
        System.out.println(bean.get(0).toString());
        System.out.println(bean0.get(0).toString());
//        long start = System.currentTimeMillis();
//        List<TestBean> bean3;
//        for (int i = 0; i < 1000000; i++) {
//            bean3 = FillTool.batch().fillEntity(list, new TestBean(),
//                    new FillConfig(EnumConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy!MM@dd#HH-mm:ss")));
//        }
//        long end = System.currentTimeMillis();
//        System.err.println("------>一百万 耗时" + (end - start) / 1000 + "秒<------");
    }

    /**
     * <p> list<String> --> E --> list<E> 针对数据库与实体类名有区别
     *
     * @author hihuzi 2018/6/26 14:51
     */
    @Test
    public void fillEntity007() {

        Map map = new HashMap(20);
        map.put("stringMax", "你好师姐!!!");
        map.put("dateMax", "2012-12-12");
        map.put("booleanMin", "");
        TestBean bean = FillTool.batch().fillEntity(map, new TestBean());
        System.out.println(bean);
        Map map1 = new HashMap(5);
        /**<p> 从对象中取出map*/
        map1 = FillTool.batch().fillMap(bean, map1);
//        map1.forEach((o, o2) -> System.out.print(o + "-->" + o2 + " "));
        System.out.println(map1.values().toString());
        System.out.println("");
        System.out.println("fillMap从对象中取出不为空的属性 并且时间自定义");
        map1 = FillTool.batch().fillMap(bean, map1,
                new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
//        map1.forEach((o, o2) -> System.out.print(o + "-->" + o2 + " "));
        System.out.println(map1.values().toString());
    }

    /**
     * <p> E --> Map  针对E与map进行填充
     *
     * @author hihuzi 2018/6/26 14:51
     */
    @Test
    public void listToEntity() {

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
        List<TestBean> bean = FillTool.batch().listToEntity(list, new TestBean());
        System.out.println(bean.get(0).toString());
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000; i++) {
//            List<TestBean> bean0 = FillTool.batch().listToEntity(list, new TestBean());
//        }
//        long end = System.currentTimeMillis();
//        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
        Map<String, Map<String, TypeCache>> classCache = ClassCache.cache;
//        classCache.forEach((s, typeCache) -> System.err.println(typeCache.size()));
        System.out.println(classCache.values().toString());
    }

    /**
     * <p> HttpServletRequest--> obj
     *
     * @author hihuzi 2018/6/14 14:50
     */
    @Test
    public void list_to_class() {

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
        for (int i = 1; i < 10000; i++) {
            list.add(map);
        }

        System.out.println("测试 ---> 第一种返回结果是List<Map>");
        List<Map> map1 = (List<Map>) FillTool.batch().listToClass(list,
                new TestBean(), new TestBeanBean());
        List<Map> map2 = (List<Map>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.LISR),
                new TestBean(), new TestBeanBean());
        map1 = null;
        map2 = null;
        List<Map> map3 = (List<Map>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.DEFAULT, FillConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy")),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第一种返回结果是List<Map>");


        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");
        Map<String, List> map4 = (Map<String, List>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.MAP_CLASS),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");
        List<TestBean> testBeans = new ArrayList<>();
        List<TestBeanBean> testBeanBean = new ArrayList<>();
        FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_LIST.setList(testBeans, testBeanBean)),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        List<TestBean> bean0 = (List<TestBean>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), TestBean.class);
        List<TestBean> bean = (List<TestBean>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), "top.hihuzi.collection.fill.TestBean");
        List<TestBean> bean1 = (List<TestBean>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), new TestBean());
        TestBean testBean = new TestBean();
        List<TestBean> bean2 = (List<TestBean>) FillTool.batch().listToClass(list,
                new FillConfig(FillConfig.ReturnEnum.FILL_CLASS), testBean);
        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        long end = System.currentTimeMillis();
        System.err.println("------>一百万 耗时" + (end - start) / 1000 + "秒<------");
    }

    /**
     * List to class.
     */
    @Test
    public void listToClass() {

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
        for (int i = 1; i < 2; i++) {
            list.add(map);
        }

        System.out.println("测试 ---> 第一种返回结果是List<Map>");
        List<Map> map1 = (List<Map>) FillTool.batch().listToClass(list,
                new TestBean(), new TestBeanBean());
        System.out.println("23423432");
        /* 配置类缓存标志位*/
        List<Map> map2 = (List<Map>) FillTool.batch().listToClass(list, new FillConfig(FillConfig.MarkCacheEnum.DEFAULT.set("love皮皮")),
                new TestBean(), new TestBeanBean());
        System.out.println(Arrays.toString(map1.toArray()));
    }

    /**
     * Fill class.
     */
    @Test
    public void fillClass() {

        List list = new ArrayList();
        TestBean testBean = new TestBean();
//        testBean.setStringMax("");
        testBean.setIntMin(888888);
        testBean.setCharacter('W');
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            list.add(testBean);
        }

        System.out.println("测试 ---> 第一种返回结果是List<Map>");
        List<PigBean> map1 = FillTool.batch().fillClass(list, new PigBean());
        List<PigBean> map10 = FillTool.batch().fillClass(list, new PigBean(), "stringMax", "intMin", "character");
        PigBean pigBean11 = FillTool.batch().fillClass(testBean, new PigBean());
        PigBean pigBean110 = FillTool.batch().fillClass(testBean, new PigBean(), "stringMax", "intMin");


        List<PigBean> map22 = FillTool.batch().fillClass(list, PigBean.class, "stringMax", "intMin", "character");
        List<PigBean> map2 = FillTool.batch().fillClass(list, PigBean.class);
        PigBean pigBean = FillTool.batch().fillClass(testBean, PigBean.class);
        PigBean pigBean00 = FillTool.batch().fillClass(testBean, PigBean.class, "stringMax", "intMin");


        List<PigBean> map3 = FillTool.batch().fillClass(list, "top.hihuzi.collection.fill.PigBean", "stringMax", "intMin");
        List<PigBean> map30 = FillTool.batch().fillClass(list, "top.hihuzi.collection.fill.PigBean");
        PigBean pigBean330 = FillTool.batch().fillClass(testBean, "top.hihuzi.collection.fill.PigBean", "stringMax", "intMin");
        PigBean pigBean3300 = FillTool.batch().fillClass(testBean, "top.hihuzi.collection.fill.PigBean");


        List<PigBean> mapyy2 = FillTool.batch().fillClass(list, PigBean.class, new FillConfig(FillConfig.NullCharEnum.NULL_TO_CHAR), "stringMax", "intMin", "character");
        PigBean pigBdfsdfeansdf = FillTool.batch().fillClass(testBean, PigBean.class, new FillConfig(FillConfig.NullCharEnum.NULL_TO_CHAR), "stringMax", "intMin", "character");
        PigBean pigBdfsdfean = FillTool.batch().fillClass(testBean, PigBean.class, new FillConfig(), "stringMax", "intMin");
    }

    /**
     * Fill class 008.
     */
    @Test
    public void fillClass008() {

        List list = new ArrayList();
        Store store = new Store();
        store.setCaptain("大利润");
        store.setClerk("达利园个");
        for (int i = 0; i < 10; i++) {
            list.add(store);
        }
        List<StoreExcel> pickMap0 = FillTool.batch().fillClass(list, Store.class, ParamUtils.fields(StoreExcel.class));
        System.out.println(pickMap0.toArray().toString());

    }

    @Override
    public void run() {

        TestBean bean = null;
        try {
            bean = FillTool.batch().fillEntity(map, new TestBean(),
                    new FillConfig(FillConfig.DateStyleEnum.DEFAULT.setFormartStyle(this.tip)));
//            System.out.println(bean.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * <p> 多线程测试
     *
     * @param
     * @return
     * @author hihuzi 2018/10/21 3:51
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

}