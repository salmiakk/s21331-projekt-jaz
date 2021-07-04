package com.pjatk.jazs21331nbp.repository;

import com.pjatk.jazs21331nbp.model.NbpResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NbpRepository extends JpaRepository<NbpResponse, Long> {
}
