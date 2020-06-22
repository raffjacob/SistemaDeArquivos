/***
 * 		Exercício Sistema de Arquivos - Sistemas Operacionais 1 - Fatec ZL 
 * 		Prof. Leandro Colevati dos Santos
 * 		Sistema de Arquivos
 * 		
 * 		Aluno: Rafael Jacob Bastos
 * 		
 * 		
 * 		Data: 21/06/2020
 */
package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {
	static int code;
	public static void main(String[] args) {
		String nomeArq = "cadastro.csv";
		int opcaoUser = 0;
		int codigo;
		code = 1;
		String nome;
		String email;
		IArquivosController arqCont = new ArquivosController();
		try {
			arqCont.verificaDirTemp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		do {
			opcaoUser = Integer.parseInt(JOptionPane.showInputDialog("1 - Imprimir cadastro.\n2 - Inserir Cadastro.\n9 - Sair"));
			switch(opcaoUser) {
				case 1:
					codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o código do cadastro: "));
					try {
						arqCont.imprimeCadastro(nomeArq, codigo);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				case 2:
					codigo = code;
					code++;
					nome = JOptionPane.showInputDialog("Insira o nome: ");
					email = JOptionPane.showInputDialog("Insira o email: ");
					try {
						arqCont.insereCadastro(nomeArq, codigo, nome, email);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
										
				case 9:
					JOptionPane.showMessageDialog(null, "Programa encerrado.");
					break;
			}
		} while(opcaoUser != 9);
	}
}

