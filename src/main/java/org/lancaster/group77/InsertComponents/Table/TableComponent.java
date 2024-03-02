package org.lancaster.group77.InsertComponents.Table;

import org.lancaster.group77.DisplayComponents.Table;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.InsertComponents.CodeSection.CodeRunButton;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.Table.ExtendedJTable;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableComponent extends DraggableComponent {

    private Point initialClick;
    private Table tableData;
    private DefaultTableModel model;
    private ExtendedJTable table;
    private JButton finishButton;
    String[][] tableContents;
    String tableContentsString;
    private int rows;

    public TableComponent(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, CSPPTFile file, int rows, int columns, int layer, int slideNum) {
        super(x, y, width, height, listener, frame1, file, "Table");
        this.rows = rows;
        model = new DefaultTableModel(rows, columns);
        table = new ExtendedJTable(model, this);

        init(x, y, width, height, listener, frame1, file, rows, columns, layer, slideNum);
        initTableData(rows, columns, x, y, width, height, layer);
        file.getSlides().get(slideNum).addObject(tableData);
    }

    public TableComponent(Table tableData, MouseHandler listener, JLayeredPane frame1, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super((int) (tableData.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (tableData.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (tableData.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) ((tableData.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y)), listener, frame1, file, "Table");

        tableContents = convertStringToArray(tableData.getTable_contents(), "@TR@", "@TC@", "@ESCAPEROW@", "@ESCAPECOL@");
        tableContentsString = tableData.getTable_contents();

        String[] columnNames = new String[tableData.getColumns()];
        model = new DefaultTableModel(tableContents, columnNames);
        table = new ExtendedJTable(model, this);

        init((int) (tableData.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (tableData.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (tableData.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (tableData.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame1, file, tableData.getRows(), tableData.getColumns(), tableData.getLayer(), slideNum);

        this.tableData = tableData;
        this.setComponentData(tableData);
        if (!isOpeningFile) {
            file.getSlides().get(slideNum).addObject(tableData);
        }
    }

    private void init(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame1, CSPPTFile file, int rows, int columns, int layer, int slideNum) {
        setLayout(null);

        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.rows = rows;
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);

        table.setBounds(7, 7, width - 14, height - 14);
        table.setRowHeight(table.getHeight() / rows);
        add(table);

        finishButton = new JButton("Finish");
        finishButton.setVisible(false);
        finishButton.setBounds(x + this.getWidth() - 80, y + this.getHeight(), 80, 30);
        add(finishButton);

        initTableListener();

        table.addMouseListener(listener);
        table.addMouseMotionListener(listener);
        DraggableComponent.initRightClickMenu(table);

    }

    public void initTableData(int rows, int columns, int x, int y, int width, int height, int layer) {
        tableContents = new String[rows][columns];
        tableContentsString = convertArrayToString(tableContents, "@TR@", "@TC@", "@ESCAPEROW@", "@ESCAPECOL@");
        tableData = new Table(x / GlobalVariables.FRAME_SIZE_INDEX_X, y / GlobalVariables.FRAME_SIZE_INDEX_Y, layer, width / GlobalVariables.FRAME_SIZE_INDEX_X, height / GlobalVariables.FRAME_SIZE_INDEX_Y, rows, columns, tableContentsString);
        this.setComponentData(tableData);
    }

    private void initTableListener() {
        finishButton.addActionListener(e -> {
            table.getCellEditor().stopCellEditing();
            tableContents = saveTableToArray(table);
            tableContentsString = convertArrayToString(tableContents, "@TR@", "@TC@", "@ESCAPEROW@", "@ESCAPECOL@");
            tableData.setTable_contents(tableContentsString);
            finishButton.setVisible(false);
        });
    }

    public String[][] saveTableToArray(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        String[][] data = new String[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Object cellValue = table.getValueAt(row, col);
                data[row][col] = cellValue == null ? "" : cellValue.toString();
            }
        }
        return data;
    }

    public String escapeData(String data, String rowEscapeToken, String colEscapeToken, String rowDelimiter, String colDelimiter) {
        if (data != null) {
            String escapedData = data.replace(colDelimiter, colEscapeToken);
            escapedData = escapedData.replace(rowDelimiter, rowEscapeToken);
            return escapedData;
        } else {
            return "";
        }
    }

    public String unescapeData(String data, String rowEscapeToken, String colEscapeToken, String rowDelimiter, String colDelimiter) {
        if (data != null) {
            String unescapedData = data.replace(colEscapeToken, colDelimiter);
            unescapedData = unescapedData.replace(rowEscapeToken, rowDelimiter);
            return unescapedData;
        } else {
            return "";
        }
    }

    private String convertArrayToString(String[][] array, String rowDelimiter, String colDelimiter, String rowEscapeToken, String colEscapeToken) {
        StringBuilder builder = new StringBuilder();
        for (String[] row : array) {
            for (int col = 0; col < row.length; col++) {
                String escapedData = escapeData(row[col], rowEscapeToken, colEscapeToken, rowDelimiter, colDelimiter);
                builder.append(escapedData);
                if (col < row.length - 1) {
                    builder.append(colDelimiter);
                }
            }
            builder.append(rowDelimiter);
        }
        if (builder.length() > 0) {
            builder.setLength(builder.length() - rowDelimiter.length());
        }
        return builder.toString();
    }

    private String[][] convertStringToArray(String str, String rowDelimiter, String colDelimiter, String rowEscapeToken, String colEscapeToken) {
        String[] rows = str.split(rowDelimiter);
        int numRows = rows.length;
        String[][] array = new String[numRows][];

        for (int row = 0; row < numRows; row++) {
            String[] cols = rows[row].split(colDelimiter);
            for (int col = 0; col < cols.length; col++) {
                cols[col] = unescapeData(cols[col], rowEscapeToken, colEscapeToken, rowDelimiter, colDelimiter);
            }
            array[row] = cols;
        }
        return array;
    }

    public void displayFinishButton() {
        if (finishButton == null) {
            finishButton = new JButton("Finish");
            finishButton.setBounds(this.getX() + this.getWidth() - 80, this.getY() + this.getHeight(), 80, 30);
            GlobalVariables.cspptFrame.getPane().add(finishButton);
        } else {
            boolean isPresent = false;
            for (Component component : GlobalVariables.cspptFrame.getPane().getComponents()) {
                if (component.equals(finishButton)) {
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                GlobalVariables.cspptFrame.getPane().add(finishButton);
            }
        }
        finishButton.setVisible(true);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        table.setBounds(7, 7, inputWidth - 14, inputHeight - 14);
        table.setRowHeight(table.getHeight() / rows);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight, layer);
        finishButton.setBounds(this.getX() + this.getWidth() - 80, this.getY() + this.getHeight(), 80, 30);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        table.setBounds(7, 7, inputWidth - 14, inputHeight - 14);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight);
    }

    @Override
    public void setLocation1(int inputX, int inputY, int layer) {
        super.setLocation1(inputX, inputY, layer);
        finishButton.setLocation(inputX + getWidth() - 80, inputY + getHeight());
    }

    public void disableInput() {
        table.setEnabled(false);
        finishButton.setVisible(false);
    }

    public void enableInput() {
        table.setEnabled(true);
    }

    public void removeFinishButton() {
        try {
            GlobalVariables.cspptFrame.getPane().remove(finishButton);
        } catch (Exception e) {
        }
    }
}
