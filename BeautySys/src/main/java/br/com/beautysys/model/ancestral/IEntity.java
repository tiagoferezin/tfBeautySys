/**
 * 
 */
package br.com.beautysys.model.ancestral;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Tiago Ferezin (Tiago, 7 de jun de 2016) Funcionalidade da Classe:
 *         Interface da Entity
 */
public interface IEntity extends Serializable {

	public Long getId();

	public void setId(Long id);

	public Calendar getDataCriacao();

	public void setDataCriacao(Calendar dataCriacao);

	public Calendar getDataDesativacao();

	public void setDataDesativacao(Calendar dataDesativacao);

	public boolean isEmptyId();

	public boolean isDeleted();

}
