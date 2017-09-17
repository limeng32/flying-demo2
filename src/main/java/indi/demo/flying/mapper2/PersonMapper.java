package indi.demo.flying.mapper2;

import java.util.Collection;

import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.Role;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Role.class }, TriggerClass = { Person.class })
public interface PersonMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Person mySelect(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Person> mySelectAll(Person t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Person mySelectOne(Person t);

	public void myInsert(Person t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdate(Person t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdatePersistent(Person t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myDelete(Person t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int myCount(Person t);
}
