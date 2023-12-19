package com.example.app.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.app.database.model.Comment;

import java.util.List;

@Dao
public interface CommentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertComment(Comment... comments);
    @Update
    public void updateComment(Comment... comments);
    @Delete
    public void deleteComment(Comment... comments);
//    @Query("SELECT * FROM kma_comment")
//    public LiveData<List<Comment>> getAllTaskComments();

    @Query("SELECT * FROM kma_comment WHERE task_id = :id")
    public Comment getKmaCommentInTask(int id);

    @Query("DELETE FROM kma_comment WHERE comment_id = :id")
    void deleteComment(int id);
}
