import java.util.List;

public interface ContactRepository {
    void save(Contact contact);

    boolean deleteById(String id);

    Contact findByPhone(String phone);

    List<Contact> findByCity(String city);

    List<Contact> findAll();
}