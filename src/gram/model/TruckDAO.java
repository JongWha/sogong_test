package gram.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;

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

	public static boolean createTruck(int inputMemberNo, String inputTruckTitle, String inputTruckInfo, Part filePart) throws IOException {
		// TODO Auto-generated method stub
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			InputStream inputStream = null;
			if(filePart != null){
				
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				inputStream = filePart.getInputStream();		
			}
			
			map.put("memberNo", inputMemberNo);
			map.put("truckTitle", inputTruckTitle);
			System.out.println(inputTruckTitle);
			System.out.println("DAO");
			map.put("truckInfo", inputTruckInfo);
			map.put("truckPic", inputStream);
			
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
	

}
