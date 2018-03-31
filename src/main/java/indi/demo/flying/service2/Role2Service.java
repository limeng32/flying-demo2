package indi.demo.flying.service2;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indi.demo.flying.mapper2.Role2Mapper;
import indi.demo.flying.pojo.Role2_;

@Service
public class Role2Service implements Role2Mapper {

	@Autowired
	private Role2Mapper mapper;

	@Override
	public Role2_ select(Object id) {
		return mapper.select(id);
	}

	@Override
	public Role2_ selectOne(Role2_ t) {
		return mapper.selectOne(t);
	}

	@Override
	public void insert(Role2_ t) {
		mapper.insert(t);
	}

	@Override
	public int update(Role2_ t) {
		return mapper.update(t);
	}

	@Override
	public Collection<Role2_> selectAll(Role2_ t) {
		return mapper.selectAll(t);
	}

	@Override
	public int updatePersistent(Role2_ t) {
		return mapper.updatePersistent(t);
	}

	@Override
	public int delete(Role2_ t) {
		return mapper.delete(t);
	}

	@Override
	public int count(Role2_ t) {
		return mapper.count(t);
	}

	@Override
	public Role2_ selectWithoutCache(Object id) {
		return mapper.selectWithoutCache(id);
	}

}