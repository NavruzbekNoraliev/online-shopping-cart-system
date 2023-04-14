package jwt.filter;

public class AuthorizationHeaderNotPresent extends RuntimeException{

    public AuthorizationHeaderNotPresent(String message){
        super(message);
    }
}
