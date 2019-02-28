package top.hihuzi.collection.utils;


import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * tips 判断是否有空的 对象 实体 集合类
 *
 * @author: hihuzi 2018/7/19 8:59
 */

public class StrUtils {

    /**
     * 初始化 get
     */
    private static StringBuffer GET = new StringBuffer().append("get");

    /**
     * 初始化 set
     */
    private static StringBuffer SET = new StringBuffer().append("set");

    /**
     * 处理驼峰 set
     */
    private static String HUMP = "[_|-|]";

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {

        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * tips 存在一个是  "" 或"  "或 null 返回 true  原名:isNullOrEmpty
     *
     * @parameter: string[] strs
     * @return: Boolean
     * @author: hihuzi 2018/5/3 16:09
     */
    public static Boolean isNoE(String... strs) {

        if (strs == null) {
            return true;
        }
        for (String str : strs) {
            if (str == null || "".equals(str) || str.trim().length() <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * tips 不存在  "" 或"  "或 null 返回 true  原名:isNotNullOrEmpty
     * tips 存在一个是  "" 或"  "或 null 返回 false
     *
     * @parameter: string[] strs
     * @return: Boolean
     * @author: hihuzi 2018/5/3 16:09
     */
    public static Boolean isNNoE(String... strs) {

        return !isNoE(strs);
    }

    /**
     * tips 全都是  "" 或"  "或 null 返回 true 原名:isNullButEmpty
     *
     * @parameter: string[] strs
     * @return: Boolean
     * @author: hihuzi 2018/5/3 16:09
     */
    public static Boolean isNbE(String... strs) {

        int i = 0;
        for (String str : strs) {
            if (str == null || "".equals(str) || str.trim().length() <= 0) {
                i++;
            }
        }
        if (strs.length == i) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * tips 存在一个是  "" 或"  "或 null 返回 true 原名:isNotNullButEmpty
     *
     * @parameter: string[] strs
     * @return: Boolean
     * @author: hihuzi 2018/5/3 16:09
     */
    public static Boolean isNNbE(String... strs) {

        return !isNbE(strs);
    }

    /**
     * tips 存在一个是  null 或size 为0 返回 true 原名:isNullOrEmptyCollections
     *
     * @parameter: Collection<E>[] collections
     * @return: Boolean
     * @author: hihuzi 2018/5/8 15:36
     */
    public static <E> Boolean isNoEC(Collection<E>... collections) {

        for (Collection<E> c : collections) {
            if (c == null || c.size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * tips 全部不是  null 或size=0 返回 true  原名:isNotNullOrEmptyCollections
     * tips 存在一个是  null 或size=0 返回 false
     *
     * @parameter: Collection<E>[] collections
     * @return: Boolean
     * @author: hihuzi 2018/7/7 8:37
     */
    public static <E> Boolean isNNoEC(Collection<E>... collections) {

        return !isNoEC(collections);
    }

    /**
     * tips t 存在一个是  null  返回 true   原名:isNullOrEmptyEntrty
     *
     * @parameter: E[] e
     * @return: Boolean
     * @author: hihuzi 2018/5/10 15:07
     */
    public static <E> Boolean isNoEE(E... e) {

        if (e == null) {
            return true;
        }
        for (E c : e) {
            if (c == null || e.length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * tips e 存在一个是  null  返回 flase  原名:isNotNullOrEmptyEntrty
     * e 全都不是 null 返回 true
     *
     * @parameter: E[] e
     * @return: Boolean
     * @author: hihuzi 2018/5/10 15:07
     */
    public static <E> Boolean isNNoEE(E... e) {

        return !isNoEE(e);
    }

    /**
     * tips 获取set + name--> setName
     *
     * @parameter: String name
     * @return: String
     * @author: hihuzi 2018/9/14 10:36
     */
    public static String achieveSetFunction(String name) {

        return SET + name.substring(0, 1).toUpperCase() + name.substring(1);

    }


    /**
     * tips 获取get + name --> getName
     *
     * @parameter: String name
     * @return: String
     * @author: hihuzi 2018/9/14 10:36
     */
    public static String achieveGetFunction(String name) {

        return GET + name.substring(0, 1).toUpperCase() + name.substring(1);

    }

    public static boolean isEquals(String names, String name) {

        return name.toLowerCase().equals(names.replaceAll(HUMP, "").toLowerCase());
    }

    /**
     * tips 拼接对象名称
     *
     * @author: hihuzi 2019/2/13 17:03
     */
    public static <E> String splicingObjectName(E... e) {

        int i = 0;
        StringBuffer buffer = new StringBuffer();
        for (E es : e) {
            buffer.append(es.getClass().getSimpleName());
            if (i >= e.length - 1) {
                return String.valueOf(buffer);
            }
            buffer.append("-");
            i++;
        }
        return String.valueOf(buffer);
    }

    /**
     * tips 拼接对象名称
     *
     * @author: hihuzi 2019/2/13 17:03
     */
    public static <E> String splicingObjectName(String mark, E... e) {

        int i = 0;
        StringBuffer buffer = new StringBuffer();
        buffer.append(mark);
        for (E es : e) {
            buffer.append(es.getClass().getSimpleName());
            if (i >= e.length - 1) {
                return String.valueOf(buffer);
            }
            buffer.append("-");
            i++;
        }
        return String.valueOf(buffer);
    }

}
