package arprast.qiyosq.ref;

public enum UnitMeasure {
    PIECES("pieces"),
    METER_1("meter_1"),
    METER_2("meter_2"),
    METER_3("meter_3");

    public final String name;

    UnitMeasure(final String name ) {
        this.name = name;
    }

    public UnitMeasure valueOfString(final String name) {
        switch (name) {
            case "pieces":
                return PIECES;
            default:
                return METER_3;

        }

    }
}
