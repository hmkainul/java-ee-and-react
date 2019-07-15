package com.example.compounds.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.compounds.entity.Compound;

@Stateless
public class Compounds {

    @PersistenceContext
    EntityManager em;

    public Compound get(int id) {
        return em.find(Compound.class, id);
    }

    public List<Compound> getAll() {
        return em.createQuery("SELECT c FROM Compound c", Compound.class)
            .getResultList();
    }

    public Compound save(Compound c) {
        return em.merge(c);
    }

    public boolean delete(int id) {
        Compound compound = get(id);
        if (compound == null)
            return false;
        em.remove(compound);
        return true;
    }

}
