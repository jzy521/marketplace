package com.intuit.marketplace.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

public class Bid {

	private Integer id;
	private Integer projectId;
	private Integer userId;
	/**
	 * bidTime only use for Database
	 */
	@JsonIgnore
	private Timestamp bidTime;
	private BigDecimal bidAmount;
	private BigDecimal bidPrice;
	/**
	 * I use bidTimeDate convert to bidTime. because in Database, only Timestampe
	 * type can have HH:mm:ss.SSS
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date bidTimeDate;

	public Bid(Integer projectId, Integer userId, Timestamp bidTime, BigDecimal bidAmount, BigDecimal bidPrice) {
		super();
		this.projectId = projectId;
		this.userId = userId;
		this.bidTime = bidTime;
		this.bidAmount = bidAmount;
		this.bidPrice = bidPrice;

	}

	public Bid() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(BigDecimal bidAmount) {
		this.bidAmount = bidAmount;
	}


	public Timestamp getBidTime() {
		return bidTime;
	}

	/**
	 * populate bidTimeDate using DB bidTime
	 */
	public void setBidTime(Timestamp bidTime) {
		this.bidTime = bidTime;
		Date DbDate = new Date(this.getBidTime().getTime());
		this.setBidTimeDate(DbDate);
	}

	@ApiModelProperty(value = "format : yyyy-MM-dd HH:mm:ss.SSS", example = "2018-05-24 16:32:28.111")
	public Date getBidTimeDate() {
		return bidTimeDate;
	}

	public void setBidTimeDate(Date bidTimeDate) {
		this.bidTimeDate = bidTimeDate;
	}

	public BigDecimal getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bidAmount == null) ? 0 : bidAmount.hashCode());
		result = prime * result + ((bidPrice == null) ? 0 : bidPrice.hashCode());
		result = prime * result + ((bidTime == null) ? 0 : bidTime.hashCode());
		result = prime * result + ((bidTimeDate == null) ? 0 : bidTimeDate.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		if (bidAmount == null) {
			if (other.bidAmount != null)
				return false;
		} else if (!bidAmount.equals(other.bidAmount))
			return false;
		if (bidPrice == null) {
			if (other.bidPrice != null)
				return false;
		} else if (!bidPrice.equals(other.bidPrice))
			return false;
		if (bidTime == null) {
			if (other.bidTime != null)
				return false;
		} else if (!bidTime.equals(other.bidTime))
			return false;
		if (bidTimeDate == null) {
			if (other.bidTimeDate != null)
				return false;
		} else if (!bidTimeDate.equals(other.bidTimeDate))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bid [id=" + id + ", projectId=" + projectId + ", userId=" + userId + ", bidTime=" + bidTime
				+ ", bidAmount=" + bidAmount + ", bidPrice=" + bidPrice + ", bidTimeDate=" + bidTimeDate + "]";
	}

	

}
