package top.hihuzi.collection.function;


/**
 * <p>
 *
 * @author hihuzi 2018/10/26 11:15
 */
@FunctionalInterface
public interface  ParallerEcho<T, R, P> {

    R handler(T t1, T t2);

}
