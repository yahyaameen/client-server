package messageclasses;

public enum ResponseCode {
	
	/* --- CLIENT ERRORS --- */
    
    CLIENT_ERROR_404_FILE_NOT_FOUND(404,"file not found"),
    CLIENT_ERROR_405_UNKNOWN_REQUEST(405,"Unsupported request"),

    /* --- SERVER ERRORS --- */
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented"),
    
    SUCCESS(200,"success")
    
    ;


    public final int STATUS_CODE;
    public final String MESSAGE;

    ResponseCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
        
    }
    
    public  int getValue() { return STATUS_CODE; }



}
