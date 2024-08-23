package com.shinhan.dailyconsume.domain;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_rank")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RankEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rankId;
	
	private String rankName;
	private Long score;
	private String rankImg;
	
	@OneToMany(mappedBy = "rank"
			, fetch = FetchType.LAZY)
	private List<MemberEntity> member;
	
}
