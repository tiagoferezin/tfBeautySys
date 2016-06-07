/**
 * 
 */
package br.com.beautysys.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Tiago Ferezin (Tiago, 7 de jun de 2016)
 * Funcionalidade da Classe:
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AcessoPublico {

}
