package com.techchallenge4.ms_logistica.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public enum EstadoEnum {

    // Região Norte
    AM("Amazonas", 69000, 69899, RegiaoEnum.NORTE),
    AC("Acre", 69900, 69999, RegiaoEnum.NORTE),
    AP("Amapá", 68900, 68999, RegiaoEnum.NORTE),
    PA("Pará", 66000, 68899, RegiaoEnum.NORTE),
    RO("Rondônia", 76800, 76999, RegiaoEnum.NORTE),
    RR("Roraima", 69300, 69399, RegiaoEnum.NORTE),
    TO("Tocantins", 77000, 77999, RegiaoEnum.NORTE),

    // Região Nordeste
    BA("Bahia", 40000, 48999, RegiaoEnum.NORDESTE),
    CE("Ceará", 60000, 63999, RegiaoEnum.NORDESTE),
    MA("Maranhão", 65000, 65999, RegiaoEnum.NORDESTE),
    PB("Paraíba", 58000, 58999, RegiaoEnum.NORDESTE),
    PE("Pernambuco", 50000, 56999, RegiaoEnum.NORDESTE),
    PI("Piauí", 64000, 64999, RegiaoEnum.NORDESTE),
    RN("Rio Grande do Norte", 59000, 59999, RegiaoEnum.NORDESTE),
    SE("Sergipe", 49000, 49999, RegiaoEnum.NORDESTE),
    AL("Alagoas", 57000, 57999, RegiaoEnum.NORDESTE),

    // Região Centro-Oeste
    DF("Distrito Federal", 70000, 72799, RegiaoEnum.CENTRO_OESTE),
    GO("Goiás", 74000, 76999, RegiaoEnum.CENTRO_OESTE),
    MS("Mato Grosso do Sul", 79000, 79999, RegiaoEnum.CENTRO_OESTE),
    MT("Mato Grosso", 78000, 78899, RegiaoEnum.CENTRO_OESTE),

    // Região Sudeste
    SP("São Paulo", 1000, 19999, RegiaoEnum.SUDESTE),
    RJ("Rio de Janeiro", 20000, 28999, RegiaoEnum.SUDESTE),
    MG("Minas Gerais", 30000, 39999, RegiaoEnum.SUDESTE),
    ES("Espírito Santo", 29000, 29999, RegiaoEnum.SUDESTE),

    // Região Sul
    PR("Paraná", 80000, 87999, RegiaoEnum.SUL),
    SC("Santa Catarina", 88000, 89999, RegiaoEnum.SUL),
    RS("Rio Grande do Sul", 90000, 99999, RegiaoEnum.SUL);

    private final String estado;
    private final int cepInicio;
    private final int cepFim;
    private final RegiaoEnum regiao;

    public static EstadoEnum getByCep(String cep) {
        if (isNull(cep) || cep.length() < 5) {
            throw new IllegalArgumentException("CEP inválido");
        }

        int cepNumerico = Integer.parseInt(cep.substring(0, 5));

        for (EstadoEnum faixa : values()) {
            if (cepNumerico >= faixa.cepInicio && cepNumerico <= faixa.cepFim) {
                return faixa;
            }
        }

        throw new IllegalArgumentException("CEP não corresponse a nenhum estado");
    }

}
