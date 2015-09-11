package util;

public class NewClass {

    public static int foo(int i, int j) {
        while (true) {
            try {
                while (i < j) {
                    i = j++ / i;
                }
            } catch (RuntimeException re) {
                i = 10;
                continue;
            }
            break;
        }
        return j;
    }

    public static void main(String[] args) {
        System.out.println("a");
        label_0:
        {
            try {
                System.out.println("b");
            } catch (RuntimeException $r9) {
                System.out.println("g");
                break label_0;
            }
            try {
                System.out.println("c");
            } catch (RuntimeException $r9) {
                System.out.println("g");
                break label_0;
            } catch (Exception $r6) {
                System.out.println("e");
                break label_0;
            }
            try {
                System.out.println("d");
            } catch (Exception $r6) {
                System.out.println("e");
            }
        } //end label_0:
        System.out.println("f");
    }
}
