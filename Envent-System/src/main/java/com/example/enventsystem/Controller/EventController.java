package com.example.enventsystem.Controller;

import com.example.enventsystem.Api.ApiResponse;
import com.example.enventsystem.Model.EventModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventController {
    ArrayList<EventModel> events = new ArrayList<>();
    static final AtomicLong idGenerator = new AtomicLong(1);

    //adding event
    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody EventModel event){
        event.setId(idGenerator.getAndIncrement());
        events.add(event);
        return new ApiResponse("Successfully added event");
    }

    //get events
    @GetMapping("/get")
    public ArrayList<EventModel> getEvents(){
        return events;
    }

    //update event
    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@RequestBody EventModel event,@PathVariable long id){
        for(EventModel e:events){
            if(e.getId()==id){
                e.setDescription(event.getDescription());
                e.setCapacity(event.getCapacity());
                e.setStartDate(event.getStartDate());
                e.setEndDate(event.getEndDate());
                return new ApiResponse("Successfully updated event");
            }
        }
        return new ApiResponse("Event not found");
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable long id){
        for(EventModel e:events){
            if(e.getId()==id){
                events.remove(e);
                return new ApiResponse("Successfully deleted event");
            }
        }
        return new ApiResponse("Event not found");
    }

    //changing capacity
    @PutMapping("/change-capacity/{id}/{capacity}")
    public Object changeCapacity(@PathVariable long id,@PathVariable int capacity){
        for(EventModel e:events){
            if(e.getId()==id){
                e.setCapacity(capacity);
                return e;
            }
        }
        return new ApiResponse("Event not found");
    }

    //search event by id
    @GetMapping("/search/{id}")
    public Object getEventById(@PathVariable long id){
        for(EventModel e:events){
            if(e.getId()==id){
                return e;
            }
        }
        return new ApiResponse("Event not found");
    }


}
