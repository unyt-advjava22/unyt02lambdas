// Fig. 17.9: Employee.java
// Employee class.
package com.eltonb.textbook.ex4;

public class Employee implements Comparable<Employee>
{
   private int id;
   private String firstName;
   private String lastName;
   private double salary; 
   private String department;
   
   // constructor 
   public Employee(int id, String firstName, String lastName,
      double salary, String department)
   {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.salary = salary;
      this.department = department;
   } 

   // set firstName
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   // get firstName
   public String getFirstName()
   {
      return firstName;
   }

   // set lastName
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   // get lastName
   public String getLastName()
   {
      return lastName;
   }

   // set salary
   public void setSalary(double salary)
   {
      this.salary = salary;
   }

   // get salary
   public double getSalary()
   {
      return salary;
   }

   // set department
   public void setDepartment(String department)
   {
      this.department = department;
   }

   // get department
   public String getDepartment()
   {
      return department;
   }

   // return Employee's first and last name combined
   public String getName()
   {
      return String.format("%s %s", getFirstName(), getLastName());
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   // return a String containing the Employee's information
   @Override
   public String toString() 
   {
      return String.format("%d: %-8s %-8s %8.2f   %s", getId(),
         getFirstName(), getLastName(), getSalary(), getDepartment());
   } // end method toString

   @Override
   public int compareTo(Employee other) {
      return this.id - other.id;
   }
} // end class Employee



/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
