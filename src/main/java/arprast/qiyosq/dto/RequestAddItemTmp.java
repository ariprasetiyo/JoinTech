package arprast.qiyosq.dto;

import javax.validation.constraints.NotNull;

public class RequestAddItemTmp extends Dto {

    @NotNull
    String itemCode;
    @NotNull
    int qty;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
