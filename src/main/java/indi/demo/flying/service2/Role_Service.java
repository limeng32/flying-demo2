package indi.demo.flying.service2;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper2.Role_Mapper;
import indi.demo.flying.pojo.Role;

@Service
public class Role_Service implements Role_Mapper {

	@Autowired
	private Role_Mapper mapper;

	@Override
	public Role mySelectWithoutCache(Object id) {
		return mapper.mySelectWithoutCache(id);
	}

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

	@Override
	public int updateDirectWithoutCache(Map<String, Object> t) {
		return mapper.updateDirectWithoutCache(t);
	}

	@Override
	public int updateDirect(Map<String, Object> t) {
		return mapper.updateDirect(t);
	}

}
