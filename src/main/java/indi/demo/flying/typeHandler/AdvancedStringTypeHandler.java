package indi.demo.flying.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import indi.demo.flying.ApplicationContextProvider;
import indi.demo.flying.service2.PersonService;

/**
 * 明确指定了MappedTypes和MappedJdbcTypes的StringTypeHandler
 * 
 * @author limeng32
 * 
 */
@MappedTypes({ String.class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class AdvancedStringTypeHandler extends BaseTypeHandler<String> implements TypeHandler<String> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		System.out.println(":::" + m());
		return rs.getString(columnName);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		System.out.println(":::" + m());
		return rs.getString(columnIndex);
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		System.out.println(":::" + m());
		return cs.getString(columnIndex);
	}

	protected Object m() {
		return (PersonService) ApplicationContextProvider.getApplicationContext().getBean(PersonService.class);
	}

}
