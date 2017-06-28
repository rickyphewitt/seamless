package com.rickyphewitt.seamless.services;

import org.springframework.stereotype.Service;

@Service
public class ColumnDisplayService {

	private int numberOfColumns;
	private int linesPerColumn;
	
	public ColumnDisplayService() {}
	
	public ColumnDisplayService(int numberOfColumns, int linesPerColumn) {
		this.numberOfColumns = numberOfColumns;
	}

	// Determines max number of lines per column
	public int calcLinesPerColumn(int totalNumberOfLines) {
		this.linesPerColumn = (int) Math.ceil((double) totalNumberOfLines / this.numberOfColumns);
		return this.linesPerColumn;
	}
	
	// Getters/Setters
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	public int getLinesPerColumn() {
		return linesPerColumn;
	}

	public void setLinesPerColumn(int linesPerColumn) {
		this.linesPerColumn = linesPerColumn;
	}
	
}
