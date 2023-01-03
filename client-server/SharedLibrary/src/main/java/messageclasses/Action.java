package messageclasses;


public enum Action  {
	FILE_DOWNLOAD(1),
	FILE_UPLOAD(2),
	FILE_DELETE(3),
	VIEW_FOLDER_CONTENT(4),
	VIEW_FOLDER_LIST(5);
	
	
	
	private final int value;

	private Action(final int newValue) {
        this.value = newValue;
    }

    public  int getValue() { return value; }
    
    
    public static String getActionList()
    {
    	String res = "";
    	for(Action ac : Action.values())
    	{
    		if(ac.getValue() != 5) {
    		   res += ac.getValue() +". "+ ac.toString() +"\r\n";
    		}
    		
    	}
    	
    	return res;
    }
    
    
    

}
