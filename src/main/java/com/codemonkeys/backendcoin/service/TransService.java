package com.codemonkeys.backendcoin.service;

public interface TransService {
    public void extract(int actorId,String graphName) throws IllegalAccessException;

    public void submit(int graphId) throws NoSuchFieldException, IllegalAccessException;

    public void loadIntoDirectorToMovie();
}
