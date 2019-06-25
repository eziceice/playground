package enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Unit Test for domain type enumeration
 */
@RunWith(JUnit4.class)
public class DomainTypeEnumTest {

    @Test
    public void testNormalDomainTypeEnum() {
        Assert.assertEquals(DomainTypeEnum.NORMAL.getCode(), "NORMAL");
        Assert.assertEquals(DomainTypeEnum.NORMAL.getId(), 1);
        Assert.assertEquals(DomainTypeEnum.PREMIUM.getCode(), "PREMIUM");
        Assert.assertEquals(DomainTypeEnum.PREMIUM.getId(), 2);
    }

    @Test
    public void testPremiumDomainTypeEnum() {
        Assert.assertEquals(DomainTypeEnum.PREMIUM.getCode(), "PREMIUM");
        Assert.assertEquals(DomainTypeEnum.PREMIUM.getId(), 2);
    }

}
