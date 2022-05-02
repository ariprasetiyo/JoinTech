package arprast.qiyosq.model;

import arprast.qiyosq.ref.ItemType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.util.Date;

public class POSItemTmpModel {
    private String itemCode;
    private String itemCodeLabel;
    private String itemName;
    private String description;
    private int qty;
    private float sellPrice;
    private float totalSellPrice;
    private PriceDetail priceDetail;
    private ItemType itemType;

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final TypeReference<PriceDetail> priceDetailRef = new TypeReference<PriceDetail>() {};
    private static final ObjectReader priceDetailDeserialize = jsonMapper.readerFor(priceDetailRef);

    public POSItemTmpModel(final String itemCode,
                           final String itemCodeLabel,
                           final String itemName,
                           final String description,
                           final int qty,
                           final float sellPrice,
                           final float totalSellPrice,
                           final String priceDetail,
                           final String itemType) {
        this.itemCode = itemCode;
        this.itemCodeLabel = itemCodeLabel;
        this.itemName = itemName;
        this.description = description;
        this.sellPrice = sellPrice;
        this.totalSellPrice = totalSellPrice;
        try {
            this.priceDetail = priceDetailDeserialize.readValue(priceDetail);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.itemType = ItemType.valueOfName(itemType);
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCodeLabel() {
        return itemCodeLabel;
    }

    public void setItemCodeLabel(String itemCodeLabel) {
        this.itemCodeLabel = itemCodeLabel;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(float totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public PriceDetail getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(PriceDetail priceDetail) {
        this.priceDetail = priceDetail;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
