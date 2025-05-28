package designpatterns.structural.composite.filesystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface FileSystemComponent {

    void showDetails(File file);
    long getSize();

}

class File1 implements FileSystemComponent {
    private String name;
    private long size;

    @Override
    public void showDetails(File file) {

    }

    @Override
    public long getSize() {
        return 0L;
    }
}

class Directory implements FileSystemComponent {
    private String name;
    private long size;
    List<FileSystemComponent> children = new ArrayList<>();

    @Override
    public void showDetails(File file) {
        for (FileSystemComponent child : children) {
            child.showDetails(file);
            child.getSize();
        }
    }

    @Override
    public long getSize() {
        return 0L;
    }
}