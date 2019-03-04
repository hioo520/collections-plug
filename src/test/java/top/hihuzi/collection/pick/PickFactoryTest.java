package top.hihuzi.collection.pick;

import org.junit.Test;
import top.hihuzi.collection.cache.ClassCache;
import top.hihuzi.collection.cache.TypeCache;
import top.hihuzi.collection.pick.factory.PickFactory;
import top.hihuzi.collection.pick.config.PickConfig;

import java.util.*;

/**
 * <p> 测试工具
 *
 * @author hihuzi 2018/7/20 8:42
 */
public class PickFactoryTest implements Runnable {


    private String tip;

    /**
     * <p>  时间格式化 多级父类
     *
     * @param
     * @return
     * @author hihuzi 2018/10/18 11:16
     */
    @Test
    public void pick0() {


        List<TestBean> list = new ArrayList<>();
        for (int i = 2; i < 3; i++) {
            TestBean userPost = new TestBean();
            userPost.setName("你好师姐");
            userPost.setId(12345 * i + "");
            userPost.setEmail(null);
            userPost.setAddress("    ");
            list.add(userPost);
        }
        /**<p> 时间格式化 多级父类*/
        List<Map> batch7 = PickFactory.batch().pick(list, new PickConfig(PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY,
                PickConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")), "date", "date0", "date1");
        batch7.forEach(map -> System.out.println(map));
        List<Map> batch6 = PickFactory.batch().pick(list, new PickConfig(PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY,
                PickConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")), "date", "date0", "date1");
        batch6.forEach(map -> System.out.println(map));
        List<Map> batch5 = PickFactory.batch().pick(list, new PickConfig(PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY,
                PickConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")), "date", "date0", "date1");
        batch5.forEach(map -> System.out.println(map));
        List<Map> batch4 = PickFactory.batch().pick(list, new PickConfig(PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "date", "date0", "date1");
        batch4.forEach(map -> System.out.println(map));
    }

    @Test
    public void pick() {

        List<TestBean> list = new ArrayList<>();
        for (int i = 2; i < 3; i++) {
            TestBean userPost = new TestBean();
            userPost.setName("你好师姐");
            userPost.setId(12345 * i + "");
            userPost.setEmail(null);
            userPost.setAddress("    ");
            list.add(userPost);
        }
        /**<p> 默认转态*/
        List<Map> batch0 = PickFactory.batch().pick(list, "id", "name", "email", "address");
        batch0.forEach(map -> System.out.println(map));
        /**<p> 和 默认一样 首字母大写*/
        List<Map> batch = PickFactory.batch().pick(list, new PickConfig(
                PickConfig.ReturnNameEnum.INITIAL_CAPITAL), "id", "name", "email", "date");
        batch.forEach(map -> System.out.println(map));
/**         <p> 空值丢掉(null 或者 "" "  ") 并且全部大写*/

        List<Map> batch3 = PickFactory.batch().pick(list, new PickConfig(
                PickConfig.ReturnNameEnum.UPPER_CASE,
                PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "date");
        batch3.forEach(map -> System.out.println(map));
        /**<p> 空值不丢掉 并且全部小写*/
        List<Map> batch2 = PickFactory.batch().pick(list, new PickConfig(
                PickConfig.ReturnNameEnum.LOWER_CASE), "id", "name", "email", "date", "address");
        batch2.forEach(map -> System.out.println(map));

        /**<p> 空值不丢掉 重新命名Key*/
        List<Map> batch4 = PickFactory.batch().pick(list, new PickConfig(
                PickConfig.ReturnNameEnum.CUSTOM_SUFFIX.setKey("我就是我!!")), "id", "name", "email", "date", "address");
        batch4.forEach(map -> System.out.println(map));

        /**<p> 时间格式化*/
        List<Map> batch5 = PickFactory.batch().pick(list, new PickConfig(
                PickConfig.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")), "date", "date0");
        batch5.forEach(map -> System.out.println(map));
    }

    /**
     * <p> 同一对象集合 返回选定字段 返回value(去重)
     *
     * @author hihuzi 2018/4/30 15:49
     */
    @Test
    public void pickValue() {

        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestBean userPost = new TestBean();
            userPost.setName("你好师姐" + i);
            userPost.setId(12345 * i + "");
            userPost.setEmail(null);
            userPost.setAddress("    ");
            list.add(userPost);
        }
        /**<p> 默认设置*/
        Set batch1 = PickFactory.batch().pickValue(list, "id", "name", "email");
        System.out.println(Arrays.asList(batch1).toString());
        /**<p> (去掉 NUll 和 "" or "      ")*/
        Set batch = PickFactory.batch().pickValue(list, new PickConfig(
                PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "address");
        System.out.println(Arrays.asList(batch).toString());

    }

    /**
     * <p> 单个对象 返回选定字段
     *
     * @author hihuzi 2018/4/30 15:49
     */
    @Test
    public void pickValue0() {

        TestBean bean = new TestBean();
        bean.setName("你好师姐");
        bean.setId(UUID.randomUUID().toString());
        bean.setEmail("");
        bean.setAddress(UUID.randomUUID().toString().substring(32) + "@163.com");
        /**<p> 默认 保留 空值*/
        Map batch0 = PickFactory.batch().pickValue(bean, "id", "name", "email", "date", "address");
        System.out.println(batch0.toString());
        /**<p> 保留 空值*/
        Map batch1 = PickFactory.batch().pickValue(bean, new PickConfig(
                PickConfig.ReturnNameEnum.DEFAULT), "id", "name", "email", "date", "address");
        System.out.println(batch1.toString());
        /**<p> 舍弃 空值*/
        Map batch = PickFactory.batch().pickValue(bean, new PickConfig(
                PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "date", "address");
        System.out.println(batch.toString());
        Map<String, Map<String, TypeCache>> classCache = ClassCache.cache;
        classCache.forEach((s, typeCacheMap) -> System.err.println(typeCacheMap.size()));
    }

    /**
     * <p> 单个对象 返回选定字段
     *
     * @author hihuzi 2018/4/30 15:49
     */
    @Test
    public void pickMap() {

        Map bean = new HashMap(5);
        bean.put("id", UUID.randomUUID());
        bean.put("name", "你好师姐");
        bean.put("age", "");
        bean.put("email", "54465@163.com");
        /**<p> 默认 保留 空值*/
        Map batch0 = PickFactory.batch().pickMap(bean, "id", "name", "email", "age");
        System.out.println(batch0.toString());
        /**<p> 保留 空值*/
        Map batch1 = PickFactory.batch().pickMap(bean, new PickConfig(
                PickConfig.ReturnNameEnum.DEFAULT), "id", "name", "email", "age");
        System.out.println(batch1.toString());
        /**<p> 舍弃 空值*/
        Map batch = PickFactory.batch().pickMap(bean, new PickConfig(
                PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "age");
        System.out.println(batch.toString());
        Map<String, Map<String, TypeCache>> classCache = ClassCache.cache;
        classCache.forEach((s, typeCacheMap) -> System.err.println(typeCacheMap.size()));
    }

    /**
     * <p> 单个对象 返回选定字段
     *
     * @author hihuzi 2018/4/30 15:49
     */
    @Test
    public void pickList() {

        List list = new ArrayList();
        Map bean = new HashMap(5);
        bean.put("id", UUID.randomUUID());
        bean.put("name", "你好师姐");
        bean.put("age", "");
        bean.put("email", "54465@163.com");
        for (int i = 0; i < 10; i++) {
            list.add(bean);
        }
        /**<p> 默认 保留 空值*/
        List batch0 = PickFactory.batch().pickList(list, new PickConfig(), "name", "email", "age");
        System.out.println(batch0.toString());
        /**<p> 保留 空值*/
        List batch1 = PickFactory.batch().pickList(list, new PickConfig(
                PickConfig.ReturnNameEnum.DEFAULT), "id", "name", "email", "age");
        System.out.println(batch1.toString());
        /**<p> 舍弃 空值*/
        List batch = PickFactory.batch().pickList(list, new PickConfig(
                PickConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "age");
        System.out.println(batch.toString());
    }

    @Override
    public void run() {

        TestBean bean = new TestBean();
        bean.setDate(new Date());
        try {
            Map map = PickFactory.batch().pickValue(bean,
                    new PickConfig(PickConfig.DateStyleEnum.DEFAULT.setFormartStyle(this.tip)), "date");
            System.out.println(map.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTip(String tip) {

        this.tip = tip;
    }

    @Test
    public void mutlThreadTest() {

        PickFactoryTest test0 = new PickFactoryTest();
        test0.setTip("yyyy-MM-----dd");
        Map maps = new HashMap(1);
        maps.put("dateMax", "2018!22@22#22-22:22");
        PickFactoryTest test = new PickFactoryTest();
        test.setTip("yyyy!MM@dd#HH-mm:ss");
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

        PickFactoryTest test0 = new PickFactoryTest();
        test0.setTip("yyyy-MM-----dd");
        Map maps = new HashMap(1);
        maps.put("dateMax", "2018!22@22#22-22:22");
        PickFactoryTest test = new PickFactoryTest();
        test.setTip("yyyy!MM@dd#HH-mm:ss");
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