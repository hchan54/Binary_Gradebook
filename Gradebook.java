//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Gradebook
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
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class models a grade book for a specific course used to store student records
 *
 */
public class Gradebook implements Iterable<StudentRecord>{
    public final String course; //the name of the course
    public final double PASSING_GRADE; //minimum passing grade for this course
    private boolean passingGradeIteratorEnabled; //indicates whether the passing grade iterator is enabled
    private BSTNode<StudentRecord> root; //root node of the BST
    private int size; //total number of StudentRecords in the current gradebook


    /**
     * Constructs an empty GradeBook for a given course with a defined
     * passing grade
     *
     * @param course the name of the course
     * @param passingGrade the minimum passing grade for this course
     * @throws IllegalArgumentException if the course is null or blank or
     *                                  if the passingGrade is out of bounds
     *
     */
    public Gradebook(String course, double passingGrade) {
        //check course is valid and passingGrade is within range
        if (course == null || course.isEmpty() || passingGrade > 100 || passingGrade < 0) {
            throw new IllegalArgumentException("Invalid course or passing grade");
        }
        this.course = course;
        this.PASSING_GRADE = passingGrade;
    }


    /**
     * Enables the passing grade iterator
     *
     */
    public void enablePassingGradeIterator() {
        this.passingGradeIteratorEnabled = true;
    }


    /**
     * Disables the passing grade iterator
     *
     */
    public void disablePassingGradeIterator() {
        this.passingGradeIteratorEnabled = false;
    }


    /**
     * Checks whether the GradeBook is empty
     *
     * @return true if GradeBook is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     *
     * @return the current size of the GradeBook
     */
    public int size() {
        return this.size;
    }


    /**
     * Tries to add the record to this tree and updates it accordingly
     *
     * @param record the record to be added to this GradeBook
     * @throws IllegalArgumentException if a match with record is already in this tree
     */
    public void addStudent(StudentRecord record) {
        if (this.root == null) { //check for root
            this.root = new BSTNode<>(record); //record is the new root
        } else { //otherwise add accordingly
            addStudentHelper(record, this.root);
        }
        this.size++; //size increases regardless
    }


    /**
     * Recursive helper method to add a record to the tree subtree rooted at node
     *
     * @param record  the new student to add
     * @param node  root of the subtree
     * @throws IllegalStateException the subtree rooted at node contains a duplicate record
     * @return the new BST root node added to the tree
     */
    protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record,
                                                             BSTNode<StudentRecord> node) {
        if (record == node.getData()) { //check if the node already exists
            throw new IllegalStateException("Node already exists");
        }

        if (record.compareTo(node.getData()) < 0) { //check if record is less than current node
            if (node.getLeft() == null) { //base case that sets a new left node
                node.setLeft(new BSTNode<StudentRecord>(record));
            } else { //otherwise keep going left
                addStudentHelper(record, node.getLeft());
            }
        } else { //check if record is greater than current node
            if (node.getRight() == null) { //base case that sets a new right node
                node.setRight(new BSTNode<StudentRecord>(record));
            } else { //otherwise keep going right
                addStudentHelper(record, node.getRight());
            }
        }
        return node; //return root
    }



    /**
     * Returns true if this BST has an identical layout (all subtrees equal) to the given tree.
     *
     * @author Ashley Samuelson
     * @see BSTNode#equals(Object)
     * @param node tree to compare this Gradebook to
     * @return true if the given tree looks identical to the root of this Gradebook
     */
    public boolean equalBST(BSTNode<StudentRecord> node) {
        return root == node || (root != null && root.equals(node));
    }


    /**
     *
     * @return inorder String representation of the GradeBook in increasing order
     */
    @Override
    public String toString() {
        return toStringHelper(this.root);
    }


    /**
     * Returns a String representation of the subtree rooted at node in increasing order
     *
     * @param node root of a subtree
     * @return String representation of the subtree rooted at node
     */
    protected static String toStringHelper(BSTNode<StudentRecord> node) {
        if (node == null) { //base case that returns nothing if the node has no data
            return "";
        }

        return toStringHelper(node.getLeft()) + node.getData().toString() //recursive call that traverses the tree in order
                + "\n" + toStringHelper(node.getRight());
    }


    /**
     * String representation of the BST in decreasing order and indents based on depth
     *
     * @return a String representation of the structure of this BST
     */
    public String prettyString() {
        int depth = 0; //initial depth is 0
        return prettyStringHelper(this.root, depth);
    }


    /**
     * Returns a decreasing-order String representation of the structure of this subtree,
     * indented by four spaces for each level of depth in the larger tree.
     *
     * @author Ashley Samuelson
     * @param node current subtree within the larger tree
     * @param depth depth of the current node within the larger tree
     * @return a String representation of the structure of this subtree
     */
    protected static String prettyStringHelper(BSTNode<StudentRecord> node, int depth) {
        if (node == null) {
            return "";
        }
        String indent = " ".repeat(depth);
        return prettyStringHelper(node.getRight(), depth + 1) + indent + node.getData().toString()
                + "\n" + prettyStringHelper(node.getLeft(), depth + 1);
    }


    /**
     * Finds a StudentRecord given the associated email address
     *
     * @param email email address of a student
     * @return the student associated with the email argument if there is a match
     */
    public StudentRecord lookup(String email) {
        return lookupHelper(new StudentRecord("random", email, 0.00), this.root);
    }


    /**
     * Recursive helper method which looks for a given StudentRecord given at the
     * BST rooted at the node
     *
     * @param target  the StudentRecord to search in the subtree rooted at the node
     * @param node root of the subtree of this BST
     * @return the student record with the matching email
     */
    protected static StudentRecord lookupHelper(StudentRecord target,
                                                BSTNode<StudentRecord> node) {
        if (node == null) { // base case: node is null, target not found
            return null;
        }

        if (target.equals(node.getData())) { //check if the current node's data matches the target
            return node.getData();
        } else if (target.compareTo(node.getData()) < 0) { //search in the left subtree
            return lookupHelper(target, node.getLeft());
        } else { // search in the right subtree
            return lookupHelper(target, node.getRight());
        }
    }


    /**
     *
     *
     * @return the student with the lexicographically smallest email in the BST
     */
    protected StudentRecord getMin() {
        return getMinHelper(this.root);
    }


    /**
     * Returns the smallest StudentRecord in the subtree rooted at node
     *
     * @param node  root of a subtree of a binary search tree
     * @return the smallest student in the subtree rooted at node
     */
    protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
        //base case that returns the node if there is no left node
        if (node.getLeft() == null) {
            return node.getData();
        }
        return getMinHelper(node.getLeft()); //recursive call to return the left most node
    }


    /**
     * Deletes a StudentRecord from this Grade book given their email
     *
     * @param email the email of the student to delete
     * @throws NoSuchElementException if there is no StudentRecord with the given email
     */
    public void removeStudent(String email) {
        BSTNode<StudentRecord> removed = null; //used to determine if a node is removed
        removed = removeStudentHelper(new StudentRecord("name", email, 0.0), this.root);
        if (removed != null) { //check if a node was removed
            size--; //decrement size if node was removed
        }
    }


    /**
     * Deletes the matching StudentRecord with toDrop if it is found within this tree
     *
     * @param toDrop the StudentRecord to be removed from the tree
     * @param node  the root of the subtree to remove the student from
     * @throws NoSuchElementException if there is no matching student Record
     * @return the new root of the subtree after removing the StudentRecord
     *
     */
    protected static BSTNode<StudentRecord> removeStudentHelper (StudentRecord toDrop,
                                                              BSTNode<StudentRecord> node) {
        if (node == null) { //check if the target node exists
            throw new NoSuchElementException("Node does not exist");
        }

        //traverse tree to find the node to drop
        if (toDrop.compareTo(node.getData()) < 0) { //key is less than the current node
            node.setLeft(removeStudentHelper(toDrop, node.getLeft()));
        } else if (toDrop.compareTo(node.getData()) > 0) { //key is greater than the current node
            node.setRight(removeStudentHelper(toDrop, node.getRight()));
        } else { //located node
            //case for internal node and 2 children
            if (node.getLeft() != null && node.getRight() != null) {
                //find the smallest node in the right subtree
                BSTNode<StudentRecord> temp = node;
                StudentRecord successor = getMinHelper(temp.getRight());
                node.setData(successor); //replace the node with the successor
                node.setRight(removeStudentHelper(successor, node.getRight()));
            } else if (node.getLeft() != null) {//internal node with left child only
                node = node.getLeft();
            } else if (node.getRight() != null) { //internal node with a right child
                node = node.getRight();
            } else { //leaf node
                node = null;
            }
        }
        return node;
    }


    /**
     * Returns the successor of a target StudentRecord
     *
     * @param target  the StudentRecord to find the successor of
     * @return  the successor of the target in the GradeBook
     */
    protected StudentRecord successor(StudentRecord target) {
        return successorHelper(target, this.root);
    }

    /**
     * Returns the successor of a target StudentRecord within the subtree
     *
     * @param target  the StudentRecord to find the successor of
     * @param node  the subtree to search for a successor to the target
     * @return  the successor of the target in the subtree rooted at node, or null if none exists
     */
    protected static StudentRecord successorHelper(StudentRecord target, BSTNode<StudentRecord> node) {
        if (node == null) { //base case that returns null if the node is null
            return null;
        }

        if (target.compareTo(node.getData()) < 0) { //if the key value is larger than the root
            StudentRecord leftNode = successorHelper(target, node.getLeft());
            // If the leftNode is null, then the current node is the successor
            return (leftNode != null) ? leftNode : node.getData();
        } else {
            return successorHelper(target, node.getRight());
        }
    }

    /**
     * Returns an iterator over the student records in this GradeBook in the increasing order. If
     * the passingGradeIterator is enabled the iterator iterates over students with a passing grade
     *
     * @return an Iterator over the elements in this GradeBook in the proper sequence
     */
    @Override
    public Iterator<StudentRecord> iterator() {
        if (this.passingGradeIteratorEnabled) { //check if iterator is enabled
            return new PassingGradeIterator(this);
        } else { //otherwise iterate through the gradeBook normally
            return new GradebookIterator(this);
        }
    }

    /**
     * Searches for the StudentRecord associated with the provided input email
     * in this BST and checks whether it has a passing grade for this course
     *
     * @param email the email of the StudentRecord to find
     * @return A String indicating whether the student having the input email has a passing or failing grade
     */
    public String checkPassingCourse(String email) {
        StudentRecord matchingStudent = lookup(email); //find the StudentRecord matching the email
        if (matchingStudent == null) { //check if record is in the gradebook
            return "No match found";
        } else if (matchingStudent.getGrade() >= this.PASSING_GRADE) { //print accordingly if passing
            return matchingStudent.toString() + ": PASS";
        } else {
            return matchingStudent.toString() + ": FAIL"; //print accordingly if not passing
        }
    }
}
