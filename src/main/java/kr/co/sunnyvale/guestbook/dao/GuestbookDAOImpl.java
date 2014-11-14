package kr.co.sunnyvale.guestbook.dao;

import java.util.List;

import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.dto.ImageDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookDAOImpl implements GuestbookDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NS = "kr.co.sunnyvale.mybatis.Guestbook.";

	
	
	@Override
	public List<GuestbookDTO> selectList() {
		return sqlSession.selectList(NS+"selectList");
	}

	@Override
	public int selectCount() {
		return sqlSession.selectOne(NS+"selectCount");
	}

	@Override
	public int insertGuestbook(GuestbookDTO guestbook) {
		return sqlSession.insert(NS+"insertGuestbook", guestbook);
	}

	@Override
	public int insertImageDTO(ImageDTO imageDTO) {
		return sqlSession.insert(NS+"insertImage", imageDTO);
	}

	@Override
	public List<ImageDTO> selectImages(int guestbookSeq) {
		return sqlSession.selectList(NS+"selectImageList", guestbookSeq);
	}

	@Override
	public ImageDTO selectImage(int imageSeq) {
		return sqlSession.selectOne(NS+"selectImage", imageSeq);
	}
	
	
}