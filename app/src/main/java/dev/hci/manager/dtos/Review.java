package dev.hci.manager.dtos;

import java.io.Serializable;

public class Review implements Serializable {

    private String user, review, time;
    private float rating;
    private int like;
    private boolean liked;
    private boolean ownReview;


    public Review(String user, String review, String time, float rating, int like, boolean liked, boolean ownReview) {
        this.user = user;
        this.review = review;
        this.time = time;
        this.rating = (float)rating;
        this.like = like;
        this.liked = liked;
        this.ownReview = ownReview;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isOwnReview() {
        return ownReview;
    }

    public void setOwnReview(boolean ownReview) {
        this.ownReview = ownReview;
    }
}
