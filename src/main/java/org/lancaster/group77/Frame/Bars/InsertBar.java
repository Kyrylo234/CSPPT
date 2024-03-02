package org.lancaster.group77.Frame.Bars;

import org.lancaster.group77.FileSystem.impl.ActionListenerImplementation;
import org.lancaster.group77.Frame.Buttons.Home.CSChartButton;
import org.lancaster.group77.Frame.Buttons.Insert.*;
import org.lancaster.group77.Frame.Buttons.IconButton;
import org.lancaster.group77.InsertComponents.impl.MouseHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InsertBar extends BaseBar{
    private List<JButton> insertButtonsArrayList = new ArrayList<>();
    private NewTextBoxButton newtextBoxButton;
    private InsertImageButton insertImageButton;
    private NewVideoButton newVideoButton;

   private InsertMathChartButton mathChartButton;
    private MouseHandler handler;
    private InsertShapeButton insertShapeButton;
    private InsertCodeSectionButton insertCodeSectionButton;

    private InsertTableButton insertTableButton;

    private CSChartButton csChartButton;


    public InsertBar(JTabbedPane tabbedPane, JPanel InsertTab, ActionListenerImplementation listener, MouseHandler inputHandler){
        super(tabbedPane,InsertTab);
        handler = inputHandler;
        tabbedPane.addTab("Insert", InsertTab);
        InsertTab.setLayout(null);

        InsertTab.add(createLabel("Insert",8,60,60,50,30));

        insertImageButton = new InsertImageButton(new ImageIcon("src/main/resources/Icon/Insert Bar/pictures.png"),10,15,30,35, "Pictures", handler);
        InsertTab.add(insertImageButton);

        newVideoButton = new NewVideoButton(new ImageIcon("src/main/resources/Icon/Insert Bar/videos.png"),60,15,30,35,"Video",handler,this);
        InsertTab.add(newVideoButton);

       // InsertTab.add(new IconButton(new ImageIcon("src/main/resources/Icon/Insert Bar/voice.png"),110,15,30,35, "Shapes"));

        InsertTab.add(createLabel("Pictures",8,5,40,50,30));
        InsertTab.add(createLabel("Video",8,60,40,50,30));
        InsertTab.add(createLabel("Audio",8,110,40,50,30));

        InsertAudioButton insertAudioButton = new InsertAudioButton(new ImageIcon("src/main/resources/Icon/Insert Bar/voice.png"), 110,15,30,35, "Audio", handler);
        InsertTab.add(insertAudioButton);


        JSeparator separator = createSeparator(152);
        InsertTab.add(separator);

        InsertTab.add(createLabel("Create",8,240,60,50,30));

        csChartButton =new CSChartButton(new ImageIcon("src/main/resources/Icon/Insert Bar/cs_chart.png"),165,15,30,35,"CSChart",handler);
        InsertTab.add(csChartButton);
        insertShapeButton = new InsertShapeButton(new ImageIcon("src/main/resources/Icon/Insert Bar/shapes.png"),215,15,30,35,"Shapes", handler);
        InsertTab.add(insertShapeButton);

        insertTableButton = new InsertTableButton(new ImageIcon("src/main/resources/Icon/Insert Bar/forms.png"),265,15,30,35,"Table",handler);
        InsertTab.add(insertTableButton);

        mathChartButton = new InsertMathChartButton(new ImageIcon("src/main/resources/Icon/Home Bar/math_chart.png"),315,15,30,35,"Chart",handler);
        InsertTab.add(mathChartButton);

        InsertTab.add(createLabel("CS Charts",8,165,40,50,30));
        InsertTab.add(createLabel("Shapes",8,215,40,50,30));
        InsertTab.add(createLabel("Tables",8,265,40,50,30));
        InsertTab.add(createLabel("Math",8,318,40,50,30));
        InsertTab.add(createLabel("Charts",8,315,50,50,30));

        JSeparator separator1 = createSeparator(352);
        InsertTab.add(separator1);

        InsertTab.add(createLabel("Boxes",8,390,60,50,30));

        newtextBoxButton = new NewTextBoxButton(new ImageIcon("src/main/resources/Icon/Insert Bar/text_box.png"),360,15,30,35,"TextBox", handler);
        InsertTab.add(newtextBoxButton);

        insertCodeSectionButton = new InsertCodeSectionButton(new ImageIcon("src/main/resources/Icon/Insert Bar/code_section.png"),410,15,30,35,"Code", handler);
        InsertTab.add(insertCodeSectionButton);

        InsertTab.add(createLabel("Text Box",8,360,40,50,30));
        InsertTab.add(createLabel("Code",8,415,40,50,30));
        InsertTab.add(createLabel("Section",8,410,50,50,30));

        JSeparator separator2 = createSeparator(450);
        InsertTab.add(separator2);

        newtextBoxButton.addActionListener(listener);
        insertImageButton.addActionListener(listener);
        newVideoButton.addActionListener(listener);
        insertShapeButton.addActionListener(listener);
        insertCodeSectionButton.addActionListener(listener);
        insertTableButton.addActionListener(listener);
        insertAudioButton.addActionListener(listener);
        mathChartButton.addActionListener(listener);
        csChartButton.addActionListener(listener);
    }
}
