import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Contact c = new Contact("abc", "Rahul", "Jaikar",
                "900288366", "rahul@gmail.com", "Jaipur", true);

        Contact c1 = new Contact("def", "Rajesh", "Kalua",
                "800188212", "rajesh@gmail.com", "Udaipur", false);
        Contact c2 = new Contact("ghi", "Rohan", "Kalua",
                "800188212", "rajesh1@gmail.com", "Udaipur", false);
//        System.out.println("Contact created is " + c.getContact());
        ContactManagerApp add = new ContactManagerApp();
        add.addContact(c);
        add.addContact(c1);
        add.addContact(c2);
        List<Contact> contacts = add.getAllContacts();
        contacts.forEach(System.out::println);

//        List<Contact> searchedContact = add.searchByName("Rahul");
//        searchedContact.forEach( contact -> System.out.println(contact.getContact()));
    }
}