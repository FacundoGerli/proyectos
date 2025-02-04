package com.reserva.auth.repository;

import com.reserva.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ITokenRepository extends JpaRepository<Token,Long>{
}
