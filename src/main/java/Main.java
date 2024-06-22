public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Júlio");

        Conta contaCorrente = new ContaCorrente(cliente);
        Conta contaPoupanca = new ContaPoupanca(cliente);

        try {
            contaCorrente.sacar(10.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            contaCorrente.depositar(15.0);
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }
        try {
            contaCorrente.sacar(10.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        contaCorrente.transferir(4.0, contaPoupanca);
        contaCorrente.imprimirExtrato();
        contaPoupanca.imprimirExtrato();
    }
}
