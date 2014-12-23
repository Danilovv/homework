import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Vladimir_Danilov on 22-Dec-14.
 */
public class Dispatcher implements CommandListener {
    private List<Robot> _robots = new ArrayList<>();
    public void add(Robot robot) {
        _robots.add(robot);
    }

    @Override
    public void command(String _command) {
        System.out.println("dispatch " + _command);
        if (_command == null) {
            return;
        }

        OUTER: for(Robot robot : _robots) {
            System.out.println(robot.getClass().getSimpleName());
            Class robotClass = robot.getClass();
            Method[] methods = robotClass.getMethods();
            RobotCommand robotCommand = null;
            for (Method method : methods) {

                if(method.isAnnotationPresent(RobotCommand.class)) {
                    robotCommand = method.getAnnotation(RobotCommand.class);
                }
                else {
                    continue;
                }

                if(Objects.equals( robotCommand.value() , _command )) {
                    try {
                        method.invoke(robot);
                        continue OUTER;
                    } catch (IllegalAccessException |InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            }
            /*switch (command) {
                case "up":
                    robot.up();
                    break;
                case "down":
                    robot.down();
                    break;
                case "left":
                    robot.left();
                    break;
                case "right":
                    robot.right();
                    break;
                case "stop":
                    robot.stop();
                    break;
                default:
                    System.out.println("unknown command: " + command);
            }*/
        }
    }
}
