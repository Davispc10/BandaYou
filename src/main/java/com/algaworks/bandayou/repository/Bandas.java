package com.algaworks.bandayou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.bandayou.model.Banda;


public interface Bandas extends JpaRepository<Banda, Long> {
	@Query(value = "select * from banda b order by b.nome", nativeQuery = true)
	
//	@Query(value = "select b.nome, b.id, COUNT(m.id) as mId from banda b inner join musica m On m.id = b.id"
//			+ " order by b.nome", nativeQuery = true)
	List<Banda> findAllBandasinOrder();
}
