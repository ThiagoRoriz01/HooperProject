package hooper.api.domain.hooper;

import jakarta.validation.constraints.NotNull;

public record HooperDataUpdate(
        @NotNull
        Long id,
        String name,
        String courtPosition) {
}
