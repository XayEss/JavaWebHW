package ua.itea;

public class DAOFactory {
	
	public DataBaseDAOInterface createObject(int i) {
		DataBaseDAOInterface executor = null;
		switch (i){
		case 0:
			executor = new DbExecutor();
		case 1:
			break;
		default:
			executor = new DbExecutor();
		}
		return executor;
	}

}
