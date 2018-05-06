package csc780.sfsu.edu.textscheduler.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by sp on 4/17/18.
 */

// Since Text uses @Entity, cols from Recipient will be created under Text
@Entity(tableName = "recipients",
        foreignKeys = @ForeignKey(entity = Text.class,
                parentColumns = "id",
                childColumns = "text_id",
                onDelete = CASCADE),
        indices = {@Index(value = {"text_id", "phone"})})
public class Recipient {

    @PrimaryKey
    @ColumnInfo(name = "text_id")
    public int textId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "phone")
    public String phone;

    public Recipient() {}
    public Recipient(int textId) {
        this.textId = textId;
    }
    public int getTextId() {
        return textId;
    }
    public void setTextId(int textId) {
        this.textId = textId;
    }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
