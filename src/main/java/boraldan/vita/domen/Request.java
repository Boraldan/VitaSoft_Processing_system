package boraldan.vita.domen;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "status", columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @NotEmpty(message = "Текст заявки не может быть пустым")
    @Size(min = 10, message = "Текст заявки должен быть не менее 10 символов")
    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Person person;


    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}