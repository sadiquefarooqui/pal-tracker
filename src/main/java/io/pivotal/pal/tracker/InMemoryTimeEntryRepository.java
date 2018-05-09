package io.pivotal.pal.tracker;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    long idseq = 1L;


    @Override
    public TimeEntry create(TimeEntry timeEntry){

        timeEntry.setId(idseq);
        timeEntryMap.put(timeEntry.getId(), timeEntry);
        idseq++;
        //this one has the id but it will be similar to what we have received at the beginning
        return timeEntry;


    }

    @Override
    public TimeEntry find(Long id){
         return timeEntryMap.get(id);


    }

    @Override
    public List<TimeEntry> list(){
        return new ArrayList(timeEntryMap.values()) ;

    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry){
        TimeEntry te = find(id);
        te.setDate(timeEntry.getDate());
        te.setHours(timeEntry.getHours());
        te.setProjectId(timeEntry.getProjectId());
        te.setUserId(timeEntry.getUserId());

        return te;

    }

    @Override
    public void delete(Long id){
        timeEntryMap.remove(id);

    }
}
