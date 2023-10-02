package io.testomat.api.common;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class BindingExceptionHandler {


    private static final String USER_DIR = "user.dir";

    public static void main(String[] args) {
        // Use catchResponseException and getLineOfCodeOfClassVariable methods
    }

    @SneakyThrows
    public static <T> Throwable catchResponseException(Exception e, Class<T> t) {
        String exceptionMessage = e.toString();
        if (exceptionMessage.contains("data not found")) {
            throw new AssertionError(
                    String.format(
                            "Failed to parse response body to %s,\n",
                            t.getName()
                    ) + "service was responded with 404 status code in the payload");
        }
        if (exceptionMessage.contains("Ой, сталась помилка!") || exceptionMessage.contains("Oops, an error occurred")) {
            throw new AssertionError(
                    String.format(
                            "Failed to parse response body to %s, service was failed with 500 status code",
                            t.getName()
                    ));
        } else if (exceptionMessage.contains("Unrecognized field")) {
            String unrecognizedField = exceptionMessage.split("Unrecognized field \"")[1].split("\" \\(class")[0];
            throw new NoSuchFieldException(
                    String.format("Add line of code:\n" +
                                    "\t@JsonProperty(\"%s\")\n" +
                                    "\tprivate String %s;\n" +
                                    "\tat %s",
                            unrecognizedField, unrecognizedField,
                            getLineOfCodeOfClassVariable(t.getName(), unrecognizedField)
                    ));
        } else if (exceptionMessage.contains("SQL")) {
            throw new SQLException("syntax or lock exception on the service");
        } else {
            return e;
        }
    }

    public static String getLineOfCodeOfClassVariable(String classPackagePath, String fieldName) {
        var className = classPackagePath.substring(classPackagePath.lastIndexOf(".") + 1) + ".java";
        var startDir = System.getProperty(USER_DIR);
        var files = new File(startDir).listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains(className)) {
                    try {
                        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                        for (int i = 0; i < lines.size(); i++) {
                            if (Pattern.compile(String.format("^\\s*val\\s+%s\\s*:\\s*[A-Z]", fieldName)).matcher(
                                    lines.get(i)).find()) {
                                return String.format("%s(%s:%d)", classPackagePath, className, i + 1);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return String.format("%s(%s:1)", classPackagePath, className);
    }

}

