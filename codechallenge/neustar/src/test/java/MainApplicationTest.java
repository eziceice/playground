import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;
import pojo.Domain;
import pojo.DomainCategory;

import java.util.*;

/**
 * Unit test for Main Application
 */
@RunWith(JUnit4.class)
public class MainApplicationTest {
    private List<Set<DomainCategory>> domainSetList = new ArrayList<>();
    private Map<Domain, Double> totalCostPerDomainMap = new HashMap<>(); // Total cost per domain name
    private MainApplication mainApplication = new MainApplication();
    private double totalCost = 0d;

    private DomainCategory domainCategory1;
    private DomainCategory domainCategory2;
    private DomainCategory domainCategory3;
    private DomainCategory domainCategory4;
    private DomainCategory domainCategory5;
    private DomainCategory domainCategory6;

    @Before
    public void setUp() {
        Set<DomainCategory> normalDomainSet = new HashSet<>();
        Set<DomainCategory> premiumDomainSet = new HashSet<>();

        domainCategory1 = DomainCategory.builder().cost(10d).category(".com").build();
        domainCategory2 = DomainCategory.builder().cost(9d).category(".net").build();
        domainCategory3 = DomainCategory.builder().cost(20d).category(".com.au").build();
        domainCategory4 = DomainCategory.builder().cost(1000d).category("apple.com.au").build();
        domainCategory5 = DomainCategory.builder().cost(800d).category("dict.com").build();
        domainCategory6 = DomainCategory.builder().cost(300d).category("education.net").build();

        normalDomainSet.add(domainCategory1);
        normalDomainSet.add(domainCategory2);
        normalDomainSet.add(domainCategory3);
        domainSetList.add(normalDomainSet);

        premiumDomainSet.add(domainCategory4);
        premiumDomainSet.add(domainCategory5);
        premiumDomainSet.add(domainCategory6);
        domainSetList.add(premiumDomainSet);

        ReflectionTestUtils.setField(mainApplication, "domainSetList", domainSetList);
        ReflectionTestUtils.setField(mainApplication, "totalCostPerDomainMap", totalCostPerDomainMap);
        ReflectionTestUtils.setField(mainApplication, "totalCost", totalCost);
    }

    @Test
    public void testCalculateCostWithValidInput() {
        String stringA = "a-domain.com,2";
        ReflectionTestUtils.invokeMethod(mainApplication, "calculateCost", stringA);
        String stringB = "c.net,2";
        ReflectionTestUtils.invokeMethod(mainApplication, "calculateCost", stringB);
        Assert.assertEquals((Double) ReflectionTestUtils.getField(mainApplication, "totalCost"), 38d, 2);
        HashMap<Domain, Double> map = new HashMap<>();
        map.put(Domain.builder().name("c.net").domainCategory(domainCategory2).length(2d).build(), 18d);
        map.put(Domain.builder().name("a-domain.com").domainCategory(domainCategory1).length(2d).build(), 20d);
        Assert.assertEquals(map, ReflectionTestUtils.getField(mainApplication, "totalCostPerDomainMap"));
    }

    @Test
    public void testCalculateCostWithInValidInput() {
        String stringA = "-domain.com,2";
        ReflectionTestUtils.invokeMethod(mainApplication, "calculateCost", stringA);
        String stringB = "c.net, 2";
        ReflectionTestUtils.invokeMethod(mainApplication, "calculateCost", stringB);
        Assert.assertEquals((Double) ReflectionTestUtils.getField(mainApplication, "totalCost"), 0d, 2);
        HashMap<Domain, Double> map = new HashMap<>();
        map.put(Domain.builder().name("c.net").domainCategory(domainCategory2).length(2d).build(), 18d);
        map.put(Domain.builder().name("a-domain.com").domainCategory(domainCategory1).length(2d).build(), 20d);
        Assert.assertEquals(0, ((HashMap<Domain, Double>) ReflectionTestUtils.getField(mainApplication, "totalCostPerDomainMap")).size());
    }
}
