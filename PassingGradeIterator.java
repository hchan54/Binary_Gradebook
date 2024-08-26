//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PassingGradeIterator
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

import java.util.NoSuchElementException;

/**
 * Iterator for traversing the records in a GradeBook in increasing order, while also skipping over StudentRecords who do not have a passing grade.
 * This iterator iterates through the StudentRecord objects with passing grades, only.
 */
public class PassingGradeIterator extends GradebookIterator{
    private StudentRecord next; //reference to the current StudentRecord with a passing grade to be returned
    private double passingGrade; //the passing grade


    /**
     * Creates new GradeBookIterator to iterate over the give GradeBook and initializes
     * the reference to the minimum StudentRecord in the gradeBook
     *
     * @param gradebook the GradeBook to iterate over
     */
    public PassingGradeIterator(Gradebook gradebook) {
        super(gradebook);
        this.passingGrade = gradebook.PASSING_GRADE;
        advanceToNextPassingGrade();
    }

    /**
     * Private helper method that advances this iterator to the next StudentRecord with a passing grade.
     *
     */
    private void advanceToNextPassingGrade() {
        while (super.hasNext()) { //check if more elements are available in the gradebook
            StudentRecord currentRecord = super.next(); //get the next element from the superclass iterator
            if (currentRecord.getGrade() >= passingGrade) { //check if the record is passing
                next = currentRecord;
                return;
            }
        }
        next = null;
    }
    /**
     *
     * @return true if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return next != null; //return true if next is not null
    }

    /**
     *
     * @throws NoSuchElementException if there is no next item
     * @return the next StudentRecord object with a passing grade in the iteration
     */
    @Override
    public StudentRecord next() {
        if (next == null) { //check if there is more elements to return
            throw new NoSuchElementException("No more elements with passing grades.");
        }
        StudentRecord currentRecord = next; //store the current next value to return it
        advanceToNextPassingGrade(); //advance to the next passing grade record
        return currentRecord;
    }
}

