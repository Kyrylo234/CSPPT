package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.util.Objects;

public class Table extends DisplayComponentBase{
    private int rows;
    private int columns;
    private String table_contents;

    public Table() {
        super("table");
    }

    public Table(double x, double y, int layer, double width1, double height1, int rows, int columns, String table_contents) {
        super(x, y, layer, "table", width1, height1);
        this.rows = rows;
        this.columns = columns;
        this.table_contents = table_contents;
    }

    public Table(double x, double y, int layer, int width1, int height1, int rows, int columns, String table_contents) {
        super(x, y, layer, "table", width1, height1);
        this.rows = rows;
        this.columns = columns;
        this.table_contents = table_contents;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public String getTable_contents() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), table_contents);

    }

    public void setTable_contents(String table_contents) {
        this.table_contents = table_contents;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Table table = (Table) object;
        return getRows() == table.getRows() && getColumns() == table.getColumns() && Objects.equals(getTable_contents(), table.getTable_contents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRows(), getColumns(), getTable_contents());
    }
}
