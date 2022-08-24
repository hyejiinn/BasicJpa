package hellojpa.ex9;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() {
    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
