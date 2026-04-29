package unagqs.dominio;

import java.math.BigDecimal;

import unagqs.excecoes.ValorInvalidoException;

public abstract class Conta {
    private int numero;
    private Cliente titular;
    protected BigDecimal saldo;

    Conta() {
        this.definirSaldo(new BigDecimal(0));
    }

    Conta(int _numero) {
        this.definirSaldo(new BigDecimal(0));
        this.definirNumero(_numero);
    }

    protected void definirNumero(int _numero) {
        this.numero = _numero;
    }

    public void adicionarTitular(Cliente cliente) {
        this.titular = cliente;
    }

    public void depositar(BigDecimal valor) throws ValorInvalidoException {
        boolean valorInferiorAoAceito = valor.compareTo(new BigDecimal(0)) < 0;
        if(valorInferiorAoAceito) throw new ValorInvalidoException();
        BigDecimal saldoAtualizado = this.saldo.add(valor);
        this.definirSaldo(saldoAtualizado);
    }

    public void transferir(Conta contaAReceber, BigDecimal valorATransferir) throws ValorInvalidoException {
        boolean valorInferiorAoAceito = valorATransferir.compareTo(new BigDecimal(0)) < 0;
        if(valorInferiorAoAceito) throw new ValorInvalidoException("Valor inferior ao mínimo aceito para esta transação");

        boolean saldoInsuficiente = this.saldo.compareTo(valorATransferir) < 0;
        if(saldoInsuficiente) throw new ValorInvalidoException("Saldo insuficiente");

        contaAReceber.depositar(valorATransferir);
        BigDecimal saldoAtualizado = this.saldo.subtract(valorATransferir);
        this.definirSaldo(saldoAtualizado);
    }

    abstract public BigDecimal saque(BigDecimal valor) throws ValorInvalidoException;

    protected void definirSaldo(BigDecimal valor) {
        this.saldo = valor;
    }


}