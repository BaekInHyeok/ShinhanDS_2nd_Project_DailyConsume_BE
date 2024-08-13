package com.shinhan.dailyconsume.domain;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_member_card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberCardEntity {
	@Id
	String cardNum;
	Timestamp expirationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cardId")
	private CardEntity card;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")
	private MemberEntity member;
}
