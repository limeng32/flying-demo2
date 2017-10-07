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
import indi.demo.flying.entity.Commodity;
import indi.demo.flying.service.CommodityService;

/**
 * 处理Commodity表跨库关联的TypeHandler
 * 
 * @author limeng32
 * 
 */
@MappedTypes({ Commodity.class })

public class CommodityTypeHandler extends BaseTypeHandler<Commodity> implements TypeHandler<Commodity> {

	@Override
	public Commodity getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Commodity getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Commodity getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Commodity arg2, JdbcType arg3)
			throws SQLException {
		if (arg2 != null) {
			arg0.setString(arg1, arg2.getId());
		}
	}

	/*
	 * 因为此TypeHandler并非第一时间初始化，不能以@Autowired方式调用CommodityService，所以采用下面的方式
	 */
	private CommodityService getService() {
		return (CommodityService) ApplicationContextProvider.getApplicationContext().getBean(CommodityService.class);
	}
}
