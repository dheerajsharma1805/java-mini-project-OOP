import java.util.List;

public class ContactService {
    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact can't be null");
        }
        repository.save(contact);
    }

    public boolean deleteContact(String id) {
        return repository.deleteById(id);
    }

    public Contact getByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    public List<Contact> getByCity(String city) {
        return repository.findByCity(city);
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }
}
