package com.backend.backend.repository;

import com.backend.backend.repository.entity.Convocatoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvocatoriaRepository extends JpaRepository<Convocatoria, Integer> {

    public List<Convocatoria> findAllByConvocatoriaBossUsername(String username);

    public Boolean existsByConvocatoriaBossId(Integer id);

    public Boolean existsByConvocatoriaBossUsername(String username);

}
