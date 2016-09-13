package rafdatabase.model.dal.files;

import rafdatabase.model.dal.datastructures.BookIndex;
import rafdatabase.model.dol.Book;

import java.io.RandomAccessFile;

/**
 * Created by j2arr on 8/25/2016.
 */
public class BookRAF {

    private static String BOOK_FILE_NAME = "./files/books.dat";
    private static RandomAccessFile booksRAF;

    public static Book readBook(BookIndex bookIndex) {
        try {
            booksRAF = new RandomAccessFile(BOOK_FILE_NAME, "rw");
            booksRAF.seek(bookIndex.getPosition());

            Book book = new Book();
            book.setId(booksRAF.readInt());
            book.setISBN(booksRAF.readUTF());
            book.setTitle(booksRAF.readUTF());
            book.setAuthor(booksRAF.readUTF());
            book.setPrice(booksRAF.readFloat());

            booksRAF.close();

            return book;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteBook(long position) {
        Book book = new Book(0, "", "", "", 0.0f);
        return writeBook(book, position) != -1;
    }

    public static long writeBook(Book book) {
        return writeBook(book, -1);
    }

    private static long writeBook(Book book, long position) {

        try {
            booksRAF = new RandomAccessFile(BOOK_FILE_NAME, "rw");
            position = (position != -1) ? position : booksRAF.length();
            booksRAF.seek(position);

            booksRAF.writeInt(book.getId());
            booksRAF.writeUTF(book.getISBN());
            booksRAF.writeUTF(book.getTitle());
            booksRAF.writeUTF(book.getAuthor());
            booksRAF.writeFloat(book.getPrice());

            booksRAF.close();
            return  position;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }
}
