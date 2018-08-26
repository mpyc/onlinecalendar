package pl.pycela.onlinecalendar.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NamedNativeQueries({

        @NamedNativeQuery(name = "Permalink.deleteExpiredPermalink",
                query = "delete from permalink where id = ?1")
})


@Entity
@Table(name = "permalink")
public class Permalink implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private LocalDateTime createDateTime;
    private LocalDateTime expiryDateTime;
    @OneToMany(mappedBy = "permalink", fetch = FetchType.LAZY)
    private List<Event> events;
    private String password;

    public Permalink() {
    }

    public Permalink(String name) {
        this.name = name;
    }


    public Permalink(String name, LocalDateTime createDateTime, LocalDateTime expiryDateTime, String password) {
        this.name = name;
        this.createDateTime = createDateTime;
        this.expiryDateTime = expiryDateTime;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
