// -------------------------------------------------------
// Assignment (#4 Part A)
// Written by: (Ibrahim Elyyan)
// For COMP 248 Section (U) – Winter 2021
// --------------------------------------------------------

//Author: Ibrahim Elyyan

//Date: April 16, 2021

/* PURPOSE: The class Creature stores information about a player of the Battle Game. It also has the methods that
 * will be implemented in the driver class*/
import java.util.Date;

public class Creature {
    private static final int FOOD2HEALTH = 6, HEALTH2POWER = 4;
    // Declaring instance variables
    private String name;
    private int foodUnits, healthUnits, firePowerUnits;
    private Date dateCreated, dateDied;
    // Initializing numStillAlive
    private static int numStillAlive = 0;

    // Using a constructor to initialize all the non static variables except name
    Creature(){
        numStillAlive++;
        foodUnits = (int)(Math.random()*12)+1;
        healthUnits = (int)(Math.random()*7)+1;
        firePowerUnits = (int)(Math.random()*10);
        normalizeUnits();
        dateCreated = new Date();
        dateDied = null;
    }

    // Using a constructor to initialize the name
    Creature (String name){
        this.name = name;
    }
    // Setting the name
    public void setName(String newName){
        name = newName;
    }
    // Setting health units
    public void setHealthUnits(int n){
        healthUnits = n;
    }
    // Setting food units
    public void setFoodUnits(int n){
        foodUnits = n;
    }
    // Reducing fire power units by the specified amount
    public void reduceFirePowerUnits(int n){
        firePowerUnits -= n;
    }
    //Accessor Method to get the name of the creature
    public String getName(){
        return name;
    }
  //Accessor Method to get the food units of the creature
    public int getFoodUnits(){
        return foodUnits;
    }
    //Accessor Method to get the health units of the creature
    public int getHealthUnits(){
        return healthUnits;
    }
    //Accessor Method to get the fire power units of the creature
    public int getFirePowerUnits(){
        return firePowerUnits;
    }
    //Accessor Method to get the creature's date of creation
    public Date getDateCreated(){
        return dateCreated;
    }
  //Accessor Method to get the creature's date of death
    public Date getDateDied(){
        return dateDied;
    }
  //Accessor Method to get the amount of creatures still alive 
    public static int getNumStillAlive(){
        return numStillAlive;
    }
    // Determining if the creature is still alive
    public boolean isAlive(){
        if (dateDied == null){
            return true;
        } else return false;
    }
    // Determines how much food is earned from option 5
    public int earnFood(){
    	int extraFood = (int)(Math.random()*15); 
        foodUnits += extraFood;
        System.out.println(extraFood + " food units.");
        normalizeUnits();
        return foodUnits;
    }
    // Method that is used when one creature attacks the other, determines how much is lost and gained
    public void attacking(Creature player){
    	foodUnits += (int)(Math.round(player.foodUnits/2.0));
    	setFoodUnits(foodUnits);
        healthUnits += (int)(Math.round(player.healthUnits/2.0));
        setHealthUnits(healthUnits);
    	player.foodUnits -= (int)(Math.round(player.foodUnits/2.0)); 
    	player.setFoodUnits(player.foodUnits);
    	player.healthUnits -= (int)(Math.round(player.healthUnits/2.0));
    	player.setHealthUnits(player.healthUnits);
        reduceFirePowerUnits(2);
        normalizeUnits();
        player.healthFoodUnitsZero();
    }
    // Method determines if the creature died
    public boolean healthFoodUnitsZero(){
        if (foodUnits <= 0 && healthUnits <= 0 && dateDied == null){
            numStillAlive--;
            died();
            return true;
        } else return false;
    }
    // Method determines the date the creature died
    private void died(){
        dateDied = new Date();
    }
    // toString method that returns a string with all of the non-static attributes of the calling object in a descriptive message.
    public String toString(){
        return "Food units" + "\tHealth units" + "\tFire power units" + "\tName"
                //   Food units       Health units       Fire power units       name
                + "\n----------" + "\t------------" + "\t----------------" + "\t----"
                + "\n" + getFoodUnits() + "\t 	" + getHealthUnits() + "\t     	" + getFirePowerUnits() + "\t		" + getName();
    }
    // This method  returns the number of the food, health and fire power units as a String
    public String showStatus(){
        return foodUnits + " food units, " + healthUnits + " health units, " + firePowerUnits + " fire power units";
    }
    // Converting food units into health units and health units into fire power units
    private void normalizeUnits() {
        while (true) {
            if (foodUnits >= FOOD2HEALTH) {
                foodUnits -= 6;
                healthUnits++;
            }
            if (healthUnits >= HEALTH2POWER+3) {
                healthUnits -= 3;
                firePowerUnits++;
            }
            if(foodUnits<6 || healthUnits<4){
                break;
            }
        }
    }
    // Method to check if the creature has the sufficient amount of fire power to initiate an attack
    public boolean firePowerCheck() {
        if (firePowerUnits>=2) {
            return true;
        } else return false;
    }

}
