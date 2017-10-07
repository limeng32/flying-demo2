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
import indi.demo.flying.entity.Cart;
import indi.demo.flying.service.CartService;

/**
 * 处理Cart表跨库关联的TypeHandler
 * 
 * @author limeng32
 * 
 */
@MappedTypes({ Cart.class })

public class CartTypeHandler extends BaseTypeHandler<Cart> implements TypeHandler<Cart> {

	@Override
	public Cart getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Cart getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public Cart getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		if (arg0.getString(arg1) == null) {
			return null;
		}
		return (getService().mySelect(arg0.getString(arg1)));
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Cart arg2, JdbcType arg3) throws SQLException {
		if (arg2 != null) {
			arg0.setString(arg1, arg2.getId());
		}
	}

	/*
	 * 因为此TypeHandler并非第一时间初始化，不能以@Autowired方式调用CartService，所以采用下面的方式
	 */
	private CartService getService() {
		return (CartService) ApplicationContextProvider.getApplicationContext().getBean(CartService.class);
	}
}
