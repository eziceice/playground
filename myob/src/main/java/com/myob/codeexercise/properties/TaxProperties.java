package com.myob.codeexercise.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for tax rules, it can be configured from application.properties
 */
@ConfigurationProperties(prefix = "tax")
@Data
public class TaxProperties {
    private String month;
}
