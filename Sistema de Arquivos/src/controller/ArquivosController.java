package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{
	String pathTemp = "C:\\TEMP";
	String nomeArq = "cadastro.csv";

	public ArquivosController() {
		super();
	}

	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File(pathTemp);
		File arq = new File(pathTemp, nomeArq);
		boolean dirExiste = false;
		if(dir.exists() && dir.isDirectory()) {
			dirExiste = true;
			if (arq.exists() && arq.isFile()) {
				
			}
			criaArq(dirExiste);
		} else {
			dir.mkdir();
			dirExiste = true;
			criaArq(dirExiste);		
		}
		
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File(pathTemp, arquivo);
		boolean busca = false;
		int cod;
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] csv = linha.split(";");
				try {
					cod = Integer.parseInt(csv[0]);
					if (cod == codigo) {
						busca = true;
					}
				} catch (NumberFormatException n) {
					
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			JOptionPane.showMessageDialog(null, "Arquivo não existe!");
		}
		return busca;
	}
	
	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException{
		File arq = new File(pathTemp, arquivo);
		boolean codExiste = false;
		int cod;
		codExiste = verificaRegistro(arquivo, codigo);
		if (codExiste == true) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			StringBuffer cadastro = new StringBuffer();
			while (linha != null) {
				String[] csv = linha.split(";");
				try {
					cod = Integer.parseInt(csv[0]);
					if (cod == codigo) {
						cadastro.append("Achou o cadastro!" + "\n");
						cadastro.append("Código: " + csv[0] + "\n");
						cadastro.append("Nome: " + csv[1] + "\n");
						cadastro.append("Email: " + csv[2] + "\n");
					}
				} catch (NumberFormatException n) {
					
				}
				linha = buffer.readLine();
			}
			JOptionPane.showMessageDialog(null, cadastro.toString());
			buffer.close();
			leitor.close();
			fluxo.close();
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro não encontrado!");
		}
	}
	
	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File arq = new File(pathTemp, arquivo);
		if(!verificaRegistro(arquivo, codigo)) {
			if(arq.exists() && arq.isFile()) {		
				String content = Integer.toString(codigo) + ";" + nome + ";" + email + "@mail.com\n";
				boolean existe = false;
				existe = true;
				FileWriter fileWriter = new FileWriter(arq, existe);
				PrintWriter print = new PrintWriter(fileWriter);
				print.write(content);
				print.flush();
				print.close();
				fileWriter.close();
			} else {
				JOptionPane.showMessageDialog(null, "Arquivo não encontrado!");
			}
		}
	}
	
	private void criaArq(Boolean existe) throws IOException {
		File arq = new File(pathTemp, nomeArq);
		
		if (arq.exists() && arq.isFile()) {
			JOptionPane.showMessageDialog(null, "Arquivo já existe!");
		} else { 
			String conteudo = "Codigo;Nome;Email\n";
			FileWriter fileWriter = new FileWriter (arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
			JOptionPane.showMessageDialog(null, "Arquivo criado!");
		}
	}
}
