package br.com.spendwise.controlefinanceiro.model.transacao;

import br.com.spendwise.controlefinanceiro.model.transacao.dto.AtualizarTransacaoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @UuidGenerator
    private String id;
    private String usuario;
    private String descricao;
    private LocalDate data;
    private BigDecimal valor;
    private String grupoId;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    public Transacao(String usuario, String grupoId, String descricao, LocalDate data, BigDecimal valor, Categoria categoria, TipoTransacao tipo) {
        this.usuario = usuario;
        this.grupoId = grupoId;
        this.descricao = descricao;
        this.data = data;
        this.valor = tipo.equals(TipoTransacao.DEBITO) ? valor.negate() : valor;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public void atualizar(AtualizarTransacaoDto atualizarTransacaoDto) {
        if(atualizarTransacaoDto.descricao() != null &&
                !atualizarTransacaoDto.descricao().isBlank() && !this.descricao.equals(atualizarTransacaoDto.descricao())) {
            this.descricao = atualizarTransacaoDto.descricao();
        }

        if(atualizarTransacaoDto.data() != null && !this.data.equals(atualizarTransacaoDto.data())) {
            this.data = atualizarTransacaoDto.data();
        }

        if(atualizarTransacaoDto.valor() != null && !this.valor.equals(atualizarTransacaoDto.valor())) {
            this.valor = atualizarTransacaoDto.valor();
        }

        if(atualizarTransacaoDto.categoria() != null && !this.categoria.equals(atualizarTransacaoDto.categoria())) {
            this.categoria = atualizarTransacaoDto.categoria();
        }

        if(atualizarTransacaoDto.tipo() != null && !this.tipo.equals(atualizarTransacaoDto.tipo())) {
            this.tipo = atualizarTransacaoDto.tipo();
        }

    }
}
