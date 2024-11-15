package com.techchallenge4.ms_logistica.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public enum CepEnum {

    // Região Norte
    AM("Amazonas", 69000, 69899),
    AC("Acre", 69900, 69999),
    AP("Amapá", 68900, 68999),
    PA("Pará", 66000, 68899),
    RO("Rondônia", 76800, 76999),
    RR("Roraima", 69300, 69399),
    TO("Tocantins", 77000, 77999),

    // Região Nordeste
    BA("Bahia", 40000, 48999),
    CE("Ceará", 60000, 63999),
    MA("Maranhão", 65000, 65999),
    PB("Paraíba", 58000, 58999),
    PE("Pernambuco", 50000, 56999),
    PI("Piauí", 64000, 64999),
    RN("Rio Grande do Norte", 59000, 59999),
    SE("Sergipe", 49000, 49999),
    AL("Alagoas", 57000, 57999),

    // Região Centro-Oeste
    DF("Distrito Federal", 70000, 72799),
    GO("Goiás", 74000, 76999),
    MS("Mato Grosso do Sul", 79000, 79999),
    MT("Mato Grosso", 78000, 78899),

    // Região Sudeste
    SP("São Paulo", 1000, 19999),
    RJ("Rio de Janeiro", 20000, 28999),
    MG("Minas Gerais", 30000, 39999),
    ES("Espírito Santo", 29000, 29999),

    // Região Sul
    PR("Paraná", 80000, 87999),
    SC("Santa Catarina", 88000, 89999),
    RS("Rio Grande do Sul", 90000, 99999);

    private final String estado;
    private final int cepInicio;
    private final int cepFim;

    public static CepEnum obterPorCep(String cep) {
        if (isNull(cep) || cep.length() < 5) {
            throw new IllegalArgumentException("CEP inválido");
        }

        int cepNumerico = Integer.parseInt(cep.substring(0, 5));

        for (CepEnum faixa : values()) {
            if (cepNumerico >= faixa.cepInicio && cepNumerico <= faixa.cepFim) {
                return faixa;
            }
        }

        throw new IllegalArgumentException("CEP não corresponse a nenhum estado");
    }

}
