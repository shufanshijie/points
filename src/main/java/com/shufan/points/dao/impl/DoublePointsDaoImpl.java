package com.shufan.points.dao.impl;

import haiyan.common.intf.database.orm.IDBRecord;


/**
 * 双倍积分策略dao实现
 * 一元钱积二分
 * @author 商杰
 *
 */
public class DoublePointsDaoImpl extends AbstractPointsDaoImpl {

	/**
	 * 根据订单信息计算累计的积分
	 */
	@Override
	public int computePoints(String userId, IDBRecord orderForm) {
		int points = orderForm.getInteger("ORDERPOINTS");//指定积分
		if(points != 0)
			return points;
		points = orderForm.getInteger("TOTALPRICE") * 2;//订单总额
		return points;
	}

}
