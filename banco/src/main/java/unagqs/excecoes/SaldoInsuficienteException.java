package unagqs.excecoes;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException() {
        super("O saldo é insuficiente para essa movimentação");
    }
    
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}