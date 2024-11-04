package fag;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class estacionamento {
    private List<vaga> vagas = new ArrayList<>();
    private List<veiculo> historico = new ArrayList<>();

    public void adicionarVaga(int numero, String tamanho) {
        vagas.add(new vaga(numero, tamanho));
    }

    public void registrarEntrada(String placa, String modelo, String tamanho) {
        veiculo veiculo = new veiculo(placa, modelo, tamanho);
        for (vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho)) {
                vaga.ocupar(veiculo);
                veiculo.setHoraEntrada(LocalDateTime.now());
                System.out.println("Entrada registrada\nVeículo: " + placa + " na vaga: " + vaga.getNumero());
                return;
            }
        }
        System.out.println("Nenhuma vaga disponível para o tamanho do veículo.");
    }

    public void registrarSaida(String placa) {
        for (vaga vaga : vagas) {
            if (!vaga.isDisponivel() && vaga.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
                veiculo veiculo = vaga.getVeiculo();
                veiculo.setHoraSaida(LocalDateTime.now());
                double valor = calcularValor(veiculo.getHoraEntrada(), veiculo.getHoraSaida());
                historico.add(veiculo);
                vaga.liberar();
                System.out.println("Saída registrada\nVeículo: " + placa + ". Valor a ser pago: R$ " + valor);
                return;
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private double calcularValor(LocalDateTime entrada, LocalDateTime saida) {
        long duracaoHoras = Duration.between(entrada, saida).toHours();
        if (duracaoHoras <= 1) {
            return 5.0;
        } else if (duracaoHoras <= 3) {
            return 10.0;
        } else {
            return 15.0;
        }
    }

    public void exibirVagasOcupadas() {
        System.out.println("Vagas ocupadas:");
        for (vaga vaga : vagas) {
            if (!vaga.isDisponivel()) {
                System.out.println("Vaga " + vaga.getNumero() + " - Tamanho: " + vaga.getTamanho() +
                        " - Placa: " + vaga.getVeiculo().getPlaca());
            }
        }
    }

    public void exibirHistorico() {
        System.out.println("Histórico de permanência de veículos:");
        for (veiculo veiculo : historico) {
            long duracaoHoras = Duration.between(veiculo.getHoraEntrada(), veiculo.getHoraSaida()).toHours();
            double valor = calcularValor(veiculo.getHoraEntrada(), veiculo.getHoraSaida());
            System.out.println("Placa: " + veiculo.getPlaca() +
                    " - Entrada: " + veiculo.getHoraEntrada() +
                    " - Saída: " + veiculo.getHoraSaida() +
                    " - Tempo estacionado: " + duracaoHoras + " horas" +
                    " - Valor pago: R$ " + valor);
        }
    }
}