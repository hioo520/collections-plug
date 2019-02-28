package top.hihuzi.collection.sql;


import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import top.hihuzi.collection.sql.config.SQLBean;
import top.hihuzi.collection.sql.config.SQLConfig;
import top.hihuzi.collection.sql.factory.SQLFactory;

import java.util.*;

/**
 * tips
 *
 * @author:hihuzi 2018/7/23 9:21
 */
public class SQlFactoryTest {

    private MockHttpServletRequest request;

    private static List list = new ArrayList();

    @Before
    public void setUp() {

        request = new MockHttpServletRequest();
        request.setCharacterEncoding("utf-8");
        this.request = request;
//        Map map = new HashMap();
//        map.put("na_Me", "小明");
//        map.put("a_Ge", "12");
//        map.put("sE_x", "女");
//        map.put("hi_G_ht", "1.7");
//        map.put("i_D", "123456");
//        for (int i = 0; i < 10000000; i++) {
//            list.add(map);
//        }
    }

    /**
     * tips 一个对象 或者多个对象灵活配置 时处理策略
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void listToEntity0() {

        SQLBean bean = new SQLBean()
                .addClazz(new Son())
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);
        System.out.println(sql);

        SQLBean bean0 = new SQLBean()
                .addClazz(new Son())
                .addNickname("Aa")
                .build();
        Object sql0 = SQLFactory.batch().getSQL(bean0);
        System.out.println(sql0);

        SQLBean bean00 = new SQLBean()
                .addClazz(new Son())
                .addNickname("AA", "CC")
                .build();
        Object sql00 = SQLFactory.batch().getSQL(bean00);
        System.out.println(sql00);

        SQLBean bean1 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son())
                .addNickname("BB")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql1 = SQLFactory.batch().getSQL(bean1);
        System.out.println(sql1);
        SQLBean bean2 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son())
                .addNickname("CC")
                .addDisplay("naMe", "aGe", "sEx", "hiGht")
                .build();
        Object sql2 = SQLFactory.batch().getSQL(bean2);
        System.out.println(sql2);
        SQLBean bean22 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("DD", "CC")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql22 = SQLFactory.batch().getSQL(bean22);
        System.out.println(sql22);
        SQLBean bean222 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("EE", "FF")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql222 = SQLFactory.batch().getSQL(bean222);
        System.out.println(sql222);
        SQLBean bean2222 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("EE", "FF", "GG")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql2222 = SQLFactory.batch().getSQL(bean2222);
        System.out.println(sql2222);
        SQLBean bean22222 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("EE", "FF", "GG")
                .addDisplay("aGe", "sEx", "hiGht", "id")
                .build();
        Object sql22222 = SQLFactory.batch().getSQL(bean22222);
        System.out.println(sql22222);
        SQLBean bean33 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("HH", "II", "JJ")
                .addDisplay("naMe", "aGe", "sEx", "hiGht")
                .build();
        Object sql33 = SQLFactory.batch().getSQL(bean33);
        System.out.println(sql33);
        SQLBean bean44 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("KK", "LL", "MM")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql44 = SQLFactory.batch().getSQL(bean44);
        System.out.println(sql44);
        SQLBean bean55 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("NN", "OO", "PP")
                .build();
        Object sql55 = SQLFactory.batch().getSQL(bean55);
        System.out.println(sql55);
        SQLBean bean66 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("QQ")
                .build();
        Object sql66 = SQLFactory.batch().getSQL(bean66);
        System.out.println(sql66);
        SQLBean bean77 = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .build();
        Object sql77 = SQLFactory.batch().getSQL(bean77);
        System.out.println(sql77);
        SQLBean bean88 = new SQLBean()
                .addClazz(new Son(), new Father(), new Mother())
                .build();
        Object sql88 = SQLFactory.batch().getSQL(bean88);
        System.out.println(sql88);
        /*na_me,hi_ght,id,a_ge,s_ex
        Aa.na_me,Aa.hi_ght,Aa.id,Aa.a_ge,Aa.s_ex
        AA.na_me,AA.hi_ght,AA.id,AA.a_ge,AA.s_ex
        BB.na_me,BB.hi_ght,BB.id,BB.a_ge,BB.s_ex
        CC.na_me,CC.hi_ght,CC.a_geCC.s_ex
        DD.na_me,DD.hi_ght,DD.id,DD.a_ge,DD.s_ex,CC.na_me,CC.hi_ght,CC.a_ge,CC.s_ex,id
        EE.na_me,EE.hi_ght,EE.id,EE.a_ge,EE.s_ex,FF.na_me,FF.hi_ght,FF.a_ge,FF.s_ex,id
        EE.na_me,EE.hi_ght,EE.id,EE.a_ge,EE.s_ex,FF.na_me,FF.hi_ght,FF.a_ge,FF.s_ex,GG.id
        EE.hi_ght,EE.id,EE.a_geEE.s_ex,FF.hi_ght,FF.a_ge,FF.s_ex,GG.id
        HH.na_me,HH.hi_ght,HH.a_geHH.s_ex,II.na_me,II.hi_ght,II.a_ge,II.s_ex,
                KK.na_me,KK.hi_ght,KK.id,KK.a_ge,KK.s_ex,LL.na_me,LL.hi_ght,LL.a_ge,LL.s_ex,MM.id
        NN.na_me,NN.hi_ght,NN.id,NN.a_ge,NN.s_ex,OO.na_me,OO.hi_ght,OO.a_ge,OO.s_ex,PP.id
        QQ.na_me,QQ.hi_ght,QQ.id,QQ.a_ge,QQ.s_ex,na_me,hi_ght,a_ge,s_ex,id
        na_me,hi_ght,id,a_ge,s_ex,na_me,hi_ght,a_ge,s_ex,id
        na_me,hi_ght,id,a_ge,s_ex,na_me,hi_ght,a_ge,s_ex,id*/
    }

    /**
     * tips HttpServletRequest--> obj
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void listToEntity1() {

        Map map = new HashMap();
        map.put("so.na_me", "小明");
        map.put("so.hi_ght", "1.99");
        map.put("so.id", "123456789");
        map.put("so.a_ge", "24");
        map.put("so.s_ex", "男");
        map.put("fa.na_me", "小丽");
        map.put("fa.hi_ght", "2.03");
        map.put("fa.a_ge", "28");
        map.put("fa.s_ex", "女");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);

        SQLConfig config = new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean));

        List<Map> map0 = (List<Map>) SQLFactory.batch().listToEntity(list, config);
        System.out.println(Arrays.asList(map0).toArray().toString());
       /* "Son.aGe" -> "24"
        "Son.naMe" -> "小明"
        "Father.aGe" -> "28"
        "Father.hiGht" -> "2.03"
        "Son.sEx" -> "男"
        "id" -> "123456789"
        "Father.sEx" -> "女"
        "Son.hiGht" -> "1.99"
        "Father.naMe" -> "小丽"*/
    }

    /**
     * tips HttpServletRequest--> obj
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void listToEntity2() {

        Map map = new HashMap();
        map.put("so.na_me", "小明");
        map.put("so.hi_ght", "1.99");
        map.put("so.id", "123456789");
        map.put("so.a_ge", "24");
        map.put("so.s_ex", "男");
        map.put("fa.na_me", "小丽");
        map.put("fa.hi_ght", "2.03");
        map.put("fa.a_ge", "28");
        map.put("fa.s_ex", "女");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);

        SQLConfig config = new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean));
        config.setReturnEnum(SQLConfig.ReturnEnum.LISR);

        List<Map> map2 = (List<Map>) SQLFactory.batch().listToEntity(list,
                config);
        System.out.println(Arrays.asList(map2).toArray().toString());
       /* "Son.aGe" -> "24"
        "Son.naMe" -> "小明"
        "Father.aGe" -> "28"
        "Father.hiGht" -> "2.03"
        "Son.sEx" -> "男"
        "id" -> "123456789"
        "Father.sEx" -> "女"
        "Son.hiGht" -> "1.99"
        "Father.naMe" -> "小丽"*/
    }

    @Test
    public void listToEntity3() {

        Map map = new HashMap();
        map.put("sona_me", "女儿-小丽丽");
        map.put("sohi_ght", "1.99");
        map.put("id", "123456789");
        map.put("soa_ge", "24");
        map.put("sos_ex", "女");
        map.put("fana_me", "爸爸");
        map.put("fahi_ght", "2.03");
        map.put("faa_ge", "28");
        map.put("fas_ex", "男");
        map.put("birthday", "2012-12-12");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father(), new Mother())
                .addNickname("so", "fa", "mo")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
//                .addDisplay( "sEx", "hiGht", "id")
                .addDisplay("sEx", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);
        System.out.println(sql);
        SQLConfig config = new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean));
        config.setReturnEnum(SQLConfig.ReturnEnum.DEFAULT);
        config.setDateStyleEnum(SQLConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy MM"));

        List<Map> map2 = (List<Map>) SQLFactory.batch().listToEntity(list,
                config);
        System.out.println(Arrays.asList(map2).toArray().toString());
       /* "Son.aGe" -> "24"
        "Son.naMe" -> "小明"
        "Father.aGe" -> "28"
        "Father.hiGht" -> "2.03"
        "Son.sEx" -> "男"
        "id" -> "123456789"
        "Father.sEx" -> "女"
        "Son.hiGht" -> "1.99"
        "Father.naMe" -> "小丽"*/
    }

    @Test
    public void listToEntity4() {

        Map map = new HashMap();
        map.put("so.na_me", "女儿-小丽丽");
        map.put("so.hi_ght", "1.99");
        map.put("so.id", "123456789");
        map.put("so.a_ge", "24");
        map.put("so.s_ex", "女");
        map.put("fa.na_me", "爸爸");
        map.put("fa.hi_ght", "2.03");
        map.put("fa.a_ge", "28");
        map.put("fa.s_ex", "男");
        map.put("fa.birthday", "2012-12-12");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);

        List<Map> map2 = (List<Map>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy MM")));
        System.out.println(Arrays.asList(map2).toArray().toString());
                   /* "Son.aGe" -> "24"
            "birthday" -> "2012-12"
            "Son.naMe" -> "女儿-小丽丽"
            "Father.aGe" -> "28"
            "Father.hiGht" -> "2.03"
            "Son.sEx" -> "女"
            "id" -> "123456789"
            "Father.sEx" -> "男"
            "Son.hiGht" -> "1.99"
            "Father.naMe" -> "爸爸"*/
    }

    @Test
    public void listToEntity5() {

        Map map = new HashMap();
        map.put("so.na_me", "女儿-小丽丽");
        map.put("so.hi_ght", "1.99");
        map.put("so.id", "123456789");
        map.put("so.a_ge", "24");
        map.put("so.s_ex", "女");
        map.put("fa.na_me", "爸爸");
        map.put("fa.hi_ght", "2.03");
        map.put("fa.a_ge", "28");
        map.put("fa.s_ex", "男");
        map.put("fa.birthday", "2012-12-12");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);

        Map<String, List> map4 = (Map<String, List>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.ReturnEnum.MAP, SQLConfig.SQLEeum.DEFAULT.set(bean)));
                   /* "Son.aGe" -> "24"
            "birthday" -> "2012-12"
            "Son.naMe" -> "女儿-小丽丽"
            "Father.aGe" -> "28"
            "Father.hiGht" -> "2.03"
            "Son.sEx" -> "女"
            "id" -> "123456789"
            "Father.sEx" -> "男"
            "Son.hiGht" -> "1.99"
            "Father.naMe" -> "爸爸"*/
    }

    @Test
    public void listToEntity6() {

        Map map = new HashMap();
        map.put("so.na_me", "女儿-小丽丽");
        map.put("so.hi_ght", "1.99");
        map.put("so.id", "123456789");
        map.put("so.a_ge", "24");
        map.put("so.s_ex", "女");
        map.put("fa.na_me", "爸爸");
        map.put("fa.hi_ght", "2.03");
        map.put("fa.a_ge", "28");
        map.put("fa.s_ex", "男");
        map.put("fa.birthday", "2012-12-12");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);

        List<Son> Son = new ArrayList<>();
        List<Father> Father = new ArrayList<>();
        SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.ReturnEnum.FILL_LIST.setList(Son, Father), SQLConfig.SQLEeum.DEFAULT.set(bean)));

                   /* "Son.aGe" -> "24"
            "birthday" -> "2012-12"
            "Son.naMe" -> "女儿-小丽丽"
            "Father.aGe" -> "28"
            "Father.hiGht" -> "2.03"
            "Son.sEx" -> "女"
            "id" -> "123456789"
            "Father.sEx" -> "男"
            "Son.hiGht" -> "1.99"
            "Father.naMe" -> "爸爸"*/
    }

    @Test
    public void listToEntity7() {

        Map map = new HashMap();
        map.put("so.na_me", "女儿-小丽丽");
        map.put("so.hi_ght", "1.99");
        map.put("so.id", "123456789");
        map.put("so.a_ge", "24");
        map.put("so.s_ex", "女");
        map.put("fa.na_me", "爸爸");
        map.put("fa.hi_ght", "2.03");
        map.put("fa.a_ge", "28");
        map.put("fa.s_ex", "男");
        map.put("fa.birthday", "2012-12-12");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
//                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
                .addDisplay("naMe", "aGe", "sEx", "hiGht", "id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);

        List<Son> beans = (List<Son>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.ReturnEnum.FILL_CLASS));

        List<Father> beans00 = (List<Father>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.ReturnEnum.FILL_CLASS), new Father());


                   /* "Son.aGe" -> "24"
            "birthday" -> "2012-12"
            "Son.naMe" -> "女儿-小丽丽"
            "Father.aGe" -> "28"
            "Father.hiGht" -> "2.03"
            "Son.sEx" -> "女"
            "id" -> "123456789"
            "Father.sEx" -> "男"
            "Son.hiGht" -> "1.99"
            "Father.naMe" -> "爸爸"*/
    }

    /**
     * tips HttpServletRequest--> obj
     *
     * @author:hihuzi 2018/6/14 14:50
     */
    @Test
    public void listToEntity() {

        Map map = new HashMap();
        map.put("sona_me", "女儿-小丽丽");
        map.put("sohi_ght", "1.99");
        map.put("id", "123456789");
        map.put("soa_ge", "24");
        map.put("sos_ex", "女");
        map.put("fana_me", "爸爸");
        map.put("fahi_ght", "2.03");
        map.put("faa_ge", "28");
        map.put("fas_ex", "男");
        map.put("birthday", "2012-12-12");
        for (int i = 0; i < 2; i++) {
            list.add(map);
        }
        SQLBean bean = new SQLBean()
                .addUnique("007")
                .addClazz(new Son(), new Father())
                .addNickname("so", "fa")
                .addRepeat("naMe", "aGe", "sEx", "hiGht")
//                .addDisplay("naMe", "aGe", "sEx", "hiGht","id")
                .build();
        Object sql = SQLFactory.batch().getSQL(bean);
        System.out.println(sql.toString());

        List<Map> map0 = (List<Map>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean)), new Son(), new Father());

        SQLConfig config = new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean));

        long start = System.currentTimeMillis();
        System.out.println("测试 ---> 第一种返回结果是List<Map>");
        List<Map> map1 = (List<Map>) SQLFactory.batch().listToEntity(list,
                config);
        List<Map> map2 = (List<Map>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.ReturnEnum.LISR), new Son(), new Father());
        List<Map> map3 = (List<Map>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.ReturnEnum.DEFAULT,
                        SQLConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy"),
                        SQLConfig.SQLEeum.DEFAULT.set(bean)),
                new Son(), new Father());
        System.out.println("测试 ---< 第一种返回结果是List<Map>");


        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");
        Map<String, List> map4 = (Map<String, List>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.ReturnEnum.MAP), new Son(), new Father());
        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");
        List<Son> testBeans = new ArrayList<>();
        List<Father> testBeanBean = new ArrayList<>();
        SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.ReturnEnum.FILL_LIST.setList(testBeans, testBeanBean)), new Son(), new Father());
        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        List<Son> beans = (List<Son>) SQLFactory.batch().listToEntity(list,
                new SQLConfig(SQLConfig.SQLEeum.DEFAULT.set(bean), SQLConfig.ReturnEnum.FILL_CLASS), new Son());
        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
    }

}