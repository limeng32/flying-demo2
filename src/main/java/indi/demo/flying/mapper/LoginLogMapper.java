package indi.demo.flying.mapper;

import java.util.Collection;

import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.LoginLog_;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Account_.class }, TriggerClass = { LoginLog_.class })
public interface LoginLogMapper  {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public LoginLog_ selectWithoutCache(Object id);
	
	@CacheAnnotation(role = CacheRoleType.Observer)
	public LoginLog_ select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<LoginLog_> selectAll(LoginLog_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public LoginLog_ selectOne(LoginLog_ t);

	public void insert(LoginLog_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(LoginLog_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(LoginLog_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(LoginLog_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(LoginLog_ t);
	
	public void loadAccount(Account_ account, LoginLog_ loginLog);

}
