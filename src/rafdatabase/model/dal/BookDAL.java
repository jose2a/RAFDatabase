package rafdatabase.model.dal;

import rafdatabase.model.dal.datastructures.BinarySearchTree;
import rafdatabase.model.dol.Book;
import rafdatabase.model.dal.datastructures.BookIndex;
import rafdatabase.model.dal.files.BookIndexFile;
import rafdatabase.model.dal.files.BookRAF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by j2arr on 8/25/2016.
 */
public class BookDAL {

    private BinarySearchTree<BookIndex> indicesTree = new BinarySearchTree<>();
    private int lastId;

    public BookDAL() {
        List<BookIndex> bookIndices = BookIndexFile.loadBookIndexFile();
        Collections.shuffle(bookIndices, new Random(System.nanoTime()));

        this.indicesTree.insertNodes(bookIndices);

        this.lastId = BookIndexFile.loadLastId();
    }

    public List<Book> getAll() {
        System.out.println("Get All");

        List<Book> books = new ArrayList<>();

        for (BookIndex bi : indicesTree.getNodes()) {
            Book book = BookRAF.readBook(bi);
            if (book.getId() != 0)
                books.add(book);
        }

        return books;
    }

    public Book get(int id) {
        System.out.println("Get");

        Book book;

        BookIndex bookIndex = new BookIndex(id);
        bookIndex = indicesTree.find(bookIndex);

        book = BookRAF.readBook(bookIndex);

        return book;
    }

    public boolean insert(Book book) {
        System.out.println("Insert");

        ++lastId;
        book.setId(lastId);

        long position = BookRAF.writeBook(book);

        if (position == -1)
            return false;

        BookIndex bookIndex = new BookIndex(book.getId(), position);

        indicesTree.insert(bookIndex);

        BookIndexFile.saveLastId(lastId);
        BookIndexFile.saveBookIndexFile(indicesTree.getNodes());

        return true;
    }

    public boolean update(Book book) {
        System.out.println("Update");

        boolean result = delete(book.getId()); // Delete

        if (!result)
            return false;

        long position = BookRAF.writeBook(book);

        if (position == -1)
            return false;

        BookIndex bookIndex = new BookIndex(book.getId(), position);

        indicesTree.insert(bookIndex);

        BookIndexFile.saveBookIndexFile(indicesTree.getNodes());

        return true;
    }

    public boolean delete(int id) {
        System.out.println("Delete");

        BookIndex bookIndex = new BookIndex(id);
        bookIndex = indicesTree.find(bookIndex);

        if (bookIndex == null)
            return false;

        boolean resDelete = BookRAF.deleteBook(bookIndex.getPosition());
        if (!resDelete)
            return false;

        boolean result = indicesTree.delete(bookIndex);
        BookIndexFile.saveBookIndexFile(indicesTree.getNodes());
        indicesTree.insertNodes(BookIndexFile.loadBookIndexFile());

        return result;
    }


}
