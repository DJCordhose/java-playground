package generics;

import java.util.ArrayList;
import java.util.List;

// http://stackoverflow.com/questions/4343202/difference-between-super-t-and-extends-t-in-java
// http://www.angelikalanger.com/GenericsFAQ/FAQSections/TypeArguments.html#FAQ103

public class BoundedWildard {

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++)
            dest.set(i, src.get(i));
    }

    interface LivingThing {

    }

    interface Animal extends LivingThing {

    }

    interface Cat extends Animal {

    }

    interface Dog extends Animal {

    }

    public static void main(String argv[]) {
        {
            List<LivingThing> dest = new ArrayList<>();
            List<Cat> src = new ArrayList<>();
            // cool
            BoundedWildard.copy(dest, src);
            // cool
            BoundedWildard.<LivingThing>copy(dest, src);
            // cool
            BoundedWildard.<Animal>copy(dest, src);
            // cool
            BoundedWildard.<Cat>copy(dest, src);
            // nope, dest is cool, but src is of type Cat which is not compatible with Dog
//            BoundedWildard.<Dog>copy(dest, src);
        }
        {
            // also cool, T is inferred as LivingThing
            List<LivingThing> dest = new ArrayList<>();
            List<LivingThing> src = new ArrayList<>();
            copy(dest, src);
        }
    }

}
