package students;

import util.DateTimeFormatterUtil;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Represents a student with personal details, including name, date of birth, gender, contact information,
 * and matriculation number.
 */
public class Student {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String contact;
    private String matricNumber;
    private String remark;

    public Student(
            String name, LocalDate dateOfBirth, String gender,
            String contact, String matricNumber, String tutorialClass
    ) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
        this.matricNumber = matricNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toFileFormat() {
        return getName() +","+ getAge() +"," + getGender() +"," + getContact() +","+ getMatricNumber() +"/n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(matricNumber, student.matricNumber); // Compare based on matricNumber
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricNumber);
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", dateOfBirth=" + DateTimeFormatterUtil.formatDate(dateOfBirth)
                + ", gender=" + gender + ", contact=" + contact + ", matricNumber=" + matricNumber + "]";
    }

}
