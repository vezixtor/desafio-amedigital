package com.herokuapp.desafioamedigital.bdd.scenario.common;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseScenario {

    public abstract CrudRepository getRepository();

    private List<String> erros = new ArrayList<String>();

    protected void runDelete(Runnable function) {
        try {
            function.run();
        } catch (Exception e) {
            //System.out.println("Nao foi possivel deletar por causa da arvore de cenarios => " + ignored.getMessage());
            erros.add("Nao foi possivel deletar por causa da arvore de cenarios => " + e.getMessage());
        }
    }

    protected void runDelete(CrudRepository repository) {
        repository.findAll().forEach(entity -> runDelete(() -> repository.delete(entity)));
    }

    public void deleteAll() {
        CrudRepository repository = getRepository();
        if (repository.count() > 0) {
            runDelete(repository);
        }
        erros.clear();
    }

    public boolean isClean() {
        long count = getRepository().count();
        if (count > 0) {
            String repositorySujo = getRepository().toString();
            throw new RuntimeException("Base Suja: " + repositorySujo + ", erros: " + String.join("\n", erros));
        }
        return count == 0;
    }
}
