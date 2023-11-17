package model;

public class Transactions {
    private int id;
    private int userId;
    private int gameId;

    public Transactions(int id, int userId, int gameId) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
