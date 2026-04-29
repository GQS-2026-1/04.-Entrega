# 🧾 Exercício — Gerenciamento de Clientes e Contas Bancárias  
### **Java + JUnit 5 + Suite**

## 🎯 Objetivo do exercício
Neste exercício, você deverá desenvolver um **mini-sistema bancário** em Java.

A proposta é simular um sistema simples de **cadastro de clientes e contas bancárias**, permitindo realizar operações como:

- depósito
- saque
- transferência
- rendimento da poupança
- busca de clientes e contas

Além disso, todas as regras implementadas deverão ser **testadas com JUnit 5**.

---

# 🏦 Contexto do problema

Um banco deseja criar um sistema básico para controlar seus **clientes** e suas **contas bancárias**.

Cada cliente poderá possuir uma ou mais contas, e o sistema deverá permitir operações comuns do dia a dia bancário.

O sistema trabalhará com dois tipos de conta:

- **Conta Corrente**
- **Conta Poupança**

Cada tipo possui regras específicas, por isso você deverá usar **herança** e **polimorfismo** na implementação.

---

# 🧱 O que deve ser desenvolvido

Você deverá criar classes para representar:

- **Cliente**
- **Conta** (classe abstrata)
- **ContaCorrente**
- **ContaPoupanca**
- **BancoService**

Também deverá criar exceções específicas para representar erros de negócio.

---

# 👤 Classe `Cliente`

A classe `Cliente` deve representar uma pessoa cadastrada no banco.

## Atributos
O cliente deve possuir:

- `id` → identificador do cliente (`UUID` ou `String`)
- `nome` → nome do cliente
- `cpf` → CPF com **11 dígitos**
- `contas` → lista de contas vinculadas ao cliente

## Regras
- O **nome não pode ser vazio**
- O **CPF deve conter exatamente 11 dígitos numéricos**
- A lista de contas deve ser disponibilizada como **somente leitura**

---

# 💳 Classe `Conta` (abstrata)

A classe `Conta` será a superclasse das contas do sistema.

## Atributos
Toda conta deve possuir:

- `numero` → número da conta
- `titular` → cliente dono da conta
- `saldo` → valor disponível na conta (`BigDecimal`)

## Regras gerais
- O saldo inicial deve ser **maior ou igual a zero**
- O valor de depósito, saque e transferência deve ser **maior que zero**
- Nenhuma operação de saque ou transferência pode deixar o saldo negativo

## Métodos esperados
A classe deve oferecer operações como:

- `depositar(valor)`
- `sacar(valor)`
- `transferir(destino, valor)`

Como o comportamento do saque muda conforme o tipo da conta, o método `sacar` pode ser implementado de forma **abstrata** na classe base.

---

# 🏧 Classe `ContaCorrente`

A `ContaCorrente` herda de `Conta`, mas possui uma regra específica para saque.

## Regra especial
Sempre que for realizado um saque, deve ser cobrada uma **tarifa fixa de R$ 1,00**.

### Exemplo
Se o cliente quiser sacar **R$ 50,00**, o sistema deve descontar:

- R$ 50,00 do saque
- R$ 1,00 de tarifa

**Total debitado:** R$ 51,00

### Atenção
O saque só pode ser realizado se o saldo for suficiente para cobrir:

**valor do saque + tarifa**

---

# 🐷 Classe `ContaPoupanca`

A `ContaPoupanca` também herda de `Conta`.

## Regras especiais
- O saque **não possui tarifa**
- A conta deve possuir um método chamado `render(taxaPercentualMensal)`

Esse método deverá aplicar rendimento sobre o saldo atual da conta.

### Exemplo
Se o saldo for **R$ 1000,00** e a taxa mensal for **2%**, o novo saldo será:

**R$ 1020,00**

## Validação
- A taxa de rendimento deve ser **maior ou igual a zero**
- Não é permitido informar taxa negativa

---

# ⚠️ Exceções personalizadas

Você deverá criar exceções específicas para representar erros de negócio do sistema.

## Exceções obrigatórias

### `ValorInvalidoException`
Usar quando:
- o valor informado for **menor ou igual a zero**
- a taxa de rendimento for negativa

### `SaldoInsuficienteException`
Usar quando:
- o saldo da conta não for suficiente para realizar saque ou transferência

### `ClienteNaoEncontradoException`
Usar quando:
- o sistema tentar buscar um cliente inexistente

### `ContaNaoEncontradaException`
Usar quando:
- o sistema tentar buscar uma conta inexistente pelo número

---

# 🏛️ Classe `BancoService`

A classe `BancoService` será responsável por controlar os dados do banco.

## Responsabilidades
Ela deve permitir:

- cadastrar clientes
- cadastrar contas
- buscar cliente por `id`
- buscar conta por `numero`

## Regras
- Ao buscar um cliente inexistente, lançar `ClienteNaoEncontradoException`
- Ao buscar uma conta inexistente, lançar `ContaNaoEncontradaException`

---

# 🧪 Testes unitários com JUnit 5

Depois de implementar as classes, você deverá criar **testes unitários** para validar o comportamento do sistema.

## Os testes devem cobrir, no mínimo:

### `ClienteTest`
Testar:
- criação de cliente válido
- nome inválido
- CPF inválido
- lista de contas somente leitura

### `ContaCorrenteTest`
Testar:
- depósito válido
- depósito com valor inválido
- saque com tarifa
- saque sem saldo suficiente
- transferência entre contas

### `ContaPoupancaTest`
Testar:
- saque sem tarifa
- saque com saldo insuficiente
- rendimento com taxa válida
- rendimento com taxa zero
- rendimento com taxa negativa

### `BancoServiceTest`
Testar:
- cadastro e busca de cliente
- busca de cliente inexistente
- cadastro e busca de conta
- busca de conta inexistente

---

# 🧩 Organização com Suite

Todos os testes deverão ser organizados em uma **suite**, para permitir a execução conjunta.

Crie uma classe de suite para reunir todos os testes do projeto.

---

# 📁 Estrutura sugerida do projeto

```bash
banco/
│── pom.xml
├── src/
│   ├── main/java/banco/
│   │   ├── dominio/
│   │   │   ├── Cliente.java
│   │   │   ├── Conta.java
│   │   │   ├── ContaCorrente.java
│   │   │   └── ContaPoupanca.java
│   │   ├── excecoes/
│   │   │   ├── ValorInvalidoException.java
│   │   │   ├── SaldoInsuficienteException.java
│   │   │   ├── ClienteNaoEncontradoException.java
│   │   │   └── ContaNaoEncontradaException.java
│   │   └── servico/
│   │       └── BancoService.java
│   └── test/java/banco/
│       ├── dominio/
│       │   ├── ContaCorrenteTest.java
│       │   ├── ContaPoupancaTest.java
│       │   └── ClienteTest.java
│       ├── servico/
│       │   └── BancoServiceTest.java
│       └── suite/
│           └── BancoAllTestsSuite.java