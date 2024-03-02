package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.util.Objects;

public class CodeSection extends DisplayComponentBase{

    private String code = "";
    private double font_size = 13; //default value

    public CodeSection() {
        super("codesection");
    }

    public CodeSection(double x, double y, int layer, String code, double font_size) {
        super(x, y, layer, "codesection", 100, 100);
        this.code = code;
        this.font_size = font_size;
    }

    public String getCode() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), code);

    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getFont_size() {
        return font_size;
    }

    public void setFont_size(double font_size) {
        this.font_size = font_size;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        CodeSection that = (CodeSection) object;
        return Double.compare(getFont_size(), that.getFont_size()) == 0 && Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getFont_size());
    }
}
