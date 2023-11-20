package com.nero.carupapi.model;

public enum EstadoSrv {
    PENDIENTE("P"), ASIGNADO("A"), CANCELADO("C");

    private String letra;

    EstadoSrv(String letra){
        this.letra = letra;
    }

    public String getLetra(){
        return letra;
    }

    public String buscarPorLetra(String letra){
        for (EstadoSrv miEnum : EstadoSrv.values()) {
            if (miEnum.getLetra().equals(letra)) {
                return miEnum.name();
            }
        }
        return "";
    }
}
