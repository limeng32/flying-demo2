package indi.demo.flying.mapper;

import java.util.Collection;

import indi.demo.flying.entity.Cart;
import indi.demo.flying.entity2.Person;
import indi.demo.flying.entity2.Role;
import indi.mybatis.flying.annotations.CacheAnnotation;
import indi.mybatis.flying.annotations.CacheRoleAnnotation;
import indi.mybatis.flying.statics.CacheRoleType;

@CacheRoleAnnotation(ObserverClass = { Person.class }, TriggerClass = { Cart.class })
public interface CartMapper {

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Cart mySelect(Object id);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Collection<Cart> mySelectAll(Cart t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public Cart mySelectOne(Cart t);

	public void myInsert(Cart t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdate(Cart t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myUpdatePersistent(Cart t);

	@CacheAnnotation(role = CacheRoleType.Trigger)
	public int myDelete(Cart t);

	@CacheAnnotation(role = CacheRoleType.Observer)
	public int myCount(Cart t);
}
