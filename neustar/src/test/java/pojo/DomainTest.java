package pojo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit test for domain pojo
 */
@RunWith(JUnit4.class)
public class DomainTest {

    private Domain domain;

    private DomainCategory domainCategory;

    @Before
    public void setUp() {
        domainCategory = DomainCategory.builder().build();
        domain = Domain.builder().name("test").length(3.0d).domainCategory(domainCategory).build();
    }

    @Test
    public void testDomainGetters() {

        Assert.assertEquals(domain.getName(), "test");
        Assert.assertEquals(domain.getDomainCategory(), domainCategory);
        Assert.assertEquals(domain.getLength(), 3.0d, 2);
    }

    @Test
    public void testDomainEquals() {
        Domain testDomain = Domain.builder().name("test").domainCategory(domainCategory).build();
        Assert.assertTrue(testDomain.equals(domain));
    }

    @Test
    public void testDomainHashCode() {
        Domain testDomain = Domain.builder().name("test").domainCategory(domainCategory).build();
        Assert.assertTrue(testDomain.hashCode() == domain.hashCode());
    }

}
