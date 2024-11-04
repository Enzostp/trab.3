package fag;

public class vaga {
    private int numero;
    private String tamanho;
    private boolean disponivel;
    private veiculo veiculo;

    public vaga(int numero, String tamanho) {
        this.numero = numero;
        this.tamanho = tamanho;
        this.disponivel = true;
    }

    public int getNumero() {
        return numero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void ocupar(veiculo veiculo) {
        this.veiculo = veiculo;
        this.disponivel = false;
    }

    public void liberar() {
        this.veiculo = null;
        this.disponivel = true;
    }

    public veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String toString() {
        return "Vaga " + numero + " (" + tamanho + "), Disponível: " + disponivel;
    }
}