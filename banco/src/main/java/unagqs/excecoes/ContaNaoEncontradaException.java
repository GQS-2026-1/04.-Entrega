package unagqs.excecoes;

public class ContaNaoEncontradaException extends Exception {
    public ContaNaoEncontradaException() {
        super("A conta do cliente não foi encontrada");
    }
    
    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}