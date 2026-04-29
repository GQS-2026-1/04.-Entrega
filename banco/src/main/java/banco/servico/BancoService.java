package banco.servico;

import java.util.ArrayList;
import java.util.List;

import banco.dominio.Cliente;
import banco.dominio.Conta;
import banco.excecoes.ClienteNaoEncontradoException;
import banco.excecoes.ContaNaoEncontradaException;

public class BancoService {

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Conta> contas = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarConta(Conta conta) {
        contas.add(conta);
    }

    public Cliente buscarClientePorId(String id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente nao encontrado com id: " + id));
    }

    public Conta buscarContaPorNumero(String numero) {
        return contas.stream()
                .filter(c -> c.getNumero().equals(numero))
                .findFirst()
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta nao encontrada com numero: " + numero));
    }
}
