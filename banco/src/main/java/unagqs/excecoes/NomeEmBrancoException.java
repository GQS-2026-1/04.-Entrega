package unagqs.excecoes;

public class NomeEmBrancoException extends Exception {
    public NomeEmBrancoException() {
        super("O nome do usuário não pode estar em branco.");
    }
    
    public NomeEmBrancoException(String mensagem) {
        super(mensagem);
    }
}