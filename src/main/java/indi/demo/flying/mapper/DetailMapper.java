package indi.demo.flying.mapper;

import java.util.Collection;

import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.demo.flying.pojo.Detail_;
import indi.demo.flying.pojo.LoginLog_;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { LoginLog_.class }, TriggerClass = { Detail_.class })
public interface DetailMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Detail_ selectWithoutCache(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Detail_ select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Detail_> selectAll(Detail_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Detail_ selectOne(Detail_ t);

	public void insert(Detail_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Detail_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Detail_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Detail_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Detail_ t);

	public void loadLoginLog(LoginLog_ loginLog, Detail_ detail);
}
