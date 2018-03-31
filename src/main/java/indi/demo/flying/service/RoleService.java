package indi.demo.flying.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper.RoleMapper;
import indi.demo.flying.pojo.Role_;

@Service
public class RoleService implements RoleMapper {

	@Autowired
	private RoleMapper mapper;

	@Override
	public Role_ select(Object id) {
		return mapper.select(id);
	}

	@Override
	public Role_ selectEverything(Object id) {
		return mapper.selectEverything(id);
	}

	@Override
	public Role_ selectNoId(Object id) {
		return mapper.selectNoId(id);
	}

	@Override
	public Role_ selectOne(Role_ t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(Role_ t) {
		mapper.insert(t);
	}

	@Override
	public int update(Role_ t) {
		return mapper.update(t);
	}

	@Override
	public Collection<Role_> selectAll(Role_ t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(Role_ t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(Role_ t) {
		return mapper.delete(t);
	}

	@Override
	public int count(Role_ t) {
		return mapper.count(t);
	}

	@Override
	public int updateDirect(Map<String, Object> m) {
		return mapper.updateDirect(m);
	}

	@Override
	public Role_ selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}

}