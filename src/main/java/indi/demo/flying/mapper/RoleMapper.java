package indi.demo.flying.mapper;

import java.util.Collection;
import java.util.Map;

import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.demo.flying.pojo.Role_;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Role_.class })
public interface RoleMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role_ selectWithoutCache(Object id);
	
	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role_ select(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role_ selectEverything(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role_ selectNoId(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Role_> selectAll(Role_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Role_ selectOne(Role_ t);

	public void insert(Role_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int update(Role_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updatePersistent(Role_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int delete(Role_ t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int count(Role_ t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int updateDirect(Map<String, Object> m);
}
