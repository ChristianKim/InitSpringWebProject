package powergen.rte.psl.dataaccess;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Mapper 인터페이스에 대한 marker Annotation(Single-value)으로  MyBatis 적용 방식 중 annotation을 사용한 방식에 대한 기준을 위해 사용된다.
 * <p>
 * Service에 injection을 위해 Component annotation을 사용하였다.  
 * </p>
 * @author jongho.kim
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Mapper {
	/**
	 * The value may indicate a suggestion for a logical mapper name, to be turned into a Spring bean in case of an autodetected component.
	 * 
	 * @return the suggested mapper name, if any
	 */
	String value() default "";
}