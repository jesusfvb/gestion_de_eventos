package com.backend.backend.service;

import com.backend.backend.repository.entity.Convocatoria;
import com.backend.backend.repository.entity.MyUser;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConvocatoriaService {

    public List<Convocatoria> list();

    public Boolean isConvocatoriaBoss(String username);

    public Boolean isConvocatoriaBoss(Integer id);

    public void save(String text);

    public void delete(Integer[] ids);

    public void update(Integer id, String text);

    public void adjuntarBoss(Integer id, MyUser user);

}
