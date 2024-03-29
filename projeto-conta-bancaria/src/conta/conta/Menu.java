package conta;
import conta.Cores.Cores;
import conta.contaController.ContaController;
import conta.contaModel.ContaCorrente;
import conta.contaModel.ContaPoupanca;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Menu {
    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		ContaController contas = new ContaController();
        

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		System.out.println("\nCriar contas\n");

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 1234, 1, "Victor Souza", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 1234, 1, "Antônio Alves", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 1234, 2, "Amanda Albuquerque", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 1234, 2, "Amanda Albuquerque", 8000f, 15);
		contas.cadastrar(cp2);

		while (true) {

			System.out.println(Cores.TEXT_BLUE_UNDERLINED + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                        " + Cores.TEXT_RESET);
			
			try {
				opcao = scan.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				scan.nextLine();
				opcao=0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				scan.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

				System.out.println("Digite o numero da Agência: ");
				agencia = scan.nextInt();
				System.out.println("Digite o nome do titular: ");
				scan.skip("\\R?");
				titular = scan.nextLine();

				do{
					System.out.println("Digite o tipo de conta (1-CC ou 2-CP): ");
					tipo = scan.nextInt();
				}while(tipo < 1 && tipo > 2);

				System.out.println("Digite o saldo da conta (R$): ");
				saldo = scan.nextFloat();

				switch(tipo){
					case 1 ->{
						System.out.println("Digite o Limite de credito (R$): ");
						limite = scan.nextFloat();
						contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
					case 2->{
						System.out.println("Digite o dia do Aniversario da conta: ");
						aniversario = scan.nextInt();
						contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}

				keyPress();
				break;
				case 2:
					System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");

					contas.listarTodas();

					keyPress();
					break;
				case 3:
					System.out.println(Cores.TEXT_WHITE + "Buscar Conta por número\n\n");

					System.out.println("Digite o número da conta: ");
					numero = scan.nextInt();

					contas.procurarPorNumero(numero);

					keyPress();
					break;
				case 4:
					System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");

					System.out.println("Digite o número da conta: ");
					numero = scan.nextInt();

					if (contas.buscarNaCollection(numero) != null) {

						System.out.println("Digite o Numero da Agência: ");
						agencia = scan.nextInt();
						System.out.println("Digite o Nome do Titular: ");
						scan.skip("\\R?");
						titular = scan.nextLine();

						System.out.println("Digite o Saldo da Conta (R$): ");
						saldo = scan.nextFloat();

						tipo = contas.retornaTipo(numero);

						switch (tipo) {
							case 1 -> {
								System.out.println("Digite o Limite de Crédito (R$): ");
								limite = scan.nextFloat();
								contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							}
							case 2 -> {
								System.out.println("Digite o dia do Aniversario da Conta: ");
								aniversario = scan.nextInt();
								contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
							}
							default -> {
								System.out.println("Tipo de conta inválido!");
							}
						}

					} else
						System.out.println("\nConta não encontrada!");

					keyPress();
					break;
				case 5:
					System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

					System.out.println("Digite o número da conta: ");
					numero = scan.nextInt();

					contas.deletar(numero);

					keyPress();
					break;
				case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				System.out.println("Digite o Numero da conta: ");
				numero = scan.nextInt();
				
				do {
					System.out.println("Digite o Valor do Saque (R$): ");
					valor = scan.nextFloat();
				}while(valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				System.out.println("Digite o Numero da conta: ");
				numero = scan.nextInt();
				
				do {
					System.out.println("Digite o Valor do Depósito (R$): ");
					valor = scan.nextFloat();
				}while(valor <= 0);

				contas.depositar(numero, valor);
				
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");

				System.out.println("Digite o Numero da Conta de Origem: ");
				numero = scan.nextInt();
				System.out.println("Digite o Numero da Conta de Destino: ");
				numeroDestino = scan.nextInt();
				
				do {
					System.out.println("Digite o Valor da Transferência (R$): ");
					valor = scan.nextFloat();
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}   
			
	}
}   


