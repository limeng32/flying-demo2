package indi.demo.flying.condition;

import indi.demo.flying.entity2.Role;
import indi.mybatis.flying.annotations.ConditionMapperAnnotation;
import indi.mybatis.flying.annotations.Or;
import indi.mybatis.flying.models.Conditionable;
import indi.mybatis.flying.models.Limitable;
import indi.mybatis.flying.models.Sortable;
import indi.mybatis.flying.statics.ConditionType;

public class RoleCondition extends Role implements Conditionable {

	private static final long serialVersionUID = 1L;

	private Limitable limiter;

	private Sortable sorter;

	@Override
	public Limitable getLimiter() {
		return limiter;
	}

	@Override
	public void setLimiter(Limitable limiter) {
		this.limiter = limiter;
	}

	@Override
	public Sortable getSorter() {
		return sorter;
	}

	@Override
	public void setSorter(Sortable sorter) {
		this.sorter = sorter;
	}

	@Or({ @ConditionMapperAnnotation(dbFieldName = "VALUE", conditionType = ConditionType.Equal),
			@ConditionMapperAnnotation(dbFieldName = "VALUE", conditionType = ConditionType.Equal) })
	private Object[] valueOr;

	public Object[] getValueOr() {
		return valueOr;
	}

	public void setValueOr(Object... valueOr) {
		this.valueOr = valueOr;
	}

}
