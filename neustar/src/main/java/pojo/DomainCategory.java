package pojo;

import enumeration.DomainTypeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class DomainCategory {

    private String category;

    private Double cost;

    private DomainTypeEnum domainType;
}
