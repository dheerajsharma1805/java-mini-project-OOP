public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String primaryPhone;
    private String email;
    private String city;
    private boolean favorite;

    public Contact(String id, String firstName, String lastName, String primaryPhone, String email, String city,boolean favorite ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.primaryPhone = primaryPhone;
        this.email = email;
        this.city = city;
        this.favorite = favorite;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPrimaryPhone() {
        return this.primaryPhone;
    }

    public String getCity() {
        return this.city;
    }

    public boolean isFavorite() {
        return this.favorite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        String[] parts = name.trim().split("\\s+");
        this.firstName = parts[0];
        this.lastName = parts.length > 1 ? parts[1] : "";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setPrimaryPhone(String primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public String toString() {
        return """
        User Profile [%s]:
        Name: %s
        Contact: %s | %s
        Location: %s
        Favorite is: %b
        """.formatted(
                getId(),           // %s
                getName(),    // %s
                getPrimaryPhone(), // %s
                getEmail(),        // %s
                getCity(),         // %s
                isFavorite()       // %b (Note: boolean getters often start with 'is' not 'get')
        );
    }


    // Set and Map will use below two methods to get uniqueness, without below methods they will not solve the problem of duplicates.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return primaryPhone !=null && primaryPhone.equals(contact.getPrimaryPhone());
    }

    @Override
    public int hashCode() {
        return primaryPhone != null ? primaryPhone.hashCode() : 0;
    }
}
