import java.util.HashSet;
import java.util.Set;

public class TestPlayGround {

    public static void main(String args []) {
        testSetBehavior();
    }

    private static void testSetBehavior() {
        Contact c1 = new Contact("1", "Rahul", "A", "999", "r1@gmail.com", "Jaipur", true);
        Contact c2 = new Contact("2", "Rahul", "B", "999", "r2@gmail.com", "Delhi", false);

        Set<Contact> set = new HashSet<>();
        set.add(c1);
        set.add(c2);

        System.out.println("Set size = " + set.size());
        for (Contact c : set) {
            System.out.println(c);
        }
    }


}
