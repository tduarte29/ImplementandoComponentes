interface FornecedorDeHoras {
    int calcularHorasTrabalhadas(String funcionarioId);
}

interface PagamentoRequerHoras {
    double calcularPagamento(String funcionarioId);
}

class ControleHoras implements FornecedorDeHoras {
    @Override
    public int calcularHorasTrabalhadas(String funcionarioId) {
        System.out.println("Calculando horas trabalhadas...");
        return 160;
    }
}

class FolhaPagamento implements PagamentoRequerHoras {
    private FornecedorDeHoras controleHoras;
    private double valorHora;

    public FolhaPagamento(FornecedorDeHoras controleHoras, double valorHora) {
        this.controleHoras = controleHoras;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularPagamento(String funcionarioId) {
        int horas = controleHoras.calcularHorasTrabalhadas(funcionarioId);
        double pagamento = horas * valorHora;
        System.out.println("Calculando pagamento para " + funcionarioId + ": " + pagamento);
        return pagamento;
    }
}

public class SistemaRH {
    public static void main(String[] args) {
        FornecedorDeHoras controleHoras = new ControleHoras();
        FolhaPagamento folha = new FolhaPagamento(controleHoras, 25.0);

        folha.calcularPagamento("FUNCIONARIO_123");
    }
}
