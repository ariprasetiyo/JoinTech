package arprast.qiyosq.model;

import java.util.Date;

public class DiscountDetail {
    private String voucherCode;
    private String voucherDesc;
    private float discountPctg = 0;
    private float discountAmount = 0;
    private Date expiredDate;
    private Date startDate;

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getVoucherDesc() {
        return voucherDesc;
    }

    public void setVoucherDesc(String voucherDesc) {
        this.voucherDesc = voucherDesc;
    }

    public float getDiscountPctg() {
        return discountPctg;
    }

    public void setDiscountPctg(float discountPctg) {
        this.discountPctg = discountPctg;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
