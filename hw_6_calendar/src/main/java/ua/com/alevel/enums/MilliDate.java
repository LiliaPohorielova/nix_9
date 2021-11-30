package ua.com.alevel.enums;

public enum MilliDate {

    YEAR(31557600000L),
    MONTH(2629800000L),
    DAY(86400000L),
    HOUR(3600000L),
    MINUTE(60000L),
    SECOND(1000L),
    MILLISECOND(1L);

    private Long valueInMillisecond;

    MilliDate(Long valueInMillisecond) {
        this.valueInMillisecond = valueInMillisecond;
    }

    public Long getValueInMillisecondOrder() {
        return this.valueInMillisecond;
    }
}
