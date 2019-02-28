**XML 对象提取器**
```
@Test
    public void batch() throws Exception {

        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            TestBean userPost = new TestBean();
            userPost.setName("你好师姐");
            userPost.setId(12345 * i + "");
            userPost.setEmail(null);
            userPost.setAddress("    ");
            list.add(userPost);
        }
        // tips 默认转态
        List<Map> batch0 = PickFactory.batch().pick(list, "id", "name", "email", "address");
        batch0.forEach(map -> System.out.println(map));
        // tips 和 默认一样 首字母大写
        List<Map> batch = PickFactory.batch().pick(list, new PickConfig(
                PickBase.ReturnStyleEnum.LIST_MAP,
                PickBase.ReturnNameEnum.INITIAL_CAPITAL,
                PickBase.SaveStyleEnum.DEFAULT), "id", "name", "email", "date");
        batch.forEach(map -> System.out.println(map));
        // tips 空值丢掉(null 或者 "" "  ") 并且全部大写

        List<Map> batch3 = PickFactory.batch().pick(list, new PickConfig(
                PickBase.ReturnStyleEnum.LIST_MAP,
                PickBase.ReturnNameEnum.UPPER_CASE,
                PickBase.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "date");
        batch3.forEach(map -> System.out.println(map));
        // tips 空值不丢掉 并且全部小写
        List<Map> batch2 = PickFactory.batch().pick(list, new PickConfig(
                PickBase.ReturnStyleEnum.LIST_MAP,
                PickBase.ReturnNameEnum.LOWER_CASE,
                PickBase.SaveStyleEnum.DEFAULT), "id", "name", "email", "date", "address");
        batch2.forEach(map -> System.out.println(map));

        // tips 空值不丢掉 重新命名Key
        List<Map> batch4 = PickFactory.batch().pick(list, new PickConfig(
                PickBase.ReturnStyleEnum.LIST_MAP,
                PickBase.ReturnNameEnum.CUSTOM_SUFFIX.setKey("我就是我!!"),
                PickBase.SaveStyleEnum.DEFAULT), "id", "name", "email", "date", "address");
        batch4.forEach(map -> System.out.println(map));

    }
```
#####{name=你好师姐, id=0, address=    , email=}
#####{Id=0, Email=, Date=Fri Sep 21 11:29:08 CST 2018, Name=你好师姐}
#####{DATE=Fri Sep 21 11:29:08 CST 2018, ID=0, NAME=你好师姐}
#####{我就是我!!id=0, 我就是我!!name=你好师姐, 我就是我!!date=Sun Sep 30 10:00:28 CST 2018, 我就是我!!email=, 我就是我!!address=    }
```
    /**
        * tips 同一对象集合 返回选定字段 返回value(去重)
        *
        * @author: hihuzi 2018/4/30 15:49
        */
       @Test
       public void pickValue() throws Exception {

           List<TestBean> list = new ArrayList<>();
           for (int i = 0; i < 5; i++) {
               TestBean userPost = new TestBean();
               userPost.setName("你好师姐" + i);
               userPost.setId(12345 * i + "");
               userPost.setEmail(null);
               userPost.setAddress("    ");
               list.add(userPost);
           }
           // tips 默认设置
           Set batch1 = PickFactory.batch().pickValue(list, "id", "name", "email");
           System.out.println(Arrays.asList(batch1).toString());
           // tips (去掉 NUll)
           Set batch = PickFactory.batch().pickValue(list, new PickConfig(
                   PickBase.ReturnStyleEnum.SET,
                   PickBase.ReturnNameEnum.DEFAULT,
                   PickBase.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "address");
           System.out.println(Arrays.asList(batch).toString());

       }

```
#####[[0, null, 49380, 24690, 37035, 你好师姐0, 12345, 你好师姐4, 你好师姐3, 你好师姐2, 你好师姐1]]
#####[[0, 49380, 24690, 37035, 你好师姐0, 12345, 你好师姐4, 你好师姐3, 你好师姐2, 你好师姐1]]
```
   /**
        * tips 单个对象 返回选定字段
        *
        * @author: hihuzi 2018/4/30 15:49
        */
       @Test
       public void pickSingle() throws Exception {

           TestBean bean = new TestBean();
           bean.setName("你好师姐");
           bean.setId(UUID.randomUUID().toString());
           bean.setEmail(null);
           bean.setAddress(UUID.randomUUID().toString().substring(32) + "@163.com");
           // tips 默认 保留 空值
           Map batch0 = PickFactory.batch().pickValue(bean, "id", "name", "email", "date", "address");
           System.out.println(batch0.toString());
           // tips 保留 空值
           Map batch1 = PickFactory.batch().pickValue(bean, new PickConfig(
                   PickBase.ReturnStyleEnum.MAP,
                   PickBase.ReturnNameEnum.DEFAULT,
                   PickBase.SaveStyleEnum.DEFAULT), "id", "name", "email", "date", "address");
           System.out.println(batch1.toString());
           // tips 舍弃 空值
           Map batch = PickFactory.batch().pickValue(bean, new PickConfig(
                   PickBase.ReturnStyleEnum.MAP,
                   PickBase.ReturnNameEnum.DEFAULT,
                   PickBase.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "date", "address");
           System.out.println(batch.toString());
           Map<String, Map<String, TypeCache>> classCache = ClassCache.cache;
           classCache.forEach((s, typeCacheMap) -> System.err.println(typeCacheMap.size()));
       }
```
#####{date=Fri Sep 21 11:34:43 CST 2018, address=2994@163.com, name=你好师姐, id=fb5396be-2c4e-44b1-b86e-4b465c49dc7e, email=}
#####{date=Fri Sep 21 11:34:43 CST 2018, address=2994@163.com, name=你好师姐, id=fb5396be-2c4e-44b1-b86e-4b465c49dc7e, email=}
#####{date=Fri Sep 21 11:34:43 CST 2018, address=2994@163.com, name=你好师姐, id=fb5396be-2c4e-44b1-b86e-4b465c49dc7e}
```
/**
     * tips 单个对象 返回选定字段
     *
     * @author: hihuzi 2018/4/30 15:49
     */
    @Test
    public void pickMap() throws Exception {

        Map bean = new HashMap();
        bean.put("id", UUID.randomUUID());
        bean.put("name", "你好师姐");
        bean.put("age", "");
        bean.put("email", "54465@163.com");
        /**tips 默认 保留 空值*/
        Map batch0 = PickFactory.batch().pickMap(bean, "id", "name", "email", "age");
        System.out.println(batch0.toString());
        /**tips 保留 空值*/
        Map batch1 = PickFactory.batch().pickMap(bean, new PickConfig(
                PickBase.ReturnNameEnum.DEFAULT), "id", "name", "email", "age");
        System.out.println(batch1.toString());
        /**tips 舍弃 空值*/
        Map batch = PickFactory.batch().pickMap(bean, new PickConfig(
                PickBase.SaveStyleEnum.REMOVE_NULL_EMPTY), "id", "name", "email", "age");
        System.out.println(batch.toString());
        Map<String, Map<String, TypeCache>> classCache = ClassCache.cache;
        classCache.forEach((s, typeCacheMap) -> System.err.println(typeCacheMap.size()));
    }
```
#####{name=你好师姐, id=67f00311-638d-4d2b-b305-3dbffb9e8289, email=54465@163.com, age=}
#####{name=你好师姐, id=67f00311-638d-4d2b-b305-3dbffb9e8289, email=54465@163.com, age=}
#####{name=你好师姐, email=54465@163.com, id=67f00311-638d-4d2b-b305-3dbffb9e8289}
#增加缓存 强化效率