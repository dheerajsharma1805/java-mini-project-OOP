import java.util.*;

public class InMemoryContactRepository implements ContactRepository{
    private final List<Contact> contacts = new ArrayList<>();
    private final Map<String, Contact> phoneIndex = new HashMap<>();
    private final Map<String, List<Contact>> cityIndex = new HashMap<>();

    @Override
    public void save(Contact contact) {
        if (phoneIndex.containsKey(contact.getPrimaryPhone())) {
            throw new RuntimeException("Contact Already exists");
        }

        contacts.add(contact);
        phoneIndex.put(contact.getPrimaryPhone(), contact);

        cityIndex.computeIfAbsent(contact.getCity(), c -> new ArrayList<>()).add(contact);
    }

    @Override
    public boolean deleteById(String id) {
        Iterator<Contact> it = contacts.iterator();

        while(it.hasNext()) {
            Contact val = it.next();
            if(val.getId().equals(id)) {
                phoneIndex.remove(val.getPrimaryPhone());
                List<Contact> cityContact = cityIndex.get(val.getCity());
                if (cityContact != null) {
                    cityContact.remove(val);
                    if (cityContact.isEmpty()) {
                        cityIndex.remove(val.getCity());
                    }
                }
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public Contact findByPhone(String phone) {
        return phoneIndex.get(phone);
    }

    @Override
    public List<Contact> findByCity(String city) {
        return new ArrayList<>(
                cityIndex.getOrDefault(city, List.of())
        );
    }

    @Override
    public List<Contact> findAll() {
        return new ArrayList<>(contacts);
    }
}
