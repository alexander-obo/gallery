package ao.gallery.dao;

public class Picture {

    // if picture does not in a storage
    private static final int DEFAULT_ID = -1;
    private final int id;
    private String name;
    private final String ownerName;
    private final byte[] content;
    private final byte[] thumbnail;

    public Picture(String name, String ownerName, byte[] content, byte[] thumbnail) {
        this(DEFAULT_ID, name, ownerName, content, thumbnail);
    }

    public Picture(int id, String name, String ownerName, byte[] content, byte[] thumbnail) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public byte[] getContent() {
        return content;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

}
