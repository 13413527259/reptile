package com.example.demo.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Seller implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	private String url;
	private String name;
	private String lv;
	private String address;
	private String info;
	private Integer top;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Seller(Integer id, String url, String name, String lv, String address, String info, Integer top) {
		this.id = id;
		this.url = url;
		this.name = name;
		this.lv = lv;
		this.address = address;
		this.info = info;
		this.top = top;
	}
	public Seller(Integer top,String url, String name, String lv, String address, String info) {
		this.url = url;
		this.name = name;
		this.lv = lv;
		this.address = address;
		this.info = info;
		this.top = top;
	}
	@Override
	public String toString() {
		return "Seller [id=" + id +", top=" + top + ", url=" + url + ", name=" + name + ", lv=" + lv + ", address=" + address
				+ ", info=" + info +  "]";
	}
	public Seller() {
	}

	
	
}
