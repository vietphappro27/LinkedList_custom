import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        LinkedListCustom<Integer> list = new LinkedListCustom<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1,2);
        list.get(2);
        list.print();
        list.isEmpty();
        List<String> a = new ArrayList<>();
        a.add("123");
    }
}