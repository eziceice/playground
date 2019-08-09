package utils;

/**
 * Menu Helper Class
 */
public class MenuHelper {

    /**
     * Instruction for domain register
     */
    public static void getInstruction() {
        System.out.println();
        System.out.println("=================Instructions==================");
        System.out.println("1. One line only can have one input            ");
        System.out.println("2. Domain and years must be separated by comma ");
        System.out.println("3. No space allowed before and after comma     ");
        System.out.println("4. If you want to see the result, enter Done   ");
        System.out.println("5: If you want to go back to Main Menu, Enter 0");
        System.out.println("===================Examples====================");
        System.out.println("a-domain.com,1                                 ");
        System.out.println("another-domain.net,2                           ");
        System.out.println("dict.com,5                                     ");
        System.out.println();
        System.out.println("Please enter your domain and length per year:  ");
    }

    /**
     * Get Menu output
     */
    public static void getMenu() {
        System.out.println("================================================================");
        System.out.println("            Welcome to use domain register system!              ");
        System.out.println("Please enter number to select what you want to do in this system");
        System.out.println("================================================================");
        System.out.println();
        System.out.println("1. Register a Domain                                            ");
        System.out.println("2. Exit                                                         ");
    }

    /**
     * Error message for unregistered domain
     */
    public static void getErrorMessageForRegisterDomain() {
        /*
            Invalid input or no registered domain found in system
         */
        System.out.println("Invalid input or system doesn't have that registered domain!");
        System.out.println("Please check your input with Instructions below");
        getInstruction();
    }

    /**
     * Error message for no valid domain
     */
    public static void getErrorMessageForNoValidDomain() {
        System.out.println("You haven't entered any valid domain yet!");
        getInstruction();
    }

    /**
     * Error message for invalid selection
     */
    public static void getErrorMessageForInvalidSelection() {
        System.out.println("Invalid Selection! Please select 1 or 2");
        System.out.println();
        System.out.println();
        getMenu();
    }
}


