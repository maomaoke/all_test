package com.open.cloud.designpattern.decorator;

import java.util.Objects;

/**
 * @author CHEN-KE-CHAO
 * @date 2019-09-15-4:13 下午
 */
public abstract class Decorator extends Component {

    private Component component;

    public Decorator() {
        this(null);
    }

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (Objects.nonNull(component)) {
            component.operation();
        }
    }
}
