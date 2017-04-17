import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
Main class; contains GUI and Game info
@authors Jacob Scarani and Bakari Wilkins
@version 2-22-2017*/
public class YahtzeeFrameV2 extends JFrame implements ActionListener{
   
   ////////////////////////// Attributes ////////////////////////////   
   //Player array
   private YahtzeeFrameV2 yf;
   Player[] players = new Player[2];

   // Current Player
   int currentPlayer = 0;

   // Strings
   private String reroll = null;
   
   //Constants
   public final int MAX_ROUNDS = 13;
   // public final int UPPER_BONUS = 35;
//    public final int YAHTZEE_BONUS = 50;
   

   // Integers
   private int score1 = 0; //I think the score needs to be an int array, so that we can differentiate between the different
   private int score2 = 0; //kinds of scores and then calculate bonuses
   private int numPlayers = 0;
   private int playerNum = 0;
   private int numRounds = 0;
   private int numRolls = 0;
   private int playerTurn = 1;
   
   // Booleans
   private boolean rerolled = false;
   private boolean isUpper;
   private boolean isGame;
   private boolean upperBonus = false;
   private boolean yahtzeeBonus = false;
   private boolean turnEnd = false;
   
   // Array of Class Player
   //Player[] players = null;
   
   // JFrame Items
   
   // JPanels
   private JPanel jpDice = new JPanel();
   private JPanel jpSection = new JPanel(new GridLayout(0, 2));
   private JPanel jpSections = new JPanel(new GridLayout(0, 1));
   private JPanel jpUpper = new JPanel();
   private JPanel jpLower = new JPanel();
   private JPanel jpInfo = new JPanel();
   private JPanel jpSouthUpper = new JPanel(new GridLayout(0, 2));
   private JPanel jpSouthLower = new JPanel();
   private JPanel jpSouth = new JPanel(new GridLayout(0, 1));
   private JPanel jpCenter = new JPanel(new GridLayout(0,1));
   private JPanel jpButtons = new JPanel();
   private JPanel jpScore = new JPanel();
   private JPanel jpReroll = new JPanel();
   
   // JCheckBox Array
   private JCheckBox[] diceReroll = new JCheckBox[5];
   
   // JRadioButton Arrays
   private JRadioButton[] section = new JRadioButton[2];
   private JRadioButton[] upper = new JRadioButton[6];
   private JRadioButton[] lower = new JRadioButton[7];
   
   // JMenus & JMenuBar
   private JMenuBar jmbYahtzee = new JMenuBar();
   private JMenu jmHelp = new JMenu("Help");
   private JMenu jmFile = new JMenu("File");
   
   // JMenuItems
   private JMenuItem jmiAbout = new JMenuItem("About");
   private JMenuItem jmiHelp = new JMenuItem("Scoring");
   
   private JMenuItem jmiNew = new JMenuItem("New Game");
   private JMenuItem jmiQuit = new JMenuItem("Quit");
   
   // JButtons
   private JButton jbReroll = new JButton("Reroll");
   private JButton jbRoll = new JButton("Roll");
   private JButton jbNext = new JButton("Next Turn");
   private JButton jbScoreIt = new JButton("Score It");
   
   // JCheckBox Array for dice reroll
   private JCheckBox[] jcbDice = new JCheckBox[5];
      
   // JLabels
   private JLabel jlAces = new JLabel("Aces");
   private JLabel jlTwos = new JLabel("Twos");
   private JLabel jlThrees = new JLabel("Threes");
   private JLabel jlFours = new JLabel("Fours");
   private JLabel jlFives = new JLabel("Fives");
   private JLabel jlSixes = new JLabel("Sixes");
   
   private JLabel jlThreeKind = new JLabel("Three of a Kind");
   private JLabel jlFourKind = new JLabel("Four of a Kind");
   private JLabel jlFull = new JLabel("Full House");
   private JLabel jlSmlStraight = new JLabel("Small Straight");
   private JLabel jlLgStraight = new JLabel("Large Straight");
   private JLabel jlYahtzee = new JLabel("Yahtzee");
   private JLabel jlChance = new JLabel("Chance");
   
   private JLabel jlSection = new JLabel("Section");
   private JLabel jlUpper = new JLabel("Upper Section", JLabel.RIGHT);
   private JLabel jlLower = new JLabel("Lower Section", JLabel.RIGHT);
   
   private JLabel[] jldice = new JLabel[5];
      
   private JLabel jlTurn = new JLabel("____'s Turn");
   private JLabel jlRound = new JLabel("Round: " + numRounds);
   
   private JLabel jlScore1 = new JLabel("Player 1 Score: " + score1);
   private JLabel jlScore2 = new JLabel("Player 2 Score: " + score2);
   private JLabel[] jlScores = new JLabel[]{jlScore1, jlScore2};
   
   private JLabel jlReroll = new JLabel("Rerolled Available: ");
   private JLabel jlYesNo = new JLabel("Yes");
   private JLabel jlRoll = new JLabel("Roll: " + numRolls);
   
   private ButtonGroup bgUpper = new ButtonGroup();
   private ButtonGroup bgLower = new ButtonGroup();
   
   private Die[] dice = new Die[5];
   
   private int[] dieValue = new int[5];
   
   /**
   Main Method
   */
   public static void main(String[] args){
      YahtzeeFrameV2 yf = new YahtzeeFrameV2();
   }
   
   /**
   Default constructor for class YahtzeeFrameV2 - sets up JFrame and instantiates new game
   */
   public YahtzeeFrameV2(){
   
      jldice[0] = new JLabel("Die 1: ");
      jldice[1] = new JLabel("Die 2: ");
      jldice[2] = new JLabel("Die 3: ");
      jldice[3] = new JLabel("Die 4: ");
      jldice[4] = new JLabel("Die 5: ");
      
      // Add JMenuItems jmiNew & jmiQuit to the Jmenu jmFile
      jmFile.add(jmiNew);
      jmFile.add(jmiQuit);
      
      // Add JmenuItems jmiHelp & jmiAbout to the JMenu jmFile
      jmHelp.add(jmiHelp);
      jmHelp.add(jmiAbout);
      
      // add both of the JMenus to the JMenuBar jmbYahtzee
      jmbYahtzee.add(jmFile);
      jmbYahtzee.add(jmHelp);
      
      // Set jmbYahtzee as the jFrame's menu bar
      setJMenuBar(jmbYahtzee);     
      
      // Add the game information to the jPanel jpInfo
      jpInfo.add(jlTurn);
      jpInfo.add(new JLabel("   "));
      jpInfo.add(jlScore1);
      jpInfo.add(new JLabel("   "));
      jpInfo.add(jlScore2);
      jpInfo.add(new JLabel("   "));
      jpInfo.add(jlRound);
      jpInfo.add(new JLabel("   "));
      jpInfo.add(jlRoll);
      jpInfo.add(new JLabel("   "));
      jpInfo.add(jlReroll);
      jpInfo.add(jlYesNo);
      
      // Check to see if the Text in jlYesNo is "yes" or "No" and turn it Red if No or Green if Yes
      if(jlYesNo.getText() == "No"){
         jlYesNo.setForeground(Color.RED);
      }
      else{
         jlYesNo.setForeground(Color.GREEN);
      }
      
      // Add jpInfo to the Northern Region of the JFrame
      add(jpInfo, BorderLayout.NORTH);
      
      // Create an Array of Dice
      for(int i=0; i< dice.length; i++){
         dice[i] = new Die();
      }
      
      // Add each of the dice to the JPanel jpDice
      jpDice.add(jldice[0]);
   //       jpDice.add(new JLabel(String.valueOf(dieValue[0])));
      jpDice.add(new JLabel("   "));
      jpDice.add(jldice[1]);
   //       jpDice.add(new JLabel(String.valueOf(dieValue[1])));
      jpDice.add(new JLabel("   "));
      jpDice.add(jldice[2]);
   //       jpDice.add(new JLabel(String.valueOf(dieValue[2])));
      jpDice.add(new JLabel("   "));
      jpDice.add(jldice[3]);
   //       jpDice.add(new JLabel(String.valueOf(dieValue[3])));
      jpDice.add(new JLabel("   "));
      jpDice.add(jldice[4]);
   //       jpDice.add(new JLabel(String.valueOf(dieValue[4])));
      
      // add a JTextBox for each die 
      for(int i = 0; i < diceReroll.length; i++){
         diceReroll[i] = new JCheckBox();
         jpReroll.add(diceReroll[i]);
      }
      
      // Add JButtons jbRoll, jbReroll, and jbNext to the JPanel jpButtons
      jpButtons.add(jbRoll);
      jpButtons.add(jbReroll);
      jpButtons.add(jbNext);
      
      // Add the JPanels jpDice, jpreroll, and jpButtons to the JPanel, jpCenter
      jpCenter.add(jpDice);
      jpCenter.add(jpReroll);
      jpCenter.add(jpButtons);
      
      // add jpCenter to the Center region
      add(jpCenter, BorderLayout.CENTER);
      
      // Create button groups bgSection, bgUpper, and bgLower
      ButtonGroup bgSection = new ButtonGroup();
      
      // Create JRadioButtons for each JPanel and add them to their corresponding ButtonGroup
      for(int i=0; i<2; i++){
         section[i] = new JRadioButton();
         bgSection.add(section[i]);
      }
      for(int i=0; i<6; i++){
         upper[i] = new JRadioButton();
         bgUpper.add(upper[i]);
      }
      for(int i=0; i<7; i++){
         lower[i] = new JRadioButton();
         bgLower.add(lower[i]);
      }
      
      // add JLabels and JradioButtons to jpSection
      jpSection.add(jlUpper);
      jpSection.add(section[0]);
      jpSection.add(jlLower);
      jpSection.add(section[1]);
      jpSection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      
      // add JLabels and JradioButtons to jpUpper
      jpUpper.add(jlAces);
      jpUpper.add(upper[0]);
      jpUpper.add(jlTwos);
      jpUpper.add(upper[1]);
      jpUpper.add(jlThrees);
      jpUpper.add(upper[2]);
      jpUpper.add(jlFours);
      jpUpper.add(upper[3]);
      jpUpper.add(jlFives);
      jpUpper.add(upper[4]);
      jpUpper.add(jlSixes);
      jpUpper.add(upper[5]);
      
      // add JLabels and JradioButtons to jpLower
      jpLower.add(jlThreeKind);
      jpLower.add(lower[0]);
      jpLower.add(jlFourKind);
      jpLower.add(lower[1]);
      jpLower.add(jlFull);
      jpLower.add(lower[2]);
      jpLower.add(jlSmlStraight);
      jpLower.add(lower[3]);
      jpLower.add(jlLgStraight);
      jpLower.add(lower[4]);
      jpLower.add(jlYahtzee);
      jpLower.add(lower[5]);
      jpLower.add(jlChance);
      jpLower.add(lower[6]);
      
      // Set the border to for jpUpper and jpLower
      jpUpper.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      jpLower.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      
      // add jpUpper and jpLower to jpSections
      jpSections.add(jpUpper);
      jpSections.add(jpLower);
      
      // add jpSection and jpSection to jpSouthUpper
      jpSouthUpper.add(jpSection);
      jpSouthUpper.add(jpSections);
      
      // add jbScoreIt to jpSouthLower
      jpSouthLower.add(jbScoreIt);
      
      // add jpSouthUpper & jpSouthLower to jpSouth
      jpSouth.add(jpSouthUpper);
      jpSouth.add(jpSouthLower);
      
      // add jpSouth to the South region
      add(jpSouth, BorderLayout.SOUTH);
      
      // add ActionListeners to the JMenuItems
      jmiHelp.addActionListener(this);      
      jmiAbout.addActionListener(this);
      jmiNew.addActionListener(this);
      jmiQuit.addActionListener(this);
      
      section[0].setSelected(true);
      for(int i=0; i<7; i++){
         lower[i].setEnabled(false);
      }
      section[0].addActionListener(this);
      section[1].addActionListener(this);
      
      jbRoll.addActionListener(this);
      jbReroll.addActionListener(this);
      jbNext.addActionListener(this);
      jbScoreIt.addActionListener(this);
      
      // JFrame Setup
      setTitle("Yahtzee");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(1300, 300);
      setVisible(true);
      pack();
      
      // Start the game
      Gameplay game = new Gameplay();
      
   }
   
   /**
   actionPerformed - Listens and responds to events
   @param e - the ActionEvent to be heard
   */
   public void actionPerformed(ActionEvent e){
      if(e.getActionCommand().equals("About")){
         doAbout();
      }
      else if(e.getActionCommand().equals("Scoring")){
         doHelp();
      }
      else if(e.getActionCommand().equals("New Game")){
         doNew();
      }
      else if(e.getActionCommand().equals("Score It")){
         doScore();
      }
      else if(e.getSource().equals(section[0])){
         upper[0].setSelected(true);
         for(int i=0; i<6; i++){
            upper[i].setEnabled(true);
         }
         for(int i=0; i<7; i++){
            lower[i].setEnabled(false);
         }
         bgLower.clearSelection();
      }
      else if(e.getSource().equals(section[1])){
         lower[0].setSelected(true);
         for(int i=0; i<7; i++){
            lower[i].setEnabled(true);
         }
         for(int i=0; i<6; i++){
            upper[i].setEnabled(false);
         }
         bgUpper.clearSelection();
      }
      else if(e.getSource().equals(jbReroll)){
         doReroll();
      }
      else if(e.getSource().equals(jbRoll)){
         doRoll();
      }
      else if(e.getSource().equals(jbNext)){
         doNext();
      }
      else{
         System.exit(0);
      }
   }
   
   /**
   doHelp - opens a text document called scoring.txt if the user(s) click Help > Scoring
   */
   public void doHelp(){
      try{
         Process proc = Runtime.getRuntime().exec("notepad scoring.txt");
      }
      catch(IOException e){
         e.printStackTrace();
      }
   }
   
   /**
   doNew - instantiates a new game
   */
   public void doNew(){
      yf = new YahtzeeFrameV2();
   }
   
   /**
   doAbout - Gives information about the project and the creators if the user(s) click Help > About
   */
   public void doAbout(){
      JOptionPane.showMessageDialog(null, "Mini Project 1 - Yahtzee\n\nAuthors:\nJacob Scarani:   jes9762@rit.edu\nBakari Wilkins:   bxw2270@rit.edu");
   }
   
   /**
   doRoll - rolls ALL of the dice in the die array, displays the new values, increments numRolls,
   disables the roll button if the player has rolled three times that turn
   */
   public void doRoll(){
   //       System.out.println("roll");
      for(int i=0; i<5; i++){
         dice[i].roll();
         String text = "Die " + (i + 1) + ": " + dice[i].getValue();
         jldice[i].setText(text);
      }
      if(rerolled == false){
         jbReroll.setEnabled(true);
      }
      numRolls++;
      if(numRolls >= 3){
         jbRoll.setEnabled(false);
         jbReroll.setEnabled(false);
      }
      jlRoll.setText("Roll: " + numRolls);
      jbScoreIt.setEnabled(true);
   }
   
   /**
   doReroll - rolls the selected dice in the die array, displays the new values, increments numRolls,
   disables the roll button if the player has rolled three times that turn
   */
   public void doReroll(){
      for(int i=0; i<5; i++){
         if(diceReroll[i].isSelected() == true){
            dice[i].roll();
            String text = "Die " + (i + 1) + ": " + dice[i].getValue();
            jldice[i].setText(text);
         }
      }
      rerolled = true;
      jlYesNo.setText("No");
      jlYesNo.setForeground(Color.RED);
      jbReroll.setEnabled(false);
      numRolls++;
      if(numRolls >= 3){
         jbRoll.setEnabled(false);
      }
      jlRoll.setText("Roll: " + numRolls);
   }
   
   /*
   doNext - starts the next turn
   */
   public void doNext(){
      if(jbScoreIt.isEnabled()){
         JOptionPane.showMessageDialog(null, "You have not scored your dice yet.");
      }
      else{
         turnEnd = true;
      }   
   }
   
   /**
   inner class to carry out the back end Yahtzee gameplay
   @authors Jacob Scarani and Bakari Wilkins
   @version 3-22-2017
   */
   class Gameplay{
      
      /**
      Default constructor for inner class Gameplay
      */
      public Gameplay(){
      
      // reset Integers
         score1 = 0; 
         score2 = 0; 
         numPlayers = 0;
         playerNum = 0;
         numRounds = 12;
         numRolls = 0;
      
      // reset rerolled
         rerolled = false;
         jlYesNo.setText("Yes");
         jlYesNo.setForeground(Color.GREEN);
         
      // reset Booleans   
      //isUpper;
      //isGame; not sure if these need to be reset, how should these work?
         upperBonus = false;
         yahtzeeBonus = false;   
      
      //Setting each player's name
         for(int i = 0; i < 2; i++){
            String name = JOptionPane.showInputDialog(null, "Player " + (i+1) + "'s name");
            players[i] = new Player(name);
            jlScores[i].setText(players[i].getName() +"'s Score: ");
         }
      
         currentPlayer = 0;
      
      //Gameplay
         while(numRounds < MAX_ROUNDS){ //For 13 rounds
          
            for(int i=0;i < 2; i++){ //for 2 turns per round
               //reset every turn-sensitive attribute
               
               //update and display currentPlayer
               currentPlayer = i;
               jlTurn.setText(players[currentPlayer].getName() + "'s turn");
               
               //update and display numRounds
               jlRound.setText("Round: " + numRounds);
               
               //reset and display numRolls
               numRolls = 0;
               jlRoll.setText("Roll: " + numRolls);
               
               //reset reroll display
               jlYesNo.setForeground(Color.GREEN);
               jlYesNo.setText("Yes");
               
               //Reset buttons
               jbRoll.setEnabled(true);
               jbReroll.setEnabled(false);
               jbScoreIt.setEnabled(false);
               jbNext.setEnabled(false);
               rerolled = false;
               turnEnd = false;
               
               for(int k=0; k<5; k++){
                  jldice[k].setText("Die " + (k + 1) + ": " + 0);
               }
               
               wait:
                  while(true){
                     try{
                        Thread.sleep(1);
                     }   
                     catch(InterruptedException ie){
                     }   
                     if(turnEnd == true){
                        break wait;
                     }
                  }
               
               
             /**
                ***Turn Structure***
                1.  Player presses "roll" and rolls the dice
                2.  Roll results are displayed
                3.  Player chooses which dice are rerolled
                4.  Player presses "roll" and rolls selected dice
                5.  Repeat step 2
                6.  Repeat step 3
                7.  Repeat step 4
                8.  Repeat step 2
                9.  Repeat step 3
                10. Repeat step 4
                11. Repeat step 2
                12. Player chooses which section they want to score in
                13. Player chooses which scoring option they want
                14. The score is calculated and displayed, and is added to the player's total
             **/ 
             
            }//end turn
            
            numRounds++;
            
         }//end rounds 
         
         System.out.println("Game Over");
         System.out.println("Final Scores- " + players[0].getName() + ": " + players[0].getScore() + "  " + players[1].getName() + ": " + players[1].getScore());
         if(players[0].getScore() > players[1].getScore()){
            Player winner = players[0];
            JOptionPane.showMessageDialog(null, winner.getName() + " has won!!!");
         }
         else{
            Player winner = players[1];
            JOptionPane.showMessageDialog(null, winner.getName() + " has won!!!"); 
         }   
         
         System.exit(0);

      }// end "public Gameplay()"
   }
   
   /**
   doScore - checks which scoring option was chosen, and scores the dice according to the Yahtzee rules
   */
   public void doScore(){
      jbNext.setEnabled(true);
      jbRoll.setEnabled(false);
      int tempScore = 0;
      
      //Aces
      if(upper[0].isSelected() == true){
         for(int i=0; i<5; i++){
            if(dice[i].getValue() == 1){
               tempScore += dice[i].getValue();
            }
         }
         
         if(tempScore == 0){
            JOptionPane.showMessageDialog(null, "There are no dice of value 1");
         }
         else{
            jbScoreIt.setEnabled(false);
         }
         
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(0, tempScore);
      }
      
      //Twos
      else if(upper[1].isSelected() == true){
         for(int i=0; i<5; i++){
            if(dice[i].getValue() == 2){
               tempScore += dice[i].getValue();
            }
         }
         if(tempScore == 0){
            JOptionPane.showMessageDialog(null, "There are no dice of value 2");
         }
         else{
            jbScoreIt.setEnabled(false);
         }
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(1, tempScore);
      }
      
      //Threes
      else if(upper[2].isSelected() == true){
         for(int i=0; i<5; i++){
            if(dice[i].getValue() == 3){
               tempScore += dice[i].getValue();
            }
         } 
         if(tempScore == 0){
            JOptionPane.showMessageDialog(null, "There are no dice of value 3");
         }
         else{
             jbScoreIt.setEnabled(false);
         }
         
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(2, tempScore);
      }
      
      //Fours
      else if(upper[3].isSelected() == true){
         for(int i=0; i<5; i++){
            if(dice[i].getValue() == 4){
               tempScore += dice[i].getValue();
            }
         } 
         if(tempScore == 0){
            JOptionPane.showMessageDialog(null, "There are no dice of value 4");
         }
         else{
             jbScoreIt.setEnabled(false);
         }
         
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(3, tempScore);
      }
      
      //Fives
      else if(upper[4].isSelected() == true){
         for(int i=0; i<5; i++){
            if(dice[i].getValue() == 5){
               tempScore += dice[i].getValue();
            }
         } 
         if(tempScore == 0){
            JOptionPane.showMessageDialog(null, "There are no dice of value 5");
         }
         else{
             jbScoreIt.setEnabled(false);
         }
         
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(4, tempScore);
      }
      
      //Sixes
      else if(upper[5].isSelected() == true){
         for(int i=0; i<5; i++){
            if(dice[i].getValue() == 6){
               tempScore += dice[i].getValue();
            }
         } 
         if(tempScore == 0){
            JOptionPane.showMessageDialog(null, "There are no dice of value 6");
         }
         else{
             jbScoreIt.setEnabled(false);
         }
         
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(5, tempScore);
      }
      
      //Three of a kind
      else if(lower[0].isSelected() == true){
         int count = 0;
         for(int i=0; i <5; i++){  //for each die...
            count = 0; // (reset the counter between each die)
            for(int k=0; k<5; k++){ //compare to each other die...
               if(dice[i].getValue() == dice[k].getValue()){ //if the two different dice have the same value...
                  count++; //increment the counter
                  if(count == 3){ // if different dice have the same value three times
                     i = 5; // end the loop
                     k = 5;
                     for(int j=0; j<5; j++){
                        tempScore += dice[j].getValue(); // set the tempScore to the sum of all dice
                     }
                  }
                  
               }
            }
         }
         if(count < 3){
            JOptionPane.showMessageDialog(null, "There are no 3 dice of equal value");
         }
         else{
            players[currentPlayer].setScore(tempScore);
            jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
            //players[currentPlayer].setScoreArray(6, tempScore);
            
             jbScoreIt.setEnabled(false);
         
         }
      }
      
      //Four of a kind
      else if(lower[1].isSelected() == true){
         int count = 0;
         for(int i=0; i <5; i++){
            count = 0;
            for(int k=0; k<5; k++){
               if(dice[i].getValue() == dice[k].getValue()){
                  count++;
                  if(count == 4){
                     i = 5; // end the loop
                     for(int j=0; j<5; j++){
                        tempScore += dice[j].getValue();
                     }
                  }
                  
               }
            }
         }
         if(count < 4){
            JOptionPane.showMessageDialog(null, "There are no 4 dice of equal value");
         }
         else{
            players[currentPlayer].setScore(tempScore);
            jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
            //players[currentPlayer].setScoreArray(7, tempScore);
            jbScoreIt.setEnabled(false);
         }     
      }
      
      //Full House
      else if(lower[2].isSelected() == true){
         int count = 0;
         int checker = 0;
         int firstKind = 0; // the first set of matching die values
         for(int i=0; i <5; i++){
            count = 0;
            for(int k=0; k<5; k++){
               if(dice[i].getValue() == dice[k].getValue()){
                  count++;
                  if(count == 3){
                     checker++;
                     firstKind = dice[k].getValue();
                     i = 5;
                     k = 5;
                  }
                  
               }
            }
         }
         for(int i=0; i <5; i++){
            count = 0;
            for(int k=0; k<5; k++){
               if(dice[i].getValue() == dice[k].getValue()){
                  count++;
                  if(count == 2 && (dice[k].getValue() != firstKind)){
                     checker++;
                     i = 5;
                     k = 5;
                  }
                  
               }
            }
         }
         if(checker == 2){
            tempScore = 25;
            players[currentPlayer].setScore(tempScore);
            jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
            //players[currentPlayer].setScoreArray(8, tempScore);
            jbScoreIt.setEnabled(false);
         }
         else{
            JOptionPane.showMessageDialog(null, "There is not a full house");
         }
         
      }
      
      //Small Straight
      else if(lower[3].isSelected() == true){
         int checker = 0;
         int[] diceValues = new int[5];
         for(int i=0;i<5;i++){
            diceValues[i] = dice[i].getValue();
         }
         Arrays.sort(diceValues);
         for(int i=0; i <4; i++){
            if(diceValues[i] == (diceValues[i+1]-1)){
               checker++;
            }
         }
         if(checker >= 3){
            tempScore = 40;
            players[currentPlayer].setScore(tempScore);
            jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
            //players[currentPlayer].setScoreArray(9, tempScore);
            jbScoreIt.setEnabled(false);
         }
         else{
            JOptionPane.showMessageDialog(null, "There are not 4 or more sequential dice");
         }
      }
      
      //Large Straight
      else if(lower[4].isSelected() == true){
         int checker = 0;
         int[] diceValues = new int[5];
         for(int i=0;i<5;i++){
            diceValues[i] = dice[i].getValue();
         }
         Arrays.sort(diceValues);
         for(int i=0; i <4; i++){
            if(diceValues[i] == (diceValues[i+1]-1)){
               checker++;
            }
         }
         if(checker == 4){
            tempScore = 50;
            players[currentPlayer].setScore(tempScore);
            jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
            //players[currentPlayer].setScoreArray(10, tempScore);
            jbScoreIt.setEnabled(false);
         }
         else{
            JOptionPane.showMessageDialog(null, "There are not 5 sequential dice");
         }
      }
      
      //Yahtzee
      else if(lower[5].isSelected() == true){
         int checker = 0;
         for(int i=0; i <4; i++){
            if(dice[i].getValue() == dice[i+1].getValue()){
               checker++;
            }
         }
         if(checker == 4){
            tempScore = 50;
            players[currentPlayer].setScore(tempScore);
            jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
            //players[currentPlayer].setScoreArray(11, tempScore);
            jbScoreIt.setEnabled(false);
         }
         else{
            JOptionPane.showMessageDialog(null, "All 5 dice are not equal in value");
         }  
      }
      
      //Chance
      else if(lower[6].isSelected() == true){
         for(int i=0; i <5; i++){
            tempScore += dice[i].getValue();
         }
         players[currentPlayer].setScore(tempScore);
         jlScores[currentPlayer].setText(players[currentPlayer].getName() +"'s Score: " + players[currentPlayer].getScore());
         //players[currentPlayer].setScoreArray(12, tempScore);
         jbScoreIt.setEnabled(false);
      }
      
      
   }
}