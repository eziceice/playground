package pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * POJO for domain object
 */
@Getter
@Setter
@ToString
@Builder
public class Domain {

    private String name;

    private Double length;

    private DomainCategory domainCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Domain domain = (Domain) o;

        return new EqualsBuilder()
                .append(name, domain.name)
                .append(domainCategory, domain.domainCategory)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(domainCategory)
                .toHashCode();
    }
}
