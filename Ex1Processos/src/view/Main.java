package view;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController rc = new RedesController();
		System.out.println(rc.os());

		//System.out.println(pc.so()); // chama função que exibe o SO e exibe o
		// sistema operacional do PC
		//pc.chamaProcesso("c:\\WINDOWS\\regedit.exe"); // Passando o nome do caminho por parametro

		if (rc.os().contains("Windows")){
		rc.leProcesso("ipconfig");
		}

		//pc.leProcesso("ipconfig"); //Manda o processo de configuração de IPs
		//pc.leProcesso("TASKLIST /FO TABLE"); // Comando para passar o nome da tabela de processos por
		//parâmetros
		//pc.mataProcesso("notepad.exe"); // pode ser cmd PID/ ou o nome do exe

	}
}