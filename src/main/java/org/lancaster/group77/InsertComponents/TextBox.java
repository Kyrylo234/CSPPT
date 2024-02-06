package org.lancaster.group77.InsertComponents;

import javax.swing.*;
import javax.swing.text.*;

import org.lancaster.group77.DisplayComponents.Text;
import org.lancaster.group77.FileSystem.CSPPTFile;
import org.lancaster.group77.FileSystem.impl.DocumentListenerImpl;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import java.awt.*;

public class TextBox extends DraggableComponent{
    private JTextPane text;
    private Text textData;

    public TextBox(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, int slideNum){
        super(x,y,width,height, listener, frame,file);
        text =new ExtendedJtextArea(this);
        add(text);
        text.addMouseListener(listener);
        text.addMouseMotionListener(listener);
        text.setBounds(7,7,width-14,height-14);
        text.getDocument().addDocumentListener(new DocumentListenerImpl(this));
        textData = new Text();
        textData.setLayer(layer);
        textData.setX(x);
        textData.setY(y);
        textData.setColor(String.valueOf(text.getForeground().getRGB()));
        textData.setBackgroundColor(String.valueOf(text.getBackground().getRGB()));
        //TODO get the slide number
        file.getSlides().get(slideNum).addObject(textData);
        //text.setLineWrap(true);
    }

    public TextBox(int x, int y, int width, int height, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int layer, String text1, int slideNum){
        super(x,y,width,height, listener, frame,file);
        text =new ExtendedJtextArea(this, text1);
        add(text);
        text.addMouseListener(listener);
        text.addMouseMotionListener(listener);
        text.setBounds(7,7,width-14,height-14);
        text.getDocument().addDocumentListener(new DocumentListenerImpl(this));
        textData = new Text();
        textData.setLayer(layer);
        textData.setX(x);
        textData.setY(y);
        textData.setText(text1);
        textData.setBackgroundColor(String.valueOf(text.getForeground().getRGB()));
        textData.setColor(String.valueOf(text.getBackground().getRGB()));
        //TODO get the slide number
        file.getSlides().get(slideNum).addObject(textData);
        //text.setLineWrap(true);
    }

    public TextBox(Text text1, MouseHandler listener, JLayeredPane frame, CSPPTFile file, int slideNum){
        super((int)text1.getX(),(int)text1.getY(),(int)text1.getWidth(),(int)text1.getHeight(), listener, frame,file);
        text =new ExtendedJtextArea(this, text1.getText());
        add(text);
        text.addMouseListener(listener);
        text.addMouseMotionListener(listener);
        text.setBounds(7,7,(int)text1.getWidth()-14,(int)text1.getHeight()-14);
        text.getDocument().addDocumentListener(new DocumentListenerImpl(this));

        if(text1.getColor() != null){
            int colour2 = Integer.parseInt(text1.getColor());
            Color color = new Color(colour2);
            text.setForeground(color);
        }else{
            System.out.println("number1");
        }


        if(text1.getBackgroundColor() != null){
            int colour3 = Integer.parseInt(text1.getBackgroundColor());
            Color color1 = new Color(colour3);
            text.setBackground(color1);
        }else{
            System.out.println("number2");
        }


        textData = new Text();
        textData.setLayer(text1.getLayer());
        textData.setX(text1.getX());
        textData.setY(text1.getHeight());
        textData.setText(text1.getText());
        textData.setColor(text1.getColor());
        textData.setBackgroundColor(text1.getBackgroundColor());
        //TODO get the slide number
        file.getSlides().get(slideNum).addObject(textData);
        //text.setLineWrap(true);
    }

    public void resizeTextBox(int inputX, int inputY){
        text.setSize(inputX-14, inputY-14);
        textData.setWidth(inputX-14);
        textData.setHeight(inputY-14);
    }

    public void setLayer(){
        textData.setLayer(textData.getLayer()-1);
    }

    public int getLayer(){
        return textData.getLayer();
    }

    public void setLayer(int i){
        textData.setLayer(i);
    }

    public void changeFont(String fontName){
        System.out.println(fontName);
        Font myFont = new Font(fontName, Font.PLAIN, 16);
        text.setFont(myFont);
    }

    public void changeFontSize(Integer size){
        System.out.println(size);
        Font myFont = new Font("Arial", Font.PLAIN, size);
        text.setFont(myFont);
        textData.setFont_size(size);
    }



    public void increaseFontSize(){
        Font font = text.getFont();
        Font newFont = new Font(font.getFontName(), font.getStyle(), font.getSize()+2);
        text.setFont(newFont);
        textData.setFont_size(font.getSize());
    }
    public void decreaseFontSize(){
        Font font = text.getFont();
        Font newFont = new Font(font.getFontName(), font.getStyle(), font.getSize()-2);
        text.setFont(newFont);
        textData.setFont_size(font.getSize());
    }

    public void boldFunction(){
        Font font = text.getFont();
        if(!font.isBold()){
            textData.setBold(true);
            if(font.isItalic()){
                text.setFont(font.deriveFont(Font.ITALIC + Font.BOLD));
            }else{
                text.setFont(font.deriveFont(Font.BOLD));
            }
        } else{
            textData.setBold(false);
            if(font.isItalic()){
                text.setFont(font.deriveFont(Font.ITALIC));
            }else{
                text.setFont(font.deriveFont(Font.PLAIN));
            }
        }
    }

    public void italicFunction(){
        Font font = text.getFont();
        if(!font.isItalic()){
            textData.setItalic(true);
            if(font.isBold()){
                text.setFont(font.deriveFont(Font.ITALIC + Font.BOLD));
            }else{
                text.setFont(font.deriveFont(Font.ITALIC));
            }
        } else{
            textData.setItalic(false);
            if(font.isBold()){
                text.setFont(font.deriveFont(Font.BOLD));
            }else{
                text.setFont(font.deriveFont(Font.PLAIN));
            }
        }
    }

    public void changeFontColour(){
        JColorChooser colorChooser = new JColorChooser();
        Color  color = JColorChooser.showDialog(null, "select", Color.RED);
        text.setForeground(color);
        String colour1 = String.valueOf(color.getRGB());
        textData.setColor(colour1);
    }
    public void underlineFunction(){
        if(!textData.getUnderline()){
            SimpleAttributeSet underlineAttribute = new SimpleAttributeSet();
            StyleConstants.setUnderline(underlineAttribute, true);
            text.getStyledDocument().setCharacterAttributes(0, text.getText().length(), underlineAttribute, false);
            textData.setUnderline(true);
        }else{
            SimpleAttributeSet underlineAttribute = new SimpleAttributeSet();
            StyleConstants.setUnderline(underlineAttribute, false);
            text.getStyledDocument().setCharacterAttributes(0, text.getText().length(), underlineAttribute, false);
            textData.setUnderline(false);
        }
    }
    float factor = 1;
    boolean replace;

    public void strikethroughFunction() {
        if(!textData.getStrikethrough()){
            String s = textData.getText();
            text.setContentType( "text/html" );
            text.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
            text.setText("<strike>" + s + "<strike>");
            textData.setStrikethrough(true);
        }else{
            text.setText(textData.getText());
            textData.setStrikethrough(false);
        }


    }

    public void lineSpacingFunction(){
        text.selectAll();
        MutableAttributeSet set = new SimpleAttributeSet(text.getParagraphAttributes());
        StyleConstants.setLineSpacing(set, 0.5f);
        text.setParagraphAttributes(set, false);
        text.setMargin(new Insets(10,5,10,5));

    }
    public void alignRightFunction(){
        Font font = text.getFont();
        text.setFont(font);
//        text.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        StyledDocument docStyle = text.getStyledDocument();
        SimpleAttributeSet rightAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(rightAttribute, StyleConstants.ALIGN_RIGHT);
        docStyle.setParagraphAttributes(0, docStyle.getLength(), rightAttribute,false);
    }

    public void alignLeftFunction(){
        Font font = text.getFont();
        text.setFont(font);
//        text.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        StyledDocument docStyle = text.getStyledDocument();
        SimpleAttributeSet leftAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(leftAttribute, StyleConstants.ALIGN_LEFT);
        docStyle.setParagraphAttributes(0, docStyle.getLength(), leftAttribute,false);
    }

    public void alignCenterFunction(){
        Font font = text.getFont();
        text.setFont(font);

        StyledDocument docStyle = text.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        docStyle.setParagraphAttributes(0, docStyle.getLength(), centerAttribute,false);
    }


    public void changeBackgroundColour(){
        JColorChooser colorChooser = new JColorChooser();
        Color  color = JColorChooser.showDialog(null, "select", Color.RED);
        setBackground(color);
        text.setBackground(color);
        String colour1 = String.valueOf(color.getRGB());
        textData.setBackgroundColor(colour1);
    }

    public void addBulletPoint(){

    }

    public void setLocation1(int x, int y){
        textData.setX(x);
        textData.setY(y);
    }

    public void saveText(){
        textData.setText(text.getText());
    }

    public void save(){
        textData.setText(text.getText());
    }

    public JTextPane getTextPane(){
        return text;
    }

}