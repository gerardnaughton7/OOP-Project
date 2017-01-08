package ie.gmit.sw.client;
//bean class for hold conf.xml elements and attributes
public class Context {
	//all fields
	protected static final String file = "conf.xml";
	private String username;
	private String host;
	private int port;
	private String dir;
	
	//constructor
	public Context() {
		super();
	}
	//all getters and setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getDir() {
		return dir;
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	//to string method
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Context [username=" + username + ", host=" + host + ", port=" + port + ", dir=" + dir + "]";
	}
	
}

