//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Gradebooktester!
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
 * This class tests the GradeBook class's implementation
 *
 */
public class GradebookTester {

    /**
     * Tests the GradeBook constructor
     *
     * @return true if the GradeBook constructor works accordingly
     */
    public static boolean constructorTester() {
        //create valid gradebook
        Gradebook gradebook = new Gradebook("A", 90.0);

        String expectedCourse = "A";
        double expectedPassGrade = 90.0;

        //check that the two instance fields have been initialized correctly
        if (!gradebook.course.equals(expectedCourse)) return false;
        if (gradebook.PASSING_GRADE != expectedPassGrade) return false;

        try { //test for empty course name
            new Gradebook("", 90.0);

            return false; //no exception was thrown
        } catch (IllegalArgumentException e) { //check for correct Exception
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { //any other exception is not good
            e.printStackTrace();
            return false;
        }

        try { //test for null course name
            new Gradebook(null, 90.0);

            return false; //no exception was thrown
        } catch (IllegalArgumentException e) { //check for correct Exception
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { //any other exception is not good
            e.printStackTrace();
            return false;
        }

        try { //test for out of bounds grade
            new Gradebook("A", 101.0);

            return false; //no exception was thrown
        } catch (IllegalArgumentException e) { //check for correct Exception
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { //any other exception is not good
            e.printStackTrace();
            return false;
        }

        return true; //all tests passed
    }

    /**
     * Tests the isEmpty(), size(), and addStudent() methods
     *
     * @return true if the following methods work accordingly
     */
    public static boolean isEmptySizeAddTester() {
        Gradebook gradebook = new Gradebook("A", 90.0);
        //students to add
        StudentRecord student1 = new StudentRecord("Max", "A",70.0);
        StudentRecord student2 = new StudentRecord("Adam", "B",91.0);
        StudentRecord student3 = new StudentRecord("Jay", "C",60.0);
        StudentRecord student4 = new StudentRecord("Mike", "D",80.0);
        StudentRecord student5 = new StudentRecord("Dan", "E",70.0);
        StudentRecord student6 = new StudentRecord("Dave", "F",45.0);

        //nodes for the expected tree
        BSTNode<StudentRecord> internalNode1 = new BSTNode<StudentRecord>(student2);
        BSTNode<StudentRecord> internalNode2 = new BSTNode<StudentRecord>(student6);
        BSTNode<StudentRecord> leafNode1 = new BSTNode<StudentRecord>(student1);
        BSTNode<StudentRecord> leafNode2 = new BSTNode<StudentRecord>(student3);
        BSTNode<StudentRecord> leafNode3 = new BSTNode<StudentRecord>(student5);

        //create tree with nodes matching diagram
        BSTNode<StudentRecord> gradeBookTree = new BSTNode<StudentRecord>(student4, internalNode1, internalNode2);
        gradeBookTree.getLeft().setLeft(leafNode1);
        gradeBookTree.getLeft().setRight(leafNode2);
        gradeBookTree.getRight().setLeft(leafNode3);


        if (!gradebook.isEmpty()) return false; //initial gradeBook should be empty

        {//test that size is updated and trees match
            int expectedSize = 6;

            //add students
            gradebook.addStudent(student4);
            gradebook.addStudent(student2);
            gradebook.addStudent(student6);
            gradebook.addStudent(student1);
            gradebook.addStudent(student3);
            gradebook.addStudent(student5);

            if (gradebook.size() != expectedSize) return false; //size should be the same

            //trees should match
            if (!gradebook.equalBST(gradeBookTree)) return false;
        }

        try { //test adding existing studentRecord
            gradebook.addStudent(student2); //added existing student

            return false; //no exception was thrown
        } catch (IllegalStateException e) { //check for correct Exception
            if (e.getMessage() == null || e.getMessage().isBlank())
                return false;
        } catch (Exception e) { //any other exception is not good
            e.printStackTrace();
            return false;
        }
        int expectedSize = 6;
        if (gradebook.size() != expectedSize) return false;

        return true; //all tests passed
    }

    /**
     * Tests the toString() method
     *
     * @return true if the toString method works accordingly
     */
    public static boolean toStringTester() {
        Gradebook gradebook = new Gradebook("A", 90.0);
        //students to add
        StudentRecord student1 = new StudentRecord("Max", "A", 70.0);
        StudentRecord student2 = new StudentRecord("Adam", "B", 91.0);
        StudentRecord student3 = new StudentRecord("Jay", "C", 60.0);
        StudentRecord student4 = new StudentRecord("Mike", "D", 80.0);
        StudentRecord student5 = new StudentRecord("Dan", "E", 70.0);


        //add students in order
        gradebook.addStudent(student4);
        gradebook.addStudent(student2);
        gradebook.addStudent(student5);
        gradebook.addStudent(student1);
        gradebook.addStudent(student3);

        //test matching string
        String expectedString = student1.toString() + "\n" + student2.toString() + "\n" + student3.toString() + "\n" + student4.toString() + "\n" + student5.toString().toString() + "\n";
        if (!expectedString.equals(gradebook.toString())) return false;

        return true; //all tests passed
    }

    /**
     * Tests the prettyString() method
     *
     * @return true if the prettyString method works accordingly
     */
    public static boolean prettyStringTester() {
        Gradebook gradebook = new Gradebook("A", 90.0);
        //students to add
        StudentRecord student1 = new StudentRecord("Max", "A",70.0);
        StudentRecord student2 = new StudentRecord("Adam", "B",91.0);
        StudentRecord student3 = new StudentRecord("Jay", "C",60.0);
        StudentRecord student4 = new StudentRecord("Mike", "D",80.0);
        StudentRecord student5 = new StudentRecord("Dan", "E",70.0);

        //add students in order
        gradebook.addStudent(student4);
        gradebook.addStudent(student2);
        gradebook.addStudent(student5);
        gradebook.addStudent(student1);
        gradebook.addStudent(student3);


        //test matching string
        String expectedString = " " + student5 + "\n" + student4 + "\n" + "  " + student3 + "\n" + " " + student2 + "\n" + "  " + student1 + "\n";
        if (!expectedString.equals(gradebook.prettyString())) return false;

        return true; //all test passed
    }

    /**
     * Tests the lookUp() method
     *
     * @return true if the lookUp method works accordingly
     */
    public static boolean lookupTester () {
        Gradebook gradebook = new Gradebook("A", 90.0);
        //students to add
        StudentRecord student1 = new StudentRecord("Max", "A",70.0);
        StudentRecord student2 = new StudentRecord("Adam", "B",91.0);
        StudentRecord student3 = new StudentRecord("Jay", "C",60.0);
        StudentRecord student4 = new StudentRecord("Mike", "D",80.0);
        StudentRecord student5 = new StudentRecord("Dan", "E",70.0);

        //add students in order
        gradebook.addStudent(student4);
        gradebook.addStudent(student2);
        gradebook.addStudent(student5);
        gradebook.addStudent(student1);
        gradebook.addStudent(student3);

        {//lookup performs accordingly
            StudentRecord expectedRecord = student2;
            if (!gradebook.lookup("B").equals(expectedRecord)) return false;
        }
        {//different email but other matching qualities
            StudentRecord expectedRecord = new StudentRecord("B", "Max", 70.0);
            if (gradebook.lookup("A").equals(expectedRecord)) return false;
        }

        {//same email but different qualities
            StudentRecord expectedRecord = new StudentRecord("A", "Adam", 91.0);
            if (gradebook.lookup("A").equals(expectedRecord)) return false;
        }

        {//lookup on non-existent node
            if (gradebook.lookup("H") != null) return false;
        }

        {//test implementation that ignores right nodes
            StudentRecord expectedRecord = student5;
            if (!gradebook.lookup("E").equals(expectedRecord)) return false;

        }

        return true; //all tests passed
    }

        /**
         * Tests the getMin method
         *
         * @return true if the getMin method gets the correct minimum value
         */
        public static boolean getMinTester () {
        Gradebook gradebook = new Gradebook("A", 90.0);

        //students to add
        StudentRecord student1 = new StudentRecord("Max", "A",70.0);
        StudentRecord student2 = new StudentRecord("Adam", "B",91.0);
        StudentRecord student3 = new StudentRecord("Jay", "C",60.0);
        StudentRecord student4 = new StudentRecord("Mike", "D",80.0);
        StudentRecord student5 = new StudentRecord("Dan", "E",40.0);


        //add students
        gradebook.addStudent(student4);
        gradebook.addStudent(student2);
        gradebook.addStudent(student5);
        gradebook.addStudent(student1);
        gradebook.addStudent(student3);

        //test for minimum
        if (!gradebook.getMin().equals(student1)) return false;

        return true; //all tests passed
    }

    /**
     * Tests the removeStudent() method
     *
     * @return true if the removeStudent method removes StudentRecords correctly
     */
    public static boolean removeStudentTester() {
        Gradebook gradebook = new Gradebook("A", 90.0);

        //students to add
        StudentRecord student1 = new StudentRecord("Max", "A", 70.0);
        StudentRecord student2 = new StudentRecord("Adam", "B", 91.0);
        StudentRecord student3 = new StudentRecord("Jay", "C", 60.0);
        StudentRecord student4 = new StudentRecord("Mike", "D", 80.0);
        StudentRecord student5 = new StudentRecord("Dan", "F", 40.0);
        StudentRecord student6 = new StudentRecord("Dane", "G", 69.0);

        //create tree
        gradebook.addStudent(student4);
        gradebook.addStudent(student2);
        gradebook.addStudent(student5);
        gradebook.addStudent(student1);
        gradebook.addStudent(student3);


        {//remove leaf node
            gradebook.removeStudent("A");
            if (gradebook.lookup("A") != null) return false; //check if it still exists

            //check the size
            int expectedSize = 4;
            if (gradebook.size() != expectedSize) return false;

            String expectedString = student2.toString() + "\n" + student3.toString() + "\n" + student4.toString() + "\n" + student5.toString() + "\n";
            if (!gradebook.toString().equals(expectedString)) return false;

        }

        {//remove internal node with one child
            //add back student that was removed
            gradebook.addStudent(student1);
            gradebook.addStudent(student6);

            //remove student
            gradebook.removeStudent("B");
            if (gradebook.lookup("B") != null) return false;

            //check that the size is updated
            int expectedSize = 5;
            if (gradebook.size() != expectedSize) return false;

            String expectedString = student1.toString() + "\n" + student3.toString() + "\n" + student4.toString() + "\n" + student5.toString().toString() + "\n" + student6.toString() + "\n";
            if (!gradebook.toString().equals(expectedString)) return false;
        }

        {//remove node that does not exist
            try {
                gradebook.removeStudent("Dan");
                return false;
            }catch (NoSuchElementException e) {
                if (e.getMessage() == null || e.getMessage().isBlank())
                    return false;
            } catch (Exception e) { //any other exception is not good
                e.printStackTrace();
                return false;
            }

            //check that size remained the same
            int expectedSize = 5;
            if (gradebook.size() != expectedSize) return false;
        }

        {//remove node with 2 children

            //add removed node back
            gradebook.addStudent(student2);

            gradebook.removeStudent("D"); //remove root node with 2 children
            if (gradebook.lookup("D") != null) return false;

            //create expected tree after removing node with 2 children
            BSTNode<StudentRecord> internalNode1 = new BSTNode<StudentRecord>(student1);
            BSTNode<StudentRecord> internalNode2 = new BSTNode<StudentRecord>(student3);
            BSTNode<StudentRecord> leafNode2 = new BSTNode<StudentRecord>(student2);
            BSTNode<StudentRecord> leafNode3 = new BSTNode<StudentRecord>(student6);

            //create tree
            BSTNode<StudentRecord> gradeBookTree = new BSTNode<StudentRecord>(student5, internalNode2, leafNode3);
            gradeBookTree.getLeft().setLeft(internalNode1);
            gradeBookTree.getLeft().getLeft().setRight(leafNode2);

            //expectedSize
            int expectedSize = 5;
            if (gradebook.size() != expectedSize) return false;

            if (!gradebook.equalBST(gradeBookTree)) return false; //trees should match

        }
            return true; //all tests past

        }

        /**
         * Tests the successor() method
         *
         * @return true if the successor method returns the correct StudentRecord
         */
        public static boolean successorTester () {

            Gradebook gradebook = new Gradebook("A", 90.0);

            //students to add
            StudentRecord student1 = new StudentRecord("Max", "A",70.0);
            StudentRecord student2 = new StudentRecord("Adam", "G",91.0);
            StudentRecord student3 = new StudentRecord("Jay", "C",60.0);
            StudentRecord student4 = new StudentRecord("Mike", "D",80.0);
            StudentRecord student5 = new StudentRecord("Dan", "E",70.0);
            StudentRecord student6 = new StudentRecord("Fat", "H", 40.0);

            //construct tree
            gradebook.addStudent(student4);
            gradebook.addStudent(student3);
            gradebook.addStudent(student2);
            gradebook.addStudent(student1);
            gradebook.addStudent(student5);

            { //test successor on leaf node
                StudentRecord expectedRecord = student3;
                if (!gradebook.successor(student1).equals(expectedRecord)) return false;
            }

            {//test successor on root node
                StudentRecord expectedRecord = student5;
                if (!gradebook.successor(student4).equals(expectedRecord)) return false;
            }

            {//test successor on node with no successor
                if (gradebook.successor(student2) != null) return false;
            }

            {//test non existent node
                if (gradebook.successor(student6) != null) return false;
            }


        return true; //all test passed
        }

        /**
         * Tests the iterator()
         *
         * @return true if the iterator method iterates appropriately
         */
        public static boolean iteratorTester() {
            //gradebook to iterate over
            Gradebook gradebook = new Gradebook("A", 90.0);

            String studentList = "";

            //students to add to gradebook
            StudentRecord student1 = new StudentRecord("Max", "A",70.0);
            StudentRecord student2 = new StudentRecord("Adam", "G",91.0);
            StudentRecord student3 = new StudentRecord("Jay", "C",60.0);
            StudentRecord student4 = new StudentRecord("Mike", "D",80.0);
            StudentRecord student5 = new StudentRecord("Dan", "E",70.0);

            //construct tree with all students
            gradebook.addStudent(student4);
            gradebook.addStudent(student3);
            gradebook.addStudent(student2);
            gradebook.addStudent(student1);
            gradebook.addStudent(student5);

            //expected string that is inorder
            String expected = "Max (A): 70.0\n"
                    + "Jay (C): 60.0\n"
                    + "Mike (D): 80.0\n"
                    + "Dan (E): 70.0\n"
                    + "Adam (G): 91.0\n";
            //iterate over every element in the gradebook
            for (StudentRecord student : gradebook) {
                studentList += student.toString() + "\n";
            }
            //should match expected
            return expected.equals(studentList);
        }

        /**
         * Tests the passingIterator() method
         *
         * @return true if the iterator method iterates appropriately
         */
        public static boolean passingIteratorTester() {
            Gradebook gradebook = new Gradebook("A", 90.0); //with 2 passing students
            Gradebook gradebook1 = new Gradebook("A", 90.0); //with 0 passing students
            //stringList to compare to
            String studentList = "";
            String studentList1 = "";
            //students for tree
            StudentRecord student1 = new StudentRecord("Max", "A", 70.0);
            StudentRecord student2 = new StudentRecord("Adam", "B", 91.0);
            StudentRecord student3 = new StudentRecord("Jay", "C", 60.0);
            StudentRecord student4 = new StudentRecord("Mike", "D", 90.0);
            StudentRecord student5 = new StudentRecord("Dan", "F", 40.0);


            gradebook.enablePassingGradeIterator(); //enable passingGrade

            {//tree with 2 passing students
                gradebook.addStudent(student1);
                gradebook.addStudent(student2);
                gradebook.addStudent(student3);
                gradebook.addStudent(student4);
                gradebook.addStudent(student5);

                String expectedString = "Adam (B): 91.0\n"
                        + "Mike (D): 90.0\n";

                for (StudentRecord student : gradebook) { //iterate and add
                    studentList += student.toString() + "\n";
                }

                if (!expectedString.equals(studentList)) { //should match the string
                    return false;
                }
            }

            { //test passingiterator with tree that has no passing students
                gradebook1.enablePassingGradeIterator(); //enable passingGrade for gradebook without any passing students

                //add all students who are not passing
                gradebook1.addStudent(student1);
                gradebook1.addStudent(student3);
                gradebook1.addStudent(student5);

                String expectedString = "";//string should be empty as there are students who dont pass

                for (StudentRecord student : gradebook1) { //iterate over gradebook1
                    studentList1 += student.toString() + "\n";
                }

                if (!expectedString.equals(studentList1)) { //should match expected
                    return false;
                }
            }
            return true;
        }

    public static void main(String [] args) {
        String printFormat = "%-29s %s\n";

        System.out.printf(printFormat, "constructorTester(): ", constructorTester());
        System.out.printf(printFormat, "isEmptySizeAddTester(): ", isEmptySizeAddTester());
        System.out.printf(printFormat, "toStringTester(): ", toStringTester());
        System.out.printf(printFormat, "prettyStringTester(): ", prettyStringTester());
        System.out.printf(printFormat, "lookupTester(): ", lookupTester());
        System.out.printf(printFormat, "getMinTester(): ", getMinTester());
        System.out.printf(printFormat, "successorTester(): ", successorTester());
        System.out.printf(printFormat, "removeStudentTester(): ", removeStudentTester());
        System.out.printf(printFormat, "iteratorTester(): ", iteratorTester());
        System.out.printf(printFormat, "passingIteratorTester(): ", passingIteratorTester());
    }

}
