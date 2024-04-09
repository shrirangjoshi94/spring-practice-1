package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.reposotories.AuthorRepository;
import guru.springframework.spring5webapp.reposotories.BookRepository;
import guru.springframework.spring5webapp.reposotories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("started bootstrapping");

        Publisher publisher = new Publisher();
        publisher.setName("SFG");
        publisher.setCity("Pune");
        publisher.setState("Maharashtra");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Lets us C", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);
        publisherRepository.save(publisher);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Jhonson");
        Book noEJB = new Book("J2EE", "123456789");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);
        publisherRepository.save(publisher);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Number of books ==== " + bookRepository.count());
        System.out.println("Publisher Number of books ==== " + publisher.getBooks().size());

    }
}
