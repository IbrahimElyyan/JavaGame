// -------------------------------------------------------
// Assignment (#4 Part B)
// Written by: (Ibrahim Elyyan)
// For COMP 248 Section (U) – Winter 2021
// --------------------------------------------------------

//Author: Ibrahim Elyyan

//Date: April 16, 2021

/* PURPOSE: This class contains the main method that will run the program. This contains
 * the methods from the Creature class and implements them.*/
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        // creatureNum is the amount of creatures, turn is the integer that determines who starts first, choice is what option the user chooses,
    	/* attackingChoice is what the number of the creature that the user decides to attack and attack is the random integer 
        that dictates whether or not the creature attacks or gets attacked */
        // x is the index of the arrays in the switch case
        int creatureNum, turn, choice, attackingChoice, attack, x=0;
        // creatureName is the name that will be set to the creature
        String creatureName;
        Scanner key = new Scanner(System.in);
        // Welcome message
        System.out.println("*****************************************");
        System.out.println("       Welcome to the Battle Game");
        System.out.println("*****************************************");
        // Prompting the user for the amount of creatures
        System.out.print("How many creatures would you like to have (minimum 2, maximum 8)? ");
        creatureNum = key.nextInt();
        // Validating the amount
        while (creatureNum<2 || creatureNum>8) {
            key = new Scanner(System.in);
            System.out.println("\n*** Illegal number of creatures requested ***\n");
            System.out.print("How many creatures would you like to have (minimum 2, maximum 8)? ");
            creatureNum = key.nextInt();
        }
        // Creating a creature array to make the correct amount of creature objects and naming them
        Creature[] creature = new Creature[creatureNum];
        for (int i=0; i<creature.length; i++) {
            key = new Scanner(System.in);
            System.out.print("\nWhat is the name of creature " + (i+1) + "? ");
            creatureName = key.nextLine();
            creature[i] = new Creature();
            creature[i].setName(creatureName);
            System.out.println(creature[i]);
            System.out.println("Date created: " + creature[i].getDateCreated());
            System.out.println("Date died: Is still alive." );
        }

        // Creating an integer array which will be used to determine the sequence that the creatures will play in 
        int[] whoNext = new int[creatureNum];
        turn = (int)(Math.random()*creatureNum)+1;
        for(int i = 0; i<whoNext.length; i++) {
            whoNext[i] = turn;
            turn++;
            if (turn > creatureNum) {
                turn = 1;
            }
        }
        // A do while loop is used to keep playing the game until one creature remains alive
        do {
        	// Resetting the turn to the creature that went first if everyone had a turn
    		// Moving on to the next creature if the current creature is dead
        	while(true) {
        		if (x==whoNext.length) {
                  x=0;
              } else if (!creature[whoNext[x]-1].isAlive()) {
            	  x++;
              } else break;
        	}
            key = new Scanner(System.in);
            // Prompting the user for what option they want for each creature
            System.out.println("\nCreature #" + whoNext[x] + ": " + creature[whoNext[x]-1].getName()+", what do you want to do?");
            System.out.println("1. How many creatures are alive?");
            System.out.println("2. See my status");
            System.out.println("3. See status of all players");
            System.out.println("4. Change my name");
            System.out.println("5. Work for some food");
            System.out.println("6. Attack another creature (Warning! may turn against you)");
            System.out.print("Your choice please > ");
            choice = key.nextInt();
            // Validating the choice
            while(choice<1 || choice>6) {
                key = new Scanner(System.in);
                System.out.println("\nCreature #" + whoNext[x] + ": " + creature[whoNext[x]-1].getName()+", what do you want to do?");
                System.out.println("1. How many creatures are alive?");
                System.out.println("2. See my status");
                System.out.println("3. See status of all players");
                System.out.println("4. Change my name");
                System.out.println("5. Work for some food");
                System.out.println("6. Attack another creature (Warning! may turn against you)");
                System.out.print("Your choice please > ");
                choice = key.nextInt();
            }
            // Determining the correct output based on choice
            switch(choice) {
                case 1:
                    System.out.println("Number of creatures alive is "+ Creature.getNumStillAlive());
                    break;
                case 2:
                    System.out.println(creature[whoNext[x]-1]);
                    System.out.println("Date created: " + creature[whoNext[x]-1].getDateCreated());
                    System.out.println("Date died: Is still alive." );
                    break;
                case 3:
                    for(int j=0; j<creature.length; j++) {
                        System.out.println(creature[j]);
                        System.out.println("Date created: " + creature[j].getDateCreated());
                        if(creature[j].isAlive()) {
                            System.out.println("Date died: Is still alive." );
                        } else {
                            System.out.println("Date died: " + creature[j].getDateDied());
                        }
                        System.out.println();
                    }
                    break;
                case 4:
                    key = new Scanner(System.in);
                    System.out.println("Your name is currently \""+creature[whoNext[x]-1].getName()+ "\"");
                    System.out.print("What is the new name: ");
                    creatureName = key.nextLine();
                    creature[whoNext[x]-1].setName(creatureName);
                    break;
                case 5:
                    System.out.print("\nYour status before working for food:" + creature[whoNext[x]-1].showStatus() + ".... You earned ");
                    creature[whoNext[x]-1].earnFood();
                    System.out.println("\nYour status after working for food: " + creature[whoNext[x]-1].showStatus());
                    // Changing turn
                    x++;
                    // Resetting the turn to the creature that went first if everyone had a turn
                    if (x>=whoNext.length) {
                        x=0;
                    }
                    break;
                case 6:
                    // Prompting the user for who they want their creature to attack
                    System.out.print("\nWho do you want to attack? (enter a number from 1 to "+ creatureNum +" other than yourself("+whoNext[x]+")): ");
                    attackingChoice = key.nextInt();
                    // Validating attacking choice
                    while (true) {
                    	if(attackingChoice<1 || attackingChoice>creatureNum) {
                    		 key = new Scanner(System.in);
                             System.out.println("That creature does not exist. Try again ...\n");
                             System.out.print("\nWho do you want to attack? (enter a number from 1 to "+ creatureNum +" other than yourself("+whoNext[x]+")): ");
                             attackingChoice = key.nextInt();
                    	}
                    	else if(attackingChoice==whoNext[x]) {
                    		key = new Scanner(System.in);
                            System.out.println("Can't attack yourself silly! Try again ...\n");
                            System.out.print("\nWho do you want to attack? (enter a number from 1 to "+ creatureNum +" other than yourself("+whoNext[x]+")): ");
                            attackingChoice = key.nextInt();
                    	}
                    	else if(!creature[attackingChoice-1].isAlive()) {
                    		 key = new Scanner(System.in);
                             System.out.println("That creature is already dead. Try again ...\n");
                             System.out.print("\nWho do you want to attack? (enter a number from 1 to "+ creatureNum +" other than yourself("+whoNext[x]+")): ");
                             attackingChoice = key.nextInt();
                    	} else break;
                    }
                    // Determining the correct output based on attackingChoice and the information of each creature
                    if(!creature[whoNext[x]-1].firePowerCheck() && !creature[attackingChoice-1].firePowerCheck()) {
                        System.out.println("Lucky you, the odds were that the other player attacks you, but " + creature[attackingChoice-1].getName() + " doesn't have enough fire power to\n"
                                + "attack you! So is status quo!!");
                        x++;
                        break;
                    }
                    // Determining the probability that the user will attack
                    attack = (int)(Math.random()*2);
                    if(creature[whoNext[x]-1].firePowerCheck() && creature[attackingChoice-1].firePowerCheck()) {
                        if (attack == 0) {
                            System.out.println("..... OH NO!!! You are being attacked by " + creature[attackingChoice-1].getName() + "!");
                            System.out.println("Your status before being attacked:" + creature[whoNext[x]-1].showStatus());
                            creature[attackingChoice-1].attacking(creature[whoNext[x]-1]);
                            System.out.println("Your status after being attacked:" + creature[whoNext[x]-1].showStatus());
                            if(!creature[whoNext[x]-1].isAlive()) {
                                System.out.println("\n---->" + creature[whoNext[x]-1].getName() + " is dead");
                            }
                        } else {
                            System.out.println("\n..... You are attacking " + creature[attackingChoice-1].getName() + "!");
                            System.out.println("Your status before attacking:" + creature[whoNext[x]-1].showStatus());
                            creature[whoNext[x]-1].attacking(creature[attackingChoice-1]);
                            System.out.println("Your status after attacking:" + creature[whoNext[x]-1].showStatus());
                            if(!creature[attackingChoice-1].isAlive()) {
                                System.out.println("\n---->" + creature[attackingChoice-1].getName() + " is dead");
                            }
                        }
                        x++;
                        break;
                    }
                    if(!creature[whoNext[x]-1].firePowerCheck() && creature[attackingChoice-1].firePowerCheck()) {
                        System.out.println("That was not a good idea ... you only had " + creature[whoNext[x]-1].getFirePowerUnits() + " Fire Power units!!!");
                        System.out.println("..... OH NO!!! You are being attacked by " + creature[attackingChoice-1].getName() + "!");
                        System.out.println("Your status before being attacked:" + creature[whoNext[x]-1].showStatus());
                        creature[attackingChoice-1].attacking(creature[whoNext[x]-1]);
                        System.out.println("Your status after being attacked:" + creature[whoNext[x]-1].showStatus());
                        if(!creature[whoNext[x]-1].isAlive()) {
                            System.out.println("\n---->" + creature[whoNext[x]-1].getName() + " is dead");
                        }
                        x++;
                        break;
                    }
                    if(creature[whoNext[x]-1].firePowerCheck() && !creature[attackingChoice-1].firePowerCheck()){
                        System.out.println("\n..... You are attacking " + creature[attackingChoice-1].getName() + "!");
                        System.out.println("Your status before attacking:" + creature[whoNext[x]-1].showStatus());
                        creature[whoNext[x]-1].attacking(creature[attackingChoice-1]);
                        System.out.println("Your status after attacking:" + creature[whoNext[x]-1].showStatus());
                        if(!creature[attackingChoice-1].isAlive()) {
                            System.out.println("\n---->" + creature[attackingChoice-1].getName() + " is dead");
                        }
                        x++;
                        break;
                    }
                    break;

                default:
                    break;
            }

        } while(Creature.getNumStillAlive()!=1);

        // Closing message
        System.out.println("\nGAME OVER!!!!!");
        System.out.println();
        for(int i=0; i<creature.length; i++) {
            System.out.println(creature[i]);
            System.out.println("Date created: " + creature[i].getDateCreated());
            if(creature[i].isAlive()) {
                System.out.println("Date died: Is still alive." );
            } else {
                System.out.println("Date died: " + creature[i].getDateDied());
            }
            System.out.println();
        }
        key.close();
    }
}