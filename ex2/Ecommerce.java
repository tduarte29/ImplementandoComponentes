
interface Estoque {
    boolean validarEstoque(String produto, int quantidade);
}

interface PedidoRequerEstoque {
    void processarPedido(String produto, int quantidade);
}

class ValidadorEstoque implements Estoque {
    private java.util.Map<String, Integer> estoque = new java.util.HashMap<>();

    public ValidadorEstoque() {
        estoque.put("Mouse", 10);
        estoque.put("Teclado", 5);
        estoque.put("Monitor", 2);
    }

    @Override
    public boolean validarEstoque(String produto, int quantidade) {
        if (!estoque.containsKey(produto)) {
            System.out.println("Produto não encontrado no estoque.");
            return false;
        }
        int disponivel = estoque.get(produto);
        return disponivel >= quantidade;
    }

    public void reduzirEstoque(String produto, int quantidade) {
        int atual = estoque.get(produto);
        estoque.put(produto, atual - quantidade);
    }

    public void mostrarEstoque() {
        System.out.println("\n--- Estoque Atual ---");
        for (var entry : estoque.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " unidades");
        }
        System.out.println("---------------------\n");
    }
}

class ProcessadorPedido implements PedidoRequerEstoque {
    private ValidadorEstoque validador;

    public ProcessadorPedido(ValidadorEstoque validador) {
        this.validador = validador;
    }

    @Override
    public void processarPedido(String produto, int quantidade) {
        System.out.println("Tentando processar pedido: " + produto + " x" + quantidade);

        if (validador.validarEstoque(produto, quantidade)) {
            validador.reduzirEstoque(produto, quantidade);
            System.out.println("Pedido processado com sucesso!");
        } else {
            System.out.println("Estoque insuficiente. Pedido não realizado.");
        }
    }
}

public class Ecommerce {
    public static void main(String[] args) {
        ValidadorEstoque validador = new ValidadorEstoque();
        PedidoRequerEstoque processador = new ProcessadorPedido(validador);

        validador.mostrarEstoque();

        processador.processarPedido("Mouse", 2);
        processador.processarPedido("Monitor", 3); 
        processador.processarPedido("Teclado", 1);

        validador.mostrarEstoque();
    }
}
