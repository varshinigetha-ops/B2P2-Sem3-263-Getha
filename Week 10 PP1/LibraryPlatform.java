import java.util.*;

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

abstract class Resource {
    String id;
    String type;

    Resource(String id, String type) {
        this.id = id;
        this.type = type;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id, "Book");
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBook extends Resource implements Reservable, Downloadable {
    EBook(String id) {
        super(id, "EBook");
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

class Library {
    private Resource[] resources = new Resource[100];
    private int size = 0;

    void add(Resource resource) {
        if (find(resource.id) != -1) {
            System.out.println("duplicate rejected");
            return;
        }

        int i = size - 1;

        while (i >= 0 && resources[i].id.compareTo(resource.id) > 0) {
            resources[i + 1] = resources[i];
            i--;
        }

        resources[i + 1] = resource;
        size++;

        System.out.println(resource.id + " added");
    }

    int find(String id) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = resources[mid].id.compareTo(id);

            if (comparison == 0)
                return mid;
            else if (comparison < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }

    void reserve(String id, String memberId) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource resource = resources[index];

        if (resource instanceof Reservable)
            ((Reservable) resource).reserve(memberId);
        else
            System.out.println(id + " rejected: reserve unsupported");
    }

    void download(String id) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource resource = resources[index];

        if (resource instanceof Downloadable)
            ((Downloadable) resource).download();
        else
            System.out.println(id + " rejected: download unsupported");
    }

    void findAndPrint(String id) {
        int index = find(id);

        if (index == -1)
            System.out.println(id + " not found");
        else
            System.out.println(id + " found at index " + index);
    }
}

public class LibraryPlatform {
    public static void main(String[] args) {
        Library library = new Library();

        library.add(new Book("B1"));
        library.add(new Book("B1"));
        library.add(new EBook("E1"));

        library.reserve("B1", "M1");
        library.download("B1");
        library.download("E1");
        library.findAndPrint("E1");
    }
}