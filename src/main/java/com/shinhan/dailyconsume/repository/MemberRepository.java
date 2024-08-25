package com.shinhan.dailyconsume.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String>, QuerydslPredicateExecutor<MemberEntity>{

	MemberEntity findByMemberId(String memberId);
	
	//로그인한 유저의 나이대와 같은 멤버 리스트를 가져오는 쿼리 
	@Query(value = " SELECT * FROM t_member m "
			+ " WHERE TIMESTAMPDIFF(YEAR, m.member_birth, curdate()) "
			+ " BETWEEN ?1 AND ?1 +9 ", nativeQuery = true)
	List<MemberEntity> findPeerByAge(int age);
	
	//유저의 모든 결제정보를 가져오는 쿼리 
	@Query("SELECT ph FROM PayHistoryEntity ph " +
	           "JOIN ph.memberCard mc " +
	           "JOIN mc.member m " +
	           "WHERE m.memberId = :memberId and DATE_FORMAT(ph.payDate,'%Y-%m')=DATE_FORMAT(now(),'%Y-%m')")
	List<PayHistoryEntity> findPayHistoriesByMemberId(@Param("memberId") String memberId);

	// 10대 조회
	@Query("SELECT m"
			+ " FROM MemberEntity m"
			+ " WHERE (YEAR(CURRENT_DATE) - YEAR(m.memberBirth)) BETWEEN 10 AND 19")
	List<MemberEntity> teenMember();
	
	// 20대 남성 조회
	@Query("SELECT m"
			+ " FROM MemberEntity m"
			+ " WHERE (YEAR(CURRENT_DATE) - YEAR(m.memberBirth)) BETWEEN 20 AND 29"
			+ " AND m.memberGender = 'Male'")
	List<MemberEntity> twentyMaleMember();
		
	// 20대 여성 조회
	@Query("SELECT m"
			+ " FROM MemberEntity m"
			+ " WHERE (YEAR(CURRENT_DATE) - YEAR(m.memberBirth)) BETWEEN 20 AND 29"
			+ " AND m.memberGender = 'Female'")
	List<MemberEntity> twentyFemaleMember();
}
