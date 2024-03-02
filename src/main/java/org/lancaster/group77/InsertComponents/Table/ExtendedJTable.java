package org.lancaster.group77.InsertComponents.Table;

import javax.swing.*;
import javax.swing.table.TableModel;

public class ExtendedJTable extends JTable {
    private TableComponent tableComponent;
    public ExtendedJTable(TableComponent tableComponent) {
        super();
        this.tableComponent = tableComponent;
    }

    public ExtendedJTable(TableModel model, TableComponent tableComponent) {
        super(model);
        this.tableComponent = tableComponent;
    }


    public TableComponent getTableComponent(){
        return tableComponent;
    }
}
