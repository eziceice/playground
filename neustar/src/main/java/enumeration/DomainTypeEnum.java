package enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration for Domain Types
 */
@Getter
@AllArgsConstructor
public enum DomainTypeEnum {
    NORMAL(1, "NORMAL"),
    PREMIUM(2, "PREMIUM");

    private int id;
    private String code;
}
