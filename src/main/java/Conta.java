import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Conta {
    private static final int AGENCIA_PADRAO = 1;
    private static int sequencial = 1;
    private int agencia;
    private int numero;
    private double saldo;
    private List<Double> extrato;
    private Cliente cliente;

    protected abstract String retornarTipoDeConta();

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = sequencial++;
        this.saldo = 0;
        extrato = new ArrayList<Double>();
        this.cliente = cliente;
    }


    public void sacar(double valor) throws ValorInvalidoException {
        if (valor < 0) {
            throw new ValorInvalidoException("Valor para saque não pode ser negativo");
        }
        if (saldo - valor < 0) {
            throw new ValorInvalidoException("Saldo indisponível para a operação");
        }

        saldo -= valor;
        extrato.add(-valor);
    }

    public void depositar(double valor) throws ValorInvalidoException {
        if (valor < 0) {
            throw new ValorInvalidoException("Valor para depósito não pode ser negativo");
        }

        saldo += valor;
        extrato.add(valor);
    }

    public void transferir(double valor, Conta contaDestino) {
        try {
            this.sacar(valor);
            contaDestino.depositar(valor);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /*
     * Utilizando retornarTipoDeConta() é possível integrar as duas implementações,
     * das classes ContaPoupanca e ContaCorrente, na classe Conta; evitando repetição e
     * criação de mais de uma fonte de verdade (e.g. mudar o código em uma classe e esquecer de mudar na outra);
     * seguindo, portanto, o princípio DRY (Don't Repeat Yourself)
     */
    public void imprimirExtrato() {
        System.out.println(String.format("=== Dados da Conta %s ===", retornarTipoDeConta()));
        System.out.println("Titular: " + this.getCliente().getNome());
        System.out.println("Agência: " + this.getAgencia());
        System.out.println("Número: " + this.getNumero());
        System.out.println("Saldo: " + this.getSaldo());
        System.out.println(String.format("--- Extrato da Conta %s ---", retornarTipoDeConta()));
        this.getExtrato().forEach(System.out::println);
    }
}
