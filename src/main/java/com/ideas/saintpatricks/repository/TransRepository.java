package com.ideas.saintpatricks.repository;

import com.ideas.saintpatricks.entities.Trans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface TransRepository extends JpaRepository<Trans, String> {


    @Override
    Optional<Trans> findById( String s);



}