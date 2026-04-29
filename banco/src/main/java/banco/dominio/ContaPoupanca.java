package banco.dominio;

import java.math.BigDecimal;
import java.math.RoundingMode;

import banco.excecoes.SaldoInsuficienteException;
import banco.excecoes.ValorInvalidoException;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numero, Cliente titular, BigDecimal saldoInicial) {
        super(numero, titular, saldoInicial);
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor de saque inválido.");
        }

        if (this.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
        }

        this.setSaldo(this.getSaldo().subtract(valor));
    }

    public void render(BigDecimal taxaPercentualMensal) {
        if (taxaPercentualMensal == null || taxaPercentualMensal.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("Taxa de rendimento não pode ser negativa.");
        }

        BigDecimal taxa = taxaPercentualMensal.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        BigDecimal rendimento = this.getSaldo().multiply(taxa);

        this.setSaldo(this.getSaldo().add(rendimento));
    }
}