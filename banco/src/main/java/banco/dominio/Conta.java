package banco.dominio;

import banco.excecoes.SaldoInsuficienteException;
import banco.excecoes.ValorInvalidoException;
import java.math.BigDecimal;

public abstract class Conta {
    private String numero;
    private Cliente titular;
    protected BigDecimal saldo;

    public Conta(String numero, Cliente titular, BigDecimal saldoInicial) {
        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("O saldo inicial não pode ser negativo.");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Método comum a todas as contas
    public void depositar(BigDecimal valor) {
        validarValorPositivo(valor);
        this.saldo = this.saldo.add(valor);
    }

    // Método abstrato: cada tipo de conta (Corrente/Poupança) implementará sua regra
    public abstract void sacar(BigDecimal valor) throws SaldoInsuficienteException;

    // Método de transferência
    public void transferir(Conta destino, BigDecimal valor) throws SaldoInsuficienteException {
        validarValorPositivo(valor);
        this.sacar(valor);
        destino.depositar(valor);
    }

    // Helper para validar se o valor informado é > 0
    protected void validarValorPositivo(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorInvalidoException("O valor da operação deve ser maior que zero.");
        }
    }

    // Getters
    public String getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    // Setters

    public void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("O número da conta não pode ser vazio.");
        }
        this.numero = numero;
    }

    public void setTitular(Cliente titular) {
        if (titular == null) {
            throw new IllegalArgumentException("O titular da conta não pode ser nulo.");
        }
        this.titular = titular;
    }

    public void setSaldo(BigDecimal saldo) {
        if (saldo == null || saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("O saldo não pode ser negativo.");
        }
        this.saldo = saldo;
    }
}