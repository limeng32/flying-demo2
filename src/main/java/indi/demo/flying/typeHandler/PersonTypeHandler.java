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
import indi.demo.flying.entity2.Person;
import indi.demo.flying.service2.PersonService;

/**
 * 处理Person表跨库关联的TypeHandler
 * 
 * @author limeng32
 * 
 */
@MappedTypes({ Person.class })
@MappedJdbcTypes({ JdbcType.VARCHAR })

public class PersonTypeHandler extends BaseTypeHandler<Person> implements TypeHandler<Person> {

	@Override
	public Person getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Person getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Person getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Person arg2, JdbcType arg3) throws SQLException {
		arg0.setString(arg1, arg2.getId());
	}

	private PersonService getService() {
		return (PersonService) ApplicationContextProvider.getApplicationContext().getBean(PersonService.class);
	}
}
