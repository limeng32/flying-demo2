package indi.demo.flying.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import indi.demo.flying.pojoHelper.PojoSupport;
import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table
public class Product extends PojoSupport<Product> implements Serializable {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation(dbFieldName = "id", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private String id;

	@Column
	private java.lang.String name;

	private java.lang.String name2;

	public String getId() {
		return id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.lang.String getName2() {
		return name2;
	}

	public void setName2(java.lang.String name2) {
		this.name2 = name2;
	}

}
