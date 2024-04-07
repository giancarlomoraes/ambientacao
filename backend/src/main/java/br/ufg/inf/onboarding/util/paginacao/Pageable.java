package br.ufg.inf.onboarding.util.paginacao;

import lombok.Data;

import java.util.List;

@Data
public class Pageable {
    private Integer pagina;
    private Integer tamanhoPagina;

    public Pageable(Integer pagina, Integer tamanhoPagina) {
        this.pagina = pagina;
        this.tamanhoPagina = tamanhoPagina;
    }

    public static <T> Paginated<T> de(Long totalElementos, Pageable paginacao, List<T> resultado) {
        Integer paginas = (int) Math.ceil((double) totalElementos / paginacao.getTamanhoPagina());

        return new Paginated<>(totalElementos, paginas, resultado);
    }

}