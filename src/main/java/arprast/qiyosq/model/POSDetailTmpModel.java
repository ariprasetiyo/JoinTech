package arprast.qiyosq.model;

import arprast.qiyosq.ref.ItemType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.util.Date;

public class POSDetailTmpModel {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final TypeReference<PriceDetail> priceDetailRef = new TypeReference<PriceDetail>() {
    };
    private static final ObjectReader priceDetailDeserialize = jsonMapper.readerFor(priceDetailRef);

    private String requestId;
    private String itemCode;
    private String itemCodeLabel;
    private String itemName;
    private String description;
    private int qty;
    private float sellPrice;
    private float totalSellPrice;
    private PriceDetail priceDetail;
    private float basicPrice;
    private ItemType itemType;

    public POSDetailTmpModel(){}

    public POSDetailTmpModel(String requestId, String itemCode, String itemCodeLabel, String itemName, String description, int qty, float sellPrice, float totalSellPrice, String priceDetail, float basicPrice, String itemType) {
        this.requestId = requestId;
        this.itemCode = itemCode;
        this.itemCodeLabel = itemCodeLabel;
        this.itemName = itemName;
        this.description = description;
        this.qty = qty;
        this.sellPrice = sellPrice;
        this.totalSellPrice = totalSellPrice;
        try {
            this.priceDetail = priceDetailDeserialize.readValue(priceDetail);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.basicPrice = basicPrice;
        this.itemType = ItemType.valueOfName(itemType);
    }

    public float getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(float totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public PriceDetail getPriceDetail() {
        return priceDetail;
    }

    public void setPriceDetail(PriceDetail priceDetail) {
        this.priceDetail = priceDetail;
    }

    public float getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(float basicPrice) {
        this.basicPrice = basicPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
