import java.util.*;

public class ContactManagerApp {
    private List<Contact> contacts = new ArrayList<>();
    private Set<String> usedPhone = new HashSet<>();
    private Map<String, Contact> phoneIndex = new HashMap<>();
    private Map<String, List<Contact>> cityIndex = new HashMap<>();

    public void addContact(Contact c){
        try {
            if(c == null) {
                throw  new IllegalArgumentException("Contact can't be empty");
            }

            String phone = c.getPrimaryPhone();

            if (phone == null || phone.isBlank()){
                throw new IllegalArgumentException("Phone number is required");
            }

            if (phoneIndex.containsKey(phone)) {
                throw new RuntimeException("Contact already exists with phone: " + phone);
            }

//            if (usedPhone.contains(phone)) {
//                throw new RuntimeException("Contact already exists with phone: " + phone);
//            }
    //        if(duplicateContact(c) || duplicateContact(c)) {
    //            throw new RuntimeException("Contact already exists");
    //        }
            contacts.add(c);
//            usedPhone.add(phone);
            phoneIndex.put(phone, c);
            String city = c.getCity();
            if(city != null && !city.isBlank()){
                cityIndex.computeIfAbsent(city, k -> new ArrayList<>()).add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts); // this returns the copy of contacts.
//        System.out.println("Contact added is "+ contacts.get(0).getContact());
        // return contacts; this lets user to make changes in the list of contacts
    }

    public List<Contact> searchByCity(String searchKeyword) {
//        Iterator it = contacts.iterator();
//
//        List <Contact> temp = new ArrayList<>();
//        while (it.hasNext() && searchKeyword != null){
//            Contact contact = (Contact) it.next();
//            if (contact.getName().contains(searchKeyword)) {
//                temp.add(contact);
//            }
//        }
//        return temp;
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return List.of();
        }
        List<Contact> result = cityIndex.get(searchKeyword);
        if (result == null) {
            return List.of();
        }

        return new ArrayList<>(result);
    }

    public Contact searchByPhone(String phoneKey) {
//        Iterator it = contacts.iterator();
//        List <Contact> temp = new ArrayList<>();
//        while(it.hasNext() && phoneKey != null) {
//            Contact contact = (Contact) it.next();
//            if (contact.getPrimaryPhone().contains(phoneKey)){
//                temp.add(contact);
//            }
//        }
//        return temp;
        if (phoneKey != null && !phoneKey.isBlank()){
            Contact contact = phoneIndex.get(phoneKey);
            return contact;
        }
        return null;
    }

    public boolean deleteById(String id) {
        Iterator<Contact> it = contacts.iterator();
        while(it.hasNext()) {
            Contact contact = it.next();
            if(contact.getId().equals(id)) {
                String phone = contact.getPrimaryPhone();
                if (phone != null && !phone.isBlank()) {
                    phoneIndex.remove(contact.getPrimaryPhone());
                }
                String city = contact.getCity();
                if (city != null && !city.isBlank()) {
                    List<Contact> contactsInCity = cityIndex.get(city);
                    if (contactsInCity != null) {
                        contactsInCity.remove(contact);
                    }
                }
//                usedPhone.remove(contact.getPrimaryPhone());
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void printCityWiseCount() {
        for (var entry : cityIndex.entrySet()) {
            System.out.println("entry is "+ entry);
            String city = entry.getKey();
            int count = entry.getValue().size();
            System.out.println(city + " → " + count + " contacts");
        }
    }

    public void printAllPhones() {
        for (var phone: phoneIndex.keySet()) {
            System.out.println(phone);
        }
    }

    public Map<String, Integer> countContactsByCity(List<Contact> contacts) {
        Map<String, Integer> counts = new HashMap<>();
        for (var contact: contacts){
            String city = contact.getCity();
            if (city == null || city.isBlank()) continue;

            counts.put(city, counts.getOrDefault(city, 0) + 1);
        }
        return counts;
    }

    private boolean duplicateContact(Contact contactData) {
        for(int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getPrimaryPhone().equals(contactData.getPrimaryPhone()) || contacts.get(i).getEmail().equals(contactData.getEmail())){
                return true;
            }
        }
        return false;
    }

    //Sorting methods

    public List<Contact> getContactsSortedByName() {
        List<Contact> copy = new ArrayList<>(contacts);
//        copy.sort(null); // uses Comparable (natural order)
        Collections.sort(copy);
        return copy;
    }

    public List<Contact> getContactsSortedByCityThenName() {
        List<Contact> copy = new ArrayList<>(contacts);

        copy.sort(
                Comparator
                        .comparing(Contact::getCity, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)
        );

        return copy;
    }

    public List<Contact> getContactsSortedByEmailLambda() {
        List<Contact> copy = new ArrayList<>(contacts);

        copy.sort((c1, c2) -> {
            String e1 = c1.getEmail();
            String e2 = c2.getEmail();
            if (e1 == null && e2 == null) return 0;
            if (e1 == null) return -1;
            if (e2 == null) return 1;
            return e1.compareToIgnoreCase(e2);
        });

        return copy;
    }

    public List<Contact> getContactsSortedByEmail() {
        List<Contact> copy = new ArrayList<>(contacts);

        copy.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                if (c1.getEmail() == null && c2.getEmail() == null) return 0;
                if (c1.getEmail() == null) return -1;
                if (c2.getEmail() == null) return 1;
                return c1.getEmail().compareToIgnoreCase(c2.getEmail());
            }
        });

        return copy;
    }



}

