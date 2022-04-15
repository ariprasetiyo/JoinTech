package arprast.qiyosq.ref;

public enum StatusPelaku {

    DPO("dpo"), TERSANGKA("tersangka"), TAHANAN("tahanan"), UNKNOW("unknown");

    public final String stringValue;

    private StatusPelaku(String stringValue) {
        this.stringValue = stringValue;
    }

    public static StatusPelaku valueOfString(String stringValue) {
        switch (stringValue) {
            case "dpo":
                return DPO;
            case "tersangka":
                return TERSANGKA;
            case "tahanan":
                return TAHANAN;
            default:
                 return UNKNOW;
        }
    }
}
