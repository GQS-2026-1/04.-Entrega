package unagqs.excecoes;

public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado");
    }
    
    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}