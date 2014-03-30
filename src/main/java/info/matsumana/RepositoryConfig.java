package info.matsumana;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;

import javax.inject.Inject;
import javax.inject.Named;

@AnnotateWith(annotations = {
        @Annotation(target = AnnotationTarget.CLASS, type = Named.class),
        @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Inject.class)})
public @interface RepositoryConfig {
}
