package model.error;

public class ParsingError {

    public static class TooManyArgumentsError extends Error {

        public TooManyArgumentsError() {
            super("Too many arguments");
        }
    }

    public static class RequestingPositiveNumberError extends Error {

        public RequestingPositiveNumberError(String previousArg, String arg) {
            super("The number after \"" + previousArg + "\" should be positive (input = " + arg + ")");
        }
    }

    public static class RequestingNumberError extends Error {

        public RequestingNumberError(String previousArg, String arg) {
            super("The argument after \"" + previousArg + "\" should be number (input = " + arg + ")");
        }
    }

    public static class MissingNumberError extends Error {

        public MissingNumberError(String arg) {
            super("The number was expected after: \"" + arg + "\"");
        }
    }

    public static class MissingStringError extends Error {

        public MissingStringError(String arg) {
            super("The string was expected after: \"" + arg + "\"");
        }
    }

    public static class NoCommandError extends Error {

        public NoCommandError() {
            super("You don't input any command");
        }
    }

    public static class NoSuchCommandError extends Error {

        public NoSuchCommandError(String arg) {
            super("No such command: \"" + arg + "\"");
        }
    }

    public static class NoSuchOptionError extends Error {

        public NoSuchOptionError(String arg) {
            super("No such option: \"" + arg + "\"");
        }
    }

    public static class NumberOutOfTheBoundError extends Error {

        public NumberOutOfTheBoundError() {
            super("Number is out of the bound");
        }
    }

    public static class JsonParsingError extends Error {

        public JsonParsingError(String fileName) {
            super("Couldn't parse JSON from file: \"" + fileName + "\"");
        }
    }
}
