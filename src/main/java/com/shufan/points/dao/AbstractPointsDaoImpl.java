package com.shufan.points.dao;

import haiyan.common.intf.database.orm.IDBResultSet;

/**
 * 积分数据访问对象抽象类
 * @author 商杰
 *
 */
public abstract class AbstractPointsDaoImpl implements PointsDao {
	/**
	 * 获取全部积分明细列表
	 */
	@Override
	public IDBResultSet getAllPointsList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取收入积分明细列表
	 */
	@Override
	public IDBResultSet getIncomePointsList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取支出积分明细列表
	 */
	@Override
	public IDBResultSet getExpenditurePointsList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取当前可用积分
	 */
	@Override
	public int getPoints(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * 消费积分
	 */
	@Override
	public boolean consumePoints(String userId, int points) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 添加积分
	 */
	@Override
	public int addPoints(String userId, int points) {
		// TODO Auto-generated method stub
		return 0;
	}

}
