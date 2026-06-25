package AbstractArt;

public abstract class Art {
    //Variable
    protected String title;
    protected String author;
    protected String description;
    //Constructor
    public Art(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
    
    // Abstract Method
    public abstract void viewArt();

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }
}
