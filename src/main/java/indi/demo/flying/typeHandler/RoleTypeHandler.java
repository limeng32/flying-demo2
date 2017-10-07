package indi.demo.flying.typeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import indi.demo.flying.ApplicationContextProvider;
import indi.demo.flying.entity2.Role;
import indi.demo.flying.service2.RoleService;

/**
 * 处理Role表跨库关联的TypeHandler
 * 
 * @author limeng32
 * 
 */
@MappedTypes({ Role.class })

public class RoleTypeHandler extends BaseTypeHandler<Role> implements TypeHandler<Role> {

	@Override
	public Role getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Role getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Role getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Role arg2, JdbcType arg3) throws SQLException {
		if (arg2 != null) {
			arg0.setString(arg1, arg2.getId());
		}
	}

	private RoleService getService() {
		return (RoleService) ApplicationContextProvider.getApplicationContext().getBean(RoleService.class);
	}
}
