package ac.ncl.yuechen.util;

import java.util.*;

/**
 * @ClassName ac.ncl.yuechen.main.util.SortedArray
 * @Description: TODO
 * @Author BENY
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {


    public boolean add(E e) {
        if (size() == 0) {
            add(0, e);
            return true;
        } else {
            E value = e;
            int x = 0;
            for (x = size(); x > 0; x--) {
                if (value.compareTo(get(x - 1)) > 0) {
                    break;
                }
            }
            add(x, value);
            return true;
        }
    }

}