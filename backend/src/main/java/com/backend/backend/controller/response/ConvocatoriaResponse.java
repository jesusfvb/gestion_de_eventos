package com.backend.backend.controller.response;

public class ConvocatoriaResponse {

    private Integer id;

    private String text;

    private UserResponse convocatoriaBoss;

    public ConvocatoriaResponse(Integer id, String text, UserResponse convocatoriaBoss) {
        this.id = id;
        this.text = text;
        this.convocatoriaBoss = convocatoriaBoss;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public UserResponse getConvocatoriaBoss() {
        return convocatoriaBoss;
    }
}
