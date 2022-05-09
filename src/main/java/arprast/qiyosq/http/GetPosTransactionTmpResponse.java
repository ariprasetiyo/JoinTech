package arprast.qiyosq.http;


import arprast.qiyosq.model.POSHeaderTmpModel;
import arprast.qiyosq.model.POSItemTmpModel;

import java.util.List;

public class GetPosTransactionTmpResponse {

    private POSHeaderTmpModel headerTrx;
    private List<POSItemTmpModel> detailTrxList;

    public POSHeaderTmpModel getHeaderTrx() {
        return headerTrx;
    }

    public void setHeaderTrx(POSHeaderTmpModel headerTrx) {
        this.headerTrx = headerTrx;
    }

    public List<POSItemTmpModel> getDetailTrxList() {
        return detailTrxList;
    }

    public void setDetailTrxList(List<POSItemTmpModel> detailTrxList) {
        this.detailTrxList = detailTrxList;
    }
}
