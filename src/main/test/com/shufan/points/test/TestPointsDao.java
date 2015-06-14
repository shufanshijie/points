package com.shufan.points.test;

import haiyan.common.config.PathUtil;
import haiyan.common.intf.database.orm.IDBRecord;
import haiyan.orm.database.DBRecord;

import java.io.File;
import java.util.Properties;

import com.shufan.points.common.ContextListener;
import com.shufan.points.dao.PointsDao;
import com.shufan.points.dao.PointsDaoFactory;

public class TestPointsDao {

	/**
	 * @param args
	 * @throws Throwable 
	 */
	public static void main(String[] args) throws Throwable {
		String s = System.getProperty("user.dir");
		Properties p = PathUtil.getEnvVars();
		p.setProperty("HAIYAN_HOME", s+File.separator+"WebContent");
		ContextListener.init(s+File.separator+"WebContent"+File.separator+"WEB-INF");
		ContextListener.USE_ES=true; 
		
		
		test();
		
	}

	private static void test() {
		PointsDao dao = PointsDaoFactory.createPointsDao(null);
		String userId = "1";
		IDBRecord orderForm = new DBRecord();
		orderForm.set("IMAGE", "http://asearch.alicdn.com/bao/uploaded/i4/19371069281480333/TB2tHmpaVXXXXajXXXXXXXXXXXX_!!26159371-0-saturn_solar.jpg_230x230.jpg_.webp");
		orderForm.set("NAME", "面包+牛奶+鸡蛋");
		orderForm.set("ID", "123");
		orderForm.set("TOTALPRICE", 10);
//		orderForm.set("POINTSTYPE", "购物赠送");
		int points = dao.addPoints(userId, orderForm);
		System.out.println("##### 本次获得积分 ： "+ points);
		orderForm.set("ORDERPOINTS", 10);
		orderForm.set("CONSUMEPOINTS", -5);
		points = dao.consumePoints(userId, orderForm);
		System.out.println("##### 本次获得积分 ： "+ points);
	}

}
