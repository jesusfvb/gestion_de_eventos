package com.backend.backend.controller.response;

public class EventoResponse {

    private Integer id;

    private String name;

    private String state;

    public EventoResponse(Integer id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

}
