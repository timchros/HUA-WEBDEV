package hua.dit.web.data;

public class DeteteUserOutputData {
	
	final boolean delete_flag;
	final int rows_affected;
	
	public DeteteUserOutputData(boolean delete_flag, int rows_affected) {
		super();
		this.delete_flag = delete_flag;
		this.rows_affected = rows_affected;
	}
	
	@Override
	public String toString() {
		return "DeteteUserOutputData [delete_flag=" + delete_flag + ", rows_affected=" + rows_affected + "]";
	}

}
