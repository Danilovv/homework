import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RobotCommand {
    String command();
}
