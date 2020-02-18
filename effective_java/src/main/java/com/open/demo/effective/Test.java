package com.open.demo.effective;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * todo description
 *
 * @author CHEN-KE-CHAO
 * @date 2020-02-13-1:49 下午
 */
public class Test {

    public static void main(String[] args) {


        List<Integer> sources = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            sources.add(new Random().nextInt(100));
        }

        int tag = 0;
        for (Integer source : sources) {
            if (tag == 2) {
                sources.remove(source);
            }
            tag++;
        }

        tag = 0;

        Iterator<Integer> iterator = sources.iterator();
        while (iterator.hasNext()) {
            if (tag == 2) {
                iterator.remove();
            }
            tag++;
        }
    }


}
