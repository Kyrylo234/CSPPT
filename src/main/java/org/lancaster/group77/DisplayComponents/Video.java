package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Video extends DisplayComponentBase{
    private String video_bytes;
    public Video() {
        super("video");
    }

    public Video(int x, int y, int layer, int width1, int height1, String video_bytes) {
        super(x, y, layer, "video", width1, height1);
        this.video_bytes = video_bytes;
    }

    public Video(double x, double y, int layer, double width1, double height1, String video_bytes) {
        super(x, y, layer, "video", width1, height1);
        this.video_bytes = video_bytes;
    }


    public String getVideo_bytes() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), video_bytes);
    }

    public void setVideo_bytes(String video_bytes) {
        this.video_bytes = video_bytes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Video video = (Video) object;
        return Objects.equals(getVideo_bytes(), video.getVideo_bytes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVideo_bytes());
    }
}
