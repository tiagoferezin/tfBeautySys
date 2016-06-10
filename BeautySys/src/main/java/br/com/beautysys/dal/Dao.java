/**
 * 
 */
package br.com.beautysys.dal;

import java.lang.reflect.ParameterizedType;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import br.com.beautysys.model.ancestral.AEntity;

/**
 * @author Tiago Ferezin (Tiago, 10 de jun de 2016) Funcionalidade da Classe:
 * @param <AEntity>
 */
public class Dao {

	protected EntityManager entityManager;
	protected AEntity entity;
	protected Validator validator;
	protected String userName;
	protected DaoFactory daoFactory;

	public Dao(AEntity entity, EntityManager entityManager) {
		// TODO Auto-generated constructor stub
		setEntity(entity);
		setEntityManager(entityManager);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public AEntity getEntity() {
		return entity;
	}

	public void setEntity(AEntity entity) {
		this.entity = entity;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	Class<AEntity> getClassT() throws Exception {
		ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();

		return (Class<AEntity>) superclass.getActualTypeArguments()[0];
	}

	protected AEntity getInstanceT() throws Exception {

		Class<AEntity> classT = getClassT();

		return classT.newInstance();
	}

	void beforePersistence() throws ConstraintViolationException, Exception {
	}

	void afterPersistence() throws ConstraintViolationException, Exception {
	}

	void validate() throws ConstraintViolationException {
		Set<ConstraintViolation<AEntity>> constraintViolations = getValidator().validate(entity);

		String validationErrors = "";

		for (ConstraintViolation<AEntity> constraintViolation : constraintViolations) {
			if (!validationErrors.isEmpty()) {
				validationErrors += ", ";
			}

			validationErrors += "\"" + constraintViolation.getPropertyPath() + "\" " + constraintViolation.getMessage();
		}

		if (!validationErrors.isEmpty()) {
			throw new ConstraintViolationException(validationErrors, constraintViolations);
		}
	}

	public void create() throws Exception, ConstraintViolationException {
		entity.setDataCriacao(Calendar.getInstance());
		boolean fecharTransacao = false;

		try {

			validate();
			beforePersistence();
			if (!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
				fecharTransacao = true;
			}

			entityManager.persist(this.entity);

			if (entityManager.getTransaction().isActive()) {
				afterPersistence();
				if (fecharTransacao) {
					entityManager.getTransaction().commit();
				}
			}
		} catch (ConstraintViolationException e) {
			// TODO: handle exception
			if (entityManager.getTransaction().isActive()) {
				if (fecharTransacao) {
					entityManager.getTransaction().rollback();

				}
			}
			
			e.printStackTrace();
			throw e;

		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				if (fecharTransacao) {
					entityManager.getTransaction().rollback();
				}
			}
			e.printStackTrace();
			throw e;

		}

	}
}