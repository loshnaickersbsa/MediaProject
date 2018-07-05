public class TestingInstanceClassVariables
{

   public static void main (String [] args)
   {
   
      A.staticValue =99; //stack variable , loaded once at class load
      
      //this is known as a class variable as it lives on the stack
      
      
      System.out.println("staticValue = " +  A.staticValue);
      
      // A.publicValue ==33; //Produces a compiler error
      //   or publicValue     //it is an instave variable
        
        
      A myA = new A(); // only wasy to access the variable is to create an object.
      myA.publicValue =33; // You don't need to call a method to access the public variable
      
      // println 
      
      A myA2 = new A();
      myA2.setPrivateValue(5000);
      // here you must use a method to set the value
      
      
      A myA3 = new A();
      
      // Dont' use this to access the static values 
      //Used to demonstrate that a static variable can on the Stack can be seen 
      //by an object on the heap.
      
      System.out.println("Static Value A3=" + myA3.staticValue);
      myA3.staticValue++;
      A myA4 = new A();
      System.out.println("Static Value A3=" + myA4.staticValue);
      myA4.staticValue++;
      A myA5 = new A();
      System.out.println("Static Value A3=" + myA5.staticValue);
      
   }
   
} //end of class  TestingInstanceClassVariables 

class A
{

   private int privateValue;
   public int publicValue;
   public static int staticValue;
   
   public A()
   {
   }
   
   public int getPrivateValue()
   {
      return privateValue;
   }
   
   public void setPrivateValue(int privateValue)
   {
      this.privateValue = privateValue;
   }
   
} //end of class A default