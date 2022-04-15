package arprast.qiyosq.ref;

public enum ItemType {
    SERVICE("service"),
    GOODS("goods");

    private String name;

    ItemType(final String name) {
        this.name = name;
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
