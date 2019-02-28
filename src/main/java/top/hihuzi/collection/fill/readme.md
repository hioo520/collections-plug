**XML 对象填充器**

```
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
        Map map1 = FillFactory.batch().fill(request, new StuffConfig(StuffConfig.SaveStyleEnum.REMOVE_NULL_EMPTY));
        map1.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println("");
        /**tips 默认属性不舍弃空值*/
        Map map2 = FillFactory.batch().fill(request, new StuffConfig(StuffConfig.SaveStyleEnum.DEFAULT));
        map2.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
        System.out.println("");
        /**tips 舍弃空值 并且去掉特定字段*/
        Map map3 = FillFactory.batch().fill(request, new StuffConfig(StuffConfig.SaveStyleEnum.REMOVE_NULL_EMPTY), "stringMax");
        map3.forEach((o, o2) -> System.out.print(o + "=" + o2 + " "));
    }

```
### 默认状态
#####longMax=12.3 integerMax=123456 intMin=    doubleMin= stringMax=你好师姐!!! 
### 舍弃掉选定字段
#####longMax=12.3 integerMax=123456 intMin=    doubleMin= 
### 去掉空的字符串
#####stringMax=你好师姐!!! longMax=12.3 integerMax=123456 
### 去掉空的字符串
#####longMax=12.3 integerMax=123456 intMin=    doubleMin= stringMax=你好师姐!!! 
### 去掉空的字符串 去掉特定字符串
#####longMax=12.3 integerMax=123456 
```
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
        for (int i = 0; i < 1000000; i++) {
            map1 = FillFactory.batch().fillEntity(request, new TestBean());
        }
        long end = System.currentTimeMillis();
        System.err.println("------>一百万 耗时" + (end - start) / 1000 + "秒<------");
        System.out.println(Arrays.asList(map1));
    }

```
### 
#####[TestBean(booleanMax=null, byteMax=null, shortMax=null, integerMax=null, longMax=null, floatMax=null, doubleMax=null, stringMax=           , bigdecimalMax=null, dateMax=null, booleanMin=false, charMin= , byteMin=0, shortMin=0, intMin=0, longMin=0, floatMin=0.0, doubleMin=0.0)]
#####------>一百万 耗时1秒<------
```
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
        for (int i = 0; i < 10000000; i++) {
        map1 = FillFactory.batch().fillEntity(request, new TestBean());
        }
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");

        System.out.println(Arrays.asList(map1).toString());
    }

```
###------>一千万 耗时31秒<------
#####[TestBean(booleanMax=true, byteMax=1, shortMax=129, integerMax=123456, longMax=132542435, floatMax=12.9, doubleMax=3.55, stringMax=你好师姐!!!, bigdecimalMax=9825485.61551, dateMax=Wed Dec 12 00:00:00 CST 2012, booleanMin=true, charMin=a, byteMin=2, shortMin=5, intMin=55, longMin=555, floatMin=0.9, doubleMin=1.94)]
     ```
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
                new StuffConfig(StuffBase.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")));
        System.out.println(Arrays.asList(map).toString());
        request.setParameter("dateMax", "2012-12-12 22:21:20");
        map = FillFactory.batch().fillEntity(request, new TestBean(),
                new StuffConfig(StuffBase.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(Arrays.asList(map).toString());
    }
```
#####[TestBean(booleanMax=null, byteMax=null, shortMax=null, integerMax=null, longMax=null, floatMax=null, doubleMax=null, stringMax=你好师姐!!!, bigdecimalMax=null, dateMax=Wed Dec 12 00:00:00 CST 2012, booleanMin=false, charMin= , byteMin=0, shortMin=0, intMin=0, longMin=0, floatMin=0.0, doubleMin=0.0)]
#####[TestBean(booleanMax=null, byteMax=null, shortMax=null, integerMax=null, longMax=null, floatMax=null, doubleMax=null, stringMax=你好师姐!!!, bigdecimalMax=null, dateMax=Wed Dec 12 22:21:20 CST 2012, booleanMin=false, charMin= , byteMin=0, shortMin=0, intMin=0, longMin=0, floatMin=0.0, doubleMin=0.0)]
```
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
         Map map1 = new HashMap();
         /**tips 从对象中取出map*/
         Map map2 = FillFactory.batch().fillMap(bean, map1);
         System.out.println(bean.toString());
         map2.forEach((o, o2) -> System.out.print(o + " " + o2));
     }

```
#####TestBean(booleanMax=true, byteMax=1, shortMax=129, integerMax=123456, longMax=132542435, floatMax=12.99, doubleMax=null, stringMax=你好师姐!!!, bigdecimalMax=9825485.6, dateMax=Wed Dec 12 00:00:00 CST 2012, booleanMin=true, charMin=a, byteMin=2, shortMin=5, intMin=55, longMin=555, floatMin=0.9, doubleMin=1.94)
#####longMax 132542435shortMin 5stringMax 你好师姐!!!byteMin 2floatMax 12.99integerMax 123456booleanMax truedateMax 2012-12-12charMin abyteMax 1intMin 55doubleMin 1.94bigdecimalMax 9825485.6floatMin 0.9booleanMin trueshortMax 129longMin 555
```
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
                new StuffConfig(StuffBase.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd HH:mm:ss")));
        System.out.println(bean0.toString() + "hashCode" + bean.hashCode());


        Map map1 = new HashMap();
        /**tips 从对象中取出map*/
        Map map2 = FillFactory.batch().fillMap(bean0, map1,
                new StuffConfig(StuffBase.DateStyleEnum.DEFAULT.setFormartStyle("yyyy-MM-dd")));
        System.out.println("fillMap从对象中取出不为空的属性");
        map2.forEach((o, o2) -> System.out.print(o + "-->" + o2));
    }
```
#####TestBean(booleanMax=null, byteMax=null, shortMax=null, integerMax=null, longMax=null, floatMax=null, doubleMax=null, stringMax=你好师姐!!!, bigdecimalMax=null, dateMax=Wed Dec 12 00:00:00 CST 2012, booleanMin=false, charMin= , byteMin=0, shortMin=0, intMin=0, longMin=0, floatMin=0.0, doubleMin=0.0)hashCode1596662473
#####TestBean(booleanMax=null, byteMax=null, shortMax=null, integerMax=null, longMax=null, floatMax=null, doubleMax=null, stringMax=你好师姐!!!, bigdecimalMax=null, dateMax=Thu Dec 13 00:23:22 CST 2012, booleanMin=false, charMin= , byteMin=0, shortMin=0, intMin=0, longMin=0, floatMin=0.0, doubleMin=0.0)hashCode1596662473
#####fillMap从对象中取出不为空的属性
#####dateMax-->2012-12-13stringMax-->你好师姐!!!
```
    /**
     * tips List<Map> --> E --> List<E>
     * tips 针对不同格式对应处理
     *
     * @author:hihuzi 2018/6/26 14:51
     */
    @Test
    public void fill_entity_list() throws Exception {

        List list = new ArrayList();
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
        List<TestBean> bean = FillFactory.batch().fillEntity(list, new TestBean());
        /**tips 特殊的时间格式处理*/
        map.put("dateMax", "2012!12@12#12-12:12");
        List<TestBean> bean0 = FillFactory.batch().fillEntity(list, new TestBean(),
                new StuffConfig(StuffBase.DateStyleEnum.DEFAULT.setFormartStyle("yyyy!MM@dd#HH-mm:ss")));
        System.out.println(bean.get(0).toString());
        System.out.println(bean0.get(0).toString());
    }
```
#####TestBean(booleanMax=true, byteMax=1, shortMax=129, integerMax=123456, longMax=132542435, floatMax=12.99, doubleMax=3.55, stringMax=你好师姐!!!, bigdecimalMax=9825485.6, dateMax=Wed Dec 12 00:00:00 CST 2012, booleanMin=true, charMin=a, byteMin=2, shortMin=5, intMin=55, longMin=555, floatMin=0.9, doubleMin=1.94)
#####TestBean(booleanMax=true, byteMax=1, shortMax=129, integerMax=123456, longMax=132542435, floatMax=12.99, doubleMax=3.55, stringMax=你好师姐!!!, bigdecimalMax=9825485.6, dateMax=Wed Dec 12 12:12:12 CST 2012, booleanMin=true, charMin=a, byteMin=2, shortMin=5, intMin=55, longMin=555, floatMin=0.9, doubleMin=1.94)
```
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
                new FillConfig(ConfigEnum.ReturnEnum.LISR),
                new TestBean(), new TestBeanBean());
        List<Map> map3 = (List<Map>) FillFactory.batch().listToClass(list,
                new FillConfig(ConfigEnum.ReturnEnum.DEFAULT),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第一种返回结果是List<Map>");


        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");
        Map<String, List> map4 = (Map<String, List>) FillFactory.batch().listToClass(list,
                new FillConfig(ConfigEnum.ReturnEnum.MAP),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第二种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");
        List<TestBean> testBeans = new ArrayList<>();
        List<TestBeanBean> testBeanBean = new ArrayList<>();
        FillFactory.batch().listToClass(list,
                new FillConfig(ConfigEnum.ReturnEnum.FILL_LIST.setList(testBeans, testBeanBean)),
                new TestBean(), new TestBeanBean());
        System.out.println("测试 ---< 第三种返回结果是Map<String, List>");


        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        List<TestBean> bean = (List<TestBean>) FillFactory.batch().listToClass(list,
                new FillConfig(ConfigEnum.ReturnEnum.FILL_CLASS), new TestBean());
        System.out.println("测试 ---< 第四种返回结果是Map<String, List>");
        long end = System.currentTimeMillis();
        System.err.println("------>一千万 耗时" + (end - start) / 1000 + "秒<------");
    }
```
#####加入缓存 一百万 耗时1秒 一千万 耗时20
#引入缓存   ClassCache.get().add( clazz, fieldsName,paramtertype);
#引入缓存   ClassCache.get().add(clazz, fieldsName);
#调用缓存1.Map<String, TypeCache> cache = ClassCache.getCache(clazz);
