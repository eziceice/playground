package utils;

import enumeration.DomainTypeEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pojo.Domain;
import pojo.DomainCategory;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Unit test for Utils
 */
@RunWith(JUnit4.class)
public class UtilsTest {

    private Set<DomainCategory> normalDomainSet = new HashSet<>();

    private Set<DomainCategory> premiumDomainSet = new HashSet<>();

    private List<Set<DomainCategory>> list = new ArrayList<>();


    @Before
    public void setUp() {
        DomainCategory domainCategory1 = DomainCategory.builder().cost(10d).category(".com").domainType(DomainTypeEnum.NORMAL).build();
        DomainCategory domainCategory2 = DomainCategory.builder().cost(9d).category(".net").domainType(DomainTypeEnum.NORMAL).build();
        DomainCategory domainCategory3 = DomainCategory.builder().cost(20d).category(".com.au").domainType(DomainTypeEnum.NORMAL).build();
        DomainCategory domainCategory4 = DomainCategory.builder().cost(1000d).category("apple.com.au").domainType(DomainTypeEnum.PREMIUM).build();
        DomainCategory domainCategory5 = DomainCategory.builder().cost(800d).category("dict.com").domainType(DomainTypeEnum.PREMIUM).build();
        DomainCategory domainCategory6 = DomainCategory.builder().cost(300d).category("education.net").domainType(DomainTypeEnum.PREMIUM).build();

        normalDomainSet.add(domainCategory1);
        normalDomainSet.add(domainCategory2);
        normalDomainSet.add(domainCategory3);
        list.add(normalDomainSet);

        premiumDomainSet.add(domainCategory4);
        premiumDomainSet.add(domainCategory5);
        premiumDomainSet.add(domainCategory6);
        list.add(premiumDomainSet);
    }


    @Test
    public void testLoadDomainPriceList() throws IOException {
        List<Set<DomainCategory>> listDomainSet = Utils.getInstance().loadDomainPriceList(Utils.PATH);
        Assert.assertEquals(listDomainSet, list);
    }

    @Test
    public void testLoadDomainPriceListFailure() throws IOException {
        try {
            Utils.getInstance().loadDomainPriceList("./text.txt");
        } catch (NoSuchFileException e) {
            // do nothing
        }
    }

    @Test
    public void testFindMatchedDomainNormal() {
        Domain domain = Domain.builder().name("abc.com.au").build();
        Domain matchedDomain = Utils.getInstance().findMatchedDomain(domain, normalDomainSet, DomainTypeEnum.NORMAL);
        Assert.assertEquals(matchedDomain.getDomainCategory().getCost(), 20d, 2);
        Assert.assertNotEquals(domain, matchedDomain);
    }

    @Test
    public void testFindMatchedDomainPremium() {
        Domain domain = Domain.builder().name("apple.com.au").build();
        Domain matchedDomain = Utils.getInstance().findMatchedDomain(domain, premiumDomainSet, DomainTypeEnum.PREMIUM);
        Assert.assertEquals(matchedDomain.getDomainCategory().getCost(), 1000d, 2);
        Assert.assertNotEquals(domain, matchedDomain);
    }

    @Test
    public void testGetIsValidSelection() {
        Assert.assertFalse(Utils.getInstance().getIsValidSelection("3"));
        Assert.assertTrue(Utils.getInstance().getIsValidSelection("1"));
        Assert.assertFalse(Utils.getInstance().getIsValidSelection("S"));
    }

    @Test
    public void testGetIsValidInput() {
        Assert.assertFalse(Utils.getInstance().getIsValidInput("3.com, 2"));
        Assert.assertTrue(Utils.getInstance().getIsValidInput("google.com,2"));
        Assert.assertFalse(Utils.getInstance().getIsValidInput("-aaa.com,2"));
    }
}
