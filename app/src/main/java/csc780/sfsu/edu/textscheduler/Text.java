package csc780.sfsu.edu.textscheduler;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by sp on 4/16/18.
 */
@Entity
public class Text {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "message")
    private String message;

    @ColumnInfo(name = "created_date")
    private String createdDate;

    @Embedded(prefix = "recip_")
    public Recipient recipient;

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
    public Text(int id) {
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
    public Recipient getRecipient() {
        return recipient;
    }
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

}

// Since Text uses @Entity, cols from Recipient will be created under Text
class Recipient {

//    @ColumnInfo(name = "first_name")
    public String firstName;

//    @ColumnInfo(name = "last_name")
    public String lastName;

//    @ColumnInfo(name = "phone")
    public int phone;

    public Recipient(int phone) {
        this.phone = phone;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

}

