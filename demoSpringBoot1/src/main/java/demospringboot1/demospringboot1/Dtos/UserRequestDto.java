package demospringboot1.demospringboot1.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequestDto(



        @Size(min = 5, max = 50)
        @NotEmpty(message = "Name cannot be blank")
        String name,

        @NotEmpty(message = "Description should be given")
        String description
) {
}
