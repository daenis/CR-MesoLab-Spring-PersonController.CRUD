package io.zipcoder.crudapp.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    /**
     * The ID of the major INSTANCE associated with this student INSTANCE.

       Ex:

            - Major instance is stored in a variable (computerScience), and holds the fields (id: 1) and
              (name: "Computer Science").

            - Student instance is stored in a variable (student), and holds the field (majorId: 1)

       majorId is a FOREIGN KEY to the major object. Therefore, when sending a JSON representation of this instance,
       we will only need to include the majorId, and not the whole major instance JSON representation.
       When we want to access the associated Major instance's information, we refer to the (major) field (ex
       if we wanted the name of the major) and Spring will automatically populate the (major) field based on the
       (majorId) value.

     * */
    private int majorId;

    /**
     * This is how Spring knows to associate the majorId with the major.

     * We join the two models together at the foreign key; the majorId of the Student class is the id value of the
       major class.

     * fetch = FetchType.EAGER causes the major information to be loaded immediately, as opposed to FetchType.LAZY
       (research!)

     * Also research cascade = CascadeType.ALL (and other cascade types), they are a parameter of the @OneToOne
       annotation (like fetch)
     
     * */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "majorId", referencedColumnName = "id", insertable = false, updatable = false)
    private Major major;

    public Student() {
    }

    public Student(String firstName, String lastName, int majorId, Major major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.majorId = majorId;
        this.major = major;
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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

}
