package org.lancaster.group77.InsertComponents.TextBox;

import javax.swing.*;
import javax.swing.text.*;

import org.lancaster.group77.DisplayComponents.CSBox;
import org.lancaster.group77.DisplayComponents.Text;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.FileChooser;
import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.impl.DocumentListenerImpl;
import org.lancaster.group77.InsertComponents.DraggableComponent;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import java.awt.*;

public class TextBox extends DraggableComponent {
    private JTextPane text;
    private Text textData;
    private CSBox csBox;
    boolean isCSBox = false;

    public TextBox(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, int slideNum) {
        super(x, y, width, height, listener, frame, file, "TextBox");

        initTextBox(width, height, listener, "");

        text.setBackground(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
        text.setForeground(Color.white);
        text.setFont(new Font("Arial", Font.PLAIN, 13));
        DraggableComponent.initRightClickMenu(text);


        initTextData(x, y, layer, "", width, height);
        text.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));

        file.getSlides().get(slideNum).addObject(textData);
    }

    public TextBox(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, String text1, int slideNum) {
        super(x, y, width, height, listener, frame, file, "TextBox");

        initTextBox(width, height, listener, "");

        text.setBackground(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
        text.setForeground(Color.white);
        text.setFont(new Font("Arial", Font.PLAIN, 13));
        DraggableComponent.initRightClickMenu(text);


        initTextData(x, y, layer, text1, width, height);

        file.getSlides().get(slideNum).addObject(textData);
    }

    public TextBox(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, String text1, int slideNum, boolean isCSBox) {
        super(x, y, width, height, listener, frame, file, "TextBox");
        this.isCSBox = isCSBox;

        initTextBox(width, height, listener, "");

        text.setBackground(new Color(GlobalVariables.DEFAULT_BACKGROUND_COLOR));
        text.setForeground(Color.white);
        text.setFont(new Font("Arial", Font.PLAIN, 13));


        if (!isCSBox) {
            initTextData(x, y, layer, text1, width, height);
            file.getSlides().get(slideNum).addObject(textData);
        }


    }

    public TextBox(Text text1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile) {
        super((int) (text1.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (text1.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (text1.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (text1.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, "TextBox");
        initTextBox((int) (text1.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X),
                (int) (text1.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, text1.getText());

        //init the text data
        textData = text1;
        this.setComponentData(textData);
        initTextStyleByTextData();

        //if we are opening a file we don't want to add the object to the file
        if (!isOpeningFile)
            file.getSlides().get(slideNum).addObject(textData);
    }

    public TextBox(Text text1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum, boolean isOpeningFile, boolean isCSBox, CSBox csBox) {
        super((int) (text1.getX() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (text1.getY() * GlobalVariables.FRAME_SIZE_INDEX_Y), (int) (text1.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X), (int) (text1.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, frame, file, "TextBox");
        initTextBox((int) (text1.getWidth() * GlobalVariables.FRAME_SIZE_INDEX_X),
                (int) (text1.getHeight() * GlobalVariables.FRAME_SIZE_INDEX_Y), listener, text1.getText());

        this.isCSBox = isCSBox;
        this.csBox = csBox;

        //if we are opening a file we don't want to add the object to the file
        if (isCSBox) {
            //init the text data
            textData = text1;
            this.setComponentData(textData);
            initTextStyleByTextData();
        }
        if (!isOpeningFile)
            file.getSlides().get(slideNum).addObject(textData);
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
        DraggableComponent.initRightClickMenu(text);
    }


    /**
     * This method is used to initialize the text data
     *
     * @param x
     * @param y
     * @param layer
     * @param text1
     */
    public void initTextData(int x, int y, int layer, String text1, int width, int height) {

        if (isCSBox) {
            textData = new Text();
            this.setComponentData(textData);
            textData.setLayer(layer);
            textData.setX(x / GlobalVariables.FRAME_SIZE_INDEX_X);
            textData.setY((y / GlobalVariables.FRAME_SIZE_INDEX_Y));
            textData.setWidth((width / GlobalVariables.FRAME_SIZE_INDEX_X));
            textData.setHeight((height / GlobalVariables.FRAME_SIZE_INDEX_Y));
            textData.setText(text1);
            textData.setColor(String.valueOf(text.getForeground().getRGB()));
            textData.setBackgroundColor(String.valueOf(text.getBackground().getRGB()));

        } else {
            textData = new Text();
            this.setComponentData(textData);
            textData.setLayer(layer);
            textData.setX(x / GlobalVariables.FRAME_SIZE_INDEX_X);
            textData.setY((y / GlobalVariables.FRAME_SIZE_INDEX_Y));
            textData.setWidth((width / GlobalVariables.FRAME_SIZE_INDEX_X));
            textData.setHeight((height / GlobalVariables.FRAME_SIZE_INDEX_Y));
            textData.setText(text1);
            textData.setColor(String.valueOf(text.getForeground().getRGB()));
            textData.setBackgroundColor(String.valueOf(text.getBackground().getRGB()));
        }
    }

    private void initTextStyleByTextData() {
        text.setFont(getFont());
        text.setForeground(new Color(Integer.parseInt(textData.getColor())));
        text.setBackground(new Color(Integer.parseInt(textData.getBackgroundColor())));
        setLineSpacing(textData.getLine_spacing());

        if (isCSBox) {
            Font font = new Font(csBox.getFont(), Font.PLAIN, (int) csBox.getFont_size());
            text.setFont(font);
            text.setForeground(new Color(Integer.parseInt(csBox.getColor())));
            text.setBackground(new Color(Integer.parseInt(csBox.getBackgroundColor())));
            if (csBox.getBold()) {
                boldFunction();
            }
            if (csBox.getItalic()) {
                italicFunction();
            }
            if (csBox.getUnderline()) {
                csBox.setUnderline(false);
                underlineFunction();
            }
            if (csBox.getBulletpoint()) {
                csBox.setBulletpoint(false);
                bulletpointFunction();
            }
            if (csBox.getStrikethrough()) {
                csBox.setStrikethrough(false);
                strikethroughFunction();
            }
            if (csBox.getLeft()) {
                alignLeftFunction();
            }
            if (csBox.getRight()) {
                alignRightFunction();
            }
            if (csBox.getCenter()) {
                alignCenterFunction();
            }
            if (csBox.getColor() != null) {
                int colour2 = Integer.parseInt(csBox.getColor());
                Color color = new Color(colour2);
                text.setForeground(color);
            }

            if (textData.getBackgroundColor() != null) {
                int colour3 = Integer.parseInt(textData.getBackgroundColor());
                Color color1 = new Color(colour3);
                text.setBackground(color1);
            }


        } else {
            Font font = new Font(textData.getFont(), Font.PLAIN, (int) textData.getFont_size());
            text.setFont(font);
            text.setForeground(new Color(Integer.parseInt(textData.getColor())));
            text.setBackground(new Color(Integer.parseInt(textData.getBackgroundColor())));
            if (textData.getBold()) {
                boldFunction();
            }
            if (textData.getItalic()) {
                italicFunction();
            }
            if (textData.getUnderline()) {
                textData.setUnderline(false);
                underlineFunction();
            }
            if (textData.getBulletpoint()) {
                textData.setBulletpoint(false);
                bulletpointFunction();
            }
            if (textData.getStrikethrough()) {
                textData.setStrikethrough(false);
                strikethroughFunction();
            }
            if (textData.getLeft()) {
                alignLeftFunction();
            }
            if (textData.getRight()) {
                alignRightFunction();
            }
            if (textData.getCenter()) {
                alignCenterFunction();
            }
            if (textData.getColor() != null) {
                int colour2 = Integer.parseInt(textData.getColor());
                Color color = new Color(colour2);
                text.setForeground(color);
            }

            if (textData.getBackgroundColor() != null) {
                int colour3 = Integer.parseInt(textData.getBackgroundColor());
                Color color1 = new Color(colour3);
                text.setBackground(color1);
            }
        }
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight, int layer) {
        text.setSize(inputWidth - 14, inputHeight - 14);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight, layer);
    }

    @Override
    public void resizeComponent(int inputX, int inputY, int inputWidth, int inputHeight) {
        text.setSize(inputWidth - 14, inputHeight - 14);
        super.resizeComponent(inputX, inputY, inputWidth, inputHeight);
    }

    public void setLayer() {
        textData.setLayer(textData.getLayer() - 1);
    }

    public int getLayer() {
        if (isCSBox) {
            return csBox.getLayer();
        } else {
            return textData.getLayer();
        }
    }

    public void changeFont(String fontName) {
        Font myFont = new Font(fontName, Font.PLAIN, text.getFont().getSize());
        text.setFont(myFont);
        textData.setFont(fontName);
    }

    public void changeFontSize(Integer size) {
        Font font = text.getFont();
        Font newFont = new Font(font.getFontName(), font.getStyle(), size);
        text.setFont(newFont);
        if (isCSBox) {
            csBox.setFont_size(size);

        } else {
            textData.setFont_size(size);

        }
    }


    public void increaseFontSize() {
        Font font = text.getFont();
        Font newFont = new Font(font.getFontName(), font.getStyle(), font.getSize() + 2);
        text.setFont(newFont);
        if (isCSBox) {
            csBox.setFont_size(font.getSize());
        } else {
            textData.setFont_size(font.getSize());
        }
        int size = newFont.getSize();
        GlobalVariables.cspptFrame.getMainHomeBar().getNumberDropdown().setSelectedIndex(size / 2);
    }

    public void decreaseFontSize() {
        Font font = text.getFont();
        Font newFont = new Font(font.getFontName(), font.getStyle(), font.getSize() - 2);
        text.setFont(newFont);
        if (isCSBox) {
            csBox.setFont_size(font.getSize());

        } else {
            textData.setFont_size(font.getSize());

        }
        int size = newFont.getSize();
        GlobalVariables.cspptFrame.getMainHomeBar().getNumberDropdown().setSelectedIndex(size / 2);
    }

    public void boldFunction() {
        Font font = text.getFont();
        if (!font.isBold()) {
            if (isCSBox) {
                csBox.setBold(true);

            } else {
                textData.setBold(true);
            }
            if (font.isItalic()) {
                text.setFont(font.deriveFont(Font.ITALIC + Font.BOLD));
            } else {
                text.setFont(font.deriveFont(Font.BOLD));
            }
        } else {
            if (isCSBox) {
                csBox.setBold(false);

            } else {
                textData.setBold(false);
            }
            if (font.isItalic()) {
                text.setFont(font.deriveFont(Font.ITALIC));
            } else {
                text.setFont(font.deriveFont(Font.PLAIN));
            }
        }
    }

    public void italicFunction() {
        Font font = text.getFont();
        if (!font.isItalic()) {
            if (isCSBox) {
                csBox.setItalic(true);

            } else {
                textData.setItalic(true);
            }
            if (font.isBold()) {
                text.setFont(font.deriveFont(Font.ITALIC + Font.BOLD));
            } else {
                text.setFont(font.deriveFont(Font.ITALIC));
            }
        } else {
            if (isCSBox) {
                csBox.setItalic(false);

            } else {
                textData.setItalic(false);
            }
            if (font.isBold()) {
                text.setFont(font.deriveFont(Font.BOLD));
            } else {
                text.setFont(font.deriveFont(Font.PLAIN));
            }
        }
    }

    public void changeFontColour() {
        Color color = JColorChooser.showDialog(FileChooser.getTempFrame(), "Select Colour", Color.RED);
        if (color == null) {
            return;
        }
        text.setForeground(color);
        String colour1 = String.valueOf(color.getRGB());

        if (isCSBox) {
            csBox.setColor(colour1);

        } else {
            textData.setColor(colour1);
        }
    }

    public void underlineFunction() {
        SimpleAttributeSet underlineAttribute = new SimpleAttributeSet();

        if (isCSBox) {
            if (!csBox.getUnderline()) {
                StyleConstants.setUnderline(underlineAttribute, true);
                csBox.setUnderline(true);
            } else {
                StyleConstants.setUnderline(underlineAttribute, false);
                csBox.setUnderline(false);
            }

        } else {
            if (!textData.getUnderline()) {
                StyleConstants.setUnderline(underlineAttribute, true);
                textData.setUnderline(true);
            } else {
                StyleConstants.setUnderline(underlineAttribute, false);
                textData.setUnderline(false);
            }
        }
        text.getStyledDocument().setCharacterAttributes(0, text.getText().length(), underlineAttribute, false);
    }

    public void bulletpointFunction() {
        String data = textData.getText();

        boolean isBullet = false;
        if (isCSBox) {
            data = csBox.getText();
            isBullet = csBox.getBulletpoint();
        } else {
            data = textData.getText();
            isBullet = textData.getBulletpoint();
        }
        if (!isBullet) {
            StringBuilder bulletText = new StringBuilder();
            String[] lines = data.split("\n");

            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    bulletText.append("• ").append(line.trim()).append("\n");
                } else {
                    bulletText.append("\n");
                }
            }

            text.setText(bulletText.toString());

            if (isCSBox) {
                csBox.setText(bulletText.toString());
                csBox.setBulletpoint(true);
            } else {
                textData.setText(bulletText.toString());
                textData.setBulletpoint(true);
            }

            textData.setBulletpoint(false);
            if (isCSBox) {
                csBox.setText(textData.getText().replace("• ", ""));
                csBox.setBulletpoint(false);
            } else {
                textData.setText(textData.getText().replace("• ", ""));
                textData.setBulletpoint(false);
            }

        }
    }

    float factor = 1;
    boolean replace;

    public void strikethroughFunction() {
        if (!textData.getStrikethrough()) {
            String s = textData.getText();
            text.setContentType("text/html");
            text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
            StringBuilder textBuilder = new StringBuilder();
            String[] strings = s.split("\n");
            for (int i = 0; i < strings.length; i++) {
                textBuilder.append("<strike>" + strings[i] + "<strike><br\\>");
            }
            text.setText(textBuilder.toString());
            if (isCSBox)
                csBox.setStrikethrough(true);
            else
                textData.setStrikethrough(true);

        } else {
            text.setText(textData.getText());
            if (isCSBox)
                csBox.setStrikethrough(false);
            else
                textData.setStrikethrough(false);
        }


    }

    public void lineSpacingFunction() {
        float currentLineSpacing = textData.getLine_spacing();
        text.selectAll();
        MutableAttributeSet set = new SimpleAttributeSet(text.getParagraphAttributes());

        currentLineSpacing += 0.3f;
        StyleConstants.setLineSpacing(set, (float) currentLineSpacing);

        text.setParagraphAttributes(set, false);
        text.setMargin(new Insets(10, 5, 10, 5));
        textData.setLine_spacing(currentLineSpacing);
    }

    public void setLineSpacing(float spacing) {
        text.selectAll();
        MutableAttributeSet set = new SimpleAttributeSet(text.getParagraphAttributes());

        StyleConstants.setLineSpacing(set, spacing);

        text.setParagraphAttributes(set, false);
        text.setMargin(new Insets(10, 5, 10, 5));
    }

    public void alignRightFunction() {
        Font font = text.getFont();
        text.setFont(font);
        StyledDocument docStyle = text.getStyledDocument();
        SimpleAttributeSet rightAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(rightAttribute, StyleConstants.ALIGN_RIGHT);
        docStyle.setParagraphAttributes(0, docStyle.getLength(), rightAttribute, false);
        if (isCSBox) {
            csBox.setLeft(false);
            csBox.setCenter(false);
            csBox.setRight(true);
        } else {
            textData.setLeft(false);
            textData.setCenter(false);
            textData.setRight(true);
        }
    }

    public void alignLeftFunction() {
        Font font = text.getFont();
        text.setFont(font);
//        text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        StyledDocument docStyle = text.getStyledDocument();
        SimpleAttributeSet leftAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(leftAttribute, StyleConstants.ALIGN_LEFT);
        docStyle.setParagraphAttributes(0, docStyle.getLength(), leftAttribute, false);
        if (isCSBox) {
            csBox.setLeft(true);
            csBox.setCenter(false);
            csBox.setRight(false);
        } else {
            textData.setLeft(true);
            textData.setCenter(false);
            textData.setRight(false);
        }
    }

    public void alignCenterFunction() {
        Font font = text.getFont();
        text.setFont(font);

        StyledDocument docStyle = text.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        docStyle.setParagraphAttributes(0, docStyle.getLength(), centerAttribute, false);

        if (isCSBox) {
            csBox.setLeft(false);
            csBox.setCenter(true);
            csBox.setRight(false);
        } else {
            textData.setLeft(false);
            textData.setCenter(true);
            textData.setRight(false);
        }

    }


    public void changeBackgroundColour() {
        Color color = JColorChooser.showDialog(FileChooser.getTempFrame(), "Select Colour", Color.RED);
        if (color == null) {
            return;
        }
        setBackground(color);
        text.setBackground(color);
        String colour1 = String.valueOf(color.getRGB());
        textData.setBackgroundColor(colour1);
    }

    public void addBulletPoint() {

    }

    public void saveText() {
        textData.setText(text.getText());
    }

    public void save() {
        if (isCSBox)
            csBox.setText(text.getText());
        else
            textData.setText(text.getText());

    }

    public JTextPane getTextPane() {
        return text;
    }

    public Text getTextData() {
        return textData;
    }

    public void setTextData(Text textData) {
        this.textData = textData;
    }

    public void disableInput() {
        text.setEditable(false);
    }

    public void enableInput() {
        text.setEditable(true);
    }

    public CSBox getCsBox() {
        return csBox;
    }

    public void setCsBox(CSBox csBox) {
        this.csBox = csBox;
    }
}