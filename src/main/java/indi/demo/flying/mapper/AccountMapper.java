package indi.demo.flying.mapper;

import java.util.Collection;
import java.util.Map;

import indi.demo.flying.pojo.Account_;
import indi.demo.flying.pojo.Role_;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Role_.class }, TriggerClass = { Account_.class })
public interface AccountMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ selectEverything(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ selectWithoutCache(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ selectWithoutRole(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account_> selectAll(Account_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account_> selectAllEverything(Account_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ selectOne(Account_ t);

	public void insert(Account_ t);

	public void insertSnowFlake(Account_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Account_ t);

	// @CacheAnnotation(role = CacheRoleType.Trigger)
	public int updateWithoutCache(Account_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Account_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Account_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Account_ t);

	public void loadRole(Role_ role, Account_ account);

	public void loadRoleDeputy(Role_ roleDeputy, Account_ accountDeputy);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Account_ selectDirect(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account_> selectAllDirect(Map<String, Object> map);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Account_> selectAccountByRole(Map<String, Object> map);
}
