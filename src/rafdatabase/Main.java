package rafdatabase;

import rafdatabase.model.dol.Book;
import rafdatabase.model.dal.BookDAL;

/**
 * Created by j2arr on 8/24/2016.
 */
public class Main {

    public static void main(String[] args) {

        BookDAL bookDAL = new BookDAL();
/*
        Book book = bookDAL.get(3);

        if (book != null) {
            System.out.println(book);
        }
        else {
            System.out.println("The book was not found.");
        }

        book.setTitle("Calculus Integral");

        boolean res = bookDAL.update(book);

        if (res) {
            System.out.println("The book has been updated.");
            book = bookDAL.get(3);
            System.out.println(book);
        }
        else {
            System.out.println("The book has not been update.");
        }*/

     /*   Book book1 = new Book("11123", "Calculus I", "A. Baldor", 145.95f);
        boolean res = bookDAL.insert(book1);

        if (res) {
            System.out.println("The book was saved to the file.");
        }*/

        boolean res = bookDAL.delete(2);
        if (res) {
            System.out.println("The book has been deleted.");
        }
        else {
            System.out.println("The book was not eliminated");
        }
        for (Book book : bookDAL.getAll()) {
            System.out.println(book);
        }

    }
}
