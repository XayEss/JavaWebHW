package ua.itea;
import ua.itea.DAOFactory;
import ua.itea.DataBaseDAOInterface;

public class Authorizator {
    public String isAuthorized(String login, String password) {
    	DAOFactory df = new DAOFactory();
		DataBaseDAOInterface de = df.createObject(0);
        return de.checkLogin(login, password);
    }
    
    public static void main(String[] args) {
    	//DbExecutor de = new DbExecutor();
        //System.out.println(de.checkLogin("admin", "123456789"));
    	DAOFactory df = new DAOFactory();
		DataBaseDAOInterface de = df.createObject(0);
		//System.out.println(de.checkLogin("albedo@overlord.com", "Qwerty12345"));
		System.out.println(de.checkLogin(null, null));
    	//System.out.println(isAuthorized("alex.seljuk@gmail.com", "Alex123456"));
    	//System.out.println(isAuthorized("admin", "123"));

	}
}
