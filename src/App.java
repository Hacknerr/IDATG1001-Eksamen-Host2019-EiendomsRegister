import me.ntnu.folk.candidatenumber.project.InputHandler;
import me.ntnu.folk.candidatenumber.project.Property;
import me.ntnu.folk.candidatenumber.project.PropertyRegister;

import java.util.Scanner;

/**
 * This class is used to manage a text-based user interface for a property-registry application.
 * @Author: 10009
 * @version 1.0
 */
public class App {

    private final int ADD_PROPERTY = 1;
    private final int LIST_ALL_PROPERTIES = 2;
    private final int FIND_PROPERTY = 3;
    private final int CALCULATE_AVERAGE_AREA = 4;
    private final int DELETE_PROPERTY = 5;
    private final int LIST_AMOUNT_OF_PROPERTIES = 6;
    private final int LIST_ALL_PROPERTIES_BY_LOTNUMBER = 7;
    private final int EXIT = 8;

    PropertyRegister propertyRegister;
    InputHandler inputHandler;
    Scanner scn;

    /**
     * Constructor that initializes a Scanner and objects of classes.
     * Also takes the user to the start-menu.
     */
    public App() {
        propertyRegister = new PropertyRegister();
        inputHandler = new InputHandler();
        scn = new Scanner(System.in);
        propertyRegister.initializeProperties();
        start();
    }

    /**
     * Creates an object of the App class.
     * Is the first function that runs when the application starts.
     * @param args
     */
    public static void main(String[] args) {
        App app = new App();
    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice)
            {
                case ADD_PROPERTY:
                    addNewPropertyMenu();
                    break;
                case LIST_ALL_PROPERTIES:
                    listAllPropertiesMenu();
                    break;
                case FIND_PROPERTY:
                    searchForPropertiesMenu();
                    break;
                case CALCULATE_AVERAGE_AREA:
                    calculateAverageAreaMenu();
                    break;
                case DELETE_PROPERTY:
                    deletePropertyMenu();
                    break;
                case LIST_AMOUNT_OF_PROPERTIES:
                    listAmountOfProperties();
                    break;
                case LIST_ALL_PROPERTIES_BY_LOTNUMBER:
                    listAllPropertiesByLotNumber();
                    break;
                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     * If 0 is returned, the user has entered a wrong value
     */
    private int showMenu() {
        int menuChoice = 0;
        System.out.println("\n***** Property Register Application v0.1 *****\n");
        System.out.println("1. Add property");
        System.out.println("2. List all properties");
        System.out.println("3. Search property");
        System.out.println("4. Calculate average area");
        System.out.println("5. Delete property");
        System.out.println("6. List amount of properties");
        System.out.println("7. List all properties by property-number");
        System.out.println("8. Quit");
        System.out.println("\nPlease enter a number between 1 and 8.\n");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * This method lets the user input information to register a new property. Done with a Scanner.
     * All inputs have to be of a certain data-type.
     */
    public void addNewPropertyMenu(){
        System.out.println("-----------Register new property-----------");

        System.out.println("Please enter the municipality number: ");
        int municipalityNumber = inputHandler.getIntInput(101, 5054);

        System.out.println("Please enter the municipality name: ");
        String municipalityName = scn.nextLine();
        municipalityName = inputHandler.checkString(municipalityName);

        System.out.println("Please enter the lot number: ");
        int lotNumber = inputHandler.getIntInput(0, 9999);

        System.out.println("Please enter the section number: ");
        int sectionNumber = inputHandler.getIntInput(0, 9999);

        System.out.println("Please enter the property area size: ");
        float propertyArea = inputHandler.getFloatInput(0, 9999);

        System.out.println("Please enter the name of the property owner: ");
        String propertyOwnerName = scn.next();
        propertyOwnerName = inputHandler.checkString(propertyOwnerName);

        Property newProperty = new Property(municipalityNumber, municipalityName, lotNumber, sectionNumber, propertyArea, propertyOwnerName);

        System.out.println("Do want to give the property a name? Enter 'yes' or 'no'.");
        scn.nextLine();
        String choice = scn.nextLine();
        choice = inputHandler.checkString(choice);

        if(choice.equalsIgnoreCase("yes")){
            System.out.println("Please enter the property name: ");
            String propertyName = scn.nextLine();
            propertyName = inputHandler.checkString(propertyName);
            newProperty = new Property(municipalityNumber, municipalityName, lotNumber, sectionNumber, propertyName, propertyArea, propertyOwnerName);
        }

        if(propertyRegister.regNewProperty(newProperty)){
            System.out.println("The property was successfully added.");
        }else{
            System.out.println("The property was not added.");
        }

    }

    /**
     * Prints a list to the user of all properties within the register.
     */
    public void listAllPropertiesMenu(){
        System.out.println("-----------All registered properties-----------");
        System.out.println("KommuneNr-gnr/bnr");
        propertyRegister.printResult(propertyRegister.listAllProperties());
    }

    /**
     * Lets the user search for properties in the registry. Done with a Scanner.
     */
    public void searchForPropertiesMenu(){
        System.out.println("-----------Search for properties-----------");

        System.out.println("Please enter the municipality number: ");
        int municipalityNumber = inputHandler.getIntInput(101, 5054);

        System.out.println("Please enter the lot number: ");
        int lotNumber = inputHandler.getIntInput(0, 9999);

        System.out.println("Please enter the section number: ");
        int sectionNumber = inputHandler.getIntInput(0, 9999);

        propertyRegister.printResult(propertyRegister.listByMunicipalityNumberLotAndSection(new Property(municipalityNumber, null, lotNumber, sectionNumber, null, 0, null)));
    }

    /**
     * Prints information about the calculated average.
     */
    public void calculateAverageAreaMenu(){
        System.out.println("-----------Calculated average of all areas in registry-----------");
        System.out.println(propertyRegister.calculateAverageArea());
    }

    /**
     * This method lets the user input information to delete a property.
     */
    public void deletePropertyMenu(){
        System.out.println("-----------Delete property-----------");

        System.out.println("Please enter the municipality number: ");
        int municipalityNumber = inputHandler.getIntInput(101, 5054);

        System.out.println("Please enter the lot number: ");
        int lotNumber = inputHandler.getIntInput(0, 9999);

        System.out.println("Please enter the section number: ");
        int sectionNumber = inputHandler.getIntInput(0, 9999);

        if(propertyRegister.deleteProperty(new Property(municipalityNumber, null, lotNumber, sectionNumber, null, 0, null))){
            System.out.println("Property has been deleted!");
        }else{
            System.out.println("Property was not deleted!");
        }
    }

    /**
     * Prints a number to the user of the total amount of properties within the register.
     */
    public void listAmountOfProperties(){
        System.out.println("-----------Total amount of properties-----------");
        System.out.println(propertyRegister.amountOfProperties());
    }

    /**
     * Prints a list to the user of all properties with the same lot-number within the register.
     */
    public void listAllPropertiesByLotNumber(){
        System.out.println("-----------List of all properties by lot-number-----------");

        System.out.println("Please enter the lot number: ");
        int lotNumber = inputHandler.getIntInput(0, 9999);
        propertyRegister.printResult(propertyRegister.listAllPropertiesByLotNumber(new Property(0, null, lotNumber, 0, null, 0, null)));
    }

}
