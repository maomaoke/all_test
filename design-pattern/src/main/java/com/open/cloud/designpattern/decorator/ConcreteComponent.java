package com.open.cloud.designpattern.decorator;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-15-4:12 下午
 */
public class ConcreteComponent extends Component {
    @Override
    public void operation() {
        System.out.println("具体对象的操作");
    }
}
