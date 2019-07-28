package com.example.elements.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.example.elements.entity.Element;

@Stateless
public class Elements {

    @PersistenceContext
    EntityManager em;

    public Element get(int number) {
        return em.find(Element.class, number);
    }

    public List<Element> getAll() {
        return em.createQuery(
            "SELECT e FROM Element e ORDER BY e.number", Element.class)
            .getResultList();
    }

    public Element save(Element e) {
        return em.merge(e);
    }

    public boolean delete(int number) {
        Element element = get(number);
        if (element == null)
            return false;
        em.remove(get(number));
        return true;
    }

}
