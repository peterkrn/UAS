package model;

public class Games {
    private int id;
    private String name;
    private String genre;
    private String image;
    private int price;

    public Games() {}

    public Games(int id, String name, String genre, int price, String image) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}