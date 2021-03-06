package com.poo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poo.jdbc.ConnectionFactory;
import com.poo.model.Aluno;

public class AlunoDAO {
	
	//insere aluno no banco CRUD - Create, Read, Update, Delete
	public void create(Aluno aluno)
	{
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		String sql = "insert into aluno(matricula, nome) values (?, ?)";
		
		try {
			
			
			stmt = con.prepareStatement(sql);  //instancia uma instrucao sql
			stmt.setString(1, aluno.getMatricula()); //primeiro parametro da query
			stmt.setString(2, aluno.getNome()); //segundo parametro
			
			stmt.executeUpdate();
			System.out.println("[AlunoDAO] Aluno incluido com sucesso");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
    
    public void alterar(Aluno aluno){
		String sql = "update aluno set Matricula = ?, nome = ?, where Matricula = ?";
		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
			preparedStatement.setString(1, aluno.getMatricula());
			preparedStatement.setString(2, aluno.getNome());
			preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void excluir(Aluno aluno){
		String sql = "delete from aluno where Matricula = ?";
		try(PreparedStatement preparedStatement = con.prepareStatement(sql)){
			preparedStatement.setString(1, aluno.getMatricula());
			preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
		
		
		
	}
	
	//ler todos os registros da tabela aluno
	public List<Aluno> readAll()
	{
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = " select matricula, nome from aluno";
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try{
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Aluno aluno = new Aluno();
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setNome(rs.getString("nome"));
				
				alunos.add(aluno);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Erro ao tentar ler tabela aluno");
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		
		return alunos;
	}

}
