package it.unisannio.ingsof20_21.group8.Care.Spring;

public class ErrorBean {

    private String message;


    public ErrorBean(String message) {
        super();

        this.message = message;

    }


    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
