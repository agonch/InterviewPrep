public class A {
    private int a;
    
    public A(int a) {
        this.a = a;
    }

    public A() {
        this(42);
    }

    public String returnHi() {
        return "HI";
    }
    
    @Override
    public String toString() {
        return "A=" + a;
    }
}