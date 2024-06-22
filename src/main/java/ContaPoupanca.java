public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    protected String retornarTipoDeConta() {
        return "Poupança";
    }
}
