package ar.com.tempo.test.repository;

import ar.com.tempo.test.domain.ParameterSap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterSapDao extends JpaRepository<ParameterSap, Long> {


}
