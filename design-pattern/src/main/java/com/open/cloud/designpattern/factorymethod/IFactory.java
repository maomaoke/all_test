package com.open.cloud.designpattern.factorymethod;

import com.open.cloud.designpattern.simplefactory.Operation;

/**
 * @author chenkechao
 * @date 2019/9/15 5:37 下午
 */
public interface IFactory {

    Operation createOperation();
}
