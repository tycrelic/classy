
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class List implements java.io.Serializable {
    int value;
    List next;
    public static void main(String[] args) {
        try {
            List list1 = new List();
            List list2 = new List();
            list1.value = 17;
            list1.next = list2;
            list2.value = 19;
            list2.next = null;

            FileOutputStream o = new FileOutputStream("t.txt");
            ObjectOutputStream out = new ObjectOutputStream(o);
            out.writeObject(list1);
            out.writeObject(list2);
            out.flush();
						
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}