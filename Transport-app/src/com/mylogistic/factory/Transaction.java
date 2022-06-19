package com.mylogistic.factory;

public class Transaction {

	private Integer shippingId;
	private String packageId;
	private Integer transportId;
	private String createdDate;
	private String eta;
	private String arrivalDate;
	private String status;

	public Transaction(Integer shippingId, String packageId, Integer transportId, String createdDate, String eta,
			String arrivalDate, String status) {
		super();
		this.shippingId = shippingId;
		this.packageId = packageId;
		this.transportId = transportId;
		this.createdDate = createdDate;
		this.eta = eta;
		this.arrivalDate = arrivalDate;
		this.status = status;
	}
	
	

	@Override
	public String toString() {
		return "Transaction [shippingId=" + shippingId + ", packageId=" + packageId + ", transportId=" + transportId
				+ ", createdDate=" + createdDate + ", eta=" + eta + ", arrivalDate=" + arrivalDate + ", status="
				+ status + "]";
	}



	public Integer getShippingId() {
		return shippingId;
	}

	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Integer getTransportId() {
		return transportId;
	}

	public void setTransportId(Integer transportId) {
		this.transportId = transportId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
