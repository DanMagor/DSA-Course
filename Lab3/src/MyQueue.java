import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Documented;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by anybi on 30.01.2017.
 */
public class MyQueue extends LinkedList {

    public boolean enqueue(Object o) {
        return super.add(o);
    }

}
