/**
 * 
 */
package br.com.beautysys.dal;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import br.com.beautysys.model.ancestral.AEntity;

/**
 * @author Tiago Ferezin (Tiago, 10 de jun de 2016) Funcionalidade da Classe:
 */
public class DaoFactory {

	public void create(AEntity entity, EntityManager entityManager) throws ConstraintViolationException, Exception {

		Dao result = new Dao(entity, entityManager);
		result.create();

	}

}
