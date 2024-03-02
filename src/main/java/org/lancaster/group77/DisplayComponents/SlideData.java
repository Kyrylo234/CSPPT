package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;

import java.util.Objects;

public class SlideData extends DisplayComponentBase{
    private int background_color = GlobalVariables.DEFAULT_BACKGROUND_COLOR;
    public SlideData() {
        super("slidedata");
    }

    public SlideData(int background_color) {
        super("slidedata");
        this.background_color = background_color;
    }

    public int getBackground_color() {
        return background_color;
    }

    public void setBackground_color(int background_color) {
        this.background_color = background_color;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        SlideData slideData = (SlideData) object;
        return getBackground_color() == slideData.getBackground_color();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBackground_color());
    }
}
