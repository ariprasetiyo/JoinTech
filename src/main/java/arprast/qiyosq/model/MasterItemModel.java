package arprast.qiyosq.model;

import arprast.qiyosq.ref.ItemType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.util.Date;

public class MasterItemModel {
    private String itemCode;
    private String itemCodeLabel;
    private String itemName;
    private String description;
    private float sellPrice;
    private PriceDetail priceDetail;
    private float basicPrice;
    private String unitMeasure;
    private boolean isActive;
    private Date createdTime;
    private Date modifiedTime;
    private ItemType itemType;
    private int stock;

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final TypeReference<PriceDetail> priceDetailRef = new TypeReference<PriceDetail>() {};
    private static final ObjectReader priceDetailDeserialize = jsonMapper.readerFor(priceDetailRef);

    public MasterItemModel(final String itemCode,
                           final String itemCodeLabel,
                           final String itemName,
                           final String description,
                           final float sellPrice,
                           final String priceDetail,
                           final float basicPrice,
                           final String unitMeasure,
                           final boolean isActive,
                           final Date modifiedTime,
                           final String itemType,
                           final Integer stock) {
        this.itemCode = itemCode;
        this.itemCodeLabel = itemCodeLabel;
        this.itemName = itemName;
        this.description = description;
        this.sellPrice = sellPrice;
        try {
            this.priceDetail = priceDetailDeserialize.readValue(priceDetail);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.basicPrice = basicPrice;
        this.unitMeasure = unitMeasure;
        this.isActive = isActive;
        this.modifiedTime = modifiedTime;
        this.itemType = ItemType.valueOfName(itemType);
        this.stock = stock== null ? 0 : stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
