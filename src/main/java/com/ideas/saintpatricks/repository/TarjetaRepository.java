package com.ideas.saintpatricks.repository;

import com.ideas.saintpatricks.entities.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {
}