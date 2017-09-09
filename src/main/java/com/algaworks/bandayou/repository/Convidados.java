package com.algaworks.bandayou.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.bandayou.model.Convidado;

public interface Convidados extends JpaRepository<Convidado, Long> {
}
