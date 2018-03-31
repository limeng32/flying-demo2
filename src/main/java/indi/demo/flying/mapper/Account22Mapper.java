package indi.demo.flying.mapper;

import java.util.Collection;

import indi.demo.flying.pojo.Account22;
import indi.demo.flying.pojo.Role2_;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Role2_.class }, TriggerClass = { Account22.class })
public interface Account22Mapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account22 selectWithoutCache(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account22 select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account22> selectAll(Account22 t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account22 selectOne(Account22 t);

	public void insert(Account22 t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Account22 t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Account22 t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Account22 t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Account22 t);

	public void loadRole2(Role2_ role2_, Account22 account22);
}
