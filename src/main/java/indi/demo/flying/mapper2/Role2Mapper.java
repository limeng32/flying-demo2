package indi.demo.flying.mapper2;

import java.util.Collection;

import indi.demo.flying.pojo.Role2_;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Role2_.class })
public interface Role2Mapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role2_ selectWithoutCache(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role2_ select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Role2_> selectAll(Role2_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role2_ selectOne(Role2_ t);

	public void insert(Role2_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Role2_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Role2_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Role2_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Role2_ t);

}
