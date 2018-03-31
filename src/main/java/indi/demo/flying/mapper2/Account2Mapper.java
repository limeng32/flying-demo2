package indi.demo.flying.mapper2;

import java.util.Collection;

import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.demo.flying.pojo.Account2_;
import indi.demo.flying.pojo.Role2_;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Role2_.class }, TriggerClass = { Account2_.class })
public interface Account2Mapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account2_ selectWithoutCache(Object id);
	
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account2_ select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account2_> selectAll(Account2_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account2_ selectOne(Account2_ t);

	public void insert(Account2_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Account2_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Account2_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Account2_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Account2_ t);

	public void loadRole2(Role2_ role2_, Account2_ account2_);
}
