package gb.spring.exceptions;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String message){
        super(message);
    }
}
