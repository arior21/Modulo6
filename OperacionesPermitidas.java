import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface OperacionesPermitidas {
    String descripcion();
}
