package Actions;

public class counter {
    static int count;

    public static void hello() {
        System.out.println("Hello from static counter: " + count++);
    }
}
