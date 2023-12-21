package com.example.app.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "kma_task_file")
public class TaskFile {
    @PrimaryKey
    @SerializedName("file_id")
    @Expose
    private int file_id;
    @SerializedName("storage")
    @Expose
    private int storage;
    @SerializedName("file_path")
    @Expose
    private String file_path;
    @SerializedName("hash_path")
    @Expose
    private String hash_path;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("file_name")
    @Expose
    private String file_name;
    @SerializedName("mimetype")
    @Expose
    private int mimetype;
    @SerializedName("mimepart")
    @Expose
    private int mimepart;
    @SerializedName("file_size")
    @Expose
    private String file_size;
    @SerializedName("mtime")
    @Expose
    private int mtime;
    @SerializedName("storage_mtime")
    @Expose
    private int storage_mtime;
    @SerializedName("encrypted")
    @Expose
    private int encrypted;
    @SerializedName("unencrypted_size")
    @Expose
    private int unencrypted_size;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("permissions")
    @Expose
    private int permissions;
    @SerializedName("checksum")
    @Expose
    private String checksum;

    public TaskFile(@NonNull int file_id, int storage, String file_path, String hash_path, String parent, String file_name, int mimetype, int mimepart,
                    String file_size, int mtime, int storage_mtime, int encrypted, int unencrypted_size, String etag, int permissions, String checksum) {
        this.file_id = file_id;
        this.storage = storage;
        this.file_path = file_path;
        this.hash_path = hash_path;
        this.parent = parent;
        this.file_name = file_name;
        this.mimetype = mimetype;
        this.mimepart = mimepart;
        this.file_size = file_size;
        this.mtime = mtime;
        this.storage_mtime = storage_mtime;
        this.encrypted = encrypted;
        this.unencrypted_size = unencrypted_size;
        this.etag = etag;
        this.permissions = permissions;
        this.checksum = checksum;
    }

    public int getFile_id() {
        return file_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_size() {
        return file_size;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
