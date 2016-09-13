package rafdatabase.model.dal.files;

import rafdatabase.model.dal.datastructures.BookIndex;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose Arriaga on 8/25/2016.
 * Class with static methods that will help to store and retrieve data from the book master file, and Id file.
 */
public class BookIndexFile {

    private static final String INDEX_FILE_NAME = "./files/index.dat"; // File that contains Id and positions of the RAF
    private static final String ID_BOOK_FILE_NAME = "./files/id.dat"; // File that stores last book ID

    /**
     * Save the IDs of the books and the position in which these books are written in the Random Access File.
     * These will be saved in another file, which makes the function of a master file.
     * @param bookIndices Contains a list of IDs of the books, and the position in which these are stored in the RAF.
     * @return true if the bookIndices were saved; false in case they could not be saved.
     */
    public static boolean saveBookIndexFile(List<BookIndex> bookIndices) {
        try {
            File file = new File(INDEX_FILE_NAME);

            // If file already exist, delete it.
            if (file.exists()) {
                if (!file.delete())
                    return false;
            }

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INDEX_FILE_NAME));

            // Saving indices to the file.
            for (BookIndex bi: bookIndices) {
                oos.writeObject(bi);
            }

            oos.close();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false; // In case of an error will return false.
        }
    }

    /**
     * Retrieve the IDs of the books and the position in which they are stored in the RAF, from the book master file.
     * @return List of BooksIndices(ID, position) of the books stored in the RAF.
     */
    public static List<BookIndex> loadBookIndexFile() {
        List<BookIndex> bookIndices = new ArrayList<>(); // List of bookIndices to return
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(INDEX_FILE_NAME));

            /*
             * Reading the file in a sequential order, this will create an EOFException because
             * it reaches the End of the File. There is no way to know when we should stop
             * reading from this file, so we just catch the exception and return the element.
            */
            Object bIndex = ois.readObject();

            while (bIndex != null) {
                bookIndices.add((BookIndex) bIndex);
                bIndex = ois.readObject();
            }
        }
        catch (Exception e) {
            if (e instanceof IOException) {
                try {
                    if (bookIndices.size() > 0) {
                        if (ois != null) {
                            ois.close();
                        }
                    }
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            else {
                e.printStackTrace();
            }
        }
        return bookIndices;
    }

    public static int loadLastId() {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(ID_BOOK_FILE_NAME));
            return in.readInt();
        }
        catch (IOException fne) {
            return 0;
        }
    }

    public static boolean saveLastId(int id) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(ID_BOOK_FILE_NAME));
            out.writeInt(id);
            out.flush();

            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
