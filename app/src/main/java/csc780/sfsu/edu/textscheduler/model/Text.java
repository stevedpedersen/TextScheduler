package csc780.sfsu.edu.textscheduler.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by sp on 4/16/18.
 */
@Entity(tableName = "texts",
        indices = {@Index(value = {"title", "created_date"})})
public class Text {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "created_date")
    private String createdDate;

////    @Embedded(prefix = "recip_")
//    @Embedded
//    public Recipient recipient;

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
    public Text() {
        createdDate = new Date().toString();
    }
    public Text(@NonNull String title) {
        this.title = title;
        createdDate = new Date().toString();
    }
    public String getTextShortSummary(){
        return title + "\n" + createdDate;
    }
    public String getTextSummary(){
        return title + "\n" + createdDate + "\n" + message;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
//    public Recipient getRecipient() {
//        return recipient;
//    }
//    public void setRecipient(Recipient recipient) {
//        this.recipient = recipient;
//    }

}



