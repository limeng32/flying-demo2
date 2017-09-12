package indi.demo.flying.entity2;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;

@TableMapperAnnotation(tableName = "PERSON")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "ID", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private String id;

	/**
	 * 姓名
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "NAME", jdbcType = JdbcType.VARCHAR)
	private String name;

	/**
	 * 关联的角色
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "ROLE_ID", jdbcType = JdbcType.VARCHAR, dbAssociationUniqueKey = "ID")
	private Role role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
