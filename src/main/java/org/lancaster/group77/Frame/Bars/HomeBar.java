package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.impl.ActionListenerImplmenetation;
import org.lancaster.group77.Frame.Buttons.Home.*;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Buttons.Insert.InsertShapeButton;
import org.lancaster.group77.Frame.CSPPTFrame;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
public class HomeBar extends BaseBar{
    private List<JButton> homeButtonsArrayList = new ArrayList<>();
    private MouseHandler handler;
    private InsertShapeButton insertShapeButton;
    private CSPPTFrame frame;

    JPanel homeTab;
    public  HomeBar(JTabbedPane tabbedPane, JPanel homeTab, ActionListenerImplmenetation listener, MouseHandler inputHandler, CSPPTFrame frame){
        super(tabbedPane,homeTab);
        handler = inputHandler;
        this.frame = frame;
        homeTab.setLayout(null);


        //clipboard
        CopyButton copyButton = new CopyButton(new ImageIcon("src/main/resources/Icon/Home Bar/copy.png"), 60, 10, 17, 17, "Copy");
        CutButton cutButton = new CutButton(new ImageIcon("src/main/resources/Icon/Home Bar/cut.png"), 60, 38, 17, 17, "Cut");
        PasteButton pasteButton = new PasteButton(new ImageIcon("src/main/resources/Icon/Home Bar/paste.png"), 10, 10, 30, 40, "Paste");
        homeTab.add(pasteButton);
        homeTab.add(cutButton);
        homeTab.add(copyButton);
        homeTab.add(createLabel("Paste", 10, 15, 45, 50, 30));
        homeTab.add(createLabel("Clipboard", 8, 20, 55, 50, 30));

        JSeparator clipboardSeparator = createSeparator(84);

        homeTab.add(clipboardSeparator);
        //slide
        NewSlide newSlideButton =new NewSlide(new ImageIcon("src/main/resources/Icon/Home Bar/new_slide.png"), 90, 15,27 , 32, "NewSlide",handler,handler.getSlideManager(), this);
        homeTab.add(createLabel("New Slide",7,87,43,60,30));
        homeTab.add(createLabel("Slide",8,90,55,50,30));
        homeTab.add(newSlideButton);
        JSeparator slideSeparator = createSeparator(125);

        // font
        JComboBox<Integer> numberDropdown = createNumberDropdown();
        numberDropdown.setBounds(245, 15, 40, 20);
        JComboBox<String> fontDropdown = createFontDropdown();
        fontDropdown.setBounds(140, 15, 100, 20);

        BoldButton boldButton = new BoldButton(new ImageIcon("src/main/resources/Icon/Home Bar/bold.png"), 140, 43,15 , 15, "Bold", handler);
        ItalicButton italicButton = new ItalicButton(new ImageIcon("src/main/resources/Icon/Home Bar/italic.png"), 160, 43,15 , 15, "Italic", handler);

        homeTab.add(slideSeparator);
        homeTab.add(fontDropdown);
        homeTab.add(numberDropdown);
        homeTab.add(boldButton);
        homeTab.add(italicButton);

      //  homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/underline.png"), 180, 43,15 , 15, "Underline"));
       // homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/strikethrough.png"), 200, 43,15 , 15, "StrikeThrough"));


        //strikethrough
        StrikethroughButton strbtn = new StrikethroughButton(new ImageIcon("src/main/resources/Icon/Home Bar/strikethrough.png"),200,43,15,15, "StrikeThrough", handler);
        homeTab.add(strbtn);
        //font color
        ColorButton colorButton = new ColorButton(new ImageIcon("src/main/resources/Icon/Home Bar/font_color.png"), 220, 43,15 , 15, "FontColor", handler);
        homeTab.add(colorButton);

        //underline
        UnderlineButton underlineButton = new UnderlineButton(new ImageIcon("src/main/resources/Icon/Home Bar/underline.png"), 180, 43, 15,15,"Underline", handler);
        homeTab.add(underlineButton);


        IncreaseFontSizeButton increaseFontSizeButton = new IncreaseFontSizeButton(new ImageIcon("src/main/resources/Icon/Home Bar/increase_font_size.png"), 240, 43,15 , 15, "IncreaseFontSize", handler);
        DecreaseFontSizeButton decreaseFontSizeButton = new DecreaseFontSizeButton(new ImageIcon("src/main/resources/Icon/Home Bar/decreas_font_size.png"), 260, 43,15 , 15, "DecreaseFontSize", handler);

        homeTab.add(increaseFontSizeButton);
        homeTab.add(decreaseFontSizeButton);

        JSeparator fontSeparator = createSeparator(295);
        homeTab.add(fontSeparator);
        homeTab.add(createLabel("Font",8,200,55,50,30));

        //paragraph
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_left.png"), 300, 15,15 , 15, "AlignLeft"));
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_center.png"), 320, 15,15 , 15, "AlignCenter"));
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_right.png"), 340, 15,15 , 15, "AlignRight"));
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/line_spacing.png"), 310, 43,18 , 15, "LineSpacing"));
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/bullets.png"), 330, 43,18 , 15, "Bullets"));
        JSeparator paragraphSeparator = createSeparator(358);
        homeTab.add(paragraphSeparator);
        homeTab.add(createLabel("Paragraph",8,300,55,60,30));


        //line spacing
        LineSpacingButton lineSpacebtn = new LineSpacingButton(new ImageIcon("src/main/resources/Icon/Home Bar/line_spacing.png"), 310, 43,18 , 15, "LineSpacing", handler);
        homeTab.add(lineSpacebtn);

        //align right
        AlignRightButton align_rightbtn = new AlignRightButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_right.png"),340,15,15,15, "AlignRight", handler);
        homeTab.add(align_rightbtn);

        //align left
        AlignLeftButton align_leftbtn = new AlignLeftButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_left.png"),300,15,15,15, "AlignLeft", handler);
        homeTab.add(align_leftbtn);

        //aligncenter
        AlignCenterButton align_centerbtn = new AlignCenterButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_center.png"),320,15,15,15, "AlignCenter", handler);
        homeTab.add(align_centerbtn);

        //illustrations

        //homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/shapes.png"), 370, 13,40 , 40, "Shapes"));
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/math_chart.png"), 420, 13,15 , 15, "MathChart"));
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/cs_chart.png"), 420, 33,15 , 15, "CSChart"));
        homeTab.add(createLabel("Shape",8,370,47,50,30));
        homeTab.add(createLabel("Math Chart",7,440,7,70,30));
        homeTab.add(createLabel("CS Chart",7,440,35,70,10));
        homeTab.add(createLabel("Illustrations",8,400,55,80,30));

        JSeparator illustrationsSeparator = createSeparator(490);

        //background
        FormatBackgroundButton formatBackgroundButton = new FormatBackgroundButton(new ImageIcon("src/main/resources/Icon/Home Bar/format_background.png"), 495, 10,50 , 55, "FormatBackground", handler);
        JSeparator backgroundSeparator = createSeparator(555 );

        //Command line
        CommandLineButton commandLineButton = new CommandLineButton(new ImageIcon("src/main/resources/Icon/Home Bar/command_line.png"), 560, 10, 50, 55, "CommandLine",frame);
        homeTab.add(commandLineButton);
        homeTab.add(createLabel("Command Line",7,560,55,70,30));
        JSeparator commandLineSeparator = createSeparator(620);


        homeTab.add(illustrationsSeparator);
        homeTab.add(formatBackgroundButton);
        homeTab.add(backgroundSeparator);
        homeTab.add(commandLineSeparator);



        //adding  action listeners for the buttons
        newSlideButton.addActionListener(listener);
        increaseFontSizeButton.addActionListener(listener);
        decreaseFontSizeButton.addActionListener(listener);
        boldButton.addActionListener(listener);
        italicButton.addActionListener(listener);
        formatBackgroundButton.addActionListener(listener);
        colorButton.addActionListener(listener);
        align_rightbtn.addActionListener(listener);
        align_leftbtn.addActionListener(listener);
        align_centerbtn.addActionListener(listener);
        underlineButton.addActionListener(listener);
        strbtn.addActionListener(listener);
        lineSpacebtn.addActionListener(listener);
        copyButton.addActionListener(listener);
        cutButton.addActionListener(listener);
        pasteButton.addActionListener(listener);
        fontDropdown.addActionListener(listener);
        numberDropdown.addActionListener(listener);
        commandLineButton.addActionListener(listener);

        tabbedPane.addTab("Home", homeTab);
    }





    private JComboBox<Integer> createNumberDropdown() {
        Integer[] numbers = new Integer[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 2 + i * 2;
        }

        JComboBox<Integer> fontSizeDropdown = new JComboBox<>(numbers);
        fontSizeDropdown.setSelectedIndex(0);
        fontSizeDropdown.setBackground(Color.LIGHT_GRAY);

        //makes the arrow button invisible
        fontSizeDropdown.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        fontSizeDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return fontSizeDropdown;
    }

    private JComboBox<String> createFontDropdown() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();

        JComboBox<String> fontDropdown = new JComboBox<>(fonts);
        fontDropdown.setSelectedIndex(0);
        fontDropdown.setBackground(Color.LIGHT_GRAY);

        //makes the arrow button invisible
        fontDropdown.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });

        // Set black border
        fontDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return fontDropdown;
    }

    public CSPPTFrame getFrame(){
        return frame;
    }



}