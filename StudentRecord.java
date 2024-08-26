//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Student Records
// Course:   CS 300 Summer 2024
//
// Author:   Hunter Chan
// Email:    hchan54@wisc.edu
// Lecturer: (Andy Kuemmel)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    none
// Partner Email:   none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         none
// Online Sources:  none
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class represents the Student's records which allows records to be added and
 * changed
 *
 */
public class StudentRecord implements Comparable<StudentRecord> {
    public final String email; //the student's current email
    private double grade; //the student's current grade
    public final String name; //the student's name

    /**
     * This constructor creates a new student record with the students email, grade,
     * and name
     *
     * @param email the student's current email
     * @param grade the student's current grade
     * @param name the student's current name
     * @throws IllegalArgumentException if the name or email is blank or null or if
     *                                  the grade is not in a valid range
     */
    public StudentRecord(String name, String email, double grade){
        //check email is valid and if grade is within range
        if (email == null || email.isEmpty() || grade < 0 || grade > 100 || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or grade given");
        }
        this.email = email;
        this.grade = grade;
        this.name = name;
    }

    /**
     * Ensures that the given object matches an exisiting record
     *
     * @param o   the reference object with which to compare emails
     * @return true if the given object is a studentRecord with a matching email
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { // Check if the objects are the same instance
            return true;
        }
        if (o == null || getClass() != o.getClass()) { // Check if o is null or if the classes differ
            return false;
        }
        StudentRecord that = (StudentRecord) o;
        return this.email.equals(that.email); // Compare the emails for equality
    }


    /**
     * Gets the student's current grade
     *
     * @return the student's current grade
     */
    public double getGrade() {
        return this.grade;
    }

    /**
     * Updates the current grade
     *
     * @param grade the grade that is updated
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     *
     * @return the string representation of the student's current information
     */
    @Override
    public String toString() {
        return this.name + " (" + this.email + "): " + this.grade;
    }

    /**
     * Compares this student record to other Student records as an input
     *
     * @param other the object to be compared.
     * @return the lexicographical value of the comparison of two emails
     */
    @Override
    public int compareTo(StudentRecord other) {
        return this.email.compareTo(other.email);
    }
}
