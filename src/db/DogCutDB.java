package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.DogCut;

public class DogCutDB implements DogCutDAO{
	private static final String INSERT_DOG_CUT_Q = "insert into dog_cut VALUES (?, ?, ?)";
	private PreparedStatement insertDogCut;
	
	public DogCutDB() throws Exception {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			insertDogCut = con.prepareStatement(INSERT_DOG_CUT_Q);
		} catch (Exception e) {
			throw new Exception("Could not prepare statements");
		}
	}

	@Override
	public void insertDogCut(DogCut dc) throws Exception {
		//TODO
	}

}
