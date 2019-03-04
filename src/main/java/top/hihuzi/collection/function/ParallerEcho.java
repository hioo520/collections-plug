package top.hihuzi.collection.function;


/**
 * <p>
 *
 * @param <T> the type parameter
 * @param <R> the type parameter
 * @param <P> the type parameter
 * @author hihuzi 2018/10/26 11:15
 */
@FunctionalInterface
public interface  ParallerEcho<T, R, P> {

    /**
     * Handler r.
     *
     * @param t1 the t 1
     * @param t2 the t 2
     * @return the r
     */
    R handler(T t1, T t2);

}
