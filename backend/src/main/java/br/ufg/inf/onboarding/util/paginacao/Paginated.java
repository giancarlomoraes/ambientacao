package br.ufg.inf.onboarding.util.paginacao;

import lombok.Data;

import java.util.List;

@Data
public class Paginated<T> {
    private Long totalElementos;
    private Integer paginas;
    private List<T> resultado;

    public Paginated(Long totalElementos, Integer paginas, List<T> resultado) {
        this.totalElementos = totalElementos;
        this.paginas = paginas;
        this.resultado = resultado;
    }
}
