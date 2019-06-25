package utils;

import enumeration.DomainTypeEnum;
import pojo.Domain;
import pojo.DomainCategory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class for domain service
 */
public class Utils {

    public static final String PATH = "./src/main/resources/domainpricelist.txt";
    private static final Utils INSTANCE = new Utils();
    private List<Set<DomainCategory>> listDomainSet = null;

    private Utils() {
    }

    public static Utils getInstance() {
        return INSTANCE;
    }

    /**
     * Load existing domain cost file from resources
     *
     * @param path for the domain price list file
     * @return
     * @throws IOException
     */
    public List<Set<DomainCategory>> loadDomainPriceList(String path) throws IOException {
        if (listDomainSet == null) {
            Set<DomainCategory> normalDomainSet = new HashSet<>();
            Set<DomainCategory> premiumDomainSet = new HashSet<>();
            List<String> result = Files.readAllLines(Paths.get(path));
            for (String string : result) {
                if (string.trim().isEmpty() || string.startsWith("#")) {
                    continue;
                }
                String[] arrays = string.split(",");

                if (arrays[0].trim().startsWith(".")) {

                    DomainCategory domainCategory = DomainCategory.builder().category(arrays[0].trim()).cost(new Double(arrays[1].trim())).domainType(DomainTypeEnum.NORMAL).build();

                    // Starts with . - normal domain
//                    Domain domain = Domain.builder().name(arrays[0].trim()).cost(new Double(arrays[1].trim())).build();
                    normalDomainSet.add(domainCategory);
                } else {
                    // Starts not with . - premium domain
//                    Domain domain = Domain.builder().name(arrays[0].trim()).cost(new Double(arrays[1].trim())).build();
                    DomainCategory domainCategory = DomainCategory.builder().category(arrays[0].trim()).cost(new Double(arrays[1].trim())).domainType(DomainTypeEnum.PREMIUM).build();
                    premiumDomainSet.add(domainCategory);
                }
            }
            List<Set<DomainCategory>> setList = new ArrayList<>();
            setList.add(normalDomainSet);
            setList.add(premiumDomainSet);
            listDomainSet = setList;
            return listDomainSet;
        }
        return listDomainSet;
    }

    /**
     * Find the matched domain in the existing list based on user input
     *
     * @param userDomain      user entered domain
     * @param sourceDomainSet system pre-defined domain list
     * @param type            type of the domain
     * @return
     */
    public Domain findMatchedDomain(Domain userDomain, Set<DomainCategory> sourceDomainSet, DomainTypeEnum type) {
        if (type == DomainTypeEnum.PREMIUM) {
            DomainCategory systemDomainCategory = sourceDomainSet.stream().filter(source -> userDomain.getName().equals(source.getCategory())).findAny().orElse(null);
            return createMatchedDomain(systemDomainCategory, userDomain);
        } else if (type == DomainTypeEnum.NORMAL) {
            String[] strings = userDomain.getName().split("\\.");
            String code = "." + strings[strings.length - 1];
            DomainCategory systemDomainCategory;
            switch (code) {
                case ".au":
                    String domainName = "." + strings[strings.length - 2] + code;
                    systemDomainCategory = sourceDomainSet.stream().filter(source -> domainName.equals(source.getCategory())).findAny().orElse(null);
                    return createMatchedDomain(systemDomainCategory, userDomain);
                //TODO: Add more country code here

                default: // Not a country code
                    systemDomainCategory = sourceDomainSet.stream().filter(source -> code.equals(source.getCategory())).findAny().orElse(null);
                    return createMatchedDomain(systemDomainCategory, userDomain);
            }
        }
        return null;
    }

    /**
     * Create a new domain based on user entered domain name and system pre-defined domain name cost
     *
     * @param systemDomainCategory pre-defined domain
     * @param userDomain   user entered domain
     * @return
     */
    private Domain createMatchedDomain(DomainCategory systemDomainCategory, Domain userDomain) {
        if (systemDomainCategory == null) {
            return null;
        }
        return Domain.builder().name(userDomain.getName()).domainCategory(systemDomainCategory).length(userDomain.getLength()).build();
    }

    /**
     * Check if user select the correct option - Currently only support 1 or 2
     *
     * @param userSelection
     * @return
     */
    public boolean getIsValidSelection(String userSelection) {
        if (userSelection.matches("[1-2]{1}")) {
            return true;
        }
        return false;
    }

    /**
     * Check if user enter the correct format of domain + year
     * Current support format (domain,length) Example: google.com,2
     *
     * @param userInput
     * @return
     */
    public boolean getIsValidInput(String userInput) {
        if (userInput.matches("^(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z0-9][a-z0-9-]{0,61}[a-z0-9]\\,[0-9]+([,.][0-9]+)?$")) {
            return true;
        }
        return false;
    }
}
