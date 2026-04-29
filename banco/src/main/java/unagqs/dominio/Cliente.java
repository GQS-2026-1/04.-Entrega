package unagqs.dominio;

import java.util.List;
import java.util.UUID;

import unagqs.excecoes.NomeEmBrancoException;

public class Cliente {
    private UUID id;
    private String nome;
    private String cpf;
    private List<Conta> contas;

    public Cliente() {
        this.generateId();
    }
    public Cliente(String _nome, String cpf) throws NomeEmBrancoException {
        this.setNome(nome);
        this.generateId();
        this.setCPF(cpf);
    }

    public void generateId() {
        this.id = UUID.randomUUID();
    }

    public UUID obterId() {
        return this.id;
    }

    public void setCPF(String _cpf) {
        boolean cpfJaExiste = this.cpf.isEmpty() == false;
        if(cpfJaExiste) return;
        this.cpf = _cpf;
    }

    public void setNome(String _nome) throws NomeEmBrancoException {
        if(nome.isEmpty() || nome.isBlank()) throw new NomeEmBrancoException();
        this.nome = _nome;
    }

    public List<Conta> obterContasVinculadas() {
        List<Conta> contasSomenteLeitura = List.copyOf(this.contas);
        return contasSomenteLeitura;
    }

}