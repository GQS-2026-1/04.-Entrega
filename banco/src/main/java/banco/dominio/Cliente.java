package banco.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Cliente {

    private final String id;
    private String nome;
    private String cpf;
    private final List<Conta> contas;

    public Cliente(String nome, String cpf) {
        validarNome(nome);
        validarCpf(cpf);

        this.id = UUID.randomUUID().toString();
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new ArrayList<>();
    }

    // Validações
    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }

    // Método para vincular conta ao cliente (usado pelo BancoService)
    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Lista somente leitura (conforme requisito)
    public List<Conta> getContas() {
        return Collections.unmodifiableList(contas);
    }
}