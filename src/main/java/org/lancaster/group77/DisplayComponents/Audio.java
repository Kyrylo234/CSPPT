package org.lancaster.group77.DisplayComponents;

import org.lancaster.group77.FileSystem.GlobalVariables;
import org.lancaster.group77.FileSystem.Tools.GeneralTools;

import java.util.Objects;

public class Audio extends DisplayComponentBase {
    String audio_bytes;
    String audio_format;

    public Audio() {
        super("audio");
    }

    public Audio(String audio_bytes, String audio_format, int x, int y, int layer, int width1, int height1) {
        super(x, y, layer, "audio", width1, height1);
        this.audio_bytes = audio_bytes;
        this.audio_format = audio_format;
    }

    public Audio(String audio_bytes, String audio_format, double x, double y, int layer, String json_name, int width1, int height1) {
        super(x, y, layer, json_name, width1, height1);
        this.audio_bytes = audio_bytes;
        this.audio_format = audio_format;
    }

    public String getAudio_bytes() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), audio_bytes);
    }

    public void setAudio_bytes(String audio_bytes) {
        this.audio_bytes = audio_bytes;
    }

    public String getAudio_format() {
        return GeneralTools.getDataFromDuplicate(GlobalVariables.cspptFrame.getFile(), audio_format);
    }

    public void setAudio_format(String audio_format) {
        this.audio_format = audio_format;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Audio audio = (Audio) object;
        return Objects.equals(getAudio_bytes(), audio.getAudio_bytes()) && Objects.equals(getAudio_format(), audio.getAudio_format());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAudio_bytes(), getAudio_format());
    }
}
