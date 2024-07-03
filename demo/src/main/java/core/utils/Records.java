package core.utils;

import java.util.List;

public class Records {
    public interface EnterExitInterface {
        Object[] params();

        Class<?>[] paramsType();

        Object thisObject();
    }

    public record Enter(
            Object[] params,
            Class<?>[] paramsType,
            Object thisObject) implements EnterExitInterface {
    }

    public record Exit<T>(
            Class<T> returnType,
            T result,
            Object[] params,
            Class<?>[] paramsType,
            Object thisObject) implements EnterExitInterface {
    }


    public interface LogInfoInterface {
        String event();

        Object target();

        List<Object[]> args();

        String name();
    }

    public record LogInfoWithOutResult(
            String event,
            Object target,
            List<Object[]> args,
            String name) implements LogInfoInterface {
    }

    public record LogInfoWithResult(
            String event,
            Object target,
            List<Object[]> args,
            Object[] result,
            String name) implements LogInfoInterface {
    }


    public record Levels(
            int target,
            int args,
            int result) {

    }

}
