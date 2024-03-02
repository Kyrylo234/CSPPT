package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.impl.ActionListenerImplementation;
import org.lancaster.group77.Frame.Buttons.Home.*;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.Frame.Buttons.Insert.InsertMathChartButton;
import org.lancaster.group77.Frame.Buttons.Insert.InsertShapeButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;

import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;


public class HomeBar extends BaseBar {

    private JComboBox<Integer> numberDropdown;

    public HomeBar(JTabbedPane tabbedPane, JPanel homeTab, ActionListenerImplementation listener, MouseHandler handler) {
        super(tabbedPane, homeTab);
        homeTab.setLayout(null);


        //clipboard
        CopyButton copyButton = new CopyButton(new ImageIcon("src/main/resources/Icon/Home Bar/copy.png"), 60, 10, 17, 17, "Copy", handler);
        CutButton cutButton = new CutButton(new ImageIcon("src/main/resources/Icon/Home Bar/cut.png"), 60, 38, 17, 17, "Cut", handler);
        PasteButton pasteButton = new PasteButton(new ImageIcon("src/main/resources/Icon/Home Bar/paste.png"), 10, 10, 30, 40, "Paste");
        homeTab.add(pasteButton);
        homeTab.add(cutButton);
        homeTab.add(copyButton);
        homeTab.add(createLabel("Paste", 10, 15, 45, 50, 30));
        homeTab.add(createLabel("Clipboard", 8, 20, 60, 50, 30));

        JSeparator clipboardSeparator = createSeparator(84);

        homeTab.add(clipboardSeparator);
        //slide
        NewSlide newSlideButton = new NewSlide(new ImageIcon("src/main/resources/Icon/Home Bar/new_slide.png"), 90, 15, 27, 32, "NewSlide", handler.getSlideManager());
        homeTab.add(createLabel("New Slide", 7, 87, 43, 60, 30));
        homeTab.add(createLabel("Slide", 8, 95, 60, 50, 30));
        homeTab.add(newSlideButton);
        JSeparator slideSeparator = createSeparator(125);


        // font
        numberDropdown = createNumberDropdown();
        numberDropdown.setName("font size");
        numberDropdown.setBounds(245, 15, 40, 20);
        JComboBox<String> fontDropdown = createFontDropdown();
        fontDropdown.setName("font");
        fontDropdown.setBounds(140, 15, 100, 20);

        BoldButton boldButton = new BoldButton(new ImageIcon("src/main/resources/Icon/Home Bar/bold.png"), 140, 43, 15, 15, "Bold", handler);
        ItalicButton italicButton = new ItalicButton(new ImageIcon("src/main/resources/Icon/Home Bar/italic.png"), 160, 43, 15, 15, "Italic", handler);

        homeTab.add(slideSeparator);
        homeTab.add(fontDropdown);
        homeTab.add(numberDropdown);
        homeTab.add(boldButton);
        homeTab.add(italicButton);

        //  homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/underline.png"), 180, 43,15 , 15, "Underline"));
        // homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/strikethrough.png"), 200, 43,15 , 15, "StrikeThrough"));


        //strikethrough
        StrikethroughButton strbtn = new StrikethroughButton(new ImageIcon("src/main/resources/Icon/Home Bar/strikethrough.png"), 200, 43, 15, 15, "StrikeThrough", handler);
        homeTab.add(strbtn);
        //font color
        ColorButton colorButton = new ColorButton(new ImageIcon("src/main/resources/Icon/Home Bar/font_color.png"), 220, 43, 15, 15, "FontColor", handler);
        homeTab.add(colorButton);

        //underline
        UnderlineButton underlineButton = new UnderlineButton(new ImageIcon("src/main/resources/Icon/Home Bar/underline.png"), 180, 43, 15, 15, "Underline", handler);
        homeTab.add(underlineButton);


        IncreaseFontSizeButton increaseFontSizeButton = new IncreaseFontSizeButton(new ImageIcon("src/main/resources/Icon/Home Bar/increase_font_size.png"), 240, 43, 15, 15, "IncreaseFontSize", handler);
        DecreaseFontSizeButton decreaseFontSizeButton = new DecreaseFontSizeButton(new ImageIcon("src/main/resources/Icon/Home Bar/decreas_font_size.png"), 260, 43, 15, 15, "DecreaseFontSize", handler);

        homeTab.add(increaseFontSizeButton);
        homeTab.add(decreaseFontSizeButton);

        JSeparator fontSeparator = createSeparator(295);
        homeTab.add(fontSeparator);
        homeTab.add(createLabel("Font", 8, 200, 60, 50, 30));

//        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/bullets.png"), 330, 43, 18, 15, "Bullets"));
        BulletPointButton bulletPointButton = new BulletPointButton(new ImageIcon("src/main/resources/Icon/Home Bar/bullets.png"), 330, 43, 18, 15, "Bullets", handler);
        homeTab.add(bulletPointButton);
        JSeparator paragraphSeparator = createSeparator(358);
        homeTab.add(paragraphSeparator);
        homeTab.add(createLabel("Paragraph", 8, 305, 60, 60, 30));


        //line spacing
        LineSpacingButton lineSpacebtn = new LineSpacingButton(new ImageIcon("src/main/resources/Icon/Home Bar/line_spacing.png"), 310, 43, 18, 15, "LineSpacing", handler);
        homeTab.add(lineSpacebtn);

        //align right
        AlignRightButton align_rightbtn = new AlignRightButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_right.png"), 340, 15, 15, 15, "AlignRight", handler);
        homeTab.add(align_rightbtn);

        //align left
        AlignLeftButton align_leftbtn = new AlignLeftButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_left.png"), 300, 15, 15, 15, "AlignLeft", handler);
        homeTab.add(align_leftbtn);

        //aligncenter
        AlignCenterButton align_centerbtn = new AlignCenterButton(new ImageIcon("src/main/resources/Icon/Home Bar/align_center.png"), 320, 15, 15, 15, "AlignCenter", handler);
        homeTab.add(align_centerbtn);

        //illustrations
        InsertShapeButton insertShapeButton = new InsertShapeButton(new ImageIcon("src/main/resources/Icon/Insert Bar/shapes.png"),370, 13, 40, 40,"Shapes", handler);
        homeTab.add(insertShapeButton);

        //homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/shapes.png"), 370, 13,40 , 40, "Shapes"));
       // NextSlide nextSlide = new NextSlide(new ImageIcon("src/main/resources/Icon/Home Bar/shapes.png"), 370, 13,40 , 40, "NextSlide", handler.getSlideManager());
        //homeTab.add(nextSlide);
        CSChartButton csChartButton = new CSChartButton(new ImageIcon("src/main/resources/Icon/Home Bar/cs_chart.png"), 420, 33,15 , 15, "CSChart",handler);
        homeTab.add(csChartButton);
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/math_chart.png"), 420, 13,15 , 15, "MathChart"));
        //homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/cs_chart.png"), 420, 33,15 , 15, "CSChart"));
        homeTab.add(createLabel("Shapes",8,370,47,50,30));


        InsertMathChartButton mathChartButton = new InsertMathChartButton(new ImageIcon("src/main/resources/Icon/Home Bar/math_chart.png"),420, 13, 15, 15,"Chart",handler);
        homeTab.add(mathChartButton);
        homeTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Home Bar/cs_chart.png"), 420, 33, 15, 15, "CSChart"));

        homeTab.add(createLabel("Math Charts", 7, 440, 7, 70, 30));
        homeTab.add(createLabel("CS Charts", 7, 440, 35, 70, 10));
        homeTab.add(createLabel("Illustrations", 8, 400, 60, 80, 30));

        JSeparator illustrationsSeparator = createSeparator(490);

        //background
        FormatBackgroundButton formatBackgroundButton = new FormatBackgroundButton(new ImageIcon("src/main/resources/Icon/Home Bar/change_colour.png"), 500, 15, 40, 40, "FormatBackground", handler);
        homeTab.add(createLabel("Change Colour", 8, 495, 60, 80, 30));
        JSeparator backgroundSeparator = createSeparator(555);

        //Command line
        CommandLineButton commandLineButton = new CommandLineButton(new ImageIcon("src/main/resources/Icon/Home Bar/command_line.png"), 560, 10, 50, 55, "CommandLine");
        homeTab.add(commandLineButton);
        homeTab.add(createLabel("Command Line", 7, 560, 60, 70, 30));
        JSeparator commandLineSeparator = createSeparator(620);


        homeTab.add(illustrationsSeparator);
        homeTab.add(formatBackgroundButton);
        homeTab.add(backgroundSeparator);
        homeTab.add(commandLineSeparator);


        //adding  action listeners for the buttons
        newSlideButton.addActionListener(listener);
        insertShapeButton.addActionListener(listener);
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
        bulletPointButton.addActionListener(listener);
        copyButton.addActionListener(listener);
        cutButton.addActionListener(listener);
        pasteButton.addActionListener(listener);
        fontDropdown.addActionListener(listener);
        numberDropdown.addActionListener(listener);
        commandLineButton.addActionListener(listener);
        csChartButton.addActionListener(listener);
        mathChartButton.addActionListener(listener);

        tabbedPane.addTab("Home", homeTab);
    }


    private JComboBox<Integer> createNumberDropdown() {
        Integer[] numbers = new Integer[50];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 1 + i * 2;
        }

        JComboBox<Integer> fontSizeDropdown = new JComboBox<>(numbers);
        fontSizeDropdown.setSelectedIndex(6);
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

    public JComboBox<Integer> getNumberDropdown() {
        return numberDropdown;
    }

    public void setNumberDropdown(JComboBox<Integer> numberDropdown) {
        this.numberDropdown = numberDropdown;
    }
}