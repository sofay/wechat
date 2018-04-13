package cn.fay.wechat.common.convert;

/**
 * @author fay  fay9395@gmail.com
 * @date 2018/4/13 下午2:59.
 */
public interface Convert<K, R> {
    R convert(K k);
}
