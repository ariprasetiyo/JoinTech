package arprast.qiyosq.ref;

public enum PaymentMethod {

    BANK_TRANSFER("bank_transfer", "bank transfer"),
    QRIS("qris", "qris"),
    CASH("cash", "cash");

    public final String methodId;
    public final String desc;

    PaymentMethod(final String methodId, final String desc) {
        this.methodId = methodId;
        this.desc = desc;
    }

    public static PaymentMethod valueOfName(final String methodId) {

        if (methodId == null) {
            return null;
        }

        switch (methodId) {
            case "bank_transfer":
                return BANK_TRANSFER;
            case "qris":
                return QRIS;
            case "cash":
            default:
                return CASH;
        }
    }

}
