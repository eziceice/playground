package com.springboot.bet.utils;

public final class ApplicationConstants {

    private ApplicationConstants() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static final String REPORT_TEMPLATE_FILE = "BetReportTemplate.html";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_TAG = "#Date";

    public static final String WIN_AMOUNT_TAG = "#WINAmount";

    public static final String PLACE_AMOUNT_TAG = "#PLACEAmount";

    public static final String TRIFECTA_AMOUNT_TAG = "#TRIFECTAAmount";

    public static final String DOUBLE_AMOUNT_TAG = "#DOUBLEAmount";

    public static final String QUADDIE_AMOUNT_TAG = "#QUADDIEAmount";

    public static final String WIN_SOLD_TAG = "#WINSold";

    public static final String PLACE_SOLD_TAG = "#PLACESold";

    public static final String TRIFECTA_SOLD_TAG = "#TRIFECTASold";

    public static final String DOUBLE_SOLD_TAG = "#DOUBLESold";

    public static final String QUADDIE_SOLD_TAG = "#QUADDIESold";

    public static final String SOLD_PER_HOUR_TAG = "#SoldPerHour";

    public static final String CUSOMTER_ID_TAG = "#CustomerId";

    public static final String CUSTOMER_ID_AMOUNT_TAG = "#CustomerIdAmount";
}
