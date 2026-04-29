package unagqs.dominio;

import java.math.BigDecimal;

import unagqs.excecoes.ValorInvalidoException;

public class ContaCorrente extends Conta {

    @Override
    public BigDecimal saque(BigDecimal valor) throws ValorInvalidoException {
       boolean valorInferiorAoAceito = valor.compareTo(new BigDecimal(0)) < 0;
        if(valorInferiorAoAceito) throw new ValorInvalidoException("Valor inferior ao mínimo aceito para esta transação");

        BigDecimal valorCorrigido = valor.add(new BigDecimal(1));
        boolean saldoInsuficiente = this.saldo.compareTo(valorCorrigido) < 0;
        if(saldoInsuficiente) throw new ValorInvalidoException("Saldo insuficiente");

        this.definirSaldo(this.saldo.subtract(valorCorrigido));
        return valor;
    }

}