package indi.demo.flying.mapper2;

import java.util.Collection;

import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.Role;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;

@CacheRoleAnnotation(ObserverClass = { Role.class }, TriggerClass = { Person.class })
public interface PersonMapper {

	public Person mySelect(Object id);

	public Collection<Person> mySelectAll(Person t);

	public Person mySelectOne(Person t);

	public void myInsert(Person t);

	public int myUpdate(Person t);

	public int myUpdatePersistent(Person t);

	public int myDelete(Person t);

	public int myCount(Person t);
}
