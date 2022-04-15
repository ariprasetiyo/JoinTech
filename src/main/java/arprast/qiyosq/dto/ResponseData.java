/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dto;

/**
 *
 * @author ari-prasetiyo
 */
public class ResponseData extends Dto {

	private static final long serialVersionUID = 3315233291686431211L;
	private Object data;
	private long totalRecord;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "ResponseData [data=" + data + ", totalRecord=" + totalRecord + "]";
	}

}
