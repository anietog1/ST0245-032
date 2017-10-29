package fplogn;

/**
 * Aproach for making a File, every File knows in which Folder it is located,
 * and it's name, also includes the size of the file and the user it belongs to.
 *
 * @author anietog1, ditrefftzr
 */
public class File {

    private final String name;
    private final Folder parent;
    private final String user;
    private final long size;

    /**
     * Builds a new File with the characteristics.
     *
     * @param name The name of the file.
     * @param parent The folder inside which this file is.
     * @param size The File's size in bytes.
     * @param user The File's user.
     */
    public File(String name, Folder parent, long size, String user) {
        this.name = name;
        this.parent = parent;
        this.size = size;
        this.user = user;
    }

    /**
     * The given name of the File.
     *
     * @return The name of the File.
     */
    public String getName() {
        return name;
    }

    /**
     * Indicates in which Folder this File is in.
     *
     * @return The parent of the File.
     */
    public Folder getParent() {
        return parent;
    }

    /**
     * Indicates the size of this File.
     *
     * @return The size of this File.
     */
    public long getSize() {
        return size;
    }

    /**
     * Indicates the user that wrote this File.
     *
     * @return The user field of this File.
     */
    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "[" + user + " " + size + "] " + name;
    }

    /**
     * Indicates the path from home to the current File, home must be the only
     * Folder without a parent.
     *
     * @return The path from home of the current File.
     */
    public String getPath() {
        if (parent == null) {
            return name;
        }

        return parent.getPath() + name;
    }
}
