package indi.demo.flying.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;

@Table(name = "CART_COMMODITY")
public class CartCommodity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 对应的购物车
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "CART_ID", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "ID")
	private Cart cart;

	/**
	 * associate commodity
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "COMM_ID", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "ID")
	private Commodity commodity;

	/**
	 * commodity amount
	 * 
	 */
	@Column(name = "AMOUNT")
	private Integer amount;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
