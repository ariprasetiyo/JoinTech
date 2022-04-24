package arprast.qiyosq.ref;

public enum ItemType {
    SERVICE("service"),
    GOODS("goods");

    public String id;

    ItemType(final String name) {
        this.id = name;
    }

    public static ItemType valueOfName(final String name) {

        if (name == null) {
            return null;
        }

        switch (name) {
            case "service":
                return SERVICE;
            case "goods":
            default:
                return GOODS;
        }
    }
}
