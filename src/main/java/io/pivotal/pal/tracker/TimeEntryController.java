package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
    private final CounterService counter;
    private final GaugeService gauge;

    public TimeEntryController(TimeEntryRepository timeEntryRepository,
                               CounterService counter,
                               GaugeService gauge){
        this.timeEntryRepository = timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){

            TimeEntry result = timeEntryRepository.create(timeEntry);
            if(result==null){
               return  ResponseEntity.noContent().build();
            }else
            {
                counter.increment("TimeEntry.created");
                gauge.submit("timeEntries.count", timeEntryRepository.list().size());
                return  new ResponseEntity(result, HttpStatus.CREATED);
            }
    }


    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable("id") long id){

        TimeEntry found = timeEntryRepository.find(id);
        if(found==null) return ResponseEntity.notFound().build();

        counter.increment("TimeEntry.read");
        return new ResponseEntity(found,HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>>  list(){
        counter.increment("TimeEntry.listed");
        return new ResponseEntity(timeEntryRepository.list(),HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id){
        timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        return ResponseEntity.noContent().build();
    }


}
