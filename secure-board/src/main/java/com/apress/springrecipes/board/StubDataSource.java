package com.apress.springrecipes.board;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.AbstractDataSource;

public class StubDataSource extends AbstractDataSource {

	@Override
	public Connection getConnection() throws SQLException {
		return new StubConnection();
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return new StubConnection();
	}


}
