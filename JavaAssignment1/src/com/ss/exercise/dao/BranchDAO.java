/**
 * 
 */
package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Branch;

/**
 * @author Ian
 *
 */
public class BranchDAO extends BaseDAO<Branch> {

	public BranchDAO(Connection conn) {
		super(conn);
	}
	
	
	public void updateBranchName(Branch branch) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_library_branch SET branchName = ? WHERE branchId = ?",
				new Object[] { branch.getBranchName(), branch.getBranchId() });
		conn.commit();
	}
	
	public void updateBranchAddress(Branch branch) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_library_branch SET branchAddress = ? WHERE branchId = ?",
				new Object[] { branch.getBranchAddress(), branch.getBranchId() });
		conn.commit();
	}
	
	public List<Branch> readAllBranches() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_library_branch", null);
	}
	
	public List<Branch> readAllBranchesByName(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_library_branch WHERE branchName LIKE ?", new Object[] {searchString});
	}
	
	
	public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_library_branch  WHERE branchId = ?", new Object[] { branch.getBranchId() });
	}
	
	public Integer addBranchWithPk(Branch branch) throws ClassNotFoundException, SQLException {
	return saveWithPk("INSERT INTO tbl_library_branch (branchName) VALUES (?)", new Object[] { branch.getBranchName() });
	}
	
	public void addBranch(Branch branch) throws ClassNotFoundException, SQLException {
	 save("INSERT INTO tbl_library_branch (branchName) VALUES (?)", new Object[] { branch.getBranchName() });
	}
	

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Branch> branches = new ArrayList<>();
		BranchDAO bdao = new BranchDAO(conn);
		while (rs.next()) {
			Branch b = new Branch(rs.getInt("branchId"), rs.getString("branchName"),rs.getString("BranchAddress"));
			branches.add(b);
		}
		return branches;
	}

}
