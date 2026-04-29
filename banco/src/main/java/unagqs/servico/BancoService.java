package unagqs.servico;

import java.util.HashMap;
import java.util.UUID;

import unagqs.dominio.Cliente;
import unagqs.dominio.Conta;
import unagqs.dominio.ContaCorrente;
import unagqs.dominio.ContaPoupanca;
import unagqs.excecoes.ClienteNaoEncontradoException;
import unagqs.excecoes.ContaNaoEncontradaException;
import unagqs.excecoes.NomeEmBrancoException;

public class BancoService {
    private HashMap<UUID, Cliente> clientesIndexadosPorId;
    private HashMap<String, Cliente> clientesIndexadosPorNome;
    private HashMap<Integer, Conta> contas;
    private int numeroUltimaContaCriada = 100;

    public Cliente cadastrarCliente(String nome, String cpf) throws NomeEmBrancoException {
        nome = nome.toUpperCase();
        Cliente clienteGerado = this.clientesIndexadosPorNome.get(nome);
        boolean clienteJaCadastrado = clienteGerado != null;
        if(clienteJaCadastrado) return clienteGerado;

        clienteGerado = new Cliente(nome, cpf);
        this.clientesIndexadosPorId.put(clienteGerado.obterId(), clienteGerado);
        this.clientesIndexadosPorNome.put(nome, clienteGerado);
        return clienteGerado;
    }
    
    public void cadastrarConta(Cliente cliente, TipoConta tipoConta) {
        if(tipoConta == TipoConta.CORRENTE) {
            Conta conta = new ContaCorrente(++this.numeroUltimaContaCriada);
            conta.adicionarTitular(cliente);
            this.contas.put(this.numeroUltimaContaCriada, conta);
        }
        
        if(tipoConta == TipoConta.POUPANCA) {
            Conta conta = new ContaPoupanca(++this.numeroUltimaContaCriada);
            conta.adicionarTitular(cliente);
            this.contas.put(this.numeroUltimaContaCriada, conta);
        }
    }
    
    public Conta buscarContaPorNumero(int numeroDaConta) throws ContaNaoEncontradaException {
        Conta conta = this.contas.get(numeroDaConta);
        if(conta == null) throw new ContaNaoEncontradaException();
        return conta;
    }

    public Cliente buscarCliente(UUID identificador) throws ClienteNaoEncontradoException {
        Cliente cliente = this.clientesIndexadosPorId.get(identificador);
        if(cliente == null) throw new ClienteNaoEncontradoException();
        return cliente;
    }

    public Cliente buscarCliente(String nome) throws ClienteNaoEncontradoException {
        Cliente cliente = this.clientesIndexadosPorNome.get(nome);
        if(cliente == null) throw new ClienteNaoEncontradoException();
        return cliente;
    }
}

enum TipoConta { CORRENTE, POUPANCA }