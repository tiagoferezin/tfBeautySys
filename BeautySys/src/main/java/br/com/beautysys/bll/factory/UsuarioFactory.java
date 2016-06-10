/**
 * 
 */
package br.com.beautysys.bll.factory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.beautysys.model.acesso.Usuario;

/**
 * @author Tiago Ferezin (Tiago, 10 de jun de 2016) Funcionalidade da Classe:
 */
public class UsuarioFactory {

	public Boolean chkSenha(Usuario usuario, String senhaDescriptografada)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Boolean result = false;

		if ((senhaDescriptografada != null) && (!senhaDescriptografada.isEmpty()) && (usuario.getSalt() != null)
				&& (!usuario.getSalt().isEmpty()) && (usuario.getSenha() != null) && (!usuario.getSenha().isEmpty())) {

			String pass = senhaDescriptografada;
			String nameSalt = usuario.getSalt();
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest((pass + nameSalt).getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaDescriptografada = hexString.toString();
			// logger.debug(senhaDescriptografada);

			result = usuario.getSenha().equals(senhaDescriptografada);

		}
		return result;

	}

	public String chkAutenticar(Usuario usuario, String senhaDescriptografada) {
		String result = "";

		try {
			if (chkSenha(usuario, senhaDescriptografada)) {
				result = "";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "<strong>BEAS-002:<strong/> O usuário e/ou senha estão incorretos.";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "<strong>BEAS-002:<strong/> O usuário e/ou senha estão incorretos.";
		}

		return result;
	}

}
