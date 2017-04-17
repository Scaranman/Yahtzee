import javax.swing.*;
/**
Class to simulate individual die
@authors Jacob Scarani and Bakari Wilkins
@version 2-22-2017
*/
public class Die extends JLabel
{
   //Attributes
   private int value = 0;
   private boolean isRerolled;
   private Icon face1 = new ImageIcon("dice/dice-six-faces-one.png");
   private Icon face2 = new ImageIcon("dice/dice-six-faces-two.png");
   private Icon face3 = new ImageIcon("dice/dice-six-faces-three.png");
   private Icon face4 = new ImageIcon("dice/dice-six-faces-four.png");
   private Icon face5 = new ImageIcon("dice/dice-six-faces-five.png");
   private Icon face6 = new ImageIcon("dice/dice-six-faces-six.png");
   private Icon[] faces = {face1, face2, face3, face4, face5, face6};
   
   
   /**
   Default constructor for class Die
   */
   public Die()
   {
     isRerolled = true;
   }
   
   /**
   roll - generates and returns random int value between 1-6 for Die object
   @return generated value
   */
   public void roll()
   {
      int randNum =(int)((Math.random() * 6) + 1);
      value = randNum;
      
   }
   
   /**
   getIsRerolled - returns boolean value of isRerolled attribute
   @return value
   */
   public boolean getIsRerolled()
   {
      return isRerolled;
   }
   
   /**
   setIsRerolled - sets boolean value of isRerolled attribute
   @param _isRerolled - new isRerolled attribute
   */
   public void setIsRerolled(boolean _isRerolled)
   {
      isRerolled = _isRerolled;
   }
   
   /**
   getValue - returns int value of Die object
   @return value
   */
   public int getValue()
   {
      return value;
   }
   
   /**
   getFace - returns the die face icon for the current die value
   @return face icon
   */ 
   public Icon getFace()
   {
      Icon faceNum = null;
      switch(value)
      {
         case 1:  faceNum = faces[0];
                  break;
         case 2:  faceNum = faces[1];
                  break;
         case 3:  faceNum = faces[2];
                  break;
         case 4:  faceNum = faces[3];
                  break;
         case 5:  faceNum = faces[4];
                  break;
         case 6:  faceNum = faces[5];
                  break;      
         default: break;                                          
      }
      return faceNum;
   }
   
   /**
    * Roller class ... extends Thread class
    */
   public class RollerThread extends Thread {
      
      /**
       * run - main for the thread
       */
      public void run() {
         int whichFace = 0; // keeps track of current face
         
         // array of face icons
         
         try {
            for(int i=0;i<60;i++) {
               whichFace = (whichFace + 1) % 6;      // increment the faces ... 0 1 2 3 4 5 0 1 2 3 4 5 ...
             //  jlSelect.setIcon(faces[whichFace]); // Set the face
               sleep(100);    // kill a little time
                              // more time causes slower rolling
            }
         }
         catch(InterruptedException ie) {
            // do nothing
            // When interrupted, just return, killing the thread
         }
      }
   }
}