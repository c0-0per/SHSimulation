package people;

import events.Event;
import objects.Observer;
import objects.sensors.Sensor;

public class Person implements Observer {

    private String Name;
    private String Surname;
    private PersonGender gender;
    private int age;
    private String email;
    private long phoneNumber;
    private String prefixTelephone;
    private PersonEvent personEvent;


    /**
     * Constructs a new Person with the specified name, surname, gender, and age.
     *
     * @param name    the first name of the person
     * @param surname the surname of the person
     * @param gender  the gender of the person
     * @param age     the age of the person
     */
    public Person(String name, String surname, PersonGender gender, int age) {
        Name = name;
        Surname = surname;
        this.gender = gender;
        this.age = age;
    }

    public void setPersonState(PersonEvent personEvent) {
        this.personEvent = personEvent;
    }

    public String getName() {
        return Name;
    }

    public String getPrefix_telephone() {
        return prefixTelephone;
    }

    public void setPrefixTelephone(String prefix_telephone) {
        this.prefixTelephone = prefix_telephone;
    }

    public String getSurname() {
        return Surname;
    }

    public PersonGender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void update(Event event, Sensor sensor) {

    }
}
