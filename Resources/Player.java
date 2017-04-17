

/**
Class to create Player objects and record player stats
@authors Jacob Scarani and Bakari Wilkins
@version 2-22-2017
*/
public class Player{
   
   private int score = 0;  //score
   private String name = null;
   private int numRolls = 0;  // number of rolls player has taken during a given round
   private int[] scores = new int[13];
   
   /**
   Default constructor for Player class
   */
   public Player(String _name)
   {
      name = _name;
   }
   
   /**
   getScore - accessor for score
   @return score
   */
   public int getScore()
   {  
      return score;
   }
   
   /**
   getNumRolls - accessor for numRolls
   @return numRolls
   */
   public int getNumRolls()
   {  
      return numRolls;
   }   
   
   /**
   getName - accessor for name
   @return name
   */
   public String getName()
   {  
      return name;
   } 
   
   /**
   setScore - mutator for score
   @param _score - new score
   */
   public void setScore(int _score)
   {  
      score += _score;
   }
   
   /**
   setScoreArray - mutator for int[13] scores
   @param index - the index to be changed
   @param _score - the new score to be put in the array
   */
   public void setScoreArray(int index, int _score){
      scores[index] = _score;
   }
   
   /**
   setNumRolls - mutator for numRolls
   @param _numRolls
   */
   public void setNumRolls(int _numRolls){  
      numRolls = _numRolls;
   }
   
   /**
   scoreUpper - scoring method for the upper section
   @param _scoreChoice - the chosen option of upper section score (Ones, Twos, Threes, etc.) to be used
   @result - the score of the dice for the chosen option
   */
   public int scoreUpper(int scoreChoice, Die[] _dice){ //should we not have a die array specifically connected to this method, or this class?
      int total = 0;
      Die[] dice = _dice;
      for(Die singleDie : dice){
         if (singleDie.getValue() == scoreChoice){
            total += singleDie.getValue();
         }
      }
      scores[scoreChoice - 1] = total;
      
      return total;
   }
   
   public int scoreThreeOfKind(Die[] _dice){
      int total = 0;
      Die[] dice = _dice;
      for(Die singleDie : dice){
         total += singleDie.getValue();
      }
      
      scores[5] = total;
      return total;
   }
   
   public int scoreFourOfKind(Die[] _dice){
      int total = 0;
      Die[] dice = _dice;
      for(Die singleDie : dice){
         total += singleDie.getValue();
      }
      
      scores[6] = total;
      return total;
   }
   
   public int scoreFullHouse(){
      int total = 25;
      scores[7] = total;
      return total;
   }
//    
//    public int scoreSmallStraight(Die[] _dice){
//    }
//   
//    public int scoreLargeStraight(Die[] _dice){
//    
//    }
//    
//    public int scoreYahtzee(Die[] _dice){
//    }
//    
//    public int scoreChance(Die[] _dice){
//    }

}