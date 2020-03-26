package model;

public class Either<L, R> {

    public static class Left<L, R> extends Either<L, R> {

        private L value;

        public Left(L value) {
            this.value = value;
        }

        public L getValue() {
            return value;
        }
    }

    public static class Right<L, R> extends Either<L, R> {

        private R value;

        public Right(R value) {
            this.value = value;
        }

        public R getValue() {
            return value;
        }
    }

    public boolean isRight() {
        return this instanceof Right;
    }

    public boolean isLeft() {
        return this instanceof Left;
    }

    public Either.Right<L, R> toRight() {
        return (Either.Right<L, R>) this;
    }

    public Either.Left<L, R> toLeft() {
        return (Either.Left<L, R>) this;
    }
}
