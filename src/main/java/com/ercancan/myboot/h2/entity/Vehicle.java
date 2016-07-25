package com.ercancan.myboot.h2.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author ercanc
 */
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String vehicleModel;
	private String vehicleBrand;
	private String website;
	@ManyToMany
	private List<Category> categories;

	public Vehicle() {
		super();
	}

	public Vehicle(String vehicleModel, String vehicleBrand, String website,  List<Category> categories) {
		super();
		this.vehicleModel = vehicleModel;
		this.vehicleBrand = vehicleBrand;
		this.website = website;
		this.categories = categories;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public boolean hasCategory(Category category) {
		for (Category containedCategory : getCategories()) {
			if (containedCategory.getId() == category.getId()) {
				return true;
			}
		}
		return false;
	}

}
