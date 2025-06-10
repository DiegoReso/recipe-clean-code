package dev.reso.recipeit.core.exceptions;

public class DuplicateRecipeKeyException extends RuntimeException {
    public DuplicateRecipeKeyException(String message) {
        super(message);
    }
}
