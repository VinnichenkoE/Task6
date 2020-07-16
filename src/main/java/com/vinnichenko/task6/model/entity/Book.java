package com.vinnichenko.task6.model.entity;

import com.vinnichenko.task6.util.IdGenerator;

import java.util.List;
import java.util.Set;

public class Book {

    private String id;
    private String title;
    private Set<Author> authors;
    private int numberPages;
    private String typography;

    public Book(String title, Set<Author> authors, int numberPages, String typography) {
        this.id = IdGenerator.generateId();
        this.title = title;
        this.authors = authors;
        this.numberPages = numberPages;
        this.typography = typography;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public String getTypography() {
        return typography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        if (numberPages != book.numberPages) {
            return false;
        }
        if (!id.equals(book.id)) {
            return false;
        }
        if (title != null ? !title.equals(book.title) : book.title != null) {
            return false;
        }

        if (authors != null ? !authors.equals(book.authors) : book.authors != null) {
            return false;
        }
        return typography != null ? typography.equals(book.typography) : book.typography == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + numberPages;
        result = 31 * result + (typography != null ? typography.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", authors=").append(authors);
        sb.append(", numberPages=").append(numberPages);
        sb.append(", typography='").append(typography).append('\'');
        sb.append('}');
        return sb.toString();
    }
}