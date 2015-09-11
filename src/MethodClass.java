public abstract class MethodClass {

    public MethodClass(int i) throws Exception {
    }

    public MethodClass(String... ls) {
    }

    public abstract void abstractVoidMethod();
    public abstract byte abstractByteMethod();
    public abstract char abstractCharMethod();
    public abstract int abstractIntMethod();
    public abstract long abstractLongMethod();
    public abstract float abstractFloatMethod();
    public abstract double abstractDoubleMethod();
    public abstract String abstractStringMethod();
    public abstract Object abstractObjectMethod();

    public static void emptyStaticVoidMethod() {
    }

    private final void emptyFinalVoidMethod() {
    }

    public synchronized void emptySynchronizedVoidMethod() {
    }

    public strictfp void emptyStrictfpVoidMethod() {
    }

    private static byte emptyStaticByteMethod() {
        return 0;
    }

    public final byte emptyFinalByteMethod() {
        return 1;
    }


    synchronized byte emptySynchronizedByteMethod() {
        return 2;
    }

    protected strictfp byte emptyStrictfpByteMethod() {
        return 3;
    }

    public char charMethod() {
        return 'a';
    }

    public int intMethod() {
        return 4;
    }

    public long longMethod() {
        return 5;
    }

    public int floatMethod() {
        return 0;
    }

    public int doubleMethod() {
        return 1;
    }

    public String stringMethod() {
        return "";
    }

    public Object objectMethod() {
        return new Object();
    }

    public int[] intArrayMethod() {
        return new int[0];
    }

    public String[][] stringArrayMethod() {
        return new String[1][];
    }

    public Object[][] objectArrayMethod() {
        return new Object[2][][];
    }
}
