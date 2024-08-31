package br.com.api_eco_feira.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiException {

    private List<String> listaErros;

    public ApiException(String message) {
        listaErros = Arrays.asList(message);
    }

    public ApiException(List<String> errorList) {
        this.listaErros = errorList;
    }


}