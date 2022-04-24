package arprast.qiyosq.model;

public class PriceDetail {
    private float sellPrice;
    private DiscountDetail discountDetail;
    private float basicPrice;
    private float marginPctg;
    private float marginAmount;

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(float basicPrice) {
        this.basicPrice = basicPrice;
    }

    public float getMarginPctg() {
        return marginPctg;
    }

    public void setMarginPctg(float marginPctg) {
        this.marginPctg = marginPctg;
    }

    public float getMarginAmount() {
        return marginAmount;
    }

    public void setMarginAmount(float marginAmount) {
        this.marginAmount = marginAmount;
    }

    public DiscountDetail getDiscountDetail() {
        return discountDetail;
    }

    public void setDiscountDetail(DiscountDetail discountDetail) {
        this.discountDetail = discountDetail;
    }
}
