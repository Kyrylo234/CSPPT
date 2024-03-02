package org.lancaster.group77.InsertComponents.UML.ClassDiagrams;

import org.lancaster.group77.DisplayComponents.CSBox;
import org.lancaster.group77.DisplayComponents.Text;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.impl.DocumentListenerImpl;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.TextBox.ExtendedJtextArea;
import org.lancaster.group77.InsertComponents.TextBox.TextBox;
import org.lancaster.group77.InsertComponents.UML.Lines.*;
import org.lancaster.group77.InsertComponents.UML.UMLBorder;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ClassDiagram extends TextBox {


    private boolean isSelected = false;
    private static ClassDiagram selectedDiagram = null;


    private MouseHandler listener;
    private JLayeredPane frame;
    private boolean menuUp = false;
    private ArrayList<BaseLine> lines = new ArrayList<>();

    private boolean doubleClickConnectionMade = false;
    private JTextPane text;
    private Text textData;
    private CSBox csBoxData;
    private int BoxNumber;

    private int slideNumber;

    //sets up class diagram adds a key listener listening for certain keys for certain action
    public ClassDiagram(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, int slideNumber, int boxcounter){
        super(x,y,width,height, listener, frame,file, layer, "", slideNumber,true);
        this.BoxNumber =boxcounter;
        this.frame =frame;
        this.listener =listener;
        this.slideNumber = slideNumber;
        JTextPane textPane = getTextPane();

        textPane.setBackground(Color.lightGray);
        textPane.setForeground(Color.black);

        setClassDiagramListener(textPane);

        initCSBoxData(x,y,layer,"",width,height,boxcounter);
        file.getSlides().get(slideNumber).addObject(csBoxData);
        this.setCsBox(csBoxData);
    }

        //gets data about class diagram to store
    public void initCSBoxData(int x, int y, int layer, String text1, int width, int height, int boxcounter) {
        csBoxData = new CSBox();
        this.setComponentData(csBoxData);
        csBoxData.setLayer(layer);
        csBoxData.setX(x / GlobalVariables.FRAME_SIZE_INDEX_X);
        csBoxData.setY((y / GlobalVariables.FRAME_SIZE_INDEX_Y));
        csBoxData.setWidth((width / GlobalVariables.FRAME_SIZE_INDEX_X));
        csBoxData.setHeight((height / GlobalVariables.FRAME_SIZE_INDEX_Y));
        csBoxData.setText(text1);
        csBoxData.setColor(String.valueOf(this.getTextPane().getForeground().getRGB()));
        csBoxData.setBackgroundColor(String.valueOf(this.getTextPane().getBackground().getRGB()));
        csBoxData.setBox_name(""+boxcounter);
    }

    //second constructor for when we open a file and create a classdiagram
    public ClassDiagram(CSBox csbox,Text text1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super(text1, listener, frame, file, slideNum, isOpeningFile,true,csbox);
        textData = text1;
        JTextPane textPane = getTextPane();
        textPane.setBackground(Color.lightGray);
        textPane.setForeground(Color.black);
        this.csBoxData = csbox;
//        initTextBox((int) ((csbox.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X)),
//                (int) ((csbox.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y)), listener, csbox.getText());
//        initTextStyleByTextData();

        this.setComponentData(csbox);
        BoxNumber = Integer.parseInt(csbox.getBox_name());

        this.setBounds((int) (csbox.getX() * GlobalVariables.FRAME_SIZE_INDEX_X),(int) Math.ceil(csbox.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y),(int) (csBoxData.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X),(int) (csBoxData.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y));

        this.setVisible(true);
        //if we are opening a file we don't want to add the object to the file
        setClassDiagramListener(textPane);
        if (!isOpeningFile)
            file.getSlides().get(slideNum).addObject(csBoxData);

    }

    private void initTextStyleByTextData() {
        Font font = new Font(csBoxData.getFont(), Font.PLAIN, (int) csBoxData.getFont_size());
        text.setFont(font);
        text.setForeground(new Color(Integer.parseInt(csBoxData.getColor())));
        text.setBackground(new Color(Integer.parseInt(csBoxData.getBackgroundColor())));
        if (csBoxData.getBold()) {
            boldFunction();
        }
        if (csBoxData.getItalic()) {
            italicFunction();
        }
        if (csBoxData.getUnderline()) {
            csBoxData.setUnderline(false);
            underlineFunction();
        }
        if (csBoxData.getBulletpoint()) {
            csBoxData.setBulletpoint(false);
            bulletpointFunction();
        }
        if (csBoxData.getStrikethrough()) {
            csBoxData.setStrikethrough(false);
            strikethroughFunction();
        }
        if (csBoxData.getLeft()) {
            alignLeftFunction();
        }
        if (csBoxData.getRight()) {
            alignRightFunction();
        }
        if (csBoxData.getCenter()) {
            alignCenterFunction();
        }
        if (csBoxData.getColor() != null) {
            int colour2 = Integer.parseInt(csBoxData.getColor());
            Color color = new Color(colour2);
            text.setForeground(color);
        }

        if (csBoxData.getBackgroundColor() != null) {
            int colour3 = Integer.parseInt(csBoxData.getBackgroundColor());
            Color color1 = new Color(colour3);
            text.setBackground(color1);
        }
    }


    private void initTextBox(int width, int height, MouseHandler listener, String textContent) {
        text = new ExtendedJtextArea(this, textContent);
        add(text);
        text.addMouseListener(listener);
        text.addMouseMotionListener(listener);
        text.setBounds(7, 7, width - 14, height - 14);
        text.getDocument().addDocumentListener(new DocumentListenerImpl(this));
        text.setCaretColor(Color.white);
        text.repaint();
    }

    //find the nearest point between two diagrams
    private Point findNearestBorderPoint(ClassDiagram diagram1, ClassDiagram diagram2) {
        Rectangle bounds1 = diagram1.getBounds();
        Rectangle bounds2 = diagram2.getBounds();

        Point center1 = new Point(bounds1.x + bounds1.width / 2, bounds1.y + bounds1.height / 2);
        Point center2 = new Point(bounds2.x + bounds2.width / 2, bounds2.y + bounds2.height / 2);
        double dx = center2.getX() - center1.getX();
        double dy = center2.getY() - center1.getY();
        double angle = Math.atan2(dy, dx);
        int x1 = (int) (bounds1.width / 2 * Math.cos(angle)) + center1.x;
        int y1 = (int) (bounds1.height / 2 * Math.sin(angle)) + center1.y;
        return new Point(x1, y1);
    }

    //this implements the handling for double-clicking teh class diagram
    //it also responsible for extra key interactions
    private void setClassDiagramListener(JTextPane textPane){
        this.addMouseListener(listener);
        textPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '=') {
                    JTextComponent textComp = (JTextComponent) e.getSource();
                    insertHorizontalLine(textComp);

                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    JTextComponent textComp = (JTextComponent) e.getSource();
                    deleteHorizontalLine(textComp);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    toggleSelection();
                    if (isSelected) {
                        if (!doubleClickConnectionMade) {
                            selectedDiagram = ClassDiagram.this;

                            doubleClickConnectionMade = true;
                        }
                    } else {
                        if (doubleClickConnectionMade) {
                            doubleClickConnectionMade = false; // Reset flag if deselected
                        }
                        selectedDiagram = null;
                    }
                } else if (selectedDiagram != null && selectedDiagram != ClassDiagram.this && !isConnected(selectedDiagram, ClassDiagram.this) && selectedDiagram != ClassDiagram.this) {
                    drawLine(selectedDiagram, ClassDiagram.this);
                    selectedDiagram = null;
                    doubleClickConnectionMade = false;
                }

            }
        });

    }
    //draw the line and make a window for user to select what line they want
    private void drawLine(ClassDiagram diagram1, ClassDiagram diagram2) {
        if(menuUp){
            return;
        }
        String[] options = {"Association", "Inheritance","Realization","Dependency"};
        LineOptions lineOptions = new LineOptions(options, this, diagram1);


        JDialog dialog = new JDialog();
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.add(lineOptions);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);


        menuUp = true;

        // Close the line menu and reset the flag when it's closed
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                menuUp = false;
                setBorder(BorderFactory.createEmptyBorder());
                selectedDiagram.setBorder(BorderFactory.createEmptyBorder());

            }
        });

//        System.out.println(Arrays.toString(GlobalVariables.handler.getSlideManager().getSelectedSlide(GlobalVariables.handler.getSlideManager().getCurrentSlideInt()).getComponents()));


    }

    //we create the line depending on what the user selected in an option menu
    public void CreateLine(boolean mode, String lineType,ClassDiagram diagram1){

        Point borderPoint1 = findNearestBorderPoint(diagram1 ,ClassDiagram.this);
        Point borderPoint2 = findNearestBorderPoint(ClassDiagram.this, diagram1);

        switch (lineType) {
            case "Association":
                Line association = new Line(borderPoint1,borderPoint2,selectedDiagram,this,0,0,1920,1080,listener,frame, "classdiagram",GlobalVariables.cspptFrame.getFile(),1,mode, slideNumber,"Association");
                selectedDiagram.lines.add(association);
                lines.add(association);

                GlobalVariables.cspptFrame.addToFrame(association);
                break;
            case "Inheritance":
                InheritanceLine inheritanceLine = new InheritanceLine(borderPoint1,borderPoint2,selectedDiagram,this,0,0,1920,1080,listener,frame, "classdiagram",GlobalVariables.cspptFrame.getFile(),1,mode,slideNumber, "Inheritance");
                selectedDiagram.lines.add(inheritanceLine);
                lines.add(inheritanceLine);
                GlobalVariables.cspptFrame.addToFrame(inheritanceLine);
                break;
            case "Realization":
                RealizationLine realizationLine = new RealizationLine(borderPoint1,borderPoint2,selectedDiagram,this,0,0,1920,1080,listener,frame, "classdiagram",GlobalVariables.cspptFrame.getFile(),1,mode,slideNumber,"Realization");
                selectedDiagram.lines.add(realizationLine);
                lines.add(realizationLine);
                GlobalVariables.cspptFrame.addToFrame(realizationLine);
                break;
            case "Dependency":
                DependencyLine  dependencyLine = new DependencyLine(borderPoint1,borderPoint2,selectedDiagram,this,0,0,1920,1080,listener,frame, "classdiagram",GlobalVariables.cspptFrame.getFile(),1,mode,slideNumber,"Dependency");
                selectedDiagram.lines.add(dependencyLine);
                lines.add(dependencyLine);
                GlobalVariables.cspptFrame.addToFrame(dependencyLine);

                break;

        }


    }
    //check if diagrams are already connected when try to link them
    private boolean isConnected(ClassDiagram diagram1, ClassDiagram diagram2) {
        for(BaseLine line: lines){
            if(line.getConnection() == diagram1 && line.getMain() == diagram2  || line.getConnection() == diagram2 && line.getMain() == diagram1 ){
                return true;
            }
        }

        return false;
    }
    //is selection on(double-clicked diagram)
    private void toggleSelection() {
        isSelected = !isSelected;
        if (isSelected) {
            setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        } else {
            setBorder(BorderFactory.createEmptyBorder());
        }
    }
    //we insert a horizontal line with '-' across the textpane
    private void insertHorizontalLine(JTextComponent textComp) {
        int caretPosition = textComp.getCaretPosition();
        try {
            int lineNumber = Utilities.getRowStart(textComp, caretPosition);
            int lineEndOffset = Utilities.getRowEnd(textComp, lineNumber);
            String lineText = textComp.getText(caretPosition, lineEndOffset - caretPosition);
            int charsToInsert = textComp.getWidth() / textComp.getFontMetrics(textComp.getFont()).charWidth('-');
            StringBuilder lineWithHyphens = new StringBuilder(lineText);
            for (int i = 0; i < charsToInsert; i++) {
                lineWithHyphens.append('-');
            }

            textComp.getDocument().remove(caretPosition, lineEndOffset - caretPosition);
            textComp.getDocument().insertString(caretPosition, lineWithHyphens.toString(), null);

        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
    //when you delete '-' it deletes all of them in a line to remove the line
    private void deleteHorizontalLine(JTextComponent textComp) {
        int caretPosition = textComp.getCaretPosition();
        try {
            int lineNumber = Utilities.getRowStart(textComp, caretPosition);

            int lineEndOffset = Utilities.getRowEnd(textComp, lineNumber);

            String lineText = textComp.getText(lineNumber, lineEndOffset - lineNumber);
            if (lineText.contains("-")) {
                String updatedLineText = lineText.replaceAll("-", "");
                textComp.getDocument().remove(lineNumber, lineEndOffset - lineNumber);
                textComp.getDocument().insertString(lineNumber, updatedLineText, null);
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    //set location when class diagram is moved along with update the lines
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        updateLines();
    }

    public void setLine(BaseLine line){lines.add(line);}

    public ArrayList<BaseLine> getLines(){return lines;}

    public int getBoxNum(){return this.BoxNumber;}
    //update lines
    public void updateLines(){
        for (BaseLine line : lines) {

            line.resizeLine(findNearestBorderPoint(line.getConnection(), line.getMain()), findNearestBorderPoint(line.getMain(), line.getConnection()));


        }

    }
    public void removeLine(BaseLine line){
        lines.remove(line);
    }


    public void deleteLines(){

        ArrayList<BaseLine> linesCopy = new ArrayList<>(lines);

        // Iterate over the copy and remove elements from the original list
        for (BaseLine line : linesCopy) {
            line.getConnectDiagram().removeLine(line);
            line.setClear();
            removeLine(line);
            lines.remove(line); // Remove the line from the original list
        }

        repaint();
    }


    public int getBoxNumber(){return this.BoxNumber;}








}
