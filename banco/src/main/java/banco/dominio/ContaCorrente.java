package banco.dominio;

import java.math.BigDecimal;

import banco.excecoes.SaldoInsuficienteException;
import banco.excecoes.ValorInvalidoException;

public class ContaCorrente extends Conta {

    private static final BigDecimal TARIFA = new BigDecimal("1.00");

    public ContaCorrente(String numero, Cliente titular, BigDecimal saldoInicial) {
        super(numero, titular, saldoInicial);
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("Valor inválido");
        }

        BigDecimal total = valor.add(TARIFA);

        if (getSaldo().compareTo(total) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        setSaldo(getSaldo().subtract(total));
    }
}