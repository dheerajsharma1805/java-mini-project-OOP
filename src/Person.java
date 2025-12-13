public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String city;

    protected Person(String firstName, String lastName, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getCity() {
        return city;
    }

}
