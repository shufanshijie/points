package com.shufan.points.dao;

/**
 * 积分dao工厂类
 * @author 商杰
 *
 */
public class PointsDaoFactory {
	
	private PointsDao pointsDao;
	
	public static PointsDaoFactory getInstance(){
		
		return null;
	}

	public PointsDao getPointsDao() {
		return pointsDao;
	}

	public void setPointsDao(PointsDao pointsDao) {
		this.pointsDao = pointsDao;
	}
}
