package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	public String os() { // Função para reconhecer o sistema operacional do
		// conputador
		String so = System.getProperty("os.name"); // so recebe o nome do
		// Sistema operacional
		return so; // função retorna o nome do SO
	}

	public void chamaProcesso(String caminho) {

		try {
			Runtime.getRuntime().exec(caminho); // Runtime executa o caminho q
												// foi passado
			// por parâmetros.
		} catch (IOException e) {
			if (e.getMessage().contains("eleva")) {
				StringBuffer sb = new StringBuffer();
				sb.append("cmd /c ");
				sb.append(caminho);
				try {
					Runtime.getRuntime().exec(sb.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		}

	}

	public void leProcesso(String caminho) {
		try {
			Process p = Runtime.getRuntime().exec(caminho); // Comando para ler
															// o arqivo
			// até o fim da linha
			InputStream fluxo = p.getInputStream(); // Leitura em bits
			InputStreamReader leitor = new InputStreamReader(fluxo);// Transforma
																	// os bits
																	// em String
			BufferedReader buffer = new BufferedReader(leitor); // Leitura com
																// quebra de
																// linha
			StringBuffer sb = new StringBuffer();
			String linha = buffer.readLine(); // Lê a primeira Linha

			boolean x = false;

			while (linha != null) { // Enquanto linha diferente de vázio, leia!



				if (linha.contains("Adaptador Ethernet")){ // Vericação do conteúdo da linha

					sb.append(linha); // Buffer aprende a linha
					sb.append("\n"); //Buffer aprende uma quebra de linha
					linha = buffer.readLine();	 //Linha recebe uma nova linha

					do{ 

						sb.append(linha); // Buffer aprende essa linha 

						if (linha.contains("IPv4")){ //Verifica se contêm a String IPv4
							x = true; // se sim, x = true
						}
						sb.append("\n");
						linha = buffer.readLine();

					}while (!linha.contains("Adaptador")); //Vai verificar todas as linhas que não conter Adaptador 

					if (x == true){
						System.out.println(sb.toString());
					}else{
						sb.delete (0, sb.length());
					}

				}
				linha = buffer.readLine();



				// System.out.println(linha);


			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mataProcesso(String processo) {
		String cmdPid = "TASKKILL /PID "; // mata o processo pelo ID, mata
											// somente um
		String cmdNome = "TASKKILL /IM "; // mata o processo pelo nome, se tiver
											// vários do
		// mesmo nome, vão morrer juntos
		int pid = 0;
		StringBuffer buffer = new StringBuffer();

		try {
			pid = Integer.parseInt(processo);
			buffer.append(cmdPid);
			buffer.append(pid);
		} catch (NumberFormatException e) {
			buffer.append(cmdNome);
			buffer.append(processo);
		}
		try {
			Runtime.getRuntime().exec(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}