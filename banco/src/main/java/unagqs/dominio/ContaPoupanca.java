package unagqs.dominio;

import java.math.BigDecimal;

import unagqs.excecoes.ValorInvalidoException;

public class ContaPoupanca extends Conta {

    @Override
    public BigDecimal saque(BigDecimal valor) throws ValorInvalidoException {
       boolean valorInferiorAoAceito = valor.compareTo(new BigDecimal(0)) < 0;
        if(valorInferiorAoAceito) throw new ValorInvalidoException("Valor inferior ao mínimo aceito para esta transação");

        boolean saldoInsuficiente = this.saldo.compareTo(valor) < 0;
        if(saldoInsuficiente) throw new ValorInvalidoException("Saldo insuficiente");

        this.definirSaldo(this.saldo.subtract(valor));
        return valor;
    }

    protected void render(int taxaPercentual) throws ValorInvalidoException {
        boolean taxaInvalida = taxaPercentual <= 0;
        if(taxaInvalida) throw new ValorInvalidoException("Taxa de rendimento possui valor inválido informado.\n Valor informado(" + taxaPercentual + ")");
        this.definirSaldo(this.saldo.multiply(new BigDecimal(1 + taxaPercentual/100)));
    }

    
}