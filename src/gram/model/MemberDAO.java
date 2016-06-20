package gram.model;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import gram.model.domain.Member;
import gram.util.DAOFactory;

public class MemberDAO {
	
	private static final String namespace ="member";
	
	public static int checkMember(String inputMemberId){
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			
			String memberId = inputMemberId;
			
			return (Integer)sqlSession.selectOne(namespace + ".checkMember", memberId);
			
		} finally {
			sqlSession.close();
		}
	}
	
	public static boolean joinMember(String inputMemberId, String inputMemberPassword, int inputMemberStatus){
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", inputMemberId);
			map.put("memberPassword", inputMemberPassword);
			map.put("memberStatus", inputMemberStatus);
			int check = 0;
			
			
			check = (Integer) sqlSession.insert(namespace +".joinMember", map);
			
			if(check == 1){
				sqlSession.commit();
				return true;
			}else {
				sqlSession.rollback();
				return false;
			}
			
		} finally {
			sqlSession.close();
		}
	}
	
	public static int loginMember(String inputMemberId, String inputMemberPassword){
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
	
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", inputMemberId);
			map.put("memberPassword", inputMemberPassword);
				
			return (Integer) sqlSession.selectOne(namespace+".loginMember", map);
			
		} finally {
			sqlSession.close();
		}
	}
	
	public static int getMemberNo (String inputMemberId){
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			
			String memberId = inputMemberId;
			
			return (Integer) sqlSession.selectOne(namespace +".getMemberNo", memberId);
		
		} finally {
		
			sqlSession.close();
		}
	}
	
public static Member getMember (String inputMemberId, String inputMemberPassword){
		
		SqlSession sqlSession = DAOFactory.getSqlSession(true);
		
		try{
			HashMap<String, Object> map = new HashMap<String,Object>();			
			
			map.put("memberId",inputMemberId);
			map.put("memberPassword", inputMemberPassword);
			
			return (Member) sqlSession.selectOne(namespace +".getMember", map);
		
		} finally {
		
			sqlSession.close();
		}
	}

}
