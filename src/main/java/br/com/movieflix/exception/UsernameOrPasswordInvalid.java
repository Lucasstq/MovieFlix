package br.com.movieflix.exception;

public class UsernameOrPasswordInvalid extends RuntimeException {

    public UsernameOrPasswordInvalid(String message) {
        super(message);
    }
}
