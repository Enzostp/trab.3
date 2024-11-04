package fag;

import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        estacionamento estacionamento = new estacionamento();
        Scanner scanner = new Scanner(System.in);

        estacionamento.adicionarVaga(1, "pequeno");
        estacionamento.adicionarVaga(2, "médio");
        estacionamento.adicionarVaga(3, "grande");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Entrada do veículo");
            System.out.println("2. Saída veículo");
            System.out.println("3. Exibir vagas ocupadas");
            System.out.println("4. Histórico de veículos");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo do veículo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Tamanho do veículo (pequeno, médio, grande): ");
                    String tamanho = scanner.nextLine();
                    estacionamento.registrarEntrada(placa, modelo, tamanho);
                    break;
                case 2:
                    System.out.print("Placa do veículo: ");
                    placa = scanner.nextLine();
                    estacionamento.registrarSaida(placa);
                    break;
                case 3:
                    estacionamento.exibirVagasOcupadas();
                    break;
                case 4:
                    estacionamento.exibirHistorico();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}