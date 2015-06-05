package com.shufan.points.dao.impl;

import haiyan.common.intf.database.orm.IDBRecord;

import com.shufan.points.dao.AbstractPointsDaoImpl;

/**
 * 单倍积分策略dao实现
 * 一元钱积一分
 * @author 商杰
 *
 */
public class HaploidPointsDaoImpl extends AbstractPointsDaoImpl {

	/**
	 * 根据订单信息计算累计的积分
	 */
	@Override
	public int computePoints(String userId, IDBRecord orderForm) {
		// TODO Auto-generated method stub
		return 0;
	}

}
