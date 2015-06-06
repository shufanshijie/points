package com.shufan.points.dao;

/**
 * 积分dao工厂类
 * @author 商杰
 *
 */
public class PointsDaoFactory {
	
	private PointsDao pointsDao;
	
	private static PointsDaoFactory factory;
	
	private PointsDaoFactory(){}
	
	public static PointsDaoFactory getInstance(){
		if(factory == null){
			synchronized (PointsDaoFactory.class) {
				if(factory == null){
					factory = new PointsDaoFactory();
				}
			}
		}
		return factory;
	}

	public PointsDao getPointsDao() {
		return pointsDao;
	}

	public void setPointsDao(PointsDao pointsDao) {
		this.pointsDao = pointsDao;
	}
}
