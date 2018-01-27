package io.zipcoder.crudapp.model;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private int majorId;

    @OneToOne
    @JoinColumn(name = "majorId", referencedColumnName = "id", insertable = false, updatable = false)
    private Major major;

    public Person() {
    }

    public Person(String firstName, String lastName, int majorId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.majorId = majorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

}
