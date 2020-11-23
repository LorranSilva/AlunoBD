package bd_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Aluno;
import jdbc.*;

public class AlunoBD{
	
	//Operação criar
	public void create(Aluno aluno){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into aluno(cpf, nome, sexo) values (?, ?, ?)";
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, aluno.getCpf());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getSexo());
			stmt.executeUpdate();
			System.out.println("[AlunoBD] Aluno incluido com sucesso");
			
		}catch (SQLException e){
			System.out.println("Erro na tentativa de insercao: "+ e.getMessage());
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	//Operação deletar
	public void delete(Aluno aluno){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "delete from aluno where cpf = ?";
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, aluno.getCpf());
			
			stmt.executeUpdate();
			System.out.println("[AlunoBD] Aluno excluido com sucesso");
			
		}catch (SQLException e){
			System.out.println("Erro na insercao: 2"+ e.getMessage());
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	//Operação Update
	public void update(Aluno aluno){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "update aluno set cpf=? , nome=? , sexo=? where cpf=?";
		
		try{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, aluno.getCpf());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getSexo());
			stmt.setString(4, aluno.getCpf());
			
			stmt.executeUpdate();
			System.out.println("[AlunoBD] Aluno alterado com sucesso");
			
		} catch (SQLException e){
			System.out.println("Erro na nsercao: 2"+ e.getMessage());
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void read(Aluno aluno)
	{
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "select cpf,nome,sexo from aluno where cpf=?";
		
		
		try{
			stmt = con.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, aluno.getCpf()); //primeiro parametro da query
			
			ResultSet rs = stmt.executeQuery();
			
			
			while(rs.next()){
				System.out.println("CPF  : " + rs.getString("cpf"));
				System.out.println("Nome : " + rs.getString("nome"));
				System.out.println("Sexo : " + rs.getString("sexo"));
			}
		} catch (SQLException e){
			System.out.println("entrou aqui");
			System.out.println("Erro na insercao: 2"+ e.getMessage());
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
