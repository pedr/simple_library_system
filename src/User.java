import java.util.HashMap;

public class User {

    private String fname;
    private String email;

    public User(String fname, String email) {
	    this.fname = fname;
	    this.email = email;
    }

    public User(String fname) {
	this(fname, "");
    }

    public HashMap<String, String> data() {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("fname", this.fname);
        data.put("email", this.email);

        return data;
    }

    public String toString() {

        return "Name: " + this.fname + " - Email: " + this.email;
    }
}
