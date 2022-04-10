package br.com.climbpedia.api;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.climbpedia.model.Via;

@ApplicationScoped
public class ViasService {

    @Inject
    EntityManager em; 

    @Transactional 
    public void createVia(String nome, String graduacao, String descricao) {
        Via via = new Via(nome, graduacao, descricao);
//        via.setName(viaDescription);
        em.persist(via);
    }
}