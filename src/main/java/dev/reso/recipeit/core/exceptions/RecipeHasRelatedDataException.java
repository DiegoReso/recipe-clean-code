package dev.reso.recipeit.core.exceptions;

public class RecipeHasRelatedDataException extends RuntimeException {
    public RecipeHasRelatedDataException(String message) {
        super(message);
    }
}
