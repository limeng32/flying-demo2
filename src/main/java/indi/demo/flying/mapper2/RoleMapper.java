package indi.demo.flying.mapper2;

import java.util.Collection;

import indi.demo.flying.entity2.Role;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;

@CacheRoleAnnotation(ObserverClass = {}, TriggerClass = { Role.class })
public interface RoleMapper {

	public Role mySelect(Object id);

	public Collection<Role> mySelectAll(Role t);

	public Role mySelectOne(Role t);

	public void myInsert(Role t);

	public int myUpdate(Role t);

	public int myUpdatePersistent(Role t);

	public int myDelete(Role t);

	public int myCount(Role t);
}
