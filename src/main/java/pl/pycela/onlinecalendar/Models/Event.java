package pl.pycela.onlinecalendar.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@NamedNativeQueries({

        @NamedNativeQuery(name = "Event.deleteExpiredEvents",
                query = "delete from event where permalink_id in (select id from permalink where id = ?1)")
})


@Entity
@Table(name = "event")
@JsonPropertyOrder({"id", "description"})
public class Event implements Serializable {

    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permalink_id")
    private Permalink permalink;

    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end_date;

    public Event() {
    }

    public Event(long id, String text, LocalDateTime beginEventDateTime, LocalDateTime endEventDateTime, Permalink permalink) {
        this.id = id;
        this.text = text;
        this.start_date = beginEventDateTime;
        this.end_date = endEventDateTime;
        this.permalink = permalink;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

}
