package com.algaworks.bandayou.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.bandayou.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {
}