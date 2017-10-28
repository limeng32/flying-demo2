package indi.demo.flying.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import com.alibaba.fastjson.annotation.JSONField;

import indi.demo.flying.entity2.Person;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table(name = "CART")
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * deal success?
	 * 
	 */
	@Column(name = "DEAL")
	private Boolean deal;

	/**
	 * 交易时间
	 * 
	 */
	@Column(name = "DEAL_TIME")
	@JSONField(format = "yyyy-MM-dd hh:mm:ss")
	private java.util.Date dealTime;

	/**
	 * 跨数据源关联 person
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "PERSON_ID", jdbcType = JdbcType.VARCHAR, dbAssociationTypeHandler = indi.demo.flying.typeHandler.PersonTypeHandler.class)
	private Person person;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getDeal() {
		return deal;
	}

	public void setDeal(Boolean deal) {
		this.deal = deal;
	}

	public java.util.Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(java.util.Date dealTime) {
		this.dealTime = dealTime;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
