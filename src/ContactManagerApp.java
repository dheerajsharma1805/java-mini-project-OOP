import java.util.*;

public class ContactManagerApp {
    private List<Contact> contacts = new ArrayList<>();
    private Set<String> usedPhone = new HashSet<>();

    public void addContact(Contact c){
        String phone = c.getPrimaryPhone();
        try {

            if (phone == null || phone.isBlank()){
                throw new IllegalArgumentException("Phone number is required");
            }

            if (usedPhone.contains(phone)) {
                throw new RuntimeException("Contact already exists with phone: " + phone);
            }
    //        if(duplicateContact(c) || duplicateContact(c)) {
    //            throw new RuntimeException("Contact already exists");
    //        }
            contacts.add(c);
            usedPhone.add(phone);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts); // this returns the copy of contacts.
//        System.out.println("Contact added is "+ contacts.get(0).getContact());
        // return contacts; this lets user to make changes in the list of contacts
    }

    public List<Contact> searchByName(String searchKeyword) {
        Iterator it = contacts.iterator();

        List <Contact> temp = new ArrayList<>();
        while (it.hasNext() && searchKeyword != null){
            Contact contact = (Contact) it.next();
            if (contact.getName().contains(searchKeyword)) {
                temp.add(contact);
            }
        }
        return temp;
    }

    public List<Contact> searchByPhone(String phoneKey) {
        Iterator it = contacts.iterator();
        List <Contact> temp = new ArrayList<>();
        while(it.hasNext() && phoneKey != null) {
            Contact contact = (Contact) it.next();
            if (contact.getPrimaryPhone().contains(phoneKey)){
                temp.add(contact);
            }
        }
        return temp;
    }

    public boolean deleteById(String id) {
        Iterator<Contact> it = contacts.iterator();
        while(it.hasNext()) {
            Contact contact = it.next();
            if(contact.getId().equals(id)) {
                usedPhone.remove(contact.getPrimaryPhone());
                it.remove();
                return true;
            }
        }
        return false;
    }

    private boolean duplicateContact(Contact contactData) {
        for(int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getPrimaryPhone().equals(contactData.getPrimaryPhone()) || contacts.get(i).getEmail().equals(contactData.getEmail())){
                return true;
            }
        }
        return false;
    }

}
