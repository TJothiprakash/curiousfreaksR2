package lowleveldesign.messagebrokersystem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {
    private final String name;
    private final List<Partition> partitions = new ArrayList<>();
    private final AtomicInteger nextPartitionIndex = new AtomicInteger(0);

    public Topic(String name, int numPartitions) {
        this.name = name;
        for (int i = 0; i < numPartitions; i++) {
            partitions.add(new Partition(i));
        }
    }

    public synchronized Partition getNextPartition() {
        int index = nextPartitionIndex.getAndUpdate(i -> (i + 1) % partitions.size());
        return partitions.get(index);
    }

    public List<Partition> getPartitions() {
        return partitions;
    }

    public String getName() {
        return name;
    }
}

