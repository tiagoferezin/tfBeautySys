/**
 * 
 */
package br.com.beautysys.model.ancestral;

/**
 * @author Tiago Ferezin (Tiago, 7 de jun de 2016) Funcionalidade da Classe:
 */
public abstract class AEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmptyId() {
		return ((getId() == null) || (getId() == 0L));
	}

	@Override
	public boolean isDeleted() {
		return (getDataDesativacao() != null);
	}

}
