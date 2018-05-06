package csc780.sfsu.edu.textscheduler.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by sp on 4/16/18.
 */

@Entity(tableName = "schedules",
        foreignKeys = @ForeignKey(entity = Text.class,
        parentColumns = "id",
        childColumns = "text_id",
        onDelete = CASCADE),
        indices = {@Index(value = {"text_id", "send_date"})})
public class Schedule {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text_id")
    public int textId;

    @ColumnInfo(name = "send_date")
    private String sendDate;

    @ColumnInfo(name = "sent")
    private Boolean sent;

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
    public Schedule() {}
    public Schedule(int textId) {
        this.textId = textId;
        this.sent = false;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTextId() {
        return textId;
    }
    public void setTextId(int textId) {
        this.textId = textId;
    }
    public String getSendDate() {
        return sendDate;
    }
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
    public Boolean getSent() {
        return sent;
    }
    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
