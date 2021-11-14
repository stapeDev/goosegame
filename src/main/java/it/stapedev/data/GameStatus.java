package it.stapedev.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameStatus {

    private Integer current = 0;

    private Integer previous;

    public void newPosition(Integer newValue) {
        this.previous = this.current;
        this.current = newValue;
    }

    public void undoPosition() {
        this.newPosition(this.previous);
    }

}
