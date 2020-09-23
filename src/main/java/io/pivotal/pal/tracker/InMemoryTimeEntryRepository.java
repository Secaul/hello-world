package io.pivotal.pal.tracker;

import javax.net.ssl.SSLEngineResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<>();
    private long id = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        this.id = this.id + 1;
        this.timeEntryHashMap.put(this.id, timeEntry);
        timeEntry.setId(this.id);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryHashMap.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntryHashMap.get(id)== null) return null;
        timeEntryHashMap.get(id).setProjectId(timeEntry.getProjectId());
        timeEntryHashMap.get(id).setUserId(timeEntry.getUserId());
        timeEntryHashMap.get(id).setHours(timeEntry.getHours());
        timeEntryHashMap.get(id).setDate(timeEntry.getDate());
        return timeEntryHashMap.get(id);
    }

    @Override
    public void delete(long id) {
        timeEntryHashMap.remove(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> result = new ArrayList<>();

        for(Map.Entry<Long, TimeEntry> entry : timeEntryHashMap.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
