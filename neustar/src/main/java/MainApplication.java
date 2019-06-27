import enumeration.DomainTypeEnum;
import pojo.Domain;
import pojo.DomainCategory;
import utils.MenuHelper;
import utils.Utils;

import java.io.IOException;
import java.util.*;

/**
 * Main Application for Domain Service
 */
public class MainApplication {

    private List<Set<DomainCategory>> domainSetList; // System support domain set list - predefined in text
    private Map<Domain, Double> totalCostPerDomainMap = new HashMap<>(); // Total cost per domain name
    private double totalCost = 0d; // Total cost

    public static void main(String[] args) {
        MainApplication mainApplication = new MainApplication();
        mainApplication.run();
    }

    /**
     * Work flow for domain service
     */
    public void run() {
        try {
            domainSetList = Utils.getInstance().loadDomainPriceList(Utils.PATH);
        } catch (IOException e) {
            System.out.println("Sorry, unexpected exception happened while loading the domain cost list. Exit...");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        showMenu(scanner);
        printResult();
    }

    /**
     * Show Menu
     *
     * @param scanner
     */
    private void showMenu(Scanner scanner) {
        MenuHelper.getMenu();
        while (scanner.hasNextLine()) {
            String userOption = scanner.nextLine().trim(); // Avoid blank space
            if (Utils.getInstance().getIsValidSelection(userOption)) {
                int option = Integer.valueOf(userOption);
                switch (option) {
                    case 1:
                        registerDomain(scanner); // Go to add domain menu
                        return;
                    case 2:
                        System.out.println("Exit...");
                        System.exit(0); // Exit
                }
            }
            MenuHelper.getErrorMessageForInvalidSelection();
        }
    }

    /**
     * Register a domain in the system
     *
     * @param scanner
     */
    private void registerDomain(Scanner scanner) {
        MenuHelper.getInstruction();
        while (scanner.hasNextLine()) {
            String readString = scanner.nextLine().trim();

            if (readString.equalsIgnoreCase("Done")) {
                if (totalCost == 0d) { // if cost is zero, means user haven't done anything
                    MenuHelper.getErrorMessageForNoValidDomain();
                    continue;
                }
                break;
            }

            if (readString.equals("0")) { // Go back to the main menu
                showMenu(scanner);
                break;
            }
            calculateCost(readString); // Cost calculation
        }
    }

    /**
     * Print cost result
     */
    private void printResult() {
        System.out.println("Total cost is :$" + totalCost);
        for (Map.Entry<Domain, Double> entry : totalCostPerDomainMap.entrySet()) {
            System.out.println(entry.getKey().getName() + " registered for " + entry.getKey().getLength()
                    + " year at $" + entry.getKey().getDomainCategory().getCost() + " per year = $" + entry.getValue());
        }
    }


    /**
     * Calculate the cost
     *
     * @param readString
     */
    private void calculateCost(String readString) {
        Set<DomainCategory> normalDomain = domainSetList.get(0);
        Set<DomainCategory> premiumDomainSet = domainSetList.get(1);

        if (Utils.getInstance().getIsValidInput(readString)) {
            String[] values = readString.split(",");
            double length = Double.valueOf(values[1]); // Get user entered length
            Domain domain = Domain.builder().name(values[0].trim()).length(length).build(); // Build a user entered domain
            Domain matchedDomain = Utils.getInstance().findMatchedDomain(domain, premiumDomainSet, DomainTypeEnum.PREMIUM); // Find matched premium domain
            if (matchedDomain == null) {
                matchedDomain = Utils.getInstance().findMatchedDomain(domain, normalDomain, DomainTypeEnum.NORMAL); // Find matched normal domain
            }

            if (matchedDomain != null) {
                if (totalCostPerDomainMap.containsKey(matchedDomain)) { // Domain is already exist, calculate new cost and length based on previous cost
                    Double newLength = totalCostPerDomainMap.get(matchedDomain) + matchedDomain.getDomainCategory().getCost() * length;
                    totalCostPerDomainMap.remove(matchedDomain);
                    matchedDomain.setLength(matchedDomain.getLength() + length);
                    totalCostPerDomainMap.put(matchedDomain, newLength);
                } else { // User never entered the domain before, create a new entry to calculate cost and length
                    totalCostPerDomainMap.put(matchedDomain, matchedDomain.getDomainCategory().getCost() * length);
                }
                totalCost += matchedDomain.getDomainCategory().getCost() * length; // calculate total cost
            }
        } else {
            MenuHelper.getErrorMessageForRegisterDomain();
            clearValue();
        }
    }

    private void clearValue()
    {
        totalCostPerDomainMap.clear();
        totalCost = 0;
    }
}

