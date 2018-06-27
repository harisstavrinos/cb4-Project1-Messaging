package window;

public class EditText {	
	
	public static String text;
	
    private EditText() {}

    private static class EditTextHolder {
        private static final EditText INSTANCE = new EditText();
    }

    public static EditText getInstance() {
        return EditTextHolder.INSTANCE;
    }

	public static String getText() {
		return text;
	}

/*	public static void setText(String text) {
		this.text = text;
	}*/
    
    
}
