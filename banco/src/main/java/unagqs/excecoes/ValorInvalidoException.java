package unagqs.excecoes;

public class ValorInvalidoException extends Exception {
    public ValorInvalidoException() {
        super("O valor é invalido");
    }
    
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}