package com.furkanarslan.gallerist.repository;

import com.furkanarslan.gallerist.entitiy.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
