package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){

            TimeEntry result = timeEntryRepository.create(timeEntry);
            if(result==null){
               return  ResponseEntity.noContent().build();
            }else
            {
                return  new ResponseEntity(result, HttpStatus.CREATED);
            }
    }


    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable("id") long id){

        TimeEntry found = timeEntryRepository.find(id);
        if(found==null) return ResponseEntity.notFound().build();

        return new ResponseEntity(found,HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>>  list(){
        return new ResponseEntity(timeEntryRepository.list(),HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody  TimeEntry timeEntry){

        TimeEntry updated = timeEntryRepository.update(id,timeEntry);
        return  Objects.nonNull(updated)? new ResponseEntity(updated,HttpStatus.OK): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id){
        timeEntryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }


}
