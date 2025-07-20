package br.com.dagostini.cad_infracoes.core.entities;

import lombok.Getter;

@Getter
public enum Type {

    VELOCITY("velocity"),
    STOPCROSSWALKING("stopCrossWalking");

    private final String type;

    Type(String type) {
        this.type = type;
    }
}