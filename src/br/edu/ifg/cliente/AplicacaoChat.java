package br.edu.ifg.cliente;

import br.edu.ifg.interf.*;

import java.io.ObjectInputStream.GetField;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("unused")
public class AplicacaoChat {
	
	private static String nick = null;
	private static String hostname = null;

	private AplicacaoChat() {
	}

	public static void main(String[] args) {
		
	            
		
		try {
			Registry registry  = LocateRegistry.getRegistry("localhost",Constant.RMI_PORT);
			final InterfaceDNS remote = (InterfaceDNS) registry.lookup(Constant.RMI_ID);
			
		    hostname = ip();
		    nick = nomeIP();
			
			if (remote.autentica(nick, hostname)) {
				System.out.println("Usuario adicionado com sucesso!");
			} else {
				System.out.print("Erro ao adicionar!");
			}
			
			//System.out.println("O IP do Marcos é: " +remote.obterIP("Marcos"));
			//System.out.println("O IP do Gabriel é: " +remote.obterIP("Gabriel"));
			
			
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
	
	public static String ip() throws UnknownHostException{
		
		InetAddress ip = InetAddress.getLocalHost();
		String hostname = ip.getHostAddress();
		return hostname;
		
	}
	
	
	public static String nomeIP() {
		JTextField campo = new JTextField();
		campo.setBounds(0, 0, 10, 10);
		campo.setVisible(true);
		String nick = JOptionPane.showInputDialog(campo, "Digite o nick do usu�rio");
		return nick;
		
	}	
	
	
}