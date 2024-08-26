//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    GradebookIterator
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
import java.util.Iterator;

/**
 * Iterator for traversing the records in a GradeBook in increasing order without skipping any element.
 *
 */
public class GradebookIterator implements Iterator<StudentRecord> {
    private StudentRecord current; //current studentBook reference
    private Gradebook gradebook; //GradeBook to iterate over

    /**
     * Creates new GradeBookIterator to iterate over the give GradeBook and initializes
     * the reference to the minimum StudentRecord in the gradeBook
     *
     * @param gradebook the GradeBook to iterate over
     */
    public GradebookIterator(Gradebook gradebook) {
        this.current = gradebook.getMin(); //smallest value in the node
        this.gradebook = gradebook;
    }

    /**
     *
     *
     * @return true if the iteration has more elements.
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * @throws NoSuchElementException if there is no next item
     * @return the next element in the iteration (current StudentRecord)
     */
    @Override
    public StudentRecord next() {
        if (current == null) { //check for next node
            throw new NoSuchElementException();
        }
        StudentRecord next = this.current;
        this.current = gradebook.successor(this.current); //set the current to its successor
        return next;
    }
}
