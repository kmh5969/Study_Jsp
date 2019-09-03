package com.bike.dto;

public class BikeDto {
	
	private String rentid;
	private String addrgu;
	private int contentid;
	private String contentnm;
	private String newaddr;
	private int cradlecount;
	private double longitude;
	private double latitude;
	
	public BikeDto() {
		
	}

	public BikeDto(String rentid, String addrgu, int contentid, String contentnm, String newaddr, int cradlecount,
			double longitude, double latitude) {
		
		this.rentid = rentid;
		this.addrgu = addrgu;
		this.contentid = contentid;
		this.contentnm = contentnm;
		this.newaddr = newaddr;
		this.cradlecount = cradlecount;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getRentid() {
		return rentid;
	}

	public void setRentid(String rentid) {
		this.rentid = rentid;
	}

	public String getAddrgu() {
		return addrgu;
	}

	public void setAddrgu(String addrgu) {
		this.addrgu = addrgu;
	}

	public int getContentid() {
		return contentid;
	}

	public void setContentid(int contentid) {
		this.contentid = contentid;
	}

	public String getContentnm() {
		return contentnm;
	}

	public void setContentnm(String contentnm) {
		this.contentnm = contentnm;
	}

	public String getNewaddr() {
		return newaddr;
	}

	public void setNewaddr(String newaddr) {
		this.newaddr = newaddr;
	}

	public int getCradlecount() {
		return cradlecount;
	}

	public void setCradlecount(int cradlecount) {
		this.cradlecount = cradlecount;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
