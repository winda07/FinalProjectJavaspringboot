package com.winda.bus.payload.request;

import com.winda.bus.models.User;

import io.swagger.annotations.ApiModelProperty;

public class BusCustomRequest {

	@ApiModelProperty(hidden = true)
	private Long id;

	private String code;

	private int capacity;

	private String make;

	@ApiModelProperty(hidden = true)
	private User agencyId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public User getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(User agencyId) {
		this.agencyId = agencyId;
	}

	public BusCustomRequest(Long id, String code, int capacity, String make, User agencyId) {
		super();
		this.id = id;
		this.code = code;
		this.capacity = capacity;
		this.make = make;
		this.agencyId = agencyId;
	}

	public BusCustomRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

}
