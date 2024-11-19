package com.techchallenge4.ms_logistica.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public enum StateEnum {

    // Região Norte
    AM("Amazonas", 69000, 69899, RegionEnum.NORTE),
    AC("Acre", 69900, 69999, RegionEnum.NORTE),
    AP("Amapá", 68900, 68999, RegionEnum.NORTE),
    PA("Pará", 66000, 68899, RegionEnum.NORTE),
    RO("Rondônia", 76800, 76999, RegionEnum.NORTE),
    RR("Roraima", 69300, 69399, RegionEnum.NORTE),
    TO("Tocantins", 77000, 77999, RegionEnum.NORTE),

    // Região Nordeste
    BA("Bahia", 40000, 48999, RegionEnum.NORDESTE),
    CE("Ceará", 60000, 63999, RegionEnum.NORDESTE),
    MA("Maranhão", 65000, 65999, RegionEnum.NORDESTE),
    PB("Paraíba", 58000, 58999, RegionEnum.NORDESTE),
    PE("Pernambuco", 50000, 56999, RegionEnum.NORDESTE),
    PI("Piauí", 64000, 64999, RegionEnum.NORDESTE),
    RN("Rio Grande do Norte", 59000, 59999, RegionEnum.NORDESTE),
    SE("Sergipe", 49000, 49999, RegionEnum.NORDESTE),
    AL("Alagoas", 57000, 57999, RegionEnum.NORDESTE),

    // Região Centro-Oeste
    DF("Distrito Federal", 70000, 72799, RegionEnum.CENTRO_OESTE),
    GO("Goiás", 74000, 76999, RegionEnum.CENTRO_OESTE),
    MS("Mato Grosso do Sul", 79000, 79999, RegionEnum.CENTRO_OESTE),
    MT("Mato Grosso", 78000, 78899, RegionEnum.CENTRO_OESTE),

    // Região Sudeste
    SP("São Paulo", 1000, 19999, RegionEnum.SUDESTE),
    RJ("Rio de Janeiro", 20000, 28999, RegionEnum.SUDESTE),
    MG("Minas Gerais", 30000, 39999, RegionEnum.SUDESTE),
    ES("Espírito Santo", 29000, 29999, RegionEnum.SUDESTE),

    // Região Sul
    PR("Paraná", 80000, 87999, RegionEnum.SUL),
    SC("Santa Catarina", 88000, 89999, RegionEnum.SUL),
    RS("Rio Grande do Sul", 90000, 99999, RegionEnum.SUL);

    private final String estado;
    private final int cepInicio;
    private final int cepFim;
    private final RegionEnum regionEnum;

    public static StateEnum getByCep(String cep) {
        if (isNull(cep) || cep.length() < 5) {
            throw new IllegalArgumentException("CEP inválido");
        }

        int cepNumerico = Integer.parseInt(cep.substring(0, 5));

        for (StateEnum faixa : values()) {
            if (cepNumerico >= faixa.cepInicio && cepNumerico <= faixa.cepFim) {
                return faixa;
            }
        }

        throw new IllegalArgumentException("CEP não corresponse a nenhum estado");
    }

}
