package indi.demo.flying.entity2;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import indi.mybatis.flying.annotations.FieldMapperAnnotation;
import indi.mybatis.flying.annotations.TableMapperAnnotation;

@TableMapperAnnotation(tableName = "ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键，以UUID方式保存
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "ID", jdbcType = JdbcType.VARCHAR, isUniqueKey = true)
	private String id;

	/**
	 * 角色名
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "NAME", jdbcType = JdbcType.VARCHAR)
	private String name;

	/**
	 * 角色值
	 * 
	 */
	@FieldMapperAnnotation(dbFieldName = "VALUE", jdbcType = JdbcType.VARCHAR)
	private RoleEnum value;
}
