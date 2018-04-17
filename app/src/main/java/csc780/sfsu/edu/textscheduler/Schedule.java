package csc780.sfsu.edu.textscheduler;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by sp on 4/16/18.
 */

@Entity(foreignKeys = @ForeignKey(entity = Text.class,
        parentColumns = "id",
        childColumns = "text_id",
        onDelete = CASCADE))

public class Schedule {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "text_id")
    public int textId;

    @ColumnInfo(name = "send_date")
    private String sendDate;

    @ColumnInfo(name = "sent")
    private Boolean sent;

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
