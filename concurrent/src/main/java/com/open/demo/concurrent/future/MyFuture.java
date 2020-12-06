package com.open.demo.concurrent.future;

/**
 * @author chenkechao
 * @date 2020/11/20 5:01 下午
 */
public interface MyFuture <V> {
    /**
     * 这个接口的get方法返回真正的结果, 如果结果还没有计算完成, get方法会堵塞知道计算完成, 如果调用过程发生异常, 则get方法抛出调用过程中的异常.
     * @return
     * @throws Exception
     */
    V get() throws Exception;
}
