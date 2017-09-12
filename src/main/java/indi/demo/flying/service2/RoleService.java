package indi.demo.flying.service2;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.entity2.Role;
import indi.demo.flying.mapper2.RoleMapper;

@Service
public class RoleService implements RoleMapper {

	@Autowired
	private RoleMapper mapper;

	@Override
	public Role mySelect(Object id) {
		return mapper.mySelect(id);
	}

	@Override
	public Collection<Role> mySelectAll(Role t) {
		return mapper.mySelectAll(t);
	}

	@Override
	public Role mySelectOne(Role t) {
		return mapper.mySelectOne(t);
	}

	@Override
	public void myInsert(Role t) {
		mapper.myInsert(t);
	}

	@Override
	public int myUpdate(Role t) {
		return mapper.myUpdate(t);
	}

	@Override
	public int myUpdatePersistent(Role t) {
		return mapper.myUpdatePersistent(t);
	}

	@Override
	public int myDelete(Role t) {
		return mapper.myDelete(t);
	}

	@Override
	public int myCount(Role t) {
		return mapper.myCount(t);
	}

}
