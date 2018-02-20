package com.intuit.marketplace.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Project {

	private Integer id;
	private String name;
	private Integer userId;
	private boolean open;
	private BigDecimal budget;
	private String description;
	/**
	 * endDate only use for Database
	 */
	@JsonIgnore
	private Timestamp endDate;
	private Integer bidNum;
	private BigDecimal winningPrice;
	private BigDecimal currentPrice;
	/**
	 * I use endDateDate convert to endDate.because in Database, only Timestampe
	 * type can have HH:mm:ss.SSS
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date endDateDate;

	public Project() {
		super();
	}

	public Project(String name, int userId, boolean open, BigDecimal budget) {
		super();
		this.name = name;
		this.userId = userId;
		this.open = open;
		this.budget = budget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ApiModelProperty(hidden = true)
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * populate endDateDate using DB endDate
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
		Date DbDate = new Date(this.getEndDate().getTime());
		this.setEndDateDate(DbDate);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBidNum() {
		return bidNum;
	}

	public void setBidNum(Integer bidNum) {
		this.bidNum = bidNum;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public BigDecimal getWinningPrice() {
		return winningPrice;
	}

	public void setWinningPrice(BigDecimal winningPrice) {
		this.winningPrice = winningPrice;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	@ApiModelProperty(value = "format : yyyy-MM-dd HH:mm:ss.SSS", example = "2018-05-24 16:32:28.111")
	public Date getEndDateDate() {
		return endDateDate;
	}

	public void setEndDateDate(Date endDateDate) {
		this.endDateDate = endDateDate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (budget != other.budget)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (open != other.open)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", userId=" + userId + ", open=" + open + ", budget=" + budget
				+ ", endDate=" + endDate + ", bidNum=" + bidNum + ", winningPrice=" + winningPrice + ", currentPrice="
				+ currentPrice + "]";
	}

}
