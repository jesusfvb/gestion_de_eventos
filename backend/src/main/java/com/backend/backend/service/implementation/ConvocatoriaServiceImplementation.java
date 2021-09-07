package com.backend.backend.service.implementation;

import com.backend.backend.repository.ConvocatoriaRepository;
import com.backend.backend.repository.entity.Convocatoria;
import com.backend.backend.repository.entity.MyUser;
import com.backend.backend.service.ConvocatoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvocatoriaServiceImplementation implements ConvocatoriaService {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Override
    public List<Convocatoria> list() {
        return convocatoriaRepository.findAll();
    }

    @Override
    public List<Convocatoria> listByBossUsername(String username) {
        return convocatoriaRepository.findAllByConvocatoriaBossUsername(username);
    }

    @Override
    public Convocatoria getConvocatoriaById(Integer id) {
        return convocatoriaRepository.findById(id).get();
    }

    @Override
    public Boolean isConvocatoriaBoss(String username) {
        return convocatoriaRepository.existsByConvocatoriaBossUsername(username);
    }

    @Override
    public Boolean isConvocatoriaBoss(Integer id) {
        return convocatoriaRepository.existsByConvocatoriaBossId(id);
    }

    @Override
    public void save(String text) {
        convocatoriaRepository.save(new Convocatoria(text));
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            convocatoriaRepository.deleteById(id);
        }

    }

    @Override
    public void update(Integer id, String text) {
        Convocatoria convocatoria = convocatoriaRepository.findById(id).get();
        convocatoria.setText(text);
        convocatoriaRepository.save(convocatoria);
    }

    @Override
    public void adjuntarBoss(Integer id, MyUser user) {
        Convocatoria convocatoria = convocatoriaRepository.findById(id).get();
        convocatoria.setConvocatoriaBoss(user);
        convocatoriaRepository.save(convocatoria);
    }

}
