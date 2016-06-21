package gram.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;

import gram.model.domain.Truck;
import gram.util.DAOFactory;

public class TruckDAO {
	
	private static final String namespace = "truck";

	public static int checkTruck(int inputMemberNo) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			int memberNo = inputMemberNo;
			
			return (Integer)sqlSession.selectOne(namespace + ".checkTruck", memberNo);
			
		} finally {
			sqlSession.close();
		}
	}

	public static boolean createTruck(int inputMemberNo, String inputTruckTitle, String inputTruckInfo, String fileName, String truckLati, String truckLong ) throws IOException {
		// TODO Auto-generated method stub
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("memberNo", inputMemberNo);
			map.put("truckTitle", inputTruckTitle);
			System.out.println(inputTruckTitle);
			System.out.println("DAO");
			map.put("truckInfo", inputTruckInfo);
			map.put("truckPicName", fileName);
			map.put("truckLong", truckLong);
			map.put("truckLati", truckLati);
			
			int createCheck = 0;
			
			createCheck = (Integer) sqlSession.insert(namespace + ".createTruck", map);
			
			if(createCheck ==  1){
				sqlSession.commit();
				return true;
			}else {
				sqlSession.rollback();
				return false;
			}
			
		} finally{
			sqlSession.close();
		}
		
	}

	public static Truck getTruck(int inputMemberNo) {
		// TODO Auto-generated method stub
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			
			int memberNo = inputMemberNo;
			
			return (Truck) sqlSession.selectOne(namespace + ".getTruck", memberNo);
		
		}finally{
		
			sqlSession.close();
		
		}
	}
	
	public static int getTruckNo(int inputMemberNo){
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);

		try{

			int memberNo = inputMemberNo;

			return (Integer) sqlSession.selectOne(namespace + ".getTruckNo", memberNo);

		}finally{

			sqlSession.close();

		}
	}
	

}
