import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
public interface FunctionalInterfacePractice {
    A foo(int i); // a functional interface has exactly one abstract method

    public static void main(String[] args) {
        FunctionalInterfacePractice f = A::new; // https://www.baeldung.com/java-8-double-colon-operator
        System.out.println(f.foo(5));
        Function<A, String> fun = A::returnHi;
        System.out.println(fun.apply(f.foo(6)));
        List<A> as = Arrays.asList(new A(1), new A(2));
        as.forEach(System.out::println);
        
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.map(i -> f.foo(i)).forEach(System.out::println);
    }
    /**
     *  A=5
        HI
        A=1
        A=2
        A=2
        A=3
        A=4
        A=5
     */
}
